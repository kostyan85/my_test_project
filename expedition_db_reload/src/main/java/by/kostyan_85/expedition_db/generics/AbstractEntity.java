package by.kostyan_85.expedition_db.generics;

import java.io.Serializable;

public abstract class AbstractEntity implements Entity {

    public AbstractEntity() {
    }

    protected Integer id;

    public AbstractEntity(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }
}
