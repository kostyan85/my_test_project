package by.kostyan_85.expedition_db.generics;


import java.util.List;

/**
 * Created by Zver on 05.03.2020.
 */
public abstract class AbstractEntityService<T extends AbstractEntity> implements Service<T> {

    private Dao<T> dao;
    private String[] cols;

    public AbstractEntityService(Dao<T> dao, String[] cols) {
        this.dao = dao;
        this.cols = cols;
    }

    @Override
    public T get(int id) {
        return dao.get(id);
    }

    @Override
    public int save(T entity) {
        return dao.save(entity);
    }

    @Override
    public void update(T entity) {
        dao.update(entity);
    }

    @Override
    public void delete(int id) {
        T entity = dao.get(id);
        dao.delete(entity);
    }

    @Override
    public boolean isUnique(T entity) {
        return dao.isUnique(entity);
    }

    public Object[][] getData() {
        List<T> entities = dao.getAll();
        Object[][] result = new Object[entities.size()][cols.length];
        for (int i = 0; i < entities.size(); i++) {
            result[i] = toArray(entities.get(i));
        }
        return result;
    }

    protected abstract Object[] toArray(T entity);

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public String getColumnName(int index) {
        return cols[index];
    }


}
