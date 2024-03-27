package com.zsgs.librarymanagement.admin;

import com.zsgs.librarymanagement.librarydatabase.LibraryDataManagement;
import com.zsgs.librarymanagement.login.LoginView;
import com.zsgs.librarymanagement.managebooks.ManageBookModel;
import com.zsgs.librarymanagement.managebooks.ManageBookView;
import com.zsgs.librarymanagement.model.LendedUserDetails;
import com.zsgs.librarymanagement.users.UsersView;


import java.util.Map;
import java.util.stream.Collectors;

public class AdminModel {
    private AdminView adminView;

    public AdminModel() {
        adminView = new AdminView();
    }

    public AdminModel(AdminView adminView) {
        this.adminView = adminView;
    }

    public void adminVerify(String username, String password) {
        LoginView loginView = new LoginView();
        loginView.adminLogin(username, password);
    }

    public void addBooks() {
        ManageBookView manageBookView = new ManageBookView();
        manageBookView.addBooks();

    }

    public void allUsers() {
        String results = LibraryDataManagement.getInstance().getUserDetails().entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining(", "));
        adminView.usersDetails(results);

    }

    public void searchBooks(String bookname, int userId) {
        ManageBookModel manageBookModel=new ManageBookModel();
        manageBookModel.searchBooks(bookname,userId);
//        if (LibraryDataManagement.getInstance().searchBook(bookname) && userId == 1) {
//            adminView.bookFound("Book found.");
//        } else if (LibraryDataManagement.getInstance().searchBook(bookname) && userId >= 1000) {
//            adminView.lendingBookEntry(bookname, userId);
//        } else {
//            adminView.bookNotAvailable("Book not available.");
//        }
    }

    public void lendBooks(int userId, String bookname, String issueDate, String returnDate) {
        LibraryDataManagement.getInstance().removeBooks(userId, bookname, issueDate, returnDate);
    }

    public void afterLending(String message) {
        UsersView usersView = new UsersView();
        usersView.afterLending(message);
    }

    public void bookNotAvailable(String message) {
        UsersView usersView = new UsersView();
        usersView.bookNotAvailable(message);
    }
    public void bookFinePresent(String message){
        adminView.userHasDueAmount(message);
    }
    public void limitExceeding(String message){
        adminView.limitExceeded(message);
    }
    public void returnBook(int userId, String bookname, String returnDate) {
        LibraryDataManagement.getInstance().returnBook(userId, bookname, returnDate);
    }

    public void bookFineAmount(int fineAmount) {
        adminView.fineAmount(fineAmount);
    }

    public void noFineAmount() {
        adminView.noFine();
    }

    public void viewLendedDetails() {
        for (Map.Entry<Integer, LendedUserDetails> entry : LibraryDataManagement.getInstance().getLendedBookDetails().entrySet()) {
            int key = entry.getKey();
            LendedUserDetails value = entry.getValue();
            adminView.lendedUsersDetails(key, value);
        }
    }
    public void getBookReview(){
        adminView.readBookReview(LibraryDataManagement.getInstance().getBookReviewArrayList());
    }
}
