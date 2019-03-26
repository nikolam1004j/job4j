package models;

public class Car {
    private int id;
    private String owner;
    private String model;
    private Dvigatel dvigatel;
    private Corobka corobka;
    private Cuzov cuzov;
    private String link;
    private double price;
    private boolean sold;

    public Car() {
    }

    public Car(String owner, String model, Dvigatel dvigatel, Corobka corobka, Cuzov cuzov, String link, double price) {
        this.owner = owner;
        this.model = model;
        this.dvigatel = dvigatel;
        this.corobka = corobka;
        this.cuzov = cuzov;
        this.link = link;
        this.price = price;
    }


    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean getSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Dvigatel getDvigatel() {
        return dvigatel;
    }

    public void setDvigatel(Dvigatel dvigatel) {
        this.dvigatel = dvigatel;
    }

    public Corobka getCorobka() {
        return corobka;
    }

    public void setCorobka(Corobka corobka) {
        this.corobka = corobka;
    }

    public Cuzov getCuzov() {
        return cuzov;
    }

    public void setCuzov(Cuzov cuzov) {
        this.cuzov = cuzov;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isSold() {
        return sold;
    }
}
