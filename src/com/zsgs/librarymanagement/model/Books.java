package com.zsgs.librarymanagement.model;

public class Books {

    private String bookName;
    private String bookAuthor;
    private String bookGenre;
    private String bookEdition;
    private int bookCount;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    public String getBookEdition() {
        return bookEdition;
    }

    public void setBookEdition(String bookEdition) {
        this.bookEdition = bookEdition;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    @Override
    public String toString() {
        return String.format("| %-20s | %-20s | %-20s | %-20s | %-10s |\n", "Book Name", "Author", "Genre", "Edition", "Count") +
                "+----------------------+----------------------+----------------------+----------------------+------------+\n" +
                String.format("| %-20s | %-20s | %-20s | %-20s | %-10d |\n", bookName, bookAuthor, bookGenre, bookEdition, bookCount);
    }

}
