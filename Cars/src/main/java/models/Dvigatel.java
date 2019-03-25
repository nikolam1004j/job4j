package models;

public class Dvigatel {
    private int id;
    private String name;
    private int maxspeed;

    public Dvigatel() {
    }

    public Dvigatel(String name, int maxspeed) {
        this.name = name;
        this.maxspeed = maxspeed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxspeed() {
        return maxspeed;
    }

    public void setMaxspeed(int maxspeed) {
        this.maxspeed = maxspeed;
    }
}
