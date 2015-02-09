package by.gsu.epamlab.model.impl;

import by.gsu.epamlab.model.beans.Status;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.connection.DbConnection;
import by.gsu.epamlab.model.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskImplDB implements ITaskDAO {

    public List<Task> getTasks(int id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Task> tasks = new ArrayList<>();

        try {
            connection = DbConnection.getConnection();    //???????? DAOException
            preparedStatement = connection.prepareStatement("SELECT id, tittle, status, expDate FROM tasks WHERE userId = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();        //???????? SQLException
            while (resultSet.next()) {
                int taskId = resultSet.getInt(1);    //???????? SQLException ?? ????? ????????
                String tittle = resultSet.getString(2);
                Status status = Status.values()[resultSet.getInt(3)];
                Date date = resultSet.getDate(4);
                tasks.add(new Task(taskId, tittle, status, date, false));
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
    public void addTask(int userId, String tittle, Date expDate) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        java.sql.Date expSQLDate = new java.sql.Date(expDate.getTime());
        try {
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
        }
    }
}
