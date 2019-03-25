package logic;

import models.Car;
import models.Corobka;
import models.Cuzov;
import models.Dvigatel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class StoreTest {
    @Test
    public void whenWeCreateANewCar() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Cuzov cuzov = session.get(Cuzov.class, 1);
        Corobka corobka = session.get(Corobka.class, 1);
        Dvigatel dvigatel = session.get(Dvigatel.class, 1);
        session.close();

        Store store = new Store();
        Car mitsubishi = new Car("Mitsubishi", dvigatel, corobka, cuzov, "img/mits.jpg");
        store.saveOrUpdate(mitsubishi);
        List<Car> allCars = store.getAllCars();
        Car mits = allCars.stream()
                .filter(car -> car.getModel().equals("Mitsubishi"))
                .findFirst()
                .orElse(null);
        Assert.assertNotNull(mits);
        assertThat(mits.getModel(), is("Mitsubishi"));
    }

    @Test
    public void whenWeDeleteACar() {
        Store store = new Store();
        int id = 1;
        Car car = store.getCarById(id);
        store.delete(car);
        List<Car> cars = store.getAllCars();
        Car deletedCar = cars.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
        Assert.assertNull(deletedCar);
    }

    @Test
    public void whenWeUpdateACar() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Cuzov cuzov = session.get(Cuzov.class, 2);
        Corobka corobka = session.get(Corobka.class, 2);
        session.close();

        Store store = new Store();
        Car car = store.getCarById(2);
        car.setCuzov(cuzov);
        car.setCorobka(corobka);
        store.saveOrUpdate(car);
        List<Car> cars = store.getAllCars();
        Car check = cars.stream()
                .filter(c -> c.getId() == 2 && c.getCorobka().getId() == 2 && c.getCuzov().getId() == 2)
                .findFirst()
                .orElse(null);
        Assert.assertNotNull(check);
    }
}