package com.zsgs.librarymanagement.login;

import com.zsgs.librarymanagement.LibraryManagement2024;
import com.zsgs.librarymanagement.admin.AdminView;
import com.zsgs.librarymanagement.createid.CreateIdView;
import com.zsgs.librarymanagement.librarydatabase.LibraryDataManagement;

import java.util.Scanner;

public class LoginView {
    private LoginModel loginModel;

    public LoginView() {
        loginModel = new LoginModel(this);
    }

    Scanner scanner = new Scanner(System.in);

    public void init() {

        System.out.println(LibraryManagement2024.getInstance().getLibraryName());
        System.out.println(LibraryManagement2024.getInstance().getLibraryAppVersion());
        System.out.print("Enter the admin (default) username for login: ");
        String username = scanner.nextLine();
        System.out.print("Enter the admin (default) password for login: ");
        String password = scanner.nextLine();
        loginModel.removeDefault(username, password);
    }

    public void newAdminUserDetails() {
        CreateIdView createIdView = new CreateIdView();
        createIdView.createIdInit();
    }

    public void adminLogin(String username, String password) {
        loginModel.adminLogin(username, password);
    }

    public void adminOnSuccess(String loginSucess) {
        System.out.println(loginSucess);

        AdminView adminView = new AdminView();
        adminView.adminMenu();
    }

    public void adminWrongPassword(String message) {
        System.out.println(message);
        redirect();
    }

    public void adminWrongUsername(String message) {
        System.out.println(message);
        redirect();
    }

    public void redirect() {
        AdminView adminView = new AdminView();
        adminView.adminLogin();
    }

    public void userLogin(int userId, String password) {
        loginModel.userLogin(userId, password);
    }

    public void defaultRemoved(String message) {
        System.out.println(message);
        newAdminUserDetails();
    }

    public void defaultNotRemoved(String message) {
        System.out.println(message);
        init();
    }
}
