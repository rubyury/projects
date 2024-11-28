package com.example.starbell;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "StarBell.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create table users(email TEXT primary key, user TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String email, String user, String password) {

        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("email", email);
        contentValues.put("user", user);
        contentValues.put("password", password);

        long result = MyDatabase.insert("users", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Boolean checkUser(String email) {

        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public Boolean checkUserPassword(String email, String password) {

        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public Cursor getUser(String email){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from users where email = ? ", new String[]{email});
        return cursor;
    }

    public ArrayList<String> getAllUsernames() {
        SQLiteDatabase myDB = this.getReadableDatabase();
        ArrayList<String> usernames = new ArrayList<>();

        Cursor cursor = myDB.rawQuery("SELECT user FROM users", null);

        if (cursor.moveToFirst()) {
            do {
                String username = cursor.getString(cursor.getColumnIndexOrThrow("user"));
                usernames.add(username);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return usernames;
    }

    public String reviewUser(String username) {
        SQLiteDatabase myDB = this.getReadableDatabase();
        String password = null;

        Cursor cursor = myDB.rawQuery(
                "select * from users where username = ?", new String[]{username});

        if (cursor.moveToFirst()) {
            password = cursor.getString(cursor.getColumnIndexOrThrow("password"));
        }
        cursor.close();
        return password;
    }


    public boolean updateUserPwd(String email, String newPassword) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", newPassword);

        int rowsAffected = myDB.update("users", values, "email = ?", new String[]{email});
        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateUserName(String email, String newUsername) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user", newUsername);

        int rowsAffected = myDB.update("users", values, "email = ?", new String[]{email});
        return rowsAffected > 0;
    }


    public boolean deleteUser(String user) {
        SQLiteDatabase myDB = this.getWritableDatabase();

        Cursor cursor = myDB.rawQuery("SELECT * FROM users WHERE username = ?", new String[]{user});
        if (cursor.getCount() > 0) {
            cursor.close();

            int rowsAffected = myDB.delete("users", "user=?", new String[]{user});
            return rowsAffected > 0;
        } else {
            cursor.close();
            return false;
        }
    }

}