package nhom1.fpoly.duan1.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nhom1.fpoly.duan1.database.CreateDatabase;
import nhom1.fpoly.duan1.model.Order;

public class OrderDao {
    private CreateDatabase dbHelper;
    private SQLiteDatabase db;

    public OrderDao(Context context) {
        dbHelper = new CreateDatabase(context);
        db = dbHelper.getWritableDatabase();
    }

    public long addOrder(Order order) {
        ContentValues values = new ContentValues();
        values.put("id_oder", order.getOrderId());
        values.put("id_customer", order.getIdUser());
        values.put("nameCustomerOder", order.getNameOder());
        values.put("phoneNumber", order.getPhoneNumber());
        values.put("address", order.getAddress());
        values.put("dateOder", order.getDateOder());
        values.put("totalMoney", order.getTotalMoney());
        values.put("status", order.getStatus());
        long orderId = db.insert("Oder", null, values);
        db.close();
        return orderId;
    }
    @SuppressLint("Range")
    public Order getOrderById(long orderId) {
        String[] projection = {"id_oder", "id_customer", "dateOder", "totalMoney", "status"};
        String selection = "id_oder = ?";
        String[] selectionArgs = {String.valueOf(orderId)};
        Cursor cursor = db.query("Order", projection, selection, selectionArgs, null, null, null);

        Order order = null;
        if (cursor.moveToFirst()) {
            order = new Order();
            order.setOrderId(cursor.getInt(cursor.getColumnIndex("id_oder")));
            order.setIdUser(cursor.getInt(cursor.getColumnIndex("id_customer")));
            order.setDateOder(cursor.getString(cursor.getColumnIndex("dateOder")));
            order.setTotalMoney(cursor.getDouble(cursor.getColumnIndex("totalMoney")));
            order.setStatus(cursor.getString(cursor.getColumnIndex("status")));
        }
        cursor.close();
        db.close();
        return order;
    }
    @SuppressLint("Range")
    public List<Order> getAllOrders() {
        String[] projection = {"id_oder", "id_customer", "dateOder", "totalMoney", "status"};
        Cursor cursor = db.query("Order", projection, null, null, null, null, null);
        List<Order> orderList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Order order = new Order();
            order.setOrderId(cursor.getInt(cursor.getColumnIndex("id_oder")));
            order.setIdUser(cursor.getInt(cursor.getColumnIndex("id_customer")));
            order.setDateOder(cursor.getString(cursor.getColumnIndex("dateOder")));
            order.setTotalMoney(cursor.getDouble(cursor.getColumnIndex("totalMoney")));
            order.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            orderList.add(order);
        }
        cursor.close();
        db.close();
        return orderList;
    }
    @SuppressLint("Range")

    public ArrayList<Order> getOrdersByStatus(String status) {
        ArrayList<Order> orders = new ArrayList<>();


        String[] columns = {"id_oder", "id_customer", "dateOder", "totalMoney", "status"};
        String selection = "status = ?";
        String[] selectionArgs = {status};

        Cursor cursor = db.query("Oder", columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Order order = new Order();
                order.setOrderId(cursor.getInt(cursor.getColumnIndex("id_oder")));
                order.setIdUser(cursor.getInt(cursor.getColumnIndex("id_customer")));
                order.setDateOder(cursor.getString(cursor.getColumnIndex("dateOder")));
                order.setTotalMoney(cursor.getDouble(cursor.getColumnIndex("totalMoney")));
                order.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                orders.add(order);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return orders;
    }
    @SuppressLint("Range")
    public List<Order> getAllOrdersWithDetails() {
        String[] projection = {"nameCustomerOder", "phoneNumber", "address"};
        Cursor cursor = db.query("Oder", projection, null, null, null, null, null);
        List<Order> orderList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Order order = new Order();

            order.setNameOder(cursor.getString(cursor.getColumnIndex("nameCustomerOder")));
            order.setPhoneNumber(cursor.getString(cursor.getColumnIndex("phoneNumber")));
            order.setAddress(cursor.getString(cursor.getColumnIndex("address")));

            orderList.add(order);
        }
        cursor.close();
        db.close();
        return orderList;
    }


    public int updateOrder(Order order) {
        ContentValues values = new ContentValues();
        values.put("id_customer", order.getOrderId());
        values.put("orderDate", order.getDateOder());
        values.put("totalMoney", order.getTotalMoney());
        values.put("status", order.getStatus());
        String whereClause = "id_oder = ?";
        String[] whereArgs = {String.valueOf(order.getOrderId())};
        int rowsAffected = db.update("Order", values, whereClause, whereArgs);
        db.close();
        return rowsAffected;
    }

    public int deleteOrder(long orderId) {
        String whereClause = "id_oder = ?";
        String[] whereArgs = {String.valueOf(orderId)};
        int rowsAffected = db.delete("Order", whereClause, whereArgs);
        db.close();
        return rowsAffected;
    }
}
