package by.kostyan_85.expedition_db.generics.swing;

import by.kostyan_85.expedition_db.generics.Entity;

public interface EntityDTO<T extends Entity> {

    Integer getId();

    T toEntity();
}
