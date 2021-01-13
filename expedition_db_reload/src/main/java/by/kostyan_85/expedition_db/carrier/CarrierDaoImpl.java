package by.kostyan_85.expedition_db.carrier;

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
 * Created by Zver on 18.04.2020.
 */
public class CarrierDaoImpl extends AbstractEntityDAO<Carrier>/* implements Dao<Carrier> */{
    public CarrierDaoImpl() {
        setClazz(Carrier.class);
    }

    @Override
    public Carrier get(int id) {
        return super.get(id);
    }

    @Override
    public boolean isUnique(Carrier entity) {
        return super.isUnique(entity);
    }

    @Override
    public List<Carrier> getAll() {
        return super.getAll();
    }

    @Override
    public int save(Carrier entity) {
        return super.save(entity);
    }

    @Override
    public void update(Carrier entity) {
        super.update(entity);
    }

    @Override
    public void delete(Carrier entity) {
        super.delete(entity);
    }
    //    @Override
//    public Carrier get(int id) {
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        return session.get(Carrier.class, id);
//    }
//
//    @Override
//    public boolean isUnique(Carrier carrier) {
//        // TODO check that it works
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        Query query = session.createQuery("select name from Carrier where contacts = :contacts");
//        Criteria crit = session.createCriteria(Carrier.class);
//        crit.add(Restrictions.ne("contacts", carrier.getContacts()));
//        List result = crit.list();
//        return result.isEmpty();
//    }
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public List<Carrier> getAll() {
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        return (List<Carrier>) session.createQuery("From Carrier ").list();
//    }
//
//    @Override
//    public int save(Carrier carrier) {
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx1 = session.beginTransaction();
//        session.save(carrier);
//        tx1.commit();
//        session.close();
//        // TODO check that it works! if not, use
//        return carrier.getId();
//    }
//
//    @Override
//    public void update(Carrier carrier) {
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx1 = session.beginTransaction();
//        session.update(carrier);
//        tx1.commit();
//        session.close();
//    }
//
//    @Override
//    public void delete(Carrier carrier) {
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx1 = session.beginTransaction();
//        session.delete(carrier);
//        tx1.commit();
//        session.close();
//    }

}
