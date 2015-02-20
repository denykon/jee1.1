package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.model.impl.ITaskDAO;

public final class TaskDAOFactory {

    private static ITaskDAO taskImpl;

    public static ITaskDAO getTaskImpl() {
        return taskImpl;
    }

    public static void setTaskImpl(ITaskDAO taskImpl) {
        TaskDAOFactory.taskImpl = taskImpl;
    }
}
