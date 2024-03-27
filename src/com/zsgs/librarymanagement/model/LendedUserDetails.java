package com.zsgs.librarymanagement.model;

public class LendedUserDetails {
    private String bookname;
    private String bookIssueDate;
    private String bookReturnDate;
    private String bookReturningDate;
    private int userFine;
    private String hasReturned;
    private int bookCount;

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

    public String getBookReturningDate() {
        return bookReturningDate;
    }

    public void setBookReturningDate(String bookReturningDate) {
        this.bookReturningDate = bookReturningDate;
    }

    public int getUserFine() {
        return userFine;
    }

    public void setUserFine(int userFine) {
        this.userFine = userFine;
    }

    public String getHasReturned() {
        return hasReturned;
    }

    public void setHasReturned(String hasReturned) {
        this.hasReturned = hasReturned;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount() {
        bookCount+=getBookCount()+1;
    }
    public void returnBookCount(int bookCount){
        this.bookCount-=getBookCount()-1;
    }

    @Override
    public String toString() {
        return "LendedUserDetails{" +
                "bookname='" + bookname + '\'' +
                ", bookIssueDate='" + bookIssueDate + '\'' +
                ", bookReturnDate='" + bookReturnDate + '\'' +
                ", bookReturningDate='" + bookReturningDate + '\'' +
                ", userFine=" + userFine +
                ", hasReturned='" + hasReturned + '\'' +
                ", bookCount=" + bookCount +
                '}';
    }
}
