package org.devJava.repository;

import org.devJava.entitie.user.Client;
import org.devJava.repository.Connection.Database;
import org.jetbrains.annotations.NotNull;


public class ClientRepository implements CrudRepository<Client, Integer> {
    private final @NotNull Database connection;

    public ClientRepository(@NotNull Database connection) {
        this.connection = connection;
    }

    public @NotNull Database getConnection() {
        return connection;
    }

    @Override
    public void create(@NotNull Client object) {

    }

    @Override
    public String read(@NotNull Integer integer) {
        return "";
    }

    @Override
    public void delete(@NotNull Integer integer) {

    }

    @Override
    public void update(@NotNull Client entity) {

    }

    @Override
    public String findAll() {
        return "";
    }
}
