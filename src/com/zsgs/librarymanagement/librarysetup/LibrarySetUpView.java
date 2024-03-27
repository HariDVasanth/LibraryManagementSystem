package com.zsgs.librarymanagement.librarysetup;

import com.zsgs.librarymanagement.admin.AdminView;
import com.zsgs.librarymanagement.managebooks.ManageBookView;
import com.zsgs.librarymanagement.model.Library;

import java.util.Scanner;

public class LibrarySetUpView {
    private LibrarySetUpModel librarySetUpModel;
    Scanner scanner = new Scanner(System.in);

    public LibrarySetUpView() {
        librarySetUpModel = new LibrarySetUpModel(this);
    }

    public void librarySetUpInit() {
        System.out.println("Welcome to your library setup.");
        isLibrarySetUp();
    }

    public void isLibrarySetUp() {
        librarySetUpModel.isLibraryNull();
    }

    public void libraryDetails() {
        System.out.println("Enter library name");
        String libraryName = scanner.nextLine();
        System.out.println("Enter library phone number");
        String phoneNo = scanner.next();
        scanner.nextLine();
        System.out.println("Enter library email id");
        String emailId = scanner.nextLine();
        System.out.println("Library incharge");
        String libraryIncharge = scanner.nextLine();
        Library library = new Library();
        librarySetUpModel.setLibrary(libraryName, phoneNo, emailId, libraryIncharge, library);
    }

    public void detailsUpdated(String message) {
        System.out.println(message);
        AdminView adminView = new AdminView();
        adminView.adminMenu();
    }

    public void invalidMail(String message) {
        System.out.println(message);
        libraryDetails();
    }

    public void invalidPhoneNumber(String message) {
        System.out.println(message);
        libraryDetails();
    }
}
