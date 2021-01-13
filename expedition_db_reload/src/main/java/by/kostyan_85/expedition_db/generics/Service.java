package by.kostyan_85.expedition_db.generics;

public interface Service<T extends Entity> {

    T get(int id);

    int save(T entity)throws Exception;

    void update(T entity)throws Exception;

    void delete(int id)throws Exception;

    boolean isUnique(T entity)throws Exception;

    Object[][] getData()throws Exception;

    int getColumnCount()throws Exception;

    String getColumnName(int index)throws Exception;

}
