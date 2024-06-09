package org.devJava.repository;

import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public interface CrudRepository<T, ID> {
    void create(@NotNull T object) throws SQLException;
    String read(@NotNull ID id);
    void update(@NotNull T entity);
    void delete(@NotNull ID id) throws SQLException;
    String findAll();
}
