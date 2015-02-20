package by.gsu.epamlab.model.impl;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.connection.DbConnection;
import by.gsu.epamlab.model.exceptions.DAOException;
import by.gsu.epamlab.model.helpers.Status;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

public class TaskImplDB implements ITaskDAO {

    @Override
    public List<Task> getTasks(int id, String type) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Task> tasks = new ArrayList<>();
        String sql;
        try {
            connection = DbConnection.getConnection();    //???????? DAOException todo: different exception
            switch (type) {
                case "today":
                    sql = "SELECT id, tittle, tasks.status, expDate, inBin, tasks.file FROM tasks WHERE userId = ? AND status = 0 AND expDate <= CURDATE() AND inBin = 0";
                    break;
                case "tomorrow":
                    sql = "SELECT id, tittle, tasks.status, expDate, inBin, tasks.file FROM tasks WHERE userId = ? AND status = 0 AND expDate = CURDATE() + INTERVAL 1 DAY AND inBin = 0";
                    break;
                case "someday":
                    sql = "SELECT id, tittle, tasks.status, expDate, inBin, tasks.file FROM tasks WHERE userId = ? AND status = 0 AND expDate > CURDATE() + INTERVAL 1 DAY AND inBin = 0";
                    break;
                case "fixed":
                    sql = "SELECT id, tittle, tasks.status, expDate, inBin, tasks.file FROM tasks WHERE userId = ? AND status = 1 AND inBin = 0";
                    break;
                case "bin":
                    sql = "SELECT id, tittle, tasks.status, expDate, inBin, tasks.file FROM tasks WHERE userId = ? AND inBin = 1";
                    break;
                default:
                    sql = "";
            }
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();        //???????? SQLException
            while (resultSet.next()) {
                int taskId = resultSet.getInt(1);    //???????? SQLException ?? ????? ????????
                String tittle = resultSet.getString(2);
                Status status = Status.values()[resultSet.getInt(3)];
                Date date = resultSet.getDate(4);
                boolean bin = resultSet.getBoolean(5);
                boolean haveFile = resultSet.getString(6) != null;
                tasks.add(new Task(taskId, tittle, status, date, bin, haveFile));
            }
            return tasks;
        } catch (SQLException e) {
            System.err.println(e);
            return tasks;
        } catch (DAOException e) {
            e.printStackTrace();
            return tasks;
        } finally {
            DbConnection.closeResources(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public void addTask(int userId, String tittle, String expDate) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Date date = dateFormat(expDate);
            java.sql.Date expSQLDate = new java.sql.Date(date.getTime());
            connection = DbConnection.getConnection();    //???????? DAOException
            preparedStatement = connection.prepareStatement("INSERT INTO tasks (userId, tittle, status, expDate, inBin) VALUES (?, ?, 0, ?, 0)");
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, tittle);
            preparedStatement.setDate(3, expSQLDate);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (DataFormatException e) {
            e.printStackTrace();
        } finally {
            DbConnection.closeResources(preparedStatement, connection);
        }
    }

    @Override
    public void actTask(String[] taskIds, String action) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            for (String taskId : taskIds) {
                connection = DbConnection.getConnection();
                switch (action) {
                    case "fix":
                        preparedStatement = connection.prepareStatement("UPDATE tasks SET tasks.status = abs(tasks.status - 1) WHERE tasks.id = ?");
                        break;
                    case "move":
                        preparedStatement = connection.prepareStatement("UPDATE tasks SET tasks.inBin = abs(tasks.inBin - 1) WHERE tasks.id = ?");
                        break;
                    case "delete":
                        preparedStatement = connection.prepareStatement("DELETE FROM tasks WHERE tasks.inBin = 1 AND tasks.id = ?");
                        break;
                    default:
                        throw new SQLException("invalid action");
                }
                preparedStatement.setInt(1, Integer.parseInt(taskId));
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            DbConnection.closeResources(preparedStatement, connection);
        }
    }

    @Override
    public void removeFileName(String taskId) throws FileNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DbConnection.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE tasks SET tasks.file = NULL WHERE tasks.id = ?");
            preparedStatement.setInt(1, Integer.parseInt(taskId));
            preparedStatement.executeUpdate();
        } catch (SQLException | DAOException e) {
            throw new FileNotFoundException("cant erase a file name");
        } finally {
            DbConnection.closeResources(preparedStatement, connection);
        }
    }

    @Override
    public String getFileName(String taskId) throws FileNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String fileName = "";
        try {
            connection = DbConnection.getConnection();
            preparedStatement = connection.prepareStatement("SELECT tasks.file FROM todo.tasks WHERE tasks.id = ? AND tasks.file IS NOT NULL");
            preparedStatement.setInt(1, Integer.parseInt(taskId));
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                fileName = resultSet.getString(1);
            }
            return fileName;
        } catch (SQLException | DAOException e) {
            throw new FileNotFoundException("can't find the file in this task");
        } finally {
            DbConnection.closeResources(resultSet, preparedStatement, connection);
        }
    }

    private Date dateFormat(String dateInString) throws DataFormatException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(dateInString);
            return date;
        } catch (ParseException e) {
            throw new DataFormatException("Incorrect date format");
        }
    }

    public void saveFileName(String taskId, String filename) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DbConnection.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE tasks SET tasks.file = ? WHERE tasks.id = ?");
            preparedStatement.setString(1, filename);
            preparedStatement.setInt(2, Integer.parseInt(taskId));
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            DbConnection.closeResources(preparedStatement, connection);
        }
    }
}
