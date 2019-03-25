package logic;

import models.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.function.Function;

public class Store {
    private final SessionFactory factory = new Configuration()
            .configure().buildSessionFactory();

    private <T> T tx(final Function<Session, T> command) {
        final Session session = factory.openSession();
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
}
