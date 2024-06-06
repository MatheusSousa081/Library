package org.devJava.entity.library;

import org.devJava.entity.book.Book;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class StockItem {
    private final @NotNull Book book;
    private int quantity;

    public StockItem(@NotNull Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public @NotNull Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockItem stockItem = (StockItem) o;
        return Objects.equals(book, stockItem.book);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(book);
    }
}
