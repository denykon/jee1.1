package by.gsu.epamlab.model.entity;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.constants.ConstantsJSP;
import by.gsu.epamlab.model.factories.TaskDAOFactory;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;

/**
 * Recycle bin
 */
public class Bin implements Serializable {

    private static final long serialVersionUID = -7381041883718761973L;

    public void empty(int userId) throws FileNotFoundException {
        List<Task> list = TaskDAOFactory.getTaskImpl().getTasks(userId, ConstantsJSP.BIN);
        Task[] tasks = new Task[list.size()];
        list.toArray(tasks);
        String[] taskIds = new String[tasks.length];
        for (int i = 0; i < tasks.length; i++) {
            taskIds[i] = String.valueOf(tasks[i].getId());
        }
        new FileData().delete(taskIds);
        TaskDAOFactory.getTaskImpl().actTask(taskIds, ConstantsJSP.DELETE);
    }

}
