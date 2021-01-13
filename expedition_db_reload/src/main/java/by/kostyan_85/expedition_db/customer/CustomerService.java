package by.kostyan_85.expedition_db.customer;

import by.kostyan_85.expedition_db.carrier.Carrier;
import by.kostyan_85.expedition_db.generics.AbstractEntityService;
import by.kostyan_85.expedition_db.generics.Dao;

import java.sql.SQLException;


/**
 * Created by Zver on 23.02.2020.
 */
public class CustomerService extends AbstractEntityService<Customer> {
    private Dao<Customer> customerDao;
    private static final String[] COLS = {/*"ID",*/ " Название", "Контакты", "Коментарий", "Договор"};


    public CustomerService(Dao<Customer> customerDao) {
        super(customerDao, COLS);
    }

    @Override
    protected Object[] toArray(Customer entity) {
        return new Object[]{/*entity.getId(),*/ entity.getName(), entity.getContacts(), entity.getComments(), null};

    }
}
