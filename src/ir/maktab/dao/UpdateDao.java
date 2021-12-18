package ir.maktab.dao;

import ir.maktab.model.Update;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UpdateDao extends Dao {
    Session session;
    Transaction transaction;

    public void save(Update update) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.persist(update);
        transaction.commit();
        session.close();
    }

}
