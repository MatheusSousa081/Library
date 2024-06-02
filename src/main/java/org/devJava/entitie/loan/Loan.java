package org.devJava.entitie.loan;

import org.devJava.exception.LibraryException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.Objects;

public final class Loan {
    private final int userId;
    private final int bookId;
    private final @NotNull Instant loanDate;
    private final @Nullable Instant returnDate;

    private Loan(int userId, int bookId, @NotNull Instant loanDate, @Nullable Instant returnDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public static @NotNull Loan create(int userId, int bookId,@NotNull Instant loanDate,@Nullable Instant returnDate) throws LibraryException {
        if (loanDate.isAfter(returnDate)) {
            throw new LibraryException("Loan date must be less than return date");
        }
        return new Loan(userId, bookId, loanDate, returnDate);
    }


    public int getUserId() {
        return userId;
    }

    public int getBookId() {
        return bookId;
    }

    public @NotNull Instant getLoanDate() {
        return loanDate;
    }

    public @Nullable  Instant getReturnDate() {
        return returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return userId == loan.userId && bookId == loan.bookId && Objects.equals(loanDate, loan.loanDate) && Objects.equals(returnDate, loan.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, bookId, loanDate, returnDate);
    }
}
