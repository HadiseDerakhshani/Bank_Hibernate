package ir.maktab.dao;

import ir.maktab.model.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class AccountDao extends Dao {
    Session session;
    Transaction transaction;

    public void save(Account account) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.persist(account);
        transaction.commit();
        session.close();
    }

    public List<Account> findByNumCard(int numCard) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Query q = session.createQuery(" from Account where numCard=:c");
        q.setParameter("c", numCard);
        List list = q.getResultList();
        transaction.commit();
        session.close();
        return list;
    }

    public int deposit(int numCard, double amount) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Query q = session.createQuery("update Account set balance=:b+balance where numCard=:num");
        q.setParameter("b", amount);
        q.setParameter("num", numCard);
        int status = q.executeUpdate();
        transaction.commit();
        session.close();
        return status;
    }

    public int withdraw(int numCard, double amount) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Query q = session.createQuery("update Account set balance=:b-balance where numCard=:num");
        q.setParameter("b", amount);
        q.setParameter("num", numCard);
        int status = q.executeUpdate();
        transaction.commit();
        session.close();
        return status;
    }

    public int maxNumAccount() {
        int number = 0;

        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Query q = session.createQuery("Select max(numAccount) from Account ");
        List result = q.getResultList();
        if (result == null) {
            return number;
        } else
            number = (int) result.get(0);
        return number;
    }

    public int maxNumCard() {
        int number = 0;

        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Query q = session.createQuery("Select max(numCard) from Account ");
        List result = q.getResultList();
        if (result == null) {
            return number;
        } else
            number = (int) result.get(0);
        return number;
    }

    public int maxCvv2() {
        int number = 0;

        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Query q = session.createQuery("Select max(cvv2) from Account ");
        List result = q.getResultList();
        if (result == null) {
            return number;
        } else
            number = (int) result.get(0);
        return number;
    }
}
