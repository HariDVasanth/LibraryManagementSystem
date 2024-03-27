package com.zsgs.librarymanagement.librarysetup;

import com.zsgs.librarymanagement.librarydatabase.LibraryDataManagement;
import com.zsgs.librarymanagement.model.Library;
import com.zsgs.librarymanagement.validator.Validator;

public class LibrarySetUpModel {
    private LibrarySetUpView librarySetUpView;
    private Validator validator;
    private Library library;

    public LibrarySetUpModel(LibrarySetUpView librarySetUpView) {
        this.librarySetUpView = librarySetUpView;
        validator = new Validator();
    }

    public void isLibraryNull() {
        if (library == null) {
            librarySetUpView.libraryDetails();
        }
    }

    public void setLibrary(String libraryName, String phoneNo, String emailId, String libraryIncharge, Library library1) {
        if (validator.emailVerifier(emailId)) {
            if (validator.numberVerifier(phoneNo)) {
                this.library = library1;
                this.library.setLibraryName(libraryName);
                this.library.setLibraryPhoneNo(phoneNo);
                this.library.setLibraryEmailId(emailId);
                this.library.setLibraryIncharge(libraryIncharge);
                LibraryDataManagement.getInstance().setUpLibrary(library);
                librarySetUpView.detailsUpdated("Library set up successful.");
            } else {
                librarySetUpView.invalidMail("Enter a valid email id");
            }
        } else {
            librarySetUpView.invalidPhoneNumber("Enter a valid phone number");
        }
    }
}
