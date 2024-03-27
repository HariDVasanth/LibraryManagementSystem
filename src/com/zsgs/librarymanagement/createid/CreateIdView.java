package com.zsgs.librarymanagement.createid;

import com.zsgs.librarymanagement.librarydatabase.LibraryDataManagement;
import com.zsgs.librarymanagement.librarysetup.LibrarySetUpView;

import com.zsgs.librarymanagement.users.UsersView;

import java.util.Scanner;

public class CreateIdView {
    private CreateIdModel createIdModel;
    Scanner scanner = new Scanner(System.in);

    public CreateIdView() {
        createIdModel = new CreateIdModel(this);
    }

    public void createIdInit() {
        createAdmin();
    }

    public void createAdmin() {
        System.out.println("Admin Sign Up");
        System.out.print("Enter new admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter new admin password: ");
        String password = scanner.nextLine();
        createIdModel.createAdmin(username, password);
    }

    public void adminAccountCreated() {
        System.out.println("Admin account created successfully. ");
        LibraryDataManagement.getInstance().serializeAdminLoginDetails();
        LibrarySetUpView librarySetUpView = new LibrarySetUpView();
        librarySetUpView.librarySetUpInit();
    }

    public void failedPassword(String message) {
        System.out.println(message);
        createAdmin();
    }

    public void failedUsername(String message) {
        System.out.println(message);
        newUser();
    }

    public void newUser() {
        System.out.println("Welcome new user ");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        createIdModel.createIdUser(username, password);
    }

    public void userIdSuccess(int userId) {
        System.out.println("Account created successfully");
        UsersView usersView = new UsersView();
        usersView.afterSignUp("Your user Id is: " + userId);
    }

    public void failedPasswordUser(String message) {
        System.out.println(message);
        newUser();
    }


}
