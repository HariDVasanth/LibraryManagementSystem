package com.zsgs.librarymanagement;

import com.zsgs.librarymanagement.librarydatabase.LibraryDataManagement;
import com.zsgs.librarymanagement.login.LoginView;

public class LibraryManagement2024 {
    private static LibraryManagement2024 libraryManagement2024;
    private String libraryName = "Library Management App";
    private String libraryAppVersion = "0.0.1";

    public static LibraryManagement2024 getInstance() {
        if (libraryManagement2024 == null) {
            libraryManagement2024 = new LibraryManagement2024();
        }
        return libraryManagement2024;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public String getLibraryAppVersion() {
        return libraryAppVersion;
    }

    public void appInit() {
        LoginView loginView = new LoginView();
        loginView.init();
    }

    public static void main(String[] args) {
          LibraryDataManagement.getInstance().defaultPassword();
          LibraryDataManagement.getInstance().deserializeAdminList();
          LibraryDataManagement.getInstance().deserializeBookDetails();
          LibraryDataManagement.getInstance().deserializedUserDetails();
          LibraryDataManagement.getInstance().deserializeLendedBookDetails();
          LibraryManagement2024.getInstance().appInit();
    }
}
