package org.devJava.repository;

import org.jetbrains.annotations.NotNull;

public interface CrudRepository<T, ID> {
    void create(@NotNull T object);
    String read(@NotNull ID id);
    void update(@NotNull T entity);
    void delete(@NotNull ID id);
    String findAll();
}
