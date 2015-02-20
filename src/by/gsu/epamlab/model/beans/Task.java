package by.gsu.epamlab.model.beans;

import by.gsu.epamlab.model.helpers.Status;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    private int id;
    private String tittle;
    private Status status;
    private Date extDate;
    private boolean inBin;
    private boolean haveFile;

    public Task(int id, String tittle, Status status, Date extDate, boolean inBin, boolean haveFile) {
        this.id = id;
        this.tittle = tittle;
        this.status = status;
        this.extDate = extDate;
        this.inBin = inBin;
        this.haveFile = haveFile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getExtDate() {
        return extDate;
    }

    public void setExtDate(Date extDate) {
        this.extDate = extDate;
    }

    public boolean isInBin() {
        return inBin;
    }

    public void setInBin(boolean inBin) {
        this.inBin = inBin;
    }

    public boolean isHaveFile() {
        return haveFile;
    }

    public void setHaveFile(boolean haveFile) {
        this.haveFile = haveFile;
    }
}
