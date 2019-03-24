package logic;

import models.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DbStorage {
    private final SessionFactory factory = new Configuration()
            .configure().buildSessionFactory();

    public List getAllTasks() {
        Session session = factory.openSession();
        session.beginTransaction();
        List items = session.createQuery("from Item order by id").list();
        session.getTransaction().commit();
        session.close();
        return items;
    }

    public List getNotDone() {
        Session session = factory.openSession();
        session.beginTransaction();
        List items = session.createQuery("from Item where done = :done order by id")
                .setParameter("done", false)
                .list();
        session.getTransaction().commit();
        session.close();
        return items;
    }

    public boolean addNewTask(Item item) {
        boolean result = false;
        Session session = factory.openSession();
        Transaction tran = session.beginTransaction();
        try {
            session.saveOrUpdate(item);
            tran.commit();
            result = true;
        } catch (Exception ex) {
            tran.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    public boolean setStatusDoneToItemById(int id) {
        boolean result = false;
        Session session = factory.openSession();
        Transaction tran = session.beginTransaction();
        try {
            List list = session.createQuery("from Item where id = :id")
                    .setParameter("id", id)
                    .list();

            if (list.size() == 1) {
                Item item = (Item) list.get(0);
                item.setDone(true);
                session.saveOrUpdate(item);
                tran.commit();
                result = true;
            }
        } catch (Exception ex) {
            tran.rollback();
        } finally {
            session.close();
        }
        return result;
    }
}