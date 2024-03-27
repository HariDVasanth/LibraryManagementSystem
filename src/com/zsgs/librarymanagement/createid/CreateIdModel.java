package com.zsgs.librarymanagement.createid;

import com.zsgs.librarymanagement.librarydatabase.LibraryDataManagement;

import com.zsgs.librarymanagement.model.Admin;
import com.zsgs.librarymanagement.model.Users;
import com.zsgs.librarymanagement.validator.Validator;

public class CreateIdModel {
    private CreateIdView createIdView;
    private Validator validator;

    public CreateIdModel(CreateIdView createIdView) {
        this.createIdView = createIdView;
    }

    public void createAdmin(String username, String password) {
        validator = new Validator();
        if (validator.usernameVerify(username)) {
            if (validator.passwordVerify(password)) {
                Admin admin = new Admin();
                admin.setAdminUsername(username);
                admin.setAdminPassword(password);
                admin.setAdmin(admin);
                LibraryDataManagement.getInstance().setAdminLoginDetails(admin);
                createIdView.adminAccountCreated();
            } else {
                createIdView.failedPassword("Please enter a strong password. ");
            }
        } else {
            createIdView.failedUsername("Enter a valid username. ");
        }
    }

    public void createIdUser(String username, String password) {
        Validator validator1 = new Validator();
        if (validator1.usernameVerify(username)) {
            if (validator1.passwordVerify(password)) {
                Users users = new Users();
                users.setUsername(username);
                users.setPassword(password);
                int userId = LibraryDataManagement.getInstance().getUserId();
                LibraryDataManagement.getInstance().setUserDetails(userId, users);
                LibraryDataManagement.getInstance().setUserId();
                createIdView.userIdSuccess(userId);
            } else {
                createIdView.failedPasswordUser("Enter a valid password.");
            }
        } else {
            createIdView.failedUsername("Enter valid username.");
        }
    }

}
