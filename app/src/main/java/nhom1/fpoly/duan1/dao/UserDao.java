package nhom1.fpoly.duan1.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nhom1.fpoly.duan1.model.User;

public class UserDao {
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public UserDao(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long addUser(User user) {
        ContentValues values = new ContentValues();
        values.put("nameUser", user.getNameUser());
        values.put("password", user.getPassword());
        values.put("phone", user.getPhone());
        values.put("address", user.getAddress());
        long userId = db.insert("User", null, values);
        db.close();
        return userId;
    }
    @SuppressLint("Range")
    public User getUserById(long userId) {
        String[] projection = {"user_id", "nameUser", "password", "phone", "address"};
        String selection = "user_id = ?";
        String[] selectionArgs = {String.valueOf(userId)};
        Cursor cursor = db.query("User", projection, selection, selectionArgs, null, null, null);

        User user = null;
        if (cursor.moveToFirst()) {
            user = new User();
            user.setUserId(cursor.getInt(cursor.getColumnIndex("user_id")));
            user.setNameUser(cursor.getString(cursor.getColumnIndex("nameUser")));
            user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            user.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            user.setAddress(cursor.getString(cursor.getColumnIndex("address")));
        }
        cursor.close();
        db.close();
        return user;
    }
    @SuppressLint("Range")
    public List<User> getAllUsers() {
        String[] projection = {"user_id", "nameUser", "password", "phone", "address"};
        Cursor cursor = db.query("User", projection, null, null, null, null, null);
        List<User> userList = new ArrayList<>();
        while (cursor.moveToNext()) {
            User user = new User();
            user.setUserId(cursor.getInt(cursor.getColumnIndex("user_id")));
            user.setNameUser(cursor.getString(cursor.getColumnIndex("nameUser")));
            user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            user.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            user.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            userList.add(user);
        }
        cursor.close();
        db.close();
        return userList;
    }

    public int updateUser(User user) {
        ContentValues values = new ContentValues();
        values.put("nameUser", user.getNameUser());
        values.put("password", user.getPassword());
        values.put("phone", user.getPhone());
        values.put("address", user.getAddress());
        String whereClause = "user_id = ?";
        String[] whereArgs = {String.valueOf(user.getUserId())};
        int rowsAffected = db.update("User", values, whereClause, whereArgs);
        db.close();
        return rowsAffected;
    }

    public int deleteUser(long userId) {
        String whereClause = "user_id = ?";
        String[] whereArgs = {String.valueOf(userId)};
        int rowsAffected = db.delete("User", whereClause, whereArgs);
        db.close();
        return rowsAffected;
    }
}
