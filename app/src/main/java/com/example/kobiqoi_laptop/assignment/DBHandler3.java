package com.example.kobiqoi_laptop.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler3 extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "ordersManager";
        private static final String TABLE_ORDERS = "orders";
        private static final String KEY_ID = "id";
        private static final String KEY_NAME = "name";
        private static final String KEY_EXTRA = "extra";
        private static final String KEY_AMOUNT = "amount";
        private static final String KEY_NOTE = "note";
        private static final String KEY_PRICE = "price";
        private static final String KEY_COST = "cost";
        private static final String KEY_TABLEID = "tableid";

        public DBHandler3(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        // Creating Table
        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_ORDERS_TABLE = "CREATE TABLE " + TABLE_ORDERS + "("
                    + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                    + KEY_EXTRA + " TEXT," + KEY_AMOUNT + " TEXT," + KEY_NOTE + " TEXT," + KEY_PRICE + "TEXT,"
                    + KEY_COST + "TEXT," + KEY_TABLEID + "TEXT" + ")";
            db.execSQL(CREATE_ORDERS_TABLE);
        }



        // Upgrading database
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
            // Create table again
            onCreate(db);
        }
    // Order(String name, String extra, String amount, String note, String price, String cost, String tableid)
        // Adding new student
        void addOrder(Order order) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, order.getName()); // Contact Name
            values.put(KEY_EXTRA, order.getExtra()); // Contact Username
            values.put(KEY_AMOUNT, order.getAmount()); // Contact Email
            values.put(KEY_NOTE, order.getNote()); // Contact Password
            values.put(KEY_PRICE, order.getPrice()); // Contact Password
            values.put(KEY_COST, order.getCost()); // Contact Password
            values.put(KEY_TABLEID, order.getTableid()); // Contact Password
            // Inserting Row
            db.insert(TABLE_ORDERS, null, values);
            //2nd argument is String containing nullColumnHack
            db.close(); // Closing database connection
        }

        // Getting single account
        Order getOrder(int id) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_ORDERS, new String[]{KEY_ID,
                            KEY_NAME, KEY_EXTRA, KEY_AMOUNT, KEY_NOTE, KEY_PRICE, KEY_COST, KEY_TABLEID}, KEY_ID + "=?",
                    new String[]{String.valueOf(id)}, null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();
            Order order = new Order(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6),
                    cursor.getString(7));
            // return account
            return order;
        }

        // Getting All Contacts
        public List<Order> getAllOrders() {
            List<Order> orderList = new ArrayList<Order>();
            // Select All Query
            String selectQuery = "SELECT * FROM " + TABLE_ORDERS;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Order order = new Order();
                    order.setID(Integer.parseInt(cursor.getString(0)));
                    order.setName(cursor.getString(1));
                    order.setExtra(cursor.getString(2));
                    order.setAmount(cursor.getString(3));
                    order.setNote(cursor.getString(4));
                    order.setPrice(cursor.getString(5));
                    order.setCost(cursor.getString(6));
                    order.setTableid(cursor.getString(7));
                    // Adding student to list
                    orderList.add(order);
                } while (cursor.moveToNext());
            }
            // return student list
            return orderList;
        }

        // Updating single student
        public int updateOrder(Order order) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, order.getName());
            values.put(KEY_EXTRA, order.getExtra());
            values.put(KEY_AMOUNT, order.getAmount());
            values.put(KEY_NOTE, order.getNote());
            values.put(KEY_PRICE, order.getPrice());
            values.put(KEY_COST, order.getCost());
            values.put(KEY_TABLEID, order.getTableid());
            // updating row
            return db.update(TABLE_ORDERS, values, KEY_ID + " = ?",
                    new String[]{String.valueOf(order.getID())});
        }

        // Deleting single student
        public void deleteOrder(Order order) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_ORDERS, KEY_ID + " = ?",
                    new String[]{String.valueOf(order.getID())});
            db.close();
        }

        // Getting student Count
        public int getOrdersCount() {
            String countQuery = "SELECT * FROM " + TABLE_ORDERS;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            cursor.close();
            // return count
            return cursor.getCount();
        }
}

