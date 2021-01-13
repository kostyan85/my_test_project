package by.kostyan_85.expedition_db.customer;

import by.kostyan_85.expedition_db.HibernateSessionFactoryUtil;
import by.kostyan_85.expedition_db.generics.AbstractEntityDAO;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import by.kostyan_85.expedition_db.generics.Dao;

import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


import java.util.List;

/**
 * Created by Zver on 27.04.2020.
 */
public class CustomerDaoImpl extends AbstractEntityDAO<Customer> /*implements Dao<Customer>*/{
    public CustomerDaoImpl() {
        setClazz(Customer.class);
    }




}
