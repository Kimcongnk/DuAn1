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
    private final SQLiteDatabase database;
    CreateDatabase createDatabase;

    public CustomerDao(Context context) {
        createDatabase = new CreateDatabase(context);
        database = createDatabase.getWritableDatabase();
    }

    public boolean insertCustomer(nhom1.fpoly.duan1.model.Customer customer) {
        ContentValues values = new ContentValues();
        values.put("fullName", customer.getFullName());
        values.put("username", customer.getUsername());
        values.put("password", customer.getPassword());
        values.put("email", customer.getEmail());
        values.put("address", customer.getAddress());
        values.put("phone", customer.getPhoneNumber());
        long check = database.insert("Customer", null, values);
        return check != -1;
    }

    public boolean updateCustomer(nhom1.fpoly.duan1.model.Customer customer) {
        ContentValues values = new ContentValues();
        values.put("id_customer", customer.getid_customer());
        values.put("fullName", customer.getFullName());
        values.put("username", customer.getUsername());
        values.put("password", customer.getPassword());
        values.put("email", customer.getEmail());
        values.put("address", customer.getAddress());
        values.put("phone", customer.getPhoneNumber());
        long check = database.update("Customer", values, "id_customer=?", new String[]{String.valueOf(customer.getid_customer())});
        return check != -1;
    }

    public int deleteCustomer(nhom1.fpoly.duan1.model.Customer customer) {
        return database.delete("Customer", "id_customer=?", new String[]{String.valueOf(customer.getid_customer())});
    }

    @SuppressLint("Range")
    public List<Customer> getAllCustomer() {
        List<Customer> usersList = new ArrayList<nhom1.fpoly.duan1.model.Customer>();
        Cursor cursor = database.rawQuery("select * from Customer", null);
        if (cursor.moveToFirst()) {
            do {
                Customer customer = new nhom1.fpoly.duan1.model.Customer();
                customer.setid_customer(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id_customer"))));
                customer.setFullName(cursor.getString(cursor.getColumnIndex("fullName")));
                customer.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                customer.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                customer.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                customer.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                customer.setPhoneNumber(cursor.getString(cursor.getColumnIndex("phone")));
                usersList.add(customer);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return usersList;
    }

    public boolean checkUserPasswordCustomer(String username, String password) {
        SQLiteDatabase sqLiteDatabase = createDatabase.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Customer where username=? and password=?", new String[]{username, password});
        return cursor.getCount() != 0;
    }
}
