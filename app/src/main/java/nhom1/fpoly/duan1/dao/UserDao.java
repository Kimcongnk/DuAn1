package nhom1.fpoly.duan1.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nhom1.fpoly.duan1.database.CreateDatabase;
import nhom1.fpoly.duan1.model.User;

public class UserDao {
    private final SQLiteDatabase database;

    public UserDao(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.getWritableDatabase();
    }

    public boolean insertUser(User user) {
        ContentValues values = new ContentValues();
        values.put("id_user", user.getUser_id());
        values.put("fullName", user.getFullName());
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        values.put("email", user.getEmail());
        values.put("address", user.getAddress());
        values.put("phone", user.getPhoneNumber());
        long check = database.insert("User", null, values);
        return check != -1;
    }
    public boolean updateUser(User user) {
        ContentValues values = new ContentValues();
        values.put("id_user", user.getUser_id());
        values.put("fullName", user.getFullName());
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        values.put("email", user.getEmail());
        values.put("address", user.getAddress());
        values.put("phone", user.getPhoneNumber());
        long check = database.update("User", values, "id_user=?", new  String[]{String.valueOf(user.getUser_id())});
        return check != -1;
    }
    public int deleteUser(User user){
        return database.delete("User", "id_user=?", new String[]{String.valueOf(user.getUser_id())});
    }

    @SuppressLint("Range")
    public List<User> getAllUsers(){
        List<User> usersList = new ArrayList<User>();
        Cursor cursor =database.rawQuery("select * from User", null);
        if (cursor.moveToFirst()){
            do {
                User user = new User();
                user.setUser_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id_user"))));
                user.setFullName(cursor.getString(cursor.getColumnIndex("fullName")));
                user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                user.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                user.setPhoneNumber(cursor.getString(cursor.getColumnIndex("phone")));
                usersList.add(user);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return usersList;
    }
}
