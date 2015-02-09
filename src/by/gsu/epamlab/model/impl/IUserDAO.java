package by.gsu.epamlab.model.impl;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.exceptions.DAOException;
import by.gsu.epamlab.model.exceptions.UserAddingException;
import by.gsu.epamlab.model.exceptions.UserNotFoundException;

public interface IUserDAO {

    public User getUser(String login, String password) throws UserNotFoundException;

    public User addUser(String login, String password, String firstName, String lastName) throws UserAddingException, DAOException;

}
