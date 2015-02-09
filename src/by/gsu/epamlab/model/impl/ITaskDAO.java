package by.gsu.epamlab.model.impl;

import by.gsu.epamlab.model.beans.Task;

import java.util.Date;
import java.util.List;

public interface ITaskDAO {
    List<Task> getTasks(int userId);

    void addTask(int userId, String tittle, Date expDate);

}
