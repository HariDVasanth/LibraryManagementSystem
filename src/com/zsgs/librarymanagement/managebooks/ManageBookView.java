package com.zsgs.librarymanagement.managebooks;
import com.zsgs.librarymanagement.librarydatabase.LibraryDataManagement;
import com.zsgs.librarymanagement.model.Books;

import java.util.Scanner;

public class ManageBookView {
    ManageBookModel manageBookModel;
    Scanner scanner = new Scanner(System.in);

    public ManageBookView() {
        manageBookModel = new ManageBookModel(this);
    }

    public void manageBookInit() {
        System.out.println("Welcome to book management.");
    }

    public void bookOptions() {
        System.out.println("1.Add Books \n2.View Books \n 3. Search \n 4. Remove books \n 5. Go back to main menu");
        System.out.println("Enter your option");
        int options = scanner.nextInt();
        switch (options) {
            case 1:
                addBooks();
                break;
            case 2:
                viewBooks();
            case 3:
                searchBook();
                break;
            case 4:
                removeBooks();
            case 5:

        }
    }

    public void addBooks() {
        System.out.println("Enter the number of books you want to add?");
        int totalBooks = scanner.nextInt();
        scanner.nextLine();
        manageBookModel.getLibraryBooks(totalBooks);
    }

    public void bookDetails() {
        System.out.print("Enter book name: ");
        String bookName = scanner.nextLine();
        System.out.print("Enter book author name: ");
        String bookAuthor = scanner.nextLine();
        System.out.print("Enter book genre: ");
        String bookGenre = scanner.nextLine();
        System.out.print("Enter book edition: ");
        String bookEdition = scanner.nextLine();
        System.out.println("Enter total copies of " + bookName + " books available.");
        int bookCount = scanner.nextInt();
        scanner.nextLine();
        Books books = new Books();
        manageBookModel.addLibraryBooks(bookName, bookAuthor, bookGenre, bookEdition, books, bookCount);
    }

    public void afterBooksAdding(Books books) {
        System.out.println("Books added successfully");
        System.out.println(books);
        LibraryDataManagement.getInstance().serializeBookDetails();
    }

    public void viewBooks() {
        System.out.println(LibraryDataManagement.getInstance().getLibraryBooks());
    }

    public void searchBook() {
        System.out.println("Enter the book you want to find: ");
        String bookName = scanner.nextLine();

    }

    public void removeBooks() {
        System.out.print("Enter the Book number you want to remove: ");
        int bookNumber = scanner.nextInt();

    }
}