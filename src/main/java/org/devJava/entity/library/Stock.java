package org.devJava.entity.library;

import org.devJava.entity.book.Book;
import org.devJava.exception.LibraryException;
import org.jetbrains.annotations.NotNull;

import java.time.Year;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class Stock implements Iterable<@NotNull StockItem> {
    private final @NotNull Set<StockItem> books;

    public Stock() {
        books = new HashSet<>();
    }

    public void add (int id,@NotNull String title,@NotNull String author,@NotNull Year year,@NotNull Book.Gender gender, int quantity) throws LibraryException {
        books.add(new StockItem(Book.create(id, title, author, year, gender), quantity));
    }

    public boolean remove(@NotNull String title) {
        return books.removeIf(book -> book.getBook().getTitle().equalsIgnoreCase(title));
    }

    public boolean remove(@NotNull Book book) {
        return books.removeIf(item -> item.getBook().equals(book));
    }

    public Optional<StockItem> get(@NotNull String title) {
        return books.stream().filter(book -> book.getBook().getTitle().equalsIgnoreCase(title)).findFirst();
    }

    public boolean contains(@NotNull String title) {
        return stream().anyMatch(book -> book.getBook().getTitle().equalsIgnoreCase(title));
    }

    public boolean contains(@NotNull Book book) {
        return books.stream().anyMatch(item -> item.getBook().equals(book));
    }

    public int size() {
        return books.size();
    }

    public @NotNull Stream<StockItem> stream() {
        return books.stream();
    }

    @NotNull
    @Override
    public Iterator<@NotNull StockItem> iterator() {
        return books.iterator();
    }
}
