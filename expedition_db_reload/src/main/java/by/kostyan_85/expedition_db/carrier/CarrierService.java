package by.kostyan_85.expedition_db.carrier;

import by.kostyan_85.expedition_db.generics.AbstractEntityService;
import by.kostyan_85.expedition_db.generics.Dao;

/**
 * Created by Zver on 02.03.2020.
 */
public class CarrierService extends AbstractEntityService<Carrier> {
    private Dao<Carrier> carrierDao;
    private static final String[] COLS = {/*"ID",*/ " Название", "Контакты", "Коментарий", "Договор"};


    public CarrierService(Dao<Carrier> carrierDao) {
        super(carrierDao, COLS);
    }

    @Override
    protected Object[] toArray(Carrier entity) {
        return new Object[]{/*entity.getId(),*/ entity.getName(), entity.getContacts(), entity.getComments(), null};

    }

}
