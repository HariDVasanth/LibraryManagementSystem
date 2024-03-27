package com.zsgs.librarymanagement.mainmenu;

import com.zsgs.librarymanagement.LibraryManagement2024;
import com.zsgs.librarymanagement.admin.AdminView;

import com.zsgs.librarymanagement.users.UsersView;

import java.util.Scanner;

public class MainMenuView {

    Scanner scanner = new Scanner(System.in);

    public void mainMenuInit() {
        popUpMenu();
    }

    public void popUpMenu() {
        System.out.println(LibraryManagement2024.getInstance().getLibraryName());
        System.out.println(LibraryManagement2024.getInstance().getLibraryAppVersion());

        System.out.println("------ Main Menu ------");
        System.out.println("1.Admin \n 2.User \n 3.Exit  ");
        int userOption = scanner.nextInt();
        switch (userOption) {
            case 1:
                admin();
                break;
            case 2:
                user();
                break;
            case 3:
                System.out.println("Exiting.......");
                return;
            default:
                System.out.println("Enter the given options");
        }
    }

    public void admin() {
        System.out.println("Welcome admin");
        AdminView adminView = new AdminView();
        adminView.adminInit();
    }

    public void user() {
        System.out.println("Welcome user.");
        UsersView usersView = new UsersView();
        usersView.userViewInit();
    }

}
