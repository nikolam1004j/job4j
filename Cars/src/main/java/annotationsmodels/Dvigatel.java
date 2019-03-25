package annotationsmodels;

import javax.persistence.*;

@Entity
@Table(name = "dvigatel")
public class Dvigatel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "maxspeed")
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
