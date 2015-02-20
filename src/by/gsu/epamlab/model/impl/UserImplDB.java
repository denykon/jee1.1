package by.gsu.epamlab.model.impl;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.connection.DbConnection;
import by.gsu.epamlab.model.constants.ConstantsJSP;
import by.gsu.epamlab.model.exceptions.DAOException;
import by.gsu.epamlab.model.exceptions.UserAddingException;
import by.gsu.epamlab.model.exceptions.UserNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserImplDB implements IUserDAO {

    private static final int LOGIN_INDEX = 1;
    private static final int PASSWORD_INDEX = 2;
    private static final int FIRST_NAME_INDEX = 3;
    private static final int LAST_NAME_INDEX = 4;

    private static final int ID_COLUMN = 1;
    private static final int FIRST_NAME_COLUMN = 2;
    private static final int LAST_NAME_COLUMN = 3;

    private static final String SQL_GET_USER = "SELECT id, first_name, last_name " +
            "FROM todo.users " +
            "WHERE login=? AND password=?";
    private static final String SQL_ADD_USER = "INSERT INTO todo.users " +
            "( login, password, first_name, last_name, registration_date ) " +
            "VALUES ( ?, ?, ?, ?, CURRENT_DATE() )";
    private static final String SQL_FIND_USER = "SELECT id FROM todo.users WHERE login = ?";


    public UserImplDB() {
        super();
    }

    /**
     * get user from DataBase
     */
    @Override
    public User getUser(String login, String password) throws UserNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //todo some md5 for password here
        try {
            connection = DbConnection.getConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_USER);
            preparedStatement.setString(LOGIN_INDEX, login);
            preparedStatement.setString(PASSWORD_INDEX, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.first()) {
                int userId = resultSet.getInt(ID_COLUMN);
                String userFirstName = resultSet.getString(FIRST_NAME_COLUMN);
                String userLastName = resultSet.getString(LAST_NAME_COLUMN);
                return new User(userId, login, userFirstName, userLastName);
            } else {
                throw new UserNotFoundException(ConstantsJSP.USER_ABSENT_ERROR);
            }

        } catch (SQLException e) {
            throw new UserNotFoundException();
        } catch (DAOException e) {
            throw new UserNotFoundException(e);
        } finally {
            DbConnection.closeResources(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public User addUser(String login, String password, String firstName, String lastName) throws UserAddingException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DbConnection.getConnection();
            synchronized (this) {
                if (!isUserExist(login, connection)) {
                    preparedStatement = connection.prepareStatement(SQL_ADD_USER);
                    preparedStatement.setString(LOGIN_INDEX, login);
                    preparedStatement.setString(PASSWORD_INDEX, password);
                    preparedStatement.setString(FIRST_NAME_INDEX, firstName);
                    preparedStatement.setString(LAST_NAME_INDEX, lastName);
                    preparedStatement.executeUpdate();

                } else {
                    throw new UserAddingException(ConstantsJSP.LOGIN_EXIST_ERROR);
                }
            }
        } catch (SQLException | DAOException e) {
            throw new UserAddingException(ConstantsJSP.ADD_DATABASE_USER_ERROR + e);
        } finally {
            DbConnection.closeResources(preparedStatement, connection);
        }
        try {
            return getUser(login, password);
        } catch (UserNotFoundException e) {
            throw new UserAddingException(ConstantsJSP.CREATE_USER_ERROR);
        }
    }

    private boolean isUserExist(String login, Connection connection) throws UserAddingException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_USER);
            preparedStatement.setString(LOGIN_INDEX, login);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new UserAddingException(ConstantsJSP.ADD_DATABASE_USER_ERROR + e);
        } finally {
            DbConnection.closeResources(preparedStatement, resultSet);
        }
    }
}
