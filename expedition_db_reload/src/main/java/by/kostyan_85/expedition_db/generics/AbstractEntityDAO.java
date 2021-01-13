package by.kostyan_85.expedition_db.generics;

import by.kostyan_85.expedition_db.HibernateSessionFactoryUtil;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Zver on 01.05.2020.
 */
public abstract class AbstractEntityDAO<T extends Entity> implements Dao<T>  {
    private Class< T > clazz;

    public void setClazz( final Class< T > clazzToSet ) {
        clazz = clazzToSet;
    }

    public T get(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return  session.get(clazz, id);
    }

    public boolean isUnique(T entity) {
        // TODO check that it works
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select name from " +clazz+" where contacts = :contacts");
        Criteria crit = session.createCriteria(clazz);
        crit.add(Restrictions.ne("contacts", entity.getContacts()));
        List result = crit.list();
        return result.isEmpty();
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return (List<T>) session.createQuery("From " + clazz.getName()).list();
    }


    public int save(T entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(entity);
        tx1.commit();
        session.close();
        // TODO check that it works! if not, use
        return entity.getId();
    }


    public void update(T entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(entity);
        tx1.commit();
        session.close();
    }


    public void delete(T entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(entity);
        tx1.commit();
        session.close();
    }



}
