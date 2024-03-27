package com.zsgs.librarymanagement.users;

import com.zsgs.librarymanagement.admin.AdminView;
import com.zsgs.librarymanagement.librarydatabase.LibraryDataManagement;
import com.zsgs.librarymanagement.login.LoginView;
import com.zsgs.librarymanagement.model.BookReview;
import com.zsgs.librarymanagement.model.LendedUserDetails;
import com.zsgs.librarymanagement.model.Library;
import com.zsgs.librarymanagement.model.UserSuggestion;

import java.util.ArrayList;

public class UsersModel {
    private UsersView usersView;

    public UsersModel() {
        usersView = new UsersView();
    }

    public UsersModel(UsersView usersView) {
        this.usersView = usersView;
    }

    public void userLogin(int userId, String password) {
        LoginView loginView = new LoginView();
        loginView.userLogin(userId, password);
    }

    public void loginSuccess(String message, int userId) {
        usersView = new UsersView();
        usersView.afterLogin(message, userId);
    }

    public void loginFailed(String message) {

        usersView.loginFailed(message);
    }

    public void viewBorrowedBooks(int userId) {
        LibraryDataManagement.getInstance().userBorrowedBooks(userId);
    }

    public void userBorrowedBooks(ArrayList<LendedUserDetails> borrowedBooks) {
        if (borrowedBooks.size() == 0) {
            usersView.borrowedBooksEmpty("User has not borrowed any books yet.");
        } else {
            for (LendedUserDetails borrowedBook : borrowedBooks) {
                usersView.userBorrowedBooks(borrowedBook);
            }
        }
    }

    public void userFavouriteBooks(int userId, String bookName) {
        LibraryDataManagement.getInstance().addFavouriteBooks(bookName, userId);
    }

    public void favouriteBookAdded(String message) {
        usersView.favoriteBookAdded(message);
    }

    public void favouriteBookNotFound(String message) {
        usersView.favouriteBookNotFound(message);
    }

    public void getFavoriteBooks(int userId) {
        LibraryDataManagement.getInstance().viewUserFavoriteBooks(userId);
    }

    public void viewFavoriteBooks(ArrayList<String> favoriteBooks) {
        if (favoriteBooks.size() == 0) {
            usersView.noFavouriteBooks("User has not added any books to his favorite books");
        } else {
            for (String books : favoriteBooks) {
                usersView.userFavouriteBooks(books);
            }
        }
    }
    public void searchBooks(String bookname,int userId){
        if(LibraryDataManagement.getInstance().searchBook(bookname)){
            usersView.writeBook(bookname,userId);
        }

    }
    public void returnBook(int username, String bookname) {
        AdminView adminView = new AdminView();
        adminView.bookReturning(username, bookname);
    }
    public void writeBookReview(int userId,String bookname,String bookreview){
        BookReview bookReview=new BookReview();
        bookReview.setUsername(userId);
        bookReview.setBookname(bookname);
        bookReview.setBookReview(bookreview);
        LibraryDataManagement.getInstance().setBookReviewArrayList(bookReview);
    }
    public void suggestionBox(int userId,String message){
        UserSuggestion userSuggestion=new UserSuggestion();
        userSuggestion.setUserId(userId);
        userSuggestion.setSuggestion(message);
        LibraryDataManagement.getInstance().setUserSuggestions(userSuggestion);
    }
    public void bookReviewSearch(String bookName){
        if(LibraryDataManagement.getInstance().searchBook(bookName)){
            LibraryDataManagement.getInstance().bookReview(bookName);
        }
    }
    public void bookReview(BookReview bookReview){
        usersView.bookReview(bookReview);
    }
}
