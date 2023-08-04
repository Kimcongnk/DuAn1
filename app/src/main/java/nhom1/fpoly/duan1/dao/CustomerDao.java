package nhom1.fpoly.duan1.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nhom1.fpoly.duan1.database.CreateDatabase;
import nhom1.fpoly.duan1.model.Customer;

public class CustomerDao {
    private CreateDatabase dbHelper;
    private SQLiteDatabase db;
    private Context context;

    private  SessionManager sessionManager;
    public CustomerDao(Context context) {
        this.context = context;
        dbHelper = new CreateDatabase(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean insertCustomer(Customer customer) {
        ContentValues values = new ContentValues();
        values.put("fullName", customer.getFullName());
        values.put("username", customer.getUsername());
        values.put("password", customer.getPassword());
        values.put("address", customer.getAddress());
        values.put("phone", customer.getPhoneNumber());
        long check = db.insert("Customer", null, values);
        return check != -1;
    }


    @SuppressLint("Range")
    public Customer getCustomerById(int customerId) {
        Cursor cursor = db.query("Customer", null, "id_customer=?", new String[]{String.valueOf(customerId)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            String fullName = cursor.getString(cursor.getColumnIndex("fullName"));
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String address = cursor.getString(cursor.getColumnIndex("address"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));

            cursor.close();
            return new Customer(customerId, fullName, username, password, phone, address);
        }

        return null;
    }

    @SuppressLint("Range")
    public boolean checkUserPasswordCustomer(String username, String password) {
        String[] projection = { "username", "password"};
        String selection = "username = ? AND password = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query("Customer", null, selection, selectionArgs, null, null, null);
        int customerId = -1;

        boolean isPasswordCorrect = cursor.moveToFirst();
        if (isPasswordCorrect) {
            customerId = cursor.getInt(cursor.getColumnIndexOrThrow("id_customer"));
            sessionManager = new SessionManager(context);
            sessionManager.setLoggedInCustomer(customerId);
        }
        cursor.close();
        db.close();
        return isPasswordCorrect;
    }
    public int capNhatMK(Integer id_customer,String passcu,String passmoi){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT*FROM Customer WHERE id_customer =? AND password=?",new String[]{String.valueOf(id_customer),passcu});
        if (cursor.getCount()>0){
            ContentValues contentValues = new ContentValues();
            contentValues.put("password",passmoi);
            long check= sqLiteDatabase.update("Customer",contentValues,"id_customer=?",new String[]{String.valueOf(id_customer)});
            if (check==-1){
                return -1;
            }
            return 1;
        }
        return 0;
    }
}
