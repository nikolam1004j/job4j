package annotationsmodels;

import javax.persistence.*;

@Entity
@Table(name = "cuzov")
public class Cuzov {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    String name;

    public Cuzov() {
    }

    public Cuzov(String name) {
        this.name = name;
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
}
