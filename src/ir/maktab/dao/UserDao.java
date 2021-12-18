package ir.maktab.dao;

import ir.maktab.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class UserDao extends Dao {
    private Session session;
    private Transaction transaction;

    public void save(User user) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        session.close();
    }

    public List<User> findByName(String name) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Query q = session.createQuery(" from User where name=:n ");
        q.setParameter("n", name);
        List list = q.getResultList();
        transaction.commit();
        session.close();
        return list;
    }

    public List<User> findByFamily(String family) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Query q = session.createQuery(" from User where family=:f");
        q.setParameter("f", family);
        List list = q.getResultList();
        transaction.commit();
        session.close();
        return list;
    }
    public List<User> findByNationalCode(String id) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Query q = session.createQuery(" from User where nationalCode=:id");
        q.setParameter("id", id);
        List list = q.getResultList();
        transaction.commit();
        session.close();
        return list;
    }
}
