package by.kostyan_85.expedition_db.generics.swing;

import by.kostyan_85.expedition_db.generics.Entity;

public abstract class AbstractDTO<T extends Entity> implements EntityDTO<T> {
    private Integer id;

    public AbstractDTO(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }
}
