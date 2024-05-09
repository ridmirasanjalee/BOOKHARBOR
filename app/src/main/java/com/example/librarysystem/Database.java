package com.example.librarysystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "librarydb";
    private static final int DATABASE_VERSION = 1;


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create 'users' table with an '_id' primary key
        String createUserTableQuery = "CREATE TABLE users (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +  // Primary key column
                "username TEXT, " +
                "email TEXT, " +
                "password TEXT)";
        db.execSQL(createUserTableQuery);

        // Create 'Book' table with an '_id' primary key
        String createBookTableQuery = "CREATE TABLE Book (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +  // Primary key column
                "BOOK_ID VARCHAR(13), " +
                "TITLE VARCHAR(30), " +
                "PUBLISHER_NAME VARCHAR(20))";
        db.execSQL(createBookTableQuery);

        // Create 'Book' table with an '_id' primary key
        String createPublisherTableQuery = "CREATE TABLE Publisher (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +  // Primary key column
                "PUBLISHER_ID VARCHAR(13), " +
                "PUBLISHER_NAME VARCHAR(30), " +
                "ADDRESS VARCHAR(20),"+"PHONE_NUMBER VARCHAR(10))";
        db.execSQL(createPublisherTableQuery);

        // Create 'Book' table with an '_id' primary key
        String createBranchTableQuery = "CREATE TABLE Branch (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +  // Primary key column
                "BRANCH_ID VARCHAR(13), " +
                "ADDRESS VARCHAR(30), " +
                "BRANCH_NAME VARCHAR(20))";
        db.execSQL(createBranchTableQuery);

        String createMemberTableQuery = "CREATE TABLE Member (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +  // Primary key column
                "MEMBER_ID VARCHAR(13), " +
                "MEMBER_NAME VARCHAR(30), " +
                "MEMBER_ADDRESS VARCHAR(20),"+"MEMBER_PHONE_NUMBER VARCHAR(10)," +
                "NO_PAID_DUES NUMBER(5,2))";
        db.execSQL(createMemberTableQuery);

        String createAuthorTableQuery = "CREATE TABLE Author (" +
                "BOOK_ID VARCHAR(13)," +
                "AUTHOR_NAME VARCHAR(20)," +
                "PRIMARY KEY(BOOK_ID, AUTHOR_NAME)," +
                "FOREIGN KEY(BOOK_ID) REFERENCES Book(BOOK_ID))";
        db.execSQL(createAuthorTableQuery);

        String createBook_CopyTableQuery="CREATE TABLE Book_Copy("+
                "BOOK_ID VARCHAR(13),"+
                "BRANCH_ID VARCHAR(5),"+
                "ACCESS_NO VARCHAR(5),"+
                "PRIMARY KEY(ACCESS_NO, BRANCH_ID),"+
                "FOREIGN KEY(BOOK_ID) REFERENCES Book,"+
                "FOREIGN KEY(BRANCH_ID) REFERENCES Branch)";
        db.execSQL(createBook_CopyTableQuery);

        String createBook_LoanTableQuery="CREATE TABLE Book_Loan("+
                "ACCESS_NO VARCHAR(13),"+
                "BRANCH_ID VARCHAR(5),"+
                "MEMBER_ID VARCHAR(5),"+
                "DATE_OUT DATE,"+
                "DATE_DUE DATE,"+
                "DATE_RETURNED DATE,"+
                "PRIMARY KEY(ACCESS_NO, BRANCH_ID, MEMBER_ID, DATE_OUT),"+
                "FOREIGN KEY(ACCESS_NO, BRANCH_ID) REFERENCES Book_Copy,"+
                "FOREIGN KEY(MEMBER_ID) REFERENCES Member,"+
                "FOREIGN KEY(BRANCH_ID) REFERENCES Branch)";
        db.execSQL(createBook_LoanTableQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop and recreate tables during upgrade (only if no migration strategy is needed)
        db.execSQL("DROP TABLE IF EXISTS Book");
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS Branch");
        db.execSQL("DROP TABLE IF EXISTS Publisher");
        db.execSQL("DROP TABLE IF EXISTS Member");
        onCreate(db);  // Re-create the database with updated schema
    }

    public void register(String username, String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);

        SQLiteDatabase db = this.getWritableDatabase();  // Get writable database instance
        db.insert("users", null, cv);
        db.close();  // Close database after use
    }

    public int login(String username, String password) {
        int result = 0;
        SQLiteDatabase db = this.getReadableDatabase();  // Get readable database instance
        Cursor c = db.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?",
                new String[]{username, password});

        if (c.moveToFirst()) {
            result = 1;  // Login successful
        }

        c.close();  // Close cursor to avoid memory leaks
        db.close();  // Close database
        return result;  // Return login result
    }

    public long addBook(String bookId, String title, String publisherName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("BOOK_ID", bookId);
        values.put("TITLE", title);
        values.put("PUBLISHER_NAME", publisherName);

        long result;
        try {
            result = db.insert("Book", null, values);  // Try to insert new book record
        } catch (Exception e) {
            result = -1;  // If insertion fails
        }

        db.close();  // Close database after use
        return result;  // Return result of insertion
    }

    public int updateBook(String bookId, String title, String publisherName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TITLE", title);
        values.put("PUBLISHER_NAME", publisherName);

        int result = db.update("Book", values, "BOOK_ID = ?", new String[]{bookId});
        db.close();  // Close database after updating
        return result;  // Return update result
    }

    public int removeBook(String bookId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("Book", "BOOK_ID = ?", new String[]{bookId});
        db.close();  // Close database after deletion
        return result;  // Return deletion result
    }

    public Cursor getBookDetails() {
        SQLiteDatabase db = this.getReadableDatabase();  // Get readable database instance
        return db.rawQuery("SELECT * FROM Book", null);  // Return all book details
    }

    public long addPublisher(String publisherId, String address, String publisherName,String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("PUBLISHER_ID", publisherId);
        values.put("ADDRESS", address);
        values.put("PUBLISHER_NAME", publisherName);
        values.put("PHONE_NUMBER",phonenumber);

        long result;
        try {
            result = db.insert("Publisher", null, values);  // Try to insert new book record
        } catch (Exception e) {
            result = -1;  // If insertion fails
        }

        db.close();  // Close database after use
        return result;  // Return result of insertion
    }

    public int updatePublisher(String publisherId, String address, String publisherName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ADDRESS",address);
        values.put("PUBLISHER_NAME", publisherName);

        int result = db.update("Publisher", values, "PUBLISHER_ID = ?", new String[]{publisherId});
        db.close();  // Close database after updating
        return result;  // Return update result
    }

    public int removePublisher(String bookId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("Publisher", "PUBLISHER_ID = ?", new String[]{bookId});
        db.close();  // Close database after deletion
        return result;  // Return deletion result
    }

    public Cursor getPublisherDetails() {
        SQLiteDatabase db = this.getReadableDatabase();  // Get readable database instance
        return db.rawQuery("SELECT * FROM Publisher", null);  // Return all book details
    }
    public long addBranch(String branchId, String address, String branchName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("BRANCH_ID", branchId);
        values.put("ADDRESS", address);
        values.put("BRANCH_NAME", branchName);

        long result;
        try {
            result = db.insert("Branch", null, values);  // Try to insert new book record
        } catch (Exception e) {
            result = -1;  // If insertion fails
        }

        db.close();  // Close database after use
        return result;  // Return result of insertion
    }

    public int updateBranch(String branchId, String address, String branchName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ADDRESS", address);
        values.put("BRANCH_NAME", branchName);

        int result = db.update("Branch", values, "BRANCH_ID = ?", new String[]{branchId});
        db.close();  // Close database after updating
        return result;  // Return update result
    }

    public int removeBranch(String branchId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("Branch", "BRANCH_ID = ?", new String[]{branchId});
        db.close();  // Close database after deletion
        return result;  // Return deletion result
    }

    public Cursor getBranchDetails() {
        SQLiteDatabase db = this.getReadableDatabase();  // Get readable database instance
        return db.rawQuery("SELECT * FROM Branch", null);  // Return all book details
    }
    public long addMember(String memberId, String memberaddress, String membername,String memberphonenumber,String nodues) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MEMBER_ID", memberId);
        values.put("MEMBER_ADDRESS", memberaddress);
        values.put("MEMBER_NAME", membername);
        values.put("MEMBER_PHONE_NUMBER",memberphonenumber);
        values.put("NO_PAID_DUES",nodues);


        long result;
        try {
            result = db.insert("Member", null, values);  // Try to insert new book record
        } catch (Exception e) {
            result = -1;  // If insertion fails
        }

        db.close();  // Close database after use
        return result;  // Return result of insertion
    }

    public int updateMember(String memberId, String memberaddress, String membername,String memberphonenumber,String nodues) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MEMBER_ADDRESS", memberaddress);
        values.put("MEMBER_NAME", membername);
        values.put("MEMBER_PHONE_NUMBER", membername);
        values.put("NO_PAID_DUES", nodues);


        int result = db.update("Member", values, "MEMBER_ID = ?", new String[]{memberId});
        db.close();  // Close database after updating
        return result;  // Return update result
    }

    public int removeMember(String memberId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("Member", "MEMBER_ID = ?", new String[]{memberId});
        db.close();  // Close database after deletion
        return result;  // Return deletion result
    }

    public Cursor getMemberDetails() {
        SQLiteDatabase db = this.getReadableDatabase();  // Get readable database instance
        return db.rawQuery("SELECT * FROM Member", null);  // Return all book details
    }

    public long addAuthor(String bookId, String authorname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("BOOK_ID", bookId);
        values.put("AUTHOR_NAME", authorname);

        long result;
        try {
            result = db.insert("Author", null, values);  // Try to insert new book record
        } catch (Exception e) {
            result = -1;  // If insertion fails
        }

        db.close();  // Close database after use
        return result;  // Return result of insertion
    }

    public int updateAuthor(String bookId, String authorname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("AUTHOR_NAME", authorname);
        values.put("BOOK_ID",bookId);

        int result = db.update("Author", values, "BOOK_ID = ? AND AUTHOR_NAME=? ", new String[]{bookId,authorname});
        db.close();  // Close database after updating
        return result;  // Return update result
    }

    public int removeAuthor(String bookId,String authorname) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("Author", "BOOK_ID = ? AND AUTHOR_NAME=?", new String[]{bookId,authorname});
        db.close();  // Close database after deletion
        return result;  // Return deletion result
    }

    public Cursor getAuthorDetails() {
        SQLiteDatabase db = this.getReadableDatabase();  // Get readable database instance
        return db.rawQuery("SELECT * FROM Author", null);  // Return all book details
    }
    public long addBookcopy(String bookId, String branchId, String accessNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("BOOK_ID", bookId);
        values.put("BRANCH_ID", branchId);
        values.put("ACCESS_NO", accessNo);

        long result;
        try {
            result = db.insert("Book_Copy", null, values);  // Try to insert new book record
        } catch (Exception e) {
            result = -1;  // If insertion fails
        }

        db.close();  // Close database after use
        return result;  // Return result of insertion
    }

    public int updateBookcopy(String bookId, String branchId, String accessNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("BOOK_ID", accessNo);
        values.put("BRANCH_ID",branchId);
        values.put("ACCESS_NO", accessNo);

        int result = db.update("Book_Copy", values, "BRANCH_ID = ? AND ACCESS_NO" , new String[]{bookId});
        db.close();  // Close database after updating
        return result;  // Return update result
    }

    public int removeBookcopy(String branchId,String accessNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("Book_Copy", "BRANCH_ID = ? AND ACCESS_NO", new String[]{branchId,accessNo});
        db.close();  // Close database after deletion
        return result;  // Return deletion result
    }

    public Cursor getBookcopyDetails() {
        SQLiteDatabase db = this.getReadableDatabase();  // Get readable database instance
        return db.rawQuery("SELECT * FROM Book_Copy", null);  // Return all book details
    }

    public long addBookloan(String accessNo, String branchId, String memberId,String datedue,String dateout,String datereturned) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ACCESS_NO", accessNo);
        values.put("BRANCH_ID", branchId);
        values.put("MEMBER_ID", memberId);
        values.put("DATE_DUE", datedue);
        values.put("DATE_OUT", dateout);
        values.put("DATE_RETURNED", datereturned);

        long result;
        try {
            result = db.insert("Book_Loan", null, values);  // Try to insert new book record
        } catch (Exception e) {
            result = -1;  // If insertion fails
        }

        db.close();  // Close database after use
        return result;  // Return result of insertion
    }

    public int updateBookloan(String accessNo,String branchId, String memberId,String datedue,String dateout,String datereturned) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ACCESS_NO", accessNo);
        values.put("BRANCH_ID", branchId);
        values.put("MEMBER_ID", memberId);
        values.put("DATE_DUE", datedue);
        values.put("DATE_OUT", dateout);
        values.put("DATE_RETURNED", datereturned);

        int result = db.update("Book_Loan", values, "ACCESS_NO = ? AND BRANCH_ID =? AND MEMBER_ID =? AND DATE_OUT ", new String[]{accessNo,branchId,memberId,dateout});
        db.close();  // Close database after updating
        return result;  // Return update result
    }

    public int removeBookloan(String accessNo,String branchId,String memberId,String dateout) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("Book_Loan", "ACCESS_NO = ? AND BRANCH_ID =? AND MEMBER_ID =? AND DATE_OUT ", new String[]{accessNo,branchId,memberId,dateout});
        db.close();  // Close database after deletion
        return result;  // Return deletion result
    }

    public Cursor getBookloanDetails() {
        SQLiteDatabase db = this.getReadableDatabase();  // Get readable database instance
        return db.rawQuery("SELECT * FROM Book_Loan", null);  // Return all book details
    }




}
