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
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (email TEXT PRIMARY KEY, user TEXT, password TEXT)");

        db.execSQL("CREATE TABLE notifications (" +
                "name TEXT PRIMARY KEY, " +
                "description TEXT, " +
                "hour INTEGER, " +
                "minutes INTEGER, " +
                "vibration INTEGER, " +
                "days TEXT, " +
                "email TEXT, " +
                "FOREIGN KEY(email) REFERENCES users(email) ON DELETE CASCADE)");

        db.execSQL("CREATE TABLE days (" +
                "id TEXT PRIMARY KEY, " +
                "day1 TEXT, " +
                "day2 TEXT, " +
                "day3 TEXT, " +
                "day4 TEXT, " +
                "day5 TEXT, " +
                "day6 TEXT, " +
                "day7 TEXT, " +
                "FOREIGN KEY(name) REFERENCES notifications(name) ON DELETE CASCADE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS days");
        db.execSQL("DROP TABLE IF EXISTS notifications");
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public boolean insertUser(String email, String user, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("email", email);
        contentValues.put("user", user);
        contentValues.put("password", password);

        long result = db.insert("users", null, contentValues);
        return result != -1;
    }

    public boolean checkUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ?", new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
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


    public ArrayList<User> getAllUsers() {
        SQLiteDatabase myDB = null;
        Cursor cursor = null;
        ArrayList<User> users = new ArrayList<>();

        try {
            myDB = this.getReadableDatabase();

            cursor = myDB.rawQuery("SELECT * FROM users", null);

            if (cursor != null && cursor.moveToFirst()) {
                do {

                    String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                    String username = cursor.getString(cursor.getColumnIndexOrThrow("user"));
                    String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));

                    User user = new User(username, email, password);
                    users.add(user);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (myDB != null) {
                myDB.close();
            }
        }
        return users;
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


    public boolean deleteUser(String email) {
        SQLiteDatabase myDB = this.getWritableDatabase();

        Cursor cursor = myDB.rawQuery("SELECT * FROM users WHERE email = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            cursor.close();

            int rowsAffected = myDB.delete("users", "email=?", new String[]{email});
            return rowsAffected > 0;
        } else {
            cursor.close();
            return false;
        }
    }

    public boolean insertNotification(String name, String description, int hour, int minutes, int vibration, String days, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("description", description);
        contentValues.put("hour", hour);
        contentValues.put("minutes", minutes);
        contentValues.put("vibration", vibration);
        contentValues.put("days", days);
        contentValues.put("email", email);

        long result = db.insert("notifications", null, contentValues);
        return result != -1;
    }

    public boolean updateNotification(String name, String description, int hour, int minutes, boolean vibration, String days) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("description", description);
        contentValues.put("hour", hour);
        contentValues.put("minutes", minutes);
        contentValues.put("vibration", vibration ? 1 : 0);
        contentValues.put("days", days);

        int rowsAffected = db.update("notifications", contentValues, "name = ?", new String[]{name});
        return rowsAffected > 0;
    }

    public boolean deleteNotification(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete("notifications", "name = ?", new String[]{name});
        return rowsDeleted > 0;
    }

    public ArrayList<Notification> getAllNotifications(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Notification> notifications = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM notifications WHERE email = ?", new String[]{email});
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                int hour = cursor.getInt(cursor.getColumnIndexOrThrow("hour"));
                int minutes = cursor.getInt(cursor.getColumnIndexOrThrow("minutes"));
                boolean vibration = cursor.getInt(cursor.getColumnIndexOrThrow("vibration")) == 1;
                String days = cursor.getString(cursor.getColumnIndexOrThrow("days"));

                notifications.add(new Notification(name, description, hour, minutes, vibration, days, email));
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }

        return notifications;
    }

    // Insert day record
    public boolean insertDay(String id, String day1, String day2, String day3, String day4, String day5, String day6, String day7) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", id);
        contentValues.put("day1", day1);
        contentValues.put("day2", day2);
        contentValues.put("day3", day3);
        contentValues.put("day4", day4);
        contentValues.put("day5", day5);
        contentValues.put("day6", day6);
        contentValues.put("day7", day7);

        long result = db.insert("days", null, contentValues);
        return result != -1;
    }

    // Update day record
    public boolean updateDay(String id, String day1, String day2, String day3, String day4, String day5, String day6, String day7) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("day1", day1);
        contentValues.put("day2", day2);
        contentValues.put("day3", day3);
        contentValues.put("day4", day4);
        contentValues.put("day5", day5);
        contentValues.put("day6", day6);
        contentValues.put("day7", day7);

        int rowsAffected = db.update("days", contentValues, "id = ?", new String[]{id});
        return rowsAffected > 0;
    }

    // Delete day record
    public boolean deleteDay(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete("days", "id = ?", new String[]{id});
        return rowsDeleted > 0;
    }

    // Get all days for a notification
    public ArrayList<String[]> getDays(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String[]> daysList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM days WHERE id = ?", new String[]{id});
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String day1 = cursor.getString(cursor.getColumnIndexOrThrow("day1"));
                String day2 = cursor.getString(cursor.getColumnIndexOrThrow("day2"));
                String day3 = cursor.getString(cursor.getColumnIndexOrThrow("day3"));
                String day4 = cursor.getString(cursor.getColumnIndexOrThrow("day4"));
                String day5 = cursor.getString(cursor.getColumnIndexOrThrow("day5"));
                String day6 = cursor.getString(cursor.getColumnIndexOrThrow("day6"));
                String day7 = cursor.getString(cursor.getColumnIndexOrThrow("day7"));

                daysList.add(new String[]{day1, day2, day3, day4, day5, day6, day7});
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }

        return daysList;
    }

}