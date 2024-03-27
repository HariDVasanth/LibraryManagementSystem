package com.zsgs.librarymanagement.model;

public class BookReview {
    private int username;
    private String bookname;
    private String bookAuthor;

    private String bookReview;
    public int getUsername() {
        return username;
    }

    public void setUsername(int username) {
        this.username = username;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookReview() {
        return bookReview;
    }

    public void setBookReview(String bookReview) {
        this.bookReview = bookReview;
    }

    @Override
    public String toString() {
        return "BookReview{" +
                "username='" + username + '\'' +
                ", bookname='" + bookname + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                '}';
    }
}
