package razilov.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "car")
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String model;
    private int cuzov_id;
    private int dvigatel_id;
    private int corobka_id;
    private String link;
    private double price;
    private boolean sold;
    private String owner;
}
