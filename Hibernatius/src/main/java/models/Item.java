package models;

import java.util.Date;

public class Item {
    private int id;
    private String descr;
    private Date created;
    private boolean done;

    public Item() {
    }

    public Item(String descr, Date created, boolean done) {
        this.descr = descr;
        this.created = created;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return String.format("Item{id=%d, descr='%s', created=%s, done=%s}",
                id, descr, created, done);
    }
}
