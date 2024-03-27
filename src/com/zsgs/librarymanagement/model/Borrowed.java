package com.zsgs.librarymanagement.model;

public class Borrowed {
    private String bookname;
    private String bookIssueDate;
    private String bookReturnDate;
    private String hasReturned;
    private String bookReturningDate;

    private String bookUserReturnedDate;

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookIssueDate() {
        return bookIssueDate;
    }

    public void setBookIssueDate(String bookIssueDate) {
        this.bookIssueDate = bookIssueDate;
    }

    public String getBookReturnDate() {
        return bookReturnDate;
    }

    public void setBookReturnDate(String bookReturnDate) {
        this.bookReturnDate = bookReturnDate;
    }

    public String getBookUserReturnedDate() {
        return bookUserReturnedDate;
    }

    public void setBookUserReturnedDate(String bookUserReturnedDate) {
        this.bookUserReturnedDate = bookUserReturnedDate;
    }

    public String getHasReturned() {
        return hasReturned;
    }

    public void setHasReturned(String hasReturned) {
        this.hasReturned = hasReturned;
    }

    public String getBookReturningDate() {
        return bookReturningDate;
    }

    public void setBookReturningDate(String bookReturningDate) {
        this.bookReturningDate = bookReturningDate;
    }

    @Override
    public String toString() {
        return "Borrowed{" +
                "bookname='" + bookname + '\'' +
                ", bookIssueDate='" + bookIssueDate + '\'' +
                ", bookReturnDate='" + bookReturnDate + '\'' +
                ", hasReturned='" + hasReturned + '\'' +
                ", bookReturningDate='" + bookReturningDate + '\'' +
                ", bookUserReturnedDate='" + bookUserReturnedDate + '\'' +
                '}';
    }
}
