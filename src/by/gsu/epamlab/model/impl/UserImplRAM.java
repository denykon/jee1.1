package by.gsu.epamlab.model.impl;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.constants.ConstantsJSP;
import by.gsu.epamlab.model.exceptions.UserAddingException;
import by.gsu.epamlab.model.exceptions.UserNotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 *  Memory user implementation
 */
public class UserImplRAM implements IUserDAO {

    private final static Map<User, String> userMap = new HashMap<>();
    private static int id = 0;

    public UserImplRAM() {
        super();
    }

    /**
     * get user map
     * @return Map of users
     */
    public Map<User, String> getUserMap() {
        return userMap;
    }

    @Override
    public User getUser(String login, String password) throws UserNotFoundException {
        synchronized (this){
            for(Map.Entry<User, String> users : userMap.entrySet()) {
                if (users.getKey().getLogin().equals(login) && users.getValue().equals(password)){
                    return users.getKey();
                }
            }
        }
        throw new UserNotFoundException(ConstantsJSP.USER_ABSENT_ERROR);
    }

    @Override
    public User addUser(String login, String password, String firstName, String lastName) throws UserAddingException {
        synchronized (this){
            User user = new User(id++, login, firstName, lastName);
            if (!getUserMap().containsKey(user)){
                getUserMap().put(user, password);
                return user;
            }
            throw new UserAddingException(ConstantsJSP.LOGIN_EXIST_ERROR);
        }
    }
}
