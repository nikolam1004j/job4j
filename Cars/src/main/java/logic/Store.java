package logic;

import annotationsmodels.Corobka;
import annotationsmodels.Cuzov;
import annotationsmodels.Dvigatel;
import annotationsmodels.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.function.Function;

public class Store {
    private static final SessionFactory FACTORY = new Configuration()
            .configure().buildSessionFactory();

    private <T> T tx(final Function<Session, T> command) {
        final Session session = FACTORY.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            tx.commit();
            session.close();
        }
    }

    public List<Car> getAllCars() {
        return tx(session -> session.createQuery("from Car order by 1").list());
    }

    public void saveOrUpdate(Car car) {
        tx(session -> {
            session.saveOrUpdate(car);
            return true;
        });
    }

    public void delete(Car car) {
        tx(session -> {
            session.remove(car);
            return true;
        });
    }

    public Car getCarById(int id) {
        return tx(session -> session.get(Car.class, id));
    }

    public List<Dvigatel> getDvigatels() {
        return tx(session -> session.createQuery("from Dvigatel order by id").list());
    }

    public List<Cuzov> getCuzovs() {
        return tx(session -> session.createQuery("from Cuzov order by id").list());
    }

    public List<Corobka> getCorobkas() {
        return tx(session -> session.createQuery("from Corobka order by id").list());
    }

    public void addNewCar(String owner, String model, int cuzov, int corobka, int dvigatel, String link, double price) {
        Session session = FACTORY.openSession();
        Cuzov cuzov1 = session.get(Cuzov.class, cuzov);
        Corobka corobka1 = session.get(Corobka.class, corobka);
        Dvigatel dvigatel1 = session.get(Dvigatel.class, dvigatel);
        Car car = new Car(owner, model, dvigatel1, corobka1, cuzov1, link, price);
        session.saveOrUpdate(car);
        session.close();
    }

    public Car getCarByOwnerAndId(String owner, int carId) {
        return (Car)tx(session -> session.createQuery("from Car where owner = :own and id = :id")
                .setParameter("own", owner)
                .setParameter("id", carId)
                .list()
                .get(0));
    }
}
