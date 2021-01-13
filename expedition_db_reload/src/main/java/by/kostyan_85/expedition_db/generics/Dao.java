package by.kostyan_85.expedition_db.generics;

import by.kostyan_85.expedition_db.carrier.Carrier;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Zver on 08.03.2020.
 */
public interface Dao<T> {
    T get(int id);

    List<T> getAll() throws Exception;

    int save(T entity)throws Exception;

    void update(T entity)throws Exception;

    void delete(T entity)throws Exception;

    boolean isUnique(T entity)throws Exception;
}
