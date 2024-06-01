package org.devJava.repository;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository<T, ID> {
    void create(T object);
    String read(ID id);
    void update(T entity);
    void delete(ID id);
    String findAll();
}
