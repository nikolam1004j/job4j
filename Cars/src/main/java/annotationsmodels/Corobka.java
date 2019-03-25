package annotationsmodels;

import javax.persistence.*;

@Entity
@Table(name = "corobka")
public class Corobka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "automatic")
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
