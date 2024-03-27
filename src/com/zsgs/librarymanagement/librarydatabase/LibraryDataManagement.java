package com.zsgs.librarymanagement.librarydatabase;
import com.google.gson.reflect.TypeToken;
import com.zsgs.librarymanagement.admin.AdminModel;
import com.zsgs.librarymanagement.model.*;
import com.zsgs.librarymanagement.serializer.JsonSerialize;
import com.zsgs.librarymanagement.users.UsersModel;
import com.zsgs.librarymanagement.model.BookReview;
import com.zsgs.librarymanagement.users.UsersView;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class LibraryDataManagement {
    private Library library;
    private List<Admin> adminLoginDetails = new ArrayList<>();

    private static LibraryDataManagement libraryDataManagement;

    private ArrayList<Books> libraryBooks = new ArrayList<>();
    private HashMap<Integer, Users> userDetails = new HashMap<>();
    private HashMap<Integer, LendedUserDetails> lendedBookDetails = new HashMap<>();
    private ArrayList<UserSuggestion>userSuggestionArrayList=new ArrayList<>();
    private ArrayList<UserSuggestion>userSuggestions=new ArrayList<>();
    private ArrayList <BookReview> bookReviewArrayList=new ArrayList<>();
    private static int userId = 1000;


    public static LibraryDataManagement getInstance() {
        if (libraryDataManagement == null) {
            libraryDataManagement = new LibraryDataManagement();
        }
        return libraryDataManagement;
    }

    public boolean adminUsername(String username) {
        for (Admin admin1 : adminLoginDetails) {
            if (admin1.getAdminUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean adminPassword(String username, String password) {
        for (Admin admin1 : adminLoginDetails) {
            if (admin1.getAdminUsername().equals(username) && admin1.getAdminPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void setAdminLoginDetails(Admin adminLoginDetails) {
        this.adminLoginDetails.add(adminLoginDetails);
    }

    public void defaultPassword() {
        Admin admin = new Admin();
        admin.setAdminUsername("ADMIN");
        admin.setAdminPassword("ADMIN");
        setAdminLoginDetails(admin);
    }

    public void removeDefault() {
        adminLoginDetails.removeAll(adminLoginDetails);
    }

    public HashMap<Integer, Users> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(Integer userId, Users users) {
        userDetails.put(userId, users);
    }

    public boolean userPassword(int userId, String password) {
        for (Map.Entry<Integer, Users> entry : LibraryDataManagement.getInstance().userDetails.entrySet()) {
            if (entry.getKey().equals(userId)) {
                return entry.getValue().getPassword().equals(password);
            }
        }
        return false;
    }

    public Library getLibrary() {
        return library;
    }

    public void setUpLibrary(Library library1) {
        this.library = library1;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId() {
        userId += 1;
    }

    public List<Admin> getAdminLoginDetails() {
        return adminLoginDetails;
    }

    public void setLibraryBooks(Books books) {
        libraryBooks.add(books);
    }

    public ArrayList<Books> getLibraryBooks() {
        return libraryBooks;
    }

    public boolean searchBook(String bookname) {
        for (Books libraryBook : libraryBooks) {
            if (libraryBook.getBookName().equals(bookname)) {
                return true;
            }
        }
        return false;
    }

    public void removeBooks(int userId, String bookName, String issueDate, String returnDate) {  //Remove books when users lends from the library

        for (Books libraryBook : libraryBooks) {
            if (libraryBook.getBookName().equals(bookName) && libraryBook.getBookCount() > 0) {
                if(userValidation(userId,bookName) ) {
                    libraryBook.setBookCount(libraryBook.getBookCount() - 1);
                    String book = libraryBook.getBookName();
                    addToBorrowed(userId, book, issueDate, returnDate);
                }
            } else {
                AdminModel adminModel = new AdminModel();
                adminModel.bookNotAvailable("Requested book not available. ");
            }
        }
    }

    public boolean userValidation(int userId,String bookName) {
        AdminModel adminModel = new AdminModel();
        for (Map.Entry<Integer, Users> entry : userDetails.entrySet()) {
            int key = entry.getKey();
            if (userId == key) {
                Users value = entry.getValue();
                if (value.getBorrowedBooks().get(userId-1000).getBookCount()>0) {
                    if (value.getBorrowedBooks().get(userId-1000).getUserFine()==0) {
                        return true;
                    } else {
                        adminModel.bookFinePresent("User has a fine amount of" + value.getBorrowedBooks().get(userId-1000) + " please pay the money to borrow new ");
                    }
                } else {
                    adminModel.limitExceeding("User already have borrowed 5 book return any one of the book to borrow a new book.");
                }
            }
        }

        return false;
    }

    public void addToBorrowed(int userId, String bookname, String issueDate, String returnDate) {
        for (Map.Entry<Integer, Users> entry : userDetails.entrySet()) {
            int key = entry.getKey();
            if (key == userId) {
                Users users = new Users();
                LendedUserDetails lendedUserDetails = new LendedUserDetails();
                Users value = entry.getValue();


                lendedUserDetails.setBookname(bookname);
                lendedUserDetails.setBookIssueDate(issueDate);
                lendedUserDetails.setBookReturnDate(returnDate);
                lendedUserDetails.setHasReturned("Not returned");
                lendedUserDetails.setBookCount();
                value.setBorrowedBooks(lendedUserDetails);
                lendedBookDetails.put(userId, lendedUserDetails);
                serializeUserDetails();
                AdminModel adminModel = new AdminModel();
                adminModel.afterLending("Book borrowing successful. ");
                serializeLendedBookDetails();

            }
        }
    }

    public void userBorrowedBooks(int userId) {
        UsersModel usersModel = new UsersModel();
        for (Map.Entry<Integer, Users> entry : userDetails.entrySet()) {
            int key = entry.getKey();
            if (key == userId) {
                Users value = entry.getValue();
                usersModel.userBorrowedBooks(value.getBorrowedBooks());
            }
        }
    }

    public void addFavouriteBooks(String bookname, int userId) {
        UsersModel usersModel = new UsersModel();
        if (searchBook(bookname)) {
            for (Map.Entry<Integer, Users> entry : userDetails.entrySet()) {
                int key = entry.getKey();
                if (key == userId) {
                    Users value = entry.getValue();
                    value.setFavouriteBooks(bookname);
                    usersModel.favouriteBookAdded("Favourite book added successfully");
                }
            }
        } else {
            usersModel.favouriteBookNotFound("Book not present in the library.");
        }
    }

    public void viewUserFavoriteBooks(int userId) {
        UsersModel usersModel = new UsersModel();
        for (Map.Entry<Integer, Users> entry : userDetails.entrySet()) {
            int key = entry.getKey();
            if (key == userId) {
                Users value = entry.getValue();
                usersModel.viewFavoriteBooks(value.getFavouriteBooks());
            }
        }
    }

    public HashMap<Integer, LendedUserDetails> getLendedBookDetails() {
        return lendedBookDetails;
    }

    public void returnBook(int userId, String bookname, String returningDate) {
        for (Map.Entry<Integer, LendedUserDetails> entry : lendedBookDetails.entrySet()) {
            int key = entry.getKey();
            if (key == userId) {
                LendedUserDetails value = entry.getValue();
                String returnDate = value.getBookReturnDate();
                int fine = daysCalculator(returnDate, returningDate);
                returnedBookAdd(bookname);
                value.setBookReturningDate(returningDate);
                value.setUserFine(Math.abs(fine) * 5);
                value.setHasReturned("Returned");
                value.returnBookCount(0);
                AdminModel adminModel = new AdminModel();
                if (fine < 0) {
                    adminModel.bookFineAmount(Math.abs(fine) * 5);
                } else {
                    adminModel.noFineAmount();
                }
            }
        }
    }

    public void returnedBookAdd(String bookname) {
        for (Books value : libraryBooks) {
            if (value.getBookName().equals(bookname)) {
                value.setBookCount(value.getBookCount() + 1);
            }
        }
    }

    public int daysCalculator(String returnDate, String returningDate1) {
        LocalDate borrowedDate = LocalDate.parse(returnDate);
        LocalDate returningDate = LocalDate.parse(returningDate1);
        long difference = ChronoUnit.DAYS.between(returningDate, borrowedDate);
        return (int) difference;
    }


    public ArrayList<UserSuggestion> getUserSuggestions() {
        return userSuggestions;
    }

    public void setUserSuggestions(UserSuggestion userSuggestions) {
        userSuggestionArrayList.add(userSuggestions);
        serializeUserSuggestion();
    }

    public ArrayList<BookReview> getBookReviewArrayList() {
        return bookReviewArrayList;
    }

    public void setBookReviewArrayList(BookReview bookReviewArrayList) {
        this.bookReviewArrayList.add(bookReviewArrayList);
        serializeBookReview();
    }
    public void bookReview(String bookName){
        UsersModel usersModel=new UsersModel();
        for(BookReview book:bookReviewArrayList){
            if(book.getBookname().contains(bookName)){
                usersModel.bookReview(book);
            }
        }
    }

    public void serializeAdminLoginDetails(){
        System.out.println("Serialized called admin");
        JsonSerialize.getJsonSerialize().serialize(getAdminLoginDetails(),"C:\\Users\\surya\\IdeaProjects\\Projects\\src\\com\\zsgs\\librarymanagement\\data\\adminLoginDetails.json");
      }
    public void serializeBookDetails(){
        System.out.println("Serialized added books");
        JsonSerialize.getJsonSerialize().serialize(getLibraryBooks(),"C:\\Users\\surya\\IdeaProjects\\Projects\\src\\com\\zsgs\\librarymanagement\\data\\BookDetails.json");
    }
    public void serializeUserDetails(){
        System.out.println("Serialized user details added");
        JsonSerialize.getJsonSerialize().serialize(getUserDetails(),"C:\\Users\\surya\\IdeaProjects\\Projects\\src\\com\\zsgs\\librarymanagement\\data\\UserDetails.json");
    }
    public void serializeLendedBookDetails(){
        System.out.println("Borrowed books added");
        JsonSerialize.getJsonSerialize().serialize(getLendedBookDetails(),"C:\\Users\\surya\\IdeaProjects\\Projects\\src\\com\\zsgs\\librarymanagement\\data\\LendedBooks.json");
    }
    public void serializeUserSuggestion(){
        System.out.println("Suggestions added");
        JsonSerialize.getJsonSerialize().serialize(getUserSuggestions(),"C:\\Users\\surya\\IdeaProjects\\Projects\\src\\com\\zsgs\\librarymanagement\\data\\UserSuggestions.json");
    }
    public void serializeBookReview(){
        System.out.println("Book review added");
        JsonSerialize.getJsonSerialize().serialize(getBookReviewArrayList(),"C:\\Users\\surya\\IdeaProjects\\Projects\\src\\com\\zsgs\\librarymanagement\\data\\BookReview.json");
    }
    public void deserializeLendedBookDetails() {
        JsonSerialize.getJsonSerialize(); // Initialize JsonSerialize instance
        this.lendedBookDetails = JsonSerialize.getJsonSerialize().deserialize("src/com/zsgs/librarymanagement/data/LendedBooks.json",new TypeToken<HashMap<Integer,LendedUserDetails>>(){});
    }


    public  void deserializeAdminList() {
        JsonSerialize.getJsonSerialize(); // Initialize JsonSerialize instance
        this.adminLoginDetails = JsonSerialize.getJsonSerialize().deserialize("C:\\Users\\surya\\IdeaProjects\\Projects\\src\\com\\zsgs\\librarymanagement\\data\\adminLoginDetails.json", new TypeToken<List<Admin>>(){});
    }

    public void deserializeBookDetails() {
        JsonSerialize.getJsonSerialize(); // Initialize JsonSerialize instance
        this.libraryBooks = JsonSerialize.getJsonSerialize().deserialize("C:\\Users\\surya\\IdeaProjects\\Projects\\src\\com\\zsgs\\librarymanagement\\data\\BookDetails.json",new TypeToken<ArrayList<Books>>(){});
    }

    public void deserializedUserDetails() {
        JsonSerialize.getJsonSerialize(); // Initialize JsonSerialize instance
        this.userDetails = JsonSerialize.getJsonSerialize().deserialize("C:\\Users\\surya\\IdeaProjects\\Projects\\src\\com\\zsgs\\librarymanagement\\data\\UserDetails.json",new TypeToken<HashMap<Integer,Users>>(){});
   }


}

