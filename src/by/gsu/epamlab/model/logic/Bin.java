package by.gsu.epamlab.model.logic;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.factories.TaskDAOFactory;

import java.util.List;

public class Bin {

    public static void empty(int userId) {
        List<Task> list = TaskDAOFactory.getTaskImpl().getTasks(userId, "bin");
        Task[] tasks = new Task[list.size()];
        list.toArray(tasks);
        String[] taskIds = new String[tasks.length];
        for (int i = 0; i < tasks.length; i++) {
            taskIds[i] = String.valueOf(tasks[i].getId());
        }
        TaskDAOFactory.getTaskImpl().actTask(taskIds, "delete");
    }

}
