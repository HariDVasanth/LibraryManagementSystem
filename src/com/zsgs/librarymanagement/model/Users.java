package com.zsgs.librarymanagement.model;

import java.util.ArrayList;

public class Users {
    private String username;
    private String password;
    private ArrayList<String> favouriteBooks = new ArrayList<>();
    private ArrayList<LendedUserDetails> borrowedBooks = new ArrayList<>();




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getFavouriteBooks() {
        return favouriteBooks;
    }

    public void setFavouriteBooks(String favouriteBooks) {
        this.favouriteBooks.add(favouriteBooks);
    }

    public ArrayList<LendedUserDetails> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(LendedUserDetails borrowedDetails) {
        borrowedBooks.add(borrowedDetails);
    }


    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", favouriteBooks=" + favouriteBooks +
                ", borrowedBooks=" + borrowedBooks
                ;
    }
}
