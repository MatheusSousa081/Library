package org.devJava.entities.user;

import org.devJava.entities.book.Book;
import org.devJava.exceptions.UserException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private Long id;
    private String name;
    private int age;
    private List <Book> borrowedBooks;

    public User(Long id,String name,int age) throws UserException {
        if (id < 0) {
            throw new UserException("Id invalid");
        }

        this.id = id;
        this.name = name;
        this.age = age;
        borrowedBooks = new ArrayList<>();
    }

    public User createUser(Long id, String name, int age) throws UserException{
        if (id < 0) {
            throw new UserException("Id invalid");
        }

        borrowedBooks = new ArrayList<>();
        return new User(id, name, age);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", " + name + ", " + age + " years.";
    }
}
