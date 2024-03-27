package com.zsgs.librarymanagement.managebooks;

import com.zsgs.librarymanagement.admin.AdminView;
import com.zsgs.librarymanagement.librarydatabase.LibraryDataManagement;
import com.zsgs.librarymanagement.mainmenu.MainMenuView;
import com.zsgs.librarymanagement.model.Admin;
import com.zsgs.librarymanagement.model.Books;

public class ManageBookModel {
    private ManageBookView manageBookView;
    public ManageBookModel(){

    }

    public ManageBookModel(ManageBookView manageBookView) {
        this.manageBookView = manageBookView;
    }

    public void getLibraryBooks(int totalBooks) {
        for (int i = 0; i < totalBooks; i++) {
            manageBookView.bookDetails();
        }
        MainMenuView mainMenuView = new MainMenuView();
        mainMenuView.mainMenuInit();

    }

    public void addLibraryBooks(String bookName, String bookAuthor, String bookGenre, String bookEdition, Books books, int bookCount) {
        books.setBookName(bookName);
        books.setBookAuthor(bookAuthor);
        books.setBookGenre(bookGenre);
        books.setBookEdition(bookEdition);
        books.setBookCount(bookCount);
        LibraryDataManagement.getInstance().setLibraryBooks(books);
        manageBookView.afterBooksAdding(books);
    }
    public void searchBooks(String bookname, int userId) {
        AdminView adminView=new AdminView();
        if (LibraryDataManagement.getInstance().searchBook(bookname) && userId == 1) {
            adminView.bookFound("Book found.");
        } else if (LibraryDataManagement.getInstance().searchBook(bookname) && userId >= 1000) {
            adminView.lendingBookEntry(bookname, userId);
        } else {
            adminView.bookNotAvailable("Book not available.");
        }
    }
}
