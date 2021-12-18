package ir.maktab.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Dao {
   static final   SessionFactory sessionFactory = new Configuration().configure("ir/maktab/hibernate/hibernate.cfg.xml").buildSessionFactory();




}
