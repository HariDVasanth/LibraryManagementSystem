package com.zsgs.librarymanagement.users;

import com.zsgs.librarymanagement.admin.AdminView;
import com.zsgs.librarymanagement.createid.CreateIdView;
import com.zsgs.librarymanagement.librarydatabase.LibraryDataManagement;
import com.zsgs.librarymanagement.mainmenu.MainMenuView;
import com.zsgs.librarymanagement.model.BookReview;
import com.zsgs.librarymanagement.model.LendedUserDetails;

import java.util.Scanner;

public class UsersView {
    private UsersModel usersModel;
    Scanner scanner = new Scanner(System.in);

    public UsersView() {
        usersModel = new UsersModel(this);
    }

    public void userViewInit() {
        userMenu();
    }

    public void userMenu() {
        System.out.println("+--------------------------------+");
        System.out.println("|             User Menu          |");
        System.out.println("+--------------------------------+");
        System.out.println("| Option |         Action         |");
        System.out.println("+--------------------------------+");
        System.out.println("|   1    |        Login           |");
        System.out.println("|   2    |       Sign Up          |");
        System.out.println("|   3    |      View Books        |");
        System.out.println("|   4    |  Go back to main menu  |");
        System.out.println("+--------------------------------+");
        System.out.print("Enter your choice: ");
        int userOption = scanner.nextInt();
        switch (userOption) {
            case 1:
                userLogin();
                break;
            case 2:
                userSignUp();
                break;
            case 3:
                viewBooks();
                break;
            case 4:
                System.out.println("Returning to main menu..........");
                MainMenuView mainMenuView = new MainMenuView();
                mainMenuView.popUpMenu();
        }
    }


    public void userLogin() {
        System.out.print("Enter your userId: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        usersModel.userLogin(userId, password);
    }

    public void userSignUp() {
        CreateIdView createIdView = new CreateIdView();
        createIdView.newUser();
    }

    public void afterSignUp(String message) {
        System.out.println(message);
        LibraryDataManagement.getInstance().serializeUserDetails();
        userMenu();
    }

    public void afterLogin(String message, int userId) {
        System.out.println(message);
        userLoginMenu(userId);
    }

    public void viewBooks() {
        System.out.println(LibraryDataManagement.getInstance().getLibraryBooks());
        userMenu();
    }

    public void loginFailed(String message) {
        System.out.println(message);
        userMenu();
    }

    public void userLoginMenu(int userId) {
        System.out.println("+-----------------------------------------+");
        System.out.println("|          User Login Menu               |");
        System.out.println("+-----------------------------------------+");
        System.out.println("| Option |            Action              |");
        System.out.println("+-----------------------------------------+");
        System.out.println("|   1    |     View borrowed books        |");
        System.out.println("|   2    |         Borrow a book          |");
        System.out.println("|   3    |     Mark favourite books       |");
        System.out.println("|   4    |     View favourite Books       |");
        System.out.println("|   5    |         Return the book        |");
        System.out.println("|   6    |      Post a book review        |");
        System.out.println("|   7    |        Post complaints         |");
        System.out.println("|   8    |        Read a book review      |");
        System.out.println("|   9    |    Return to main menu         |");
        System.out.println("+-----------------------------------------+");
        System.out.print("Enter your choice: ");
        int userChoice = scanner.nextInt();
        scanner.nextLine();
        switch (userChoice) {
            case 1:
                borrowedBooks(userId);
                break;
            case 2:
                borrowBook(userId);
                break;
            case 3:
                addTofavouriteBook(userId);
                break;
            case 4:
                viewFavouriteBooks(userId);
                break;
            case 5:
                returnBook(userId);
                break;
            case 6:
                bookReview(userId);
                break;
            case 7:
                postCompliants(userId);
                break;
            case 8:
                readBookReview();
                break;
            case 9:
                MainMenuView mainMenuView = new MainMenuView();
                mainMenuView.popUpMenu();
                break;
        }
    }

    public void borrowedBooks(int userId) {
        usersModel.viewBorrowedBooks(userId);
        userMenu();
    }

    public void userBorrowedBooks(LendedUserDetails books) {
        System.out.println(books);

    }

    public void borrowedBooksEmpty(String messsage) {
        System.out.println(messsage);
    }

    public void borrowBook(int userId) {
        System.out.print("Enter the book you want to borrow: ");
        String bookname = scanner.nextLine();
        AdminView adminView = new AdminView();
        adminView.lendBooks(userId, bookname);
    }

    public void afterLending(String message) {
        System.out.println(message);
        userMenu();
    }

    public void bookNotAvailable(String message) {
        System.out.println(message);
    }

    public void addTofavouriteBook(int userId) {
        System.out.print("Enter the book name: ");
        String favoriteBooks = scanner.nextLine();
        usersModel.userFavouriteBooks(userId, favoriteBooks);
        userLoginMenu(userId);
    }

    public void favoriteBookAdded(String message) {
        System.out.println(message);
        userMenu();
    }

    public void favouriteBookNotFound(String message) {
        System.out.println(message);
        userMenu();
    }

    public void viewFavouriteBooks(int userId) {
        usersModel.getFavoriteBooks(userId);
        userLoginMenu(userId);
    }

    public void userFavouriteBooks(String books) {
        System.out.println(books);
        userMenu();
    }

    public void noFavouriteBooks(String message) {
        System.out.println(message);
        userMenu();
    }

    public void returnBook(int userId) {
        System.out.println("Enter the book you want to return: ");
        String bookname = scanner.nextLine();
        usersModel.returnBook(userId, bookname);
        userLoginMenu(userId);
    }

    public void bookReview(int userId) {
        System.out.println("Post a book review");
        System.out.println("Enter the name of the book: ");
        String bookName=scanner.nextLine();
        usersModel.searchBooks(bookName,userId);
        userMenu();
    }
    public void writeBook(String bookname,int userId){
        System.out.println("Write book review for the book: "+bookname);
        String bookReview=scanner.nextLine();
        usersModel.writeBookReview(userId,bookname,bookReview);
        userMenu();
    }
    public void postCompliants(int userId) {
        System.out.println("We always welcome suggestions");
        String suggestion=scanner.nextLine();
        usersModel.suggestionBox(userId,suggestion);
        userMenu();
    }
    public void readBookReview(){
        System.out.print("Enter book name: ");
        String bookName=scanner.nextLine();
        usersModel.bookReviewSearch(bookName);
        userMenu();
    }
    public void bookReview(BookReview bookReview){
        System.out.println(bookReview);
        userMenu();
    }
}
