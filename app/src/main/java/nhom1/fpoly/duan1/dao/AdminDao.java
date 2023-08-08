package nhom1.fpoly.duan1.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nhom1.fpoly.duan1.database.CreateDatabase;
import nhom1.fpoly.duan1.model.Admin;

public class AdminDao {
    private final SQLiteDatabase database;
    CreateDatabase createDatabase;
    public AdminDao(Context context) {
         createDatabase = new CreateDatabase(context);
        database = createDatabase.getWritableDatabase();
    }

    public boolean insertAdmin(Admin admin) {
        ContentValues values = new ContentValues();
        values.put("id_admin", admin.getId_Admin());
        values.put("fullName", admin.getFullName());
        values.put("username", admin.getUsername());
        values.put("password", admin.getPassword());
        long check = database.insert("Admin", null, values);
        return check != -1;
    }

    public boolean checkUserPasswordAdmin(String username, String password) {
        SQLiteDatabase sqLiteDatabase = createDatabase.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Admin where username=? and password=?", new String[]{username, password});
        return cursor.getCount() != 0;
    }

}
