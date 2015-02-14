package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.model.exceptions.DAOFactoryException;
import by.gsu.epamlab.model.impl.IUserDAO;
import by.gsu.epamlab.model.impl.MemoryUser;
import by.gsu.epamlab.model.impl.UserImplDB;

public final class UserDAOFactory {
    public static IUserDAO createDAOUser(String dao) throws DAOFactoryException {
        switch (dao){
            case "memory": return new MemoryUser();
            case "db":
                return new UserImplDB();
            default: throw new DAOFactoryException("Can't create user implementation");
        }
    }
}
