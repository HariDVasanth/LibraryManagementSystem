package com.zsgs.librarymanagement.login;

import com.zsgs.librarymanagement.librarydatabase.LibraryDataManagement;
import com.zsgs.librarymanagement.model.Admin;
import com.zsgs.librarymanagement.users.UsersModel;

import java.util.List;

public class LoginModel {
    private LoginView loginView;

    public LoginModel(LoginView loginView) {
        this.loginView = loginView;
    }

    public void adminLogin(String username, String password) {
        List<Admin> details = LibraryDataManagement.getInstance().getAdminLoginDetails();
        if (details.stream().anyMatch(n -> n.getAdminUsername().equals(username))) {
            if (details.stream().anyMatch(n -> n.getAdminPassword().equals(password))) {
                loginView.adminOnSuccess("Admin login success.");
            } else {
                loginView.adminWrongPassword("Please enter the right password.");
            }
        } else {
            loginView.adminWrongUsername("Wrong username.");
        }
    }

    public void removeDefault(String username, String password) {
        if (LibraryDataManagement.getInstance().adminUsername(username)) {
            if (LibraryDataManagement.getInstance().adminPassword(username, password)) {
                LibraryDataManagement.getInstance().removeDefault();
                loginView.defaultRemoved("Default username and password removed successfully");
            } else {
                loginView.defaultRemoved("Enter correct password");
            }
        } else {
            loginView.defaultNotRemoved("Enter correct username.");
        }
    }

    public void userLogin(int userId, String password) {
        if (LibraryDataManagement.getInstance().userPassword(userId, password)) {
            UsersModel usersModel = new UsersModel();
            usersModel.loginSuccess(userId + " Log in success", userId);
        } else {
            UsersModel usersModel = new UsersModel();
            usersModel.loginFailed("Please enter correct details");
        }
    }
}

