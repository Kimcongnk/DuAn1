package nhom1.fpoly.duan1.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nhom1.fpoly.duan1.database.CreateDatabase;
import nhom1.fpoly.duan1.model.Customer;

public class CustomerDao {
    private CreateDatabase dbHelper;
    private SQLiteDatabase db;

    public CustomerDao(Context context) {
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

    public long updateCustomer(Customer customer) {
        ContentValues values = new ContentValues();
        values.put("fullName", customer.getFullName());
        values.put("username", customer.getUsername());
        values.put("password", customer.getPassword());
        values.put("address", customer.getAddress());
        values.put("phone", customer.getPhoneNumber());
        return db.update("Customer", values, "id_customer=?", new String[]{String.valueOf(customer.getid_customer())});
    }

    public long deleteCustomer(int customerId) {
        return db.delete("Customer", "id_customer=?", new String[]{String.valueOf(customerId)});
    }
    @SuppressLint("Range")
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        Cursor cursor = db.query("Customer", null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndex("id_customer"));
                String fullName = cursor.getString(cursor.getColumnIndex("fullName"));
                String username = cursor.getString(cursor.getColumnIndex("username"));
                String password = cursor.getString(cursor.getColumnIndex("password"));
                String address = cursor.getString(cursor.getColumnIndex("address"));
                String phone = cursor.getString(cursor.getColumnIndex("phone"));

                Customer customer = new Customer(id, fullName, username, password, phone, address);
                customers.add(customer);

                cursor.moveToNext();
            }
            cursor.close();
        }

        return customers;
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
        Cursor cursor = db.query("Customer", projection, selection, selectionArgs, null, null, null);

        boolean isPasswordCorrect = cursor.moveToFirst();

        cursor.close();
        db.close();
        return isPasswordCorrect;
    }
}
