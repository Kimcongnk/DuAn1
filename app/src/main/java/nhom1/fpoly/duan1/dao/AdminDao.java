package nhom1.fpoly.duan1.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


import nhom1.fpoly.duan1.model.Admin;

public class AdminDao {
    private final SQLiteDatabase database;
    DBHelper createDatabase;
    public AdminDao(Context context) {
        createDatabase = new CreateDatabase(context);
        database = createDatabase.getWritableDatabase();
    }

    public boolean insertAdmin(Admin admin) {
        ContentValues values = new ContentValues();
        values.put("id_admin", admin.getId_Admin());
        values.put("nameAdmin", admin.getNameAdmin());
        values.put("password", admin.getPassword());
        long check = database.insert("Admin", null, values);
        return check != -1;
    }

    public boolean updateAdmin(Admin admin) {
        ContentValues values = new ContentValues();
        values.put("id_admin", admin.getId_Admin());
        values.put("nameAdmin", admin.getNameAdmin());
        values.put("password", admin.getPassword());
        long check = database.update("Admin", values, "id_admin=?", new String[]{String.valueOf(admin.getId_Admin())});
        return check != -1;
    }

    public int deleteAdmin(Admin admin) {
        return database.delete("Admin", "id_admin=?", new String[]{String.valueOf(admin.getId_Admin())});
    }

    @SuppressLint("Range")
    public List<Admin> getAllAdmin() {
        List<Admin> listAdmin = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Admin", null);
        if (cursor.moveToFirst()) {
            do {
                Admin admin = new Admin();
                admin.setId_Admin(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id_admin"))));
                admin.setNameAdmin(cursor.getString(cursor.getColumnIndex("nameAdmin")));
                admin.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                listAdmin.add(admin);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listAdmin;
    }

    public boolean checkUserPassword(String username, String password) {
        SQLiteDatabase sqLiteDatabase = createDatabase.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Admin where nameAdmin=? and password=?", new String[]{username, password});
        if(cursor.getCount() != 0) {
            return true;
        }else{
            return false;
        }
    }

}
