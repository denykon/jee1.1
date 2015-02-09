package by.gsu.epamlab.model.beans;

import java.util.Date;

public class Task {
    private int id;
    private String tittle;
    private Status status;
    private Date extDate;
    private boolean inBin;

    public Task(int id, String tittle, Status status, Date extDate, boolean inBin) {
        this.id = id;
        this.tittle = tittle;
        this.status = status;
        this.extDate = extDate;
        this.inBin = inBin;
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
}
