package by.gsu.epamlab.model.impl;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.connection.DbConnection;
import by.gsu.epamlab.model.constants.ConstantsJSP;
import by.gsu.epamlab.model.entity.Status;
import by.gsu.epamlab.model.exceptions.DAOException;

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

/**
 * Data base task implementation
 */
public class TaskImplDB implements ITaskDAO {

    private static final String FIX_ACTION = "fix";
    private static final String MOVE_ACTION = "move";
    private static final String DELETE_ACTION = "delete";
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    private static final String SELECT_TODAY_TASKS = "SELECT id, tittle, tasks.status, expDate, inBin, tasks.file " +
            "FROM tasks WHERE userId = ? AND status = 0 AND expDate <= CURDATE() AND inBin = 0";
    private static final String SELECT_TOMORROW_TASKS = "SELECT id, tittle, tasks.status, expDate, inBin, tasks.file " +
            "FROM tasks WHERE userId = ? AND status = 0 AND expDate = CURDATE() + INTERVAL 1 DAY AND inBin = 0";
    private static final String SELECT_SOMEDAY_TASKS = "SELECT id, tittle, tasks.status, expDate, inBin, tasks.file " +
            "FROM tasks WHERE userId = ? AND status = 0 AND expDate > CURDATE() + INTERVAL 1 DAY AND inBin = 0";
    private static final String SELECT_FIXED_TASKS = "SELECT id, tittle, tasks.status, expDate, inBin, tasks.file " +
            "FROM tasks WHERE userId = ? AND status = 1 AND inBin = 0";
    private static final String SELECT_BIN_TASKS = "SELECT id, tittle, tasks.status, expDate, inBin, tasks.file " +
            "FROM tasks WHERE userId = ? AND inBin = 1";
    private static final String SQL_INSERT_TASK = "INSERT INTO tasks (userId, tittle, status, expDate, inBin) " +
            "VALUES (?, ?, 0, ?, 0)";
    private static final String SQL_FIX_TASK = "UPDATE tasks SET tasks.status = abs(tasks.status - 1) " +
            "WHERE tasks.id = ?";
    private static final String SQL_MOVE_TASK = "UPDATE tasks SET tasks.inBin = abs(tasks.inBin - 1) " +
            "WHERE tasks.id = ?";
    private static final String SQL_DELETE_TASK = "DELETE FROM tasks WHERE tasks.inBin = 1 AND tasks.id = ?";
    private static final String SQL_FILENAME_REMOVE = "UPDATE tasks SET tasks.file = NULL WHERE tasks.id = ?";
    private static final String SQL_GET_FILENAME = "SELECT tasks.file FROM todo.tasks WHERE tasks.id = ? " +
            "AND tasks.file IS NOT NULL";
    private static final String SQL_SAVE_FILENAME = "UPDATE tasks SET tasks.file = ? WHERE tasks.id = ?";

    private static final int TASK_ID_PARAMETER_INDEX = 1;
    private static final int TASK_TITTLE_PARAMETER_INDEX = 2;
    private static final int TASK_DATE_PARAMETER_INDEX = 3;

    private static final int TASK_ID_COLUMN_INDEX = 1;
    private static final int TASK_TITTLE_COLUMN_INDEX = 2;
    private static final int TASK_STATUS_COLUMN_INDEX = 3;
    private static final int TASK_DATE_COLUMN_INDEX = 4;
    private static final int RECYCLE_BIN_COLUMN_INDEX = 5;
    private static final int FILE_COLUMN_INDEX = 6;

    private static final int FILE_NAME_PARAMETER_INDEX = 1;
    private static final int FILE_NAME_TASK_PARAMETER_INDEX = 2;

    @Override
    public List<Task> getTasks(int id, String type) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Task> tasks = new ArrayList<>();
        String sql;
        try {
            connection = DbConnection.getConnection();
            switch (type) {
                case ConstantsJSP.TODAY_PAGE:
                    sql = SELECT_TODAY_TASKS;
                    break;
                case ConstantsJSP.TOMORROW_PAGE:
                    sql = SELECT_TOMORROW_TASKS;
                    break;
                case ConstantsJSP.SOMEDAY_PAGE:
                    sql = SELECT_SOMEDAY_TASKS;
                    break;
                case ConstantsJSP.FIXED_PAGE:
                    sql = SELECT_FIXED_TASKS;
                    break;
                case ConstantsJSP.BIN_PAGE:
                    sql = SELECT_BIN_TASKS;
                    break;
                default:
                    sql = "";
            }
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(TASK_ID_PARAMETER_INDEX, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int taskId = resultSet.getInt(TASK_ID_COLUMN_INDEX);
                String tittle = resultSet.getString(TASK_TITTLE_COLUMN_INDEX);
                Status status = Status.values()[resultSet.getInt(TASK_STATUS_COLUMN_INDEX)];
                Date date = resultSet.getDate(TASK_DATE_COLUMN_INDEX);
                boolean bin = resultSet.getBoolean(RECYCLE_BIN_COLUMN_INDEX);
                boolean haveFile = resultSet.getString(FILE_COLUMN_INDEX) != null;
                tasks.add(new Task(taskId, tittle, status, date, bin, haveFile));
            }
            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();
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
            connection = DbConnection.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_TASK);
            preparedStatement.setInt(TASK_ID_PARAMETER_INDEX, userId);
            preparedStatement.setString(TASK_TITTLE_PARAMETER_INDEX, tittle);
            preparedStatement.setDate(TASK_DATE_PARAMETER_INDEX, expSQLDate);
            preparedStatement.execute();
        } catch (SQLException | DAOException | DataFormatException e) {
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
                    case FIX_ACTION:
                        preparedStatement = connection.prepareStatement(SQL_FIX_TASK);
                        break;
                    case MOVE_ACTION:
                        preparedStatement = connection.prepareStatement(SQL_MOVE_TASK);
                        break;
                    case DELETE_ACTION:
                        preparedStatement = connection.prepareStatement(SQL_DELETE_TASK);
                        break;
                    default:
                        throw new SQLException("invalid action");
                }
                preparedStatement.setInt(TASK_ID_PARAMETER_INDEX, Integer.parseInt(taskId));
                preparedStatement.execute();
            }
        } catch (SQLException | DAOException e) {
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
            preparedStatement = connection.prepareStatement(SQL_FILENAME_REMOVE);
            preparedStatement.setInt(TASK_ID_PARAMETER_INDEX, Integer.parseInt(taskId));
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
            preparedStatement = connection.prepareStatement(SQL_GET_FILENAME);
            preparedStatement.setInt(TASK_ID_PARAMETER_INDEX, Integer.parseInt(taskId));
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
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN);
        Date date;
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
            preparedStatement = connection.prepareStatement(SQL_SAVE_FILENAME);
            preparedStatement.setString(FILE_NAME_PARAMETER_INDEX, filename);
            preparedStatement.setInt(FILE_NAME_TASK_PARAMETER_INDEX, Integer.parseInt(taskId));
            preparedStatement.execute();
        } catch (SQLException | DAOException e) {
            e.printStackTrace();
        } finally {
            DbConnection.closeResources(preparedStatement, connection);
        }
    }
}
