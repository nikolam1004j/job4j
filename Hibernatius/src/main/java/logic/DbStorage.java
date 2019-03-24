package logic;

import models.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.function.Function;

public class DbStorage {
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

    public List getAllTasks() {
        return this.tx(s -> s.createQuery("from Item order by id").list());
    }

    public List getNotDone() {
        return this.tx(s -> s.createQuery("from Item where done = :done order by id")
                .setParameter("done", false)
                .list());
    }

    public boolean addNewTask(Item item) {
        return this.tx(s -> { s.saveOrUpdate(item); return true; } );
    }

    public boolean setStatusDoneToItemById(int id) {
        Function<Session, Boolean> func = ses -> {
            boolean result = false;
            List list = ses.createQuery("from Item where id = :id")
                    .setParameter("id", id)
                    .list();

            if (list.size() == 1) {
                Item item = (Item) list.get(0);
                item.setDone(true);
                ses.saveOrUpdate(item);
                result = true;
            }
            return result;
        };
        return tx(func);
    }
}