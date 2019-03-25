package models;

public class Corobka {
    private int id;
    private boolean automatic;

    public Corobka() {
    }

    public Corobka(boolean automatic) {
        this.automatic = automatic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAutomatic() {
        return automatic;
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
    }
}
