package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.model.impl.IUserDAO;

public final class UserDAOFactory {

    private static IUserDAO userImpl;

    public static IUserDAO getUserImpl() {
        return userImpl;
    }

    public static void setUserImpl(IUserDAO userImpl) {
        UserDAOFactory.userImpl = userImpl;
    }
}
