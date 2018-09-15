package com.example.kobiqoi_laptop.assignment;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "accountsManager";
    private static final String TABLE_ACCOUNTS = "accounts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_USERNAME = "user_name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // Creating Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + TABLE_ACCOUNTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_USERNAME + " TEXT," + KEY_EMAIL + " TEXT," + KEY_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_ACCOUNTS_TABLE);
    }
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
        // Create table again
        onCreate(db);
    }
    // Adding new student
    void addAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, account.getName()); // Contact Name
        values.put(KEY_USERNAME, account.getUsername()); // Contact Username
        values.put(KEY_EMAIL, account.getEmail()); // Contact Email
        values.put(KEY_PASSWORD, account.getPassword()); // Contact Password
        // Inserting Row
        db.insert(TABLE_ACCOUNTS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
    // Getting single account
    Account getAccount(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ACCOUNTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_USERNAME, KEY_EMAIL, KEY_PASSWORD }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Account account = new Account(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4));
        // return account
        return account;
    }
    // Getting All Contacts
    public List<Account> getAllAccounts() {
        List<Account> accountList = new ArrayList<Account>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_ACCOUNTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Account account = new Account();
                account.setID(Integer.parseInt(cursor.getString(0)));
                account.setName(cursor.getString(1));
                account.setUsername(cursor.getString(2));
                account.setEmail(cursor.getString(3));
                account.setPassword(cursor.getString(4));
                // Adding student to list
                accountList.add(account);
            } while (cursor.moveToNext());
        }
        // return student list
        return accountList;
    }
    // Updating single student
    public int updateAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, account.getName());
        values.put(KEY_USERNAME, account.getUsername());
        values.put(KEY_EMAIL, account.getEmail());
        values.put(KEY_PASSWORD, account.getPassword());
        // updating row
        return db.update(TABLE_ACCOUNTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(account.getID()) });
    }
    // Deleting single student
    public void deleteAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ACCOUNTS, KEY_ID + " = ?",
                new String[] { String.valueOf(account.getID()) });
        db.close();
    }
    // Getting student Count
    public int getAccountsCount() {
        String countQuery = "SELECT * FROM " + TABLE_ACCOUNTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }
}
