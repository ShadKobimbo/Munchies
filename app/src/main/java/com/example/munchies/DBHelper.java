package com.example.munchies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBHelper";

    private static final String DB_NAME = "munchies.db";
    private static final String TABLE_NAME = "users";
    private static final String COL1 = "email";
    private static final String COL2 = "passwrd";


    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase munchiesDB) {

        munchiesDB.execSQL("CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT, first_name TEXT, second_name TEXT, email VARCHAR(255), passwrd VARCHAR(255))");
        munchiesDB.execSQL("CREATE TABLE restaurants(id INTEGER PRIMARY KEY AUTOINCREMENT, restaurant_name VARCHAR(255), food_style TEXT, location VARCHAR(255), minimum_order INTEGER)");
        munchiesDB.execSQL("CREATE TABLE foods(id INTEGER PRIMARY KEY AUTOINCREMENT, food_name VARCHAR(255), food_style TEXT, restaurant_name VARCHAR(255), price INTEGER, description VARCHAR(255))");
        munchiesDB.execSQL("CREATE TABLE orders(id INTEGER PRIMARY KEY AUTOINCREMENT, food_name VARCHAR(255), restaurant_name VARCHAR(255), client_name VARCHAR(255), quantity INTEGER, food_cost INTEGER, delivery_address VARCHAR(255), delivery_cost INTEGER, total_cost INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase munchiesDB, int i, int i1) {
        munchiesDB.execSQL("DROP TABLE IF EXISTS users");
        munchiesDB.execSQL("DROP TABLE IF EXISTS restaurants");
        munchiesDB.execSQL("DROP TABLE IF EXISTS foods");
        munchiesDB.execSQL("DROP TABLE IF EXISTS orders");

        onCreate(munchiesDB);
    }

    public boolean insertUserData(String first_name, String second_name, String email, String passwrd) {
        SQLiteDatabase munchiesDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("first_name", first_name);
        contentValues.put("second_name", second_name);
        contentValues.put("email", email);
        contentValues.put("passwrd", passwrd);

        long result = munchiesDB.insert("users", null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertRestaurantData(String restaurant_name, String food_style, String location, Integer minimum_order){

        SQLiteDatabase munchiesDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("restaurant_name", restaurant_name);
        contentValues.put("food_style", food_style);
        contentValues.put("location", location);
        contentValues.put("minimum_order", minimum_order);


        long result = munchiesDB.insert("restaurants", null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertFoodData(String food_name, String food_style, String restaurant_name, Integer price, String description){

        SQLiteDatabase munchiesDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("food_name", food_name);
        contentValues.put("food_style", food_style);
        contentValues.put("restaurant_name", restaurant_name);
        contentValues.put("price", price);
        contentValues.put("description", description);

        long result = munchiesDB.insert("foods", null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertOrderData(String food_name , String restaurant_name, String client_name , Integer quantity , Integer food_cost, String delivery_address, Integer delivery_cost, Integer total_cost) {

        SQLiteDatabase munchiesDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("food_name", food_name);
        contentValues.put("restaurant_name", restaurant_name);
        contentValues.put("client_name", client_name);
        contentValues.put("quantity", quantity);
        contentValues.put("food_cost", food_cost);
        contentValues.put("delivery_address", delivery_address);
        contentValues.put("delivery_cost", delivery_cost);
        contentValues.put("total_cost", total_cost);

        long result = munchiesDB.insert("orders", null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean checkEmail(String email) {
        SQLiteDatabase munchiesDB = this.getWritableDatabase();
        Cursor cursor = munchiesDB.rawQuery("SELECT * FROM users WHERE email = ?", new String[] {email});

        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean checkUserPasswrd(String email, String passwrd) {
        SQLiteDatabase munchiesDB = this.getWritableDatabase();
        Cursor cursor = munchiesDB.rawQuery("SELECT * FROM users WHERE email = ? AND passwrd = ?", new String[] {email, passwrd});

        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
}