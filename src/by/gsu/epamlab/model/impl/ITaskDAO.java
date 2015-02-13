package by.gsu.epamlab.model.impl;

import by.gsu.epamlab.model.beans.Task;

import java.util.List;

public interface ITaskDAO {
    List<Task> getTasks(int userId, String type);

    void addTask(int userId, String tittle, String expDate);

    void actTask(String[] taskIds, String action);

}
