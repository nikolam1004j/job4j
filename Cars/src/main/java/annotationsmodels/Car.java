package annotationsmodels;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "model")
    private String model;

    @ManyToOne
    @JoinColumn(name = "dvigatel_id")
    private Dvigatel dvigatel;

    @ManyToOne
    @JoinColumn(name = "corobka_id")
    private Corobka corobka;

    @ManyToOne
    @JoinColumn(name = "cuzov_id")
    private Cuzov cuzov;

    private String link;

    public Car() {
    }

    public Car(String model, Dvigatel dvigatel, Corobka corobka, Cuzov cuzov, String link) {
        this.model = model;
        this.dvigatel = dvigatel;
        this.corobka = corobka;
        this.cuzov = cuzov;
        this.link = link;
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
}
