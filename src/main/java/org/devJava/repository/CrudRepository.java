package org.devJava.repository;

import org.devJava.entitie.book.Book;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public interface CrudRepository<T, ID> {
    void create(@NotNull T object);
    String read(@NotNull ID id);
    void update(@NotNull T entity);
    void delete(@NotNull ID id);
    Set<T> findAll();
}
