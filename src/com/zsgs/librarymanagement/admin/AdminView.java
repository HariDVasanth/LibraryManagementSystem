package com.zsgs.librarymanagement.admin;

import com.zsgs.librarymanagement.librarydatabase.LibraryDataManagement;
import com.zsgs.librarymanagement.mainmenu.MainMenuView;
import com.zsgs.librarymanagement.model.BookReview;
import com.zsgs.librarymanagement.model.LendedUserDetails;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminView {
    private AdminModel adminModel;

    public AdminView() {
        adminModel = new AdminModel(this);
    }

    Scanner scanner = new Scanner(System.in);

    public void adminInit() {
        adminLogin();
    }

    public void adminLogin() {
        System.out.println("Welcome Admin");
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();
        adminModel.adminVerify(username, password);
    }

    public void adminMenu() {
        System.out.println("=============================================");
        System.out.println("               Admin Menu                    ");
        System.out.println("=============================================");
        System.out.println("1. Add books");
        System.out.println("2. View all books");
        System.out.println("3. View users data");
        System.out.println("4. Search books");
        System.out.println("5. View borrowed book details");
        System.out.println("6. View book review");
        System.out.println("7. Return to main menu");

        System.out.println("---------------------------------------------");
        System.out.print("Enter your choice: ");

        int adminChoice = scanner.nextInt();
        scanner.nextLine();
        switch (adminChoice) {
            case 1:
                addBooks();
                break;
            case 2:
                allBooks();
                break;
            case 3:
                allUsers();
                break;
            case 4:
                searchBook();
                break;
            case 5:
                viewLendedBooks();
                break;
            case 6:
                allBookReview();
                break;
            case 7:
                MainMenuView mainMenuView = new MainMenuView();
                mainMenuView.popUpMenu();

            default:
                System.out.println("Enter the right option");
                break;
        }
    }

    public void addBooks() {
        adminModel.addBooks();
    }

    public void allBooks() {
        System.out.println(LibraryDataManagement.getInstance().getLibraryBooks());
        adminMenu();
    }

    public void allUsers() {
        adminModel.allUsers();
        adminMenu();
    }

    public void searchBook() {
        System.out.print("Enter the book name: ");
        String bookname = scanner.nextLine();
        adminModel.searchBooks(bookname, 1);
    }

    public void bookFound(String message) {
        System.out.println(message);
        adminMenu();
    }

    public void bookNotAvailable(String message) {
        System.out.println(message);
        adminMenu();
    }

    public void usersDetails(String result) {
        System.out.println(result);
    }

    public void afterRemoving(String message) {
        System.out.println(message);
        adminMenu();
    }

    public void lendBooks(int userId, String bookname) {
        adminModel.searchBooks(bookname, userId);
    }

    public void lendingBookEntry(String bookname, int userId) {
        System.out.println("----------Library Admin---------");
        System.out.println("Enter the book issue date. ");
        String issueDate = scanner.nextLine();
        System.out.println("Enter the expected book return date");
        String returnDate = scanner.nextLine();
        adminModel.lendBooks(userId, bookname, issueDate, returnDate);
    }

    public void bookReturning(int userId, String bookname) {
        System.out.print("Enter the book returning date: ");
        String returnDate = scanner.nextLine();
        adminModel.returnBook(userId, bookname, returnDate);
    }

    public void fineAmount(int fineAmount) {
        System.out.println("Fine amount of rupees: " + fineAmount);
    }

    public void noFine() {
        System.out.println("The book returned on time no fine amount");
    }

    public void viewLendedBooks() {
        adminModel.viewLendedDetails();
    }

    public void lendedUsersDetails(int userId, LendedUserDetails lendedUserDetails) {
        System.out.println(userId + " " + lendedUserDetails);
    }
    public void userHasDueAmount(String message){
        System.out.println(message);
    }
    public void limitExceeded(String message){
        System.out.println(message);
    }
    public void allBookReview(){
        adminModel.getBookReview();
    }
    public void readBookReview(ArrayList<BookReview>arrayList){
        System.out.println(arrayList);
    }

}
