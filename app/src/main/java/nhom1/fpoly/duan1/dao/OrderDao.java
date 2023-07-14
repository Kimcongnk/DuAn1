package nhom1.fpoly.duan1.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nhom1.fpoly.duan1.model.Order;

public class OrderDao {
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public OrderDao(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long addOrder(Order order) {
        ContentValues values = new ContentValues();
        values.put("user_id", order.getIdUser());
        values.put("orderDate", order.getDateOder());
        values.put("totalMoney", order.getTotalMoney());
        values.put("status", order.getStatus());
        long orderId = db.insert("Order", null, values);
        db.close();
        return orderId;
    }
    @SuppressLint("Range")
    public Order getOrderById(long orderId) {
        String[] projection = {"order_id", "user_id", "order_date", "total_money", "status"};
        String selection = "order_id = ?";
        String[] selectionArgs = {String.valueOf(orderId)};
        Cursor cursor = db.query("Order", projection, selection, selectionArgs, null, null, null);

        Order order = null;
        if (cursor.moveToFirst()) {
            order = new Order();
            order.setOrderId(cursor.getInt(cursor.getColumnIndex("order_id")));
            order.setIdUser(cursor.getInt(cursor.getColumnIndex("user_id")));
            order.setDateOder(cursor.getString(cursor.getColumnIndex("order_date")));
            order.setTotalMoney(cursor.getDouble(cursor.getColumnIndex("total_money")));
            order.setStatus(cursor.getString(cursor.getColumnIndex("status")));
        }
        cursor.close();
        db.close();
        return order;
    }
    @SuppressLint("Range")
    public List<Order> getAllOrders() {
        String[] projection = {"order_id", "user_id", "order_date", "total_money", "status"};
        Cursor cursor = db.query("Order", projection, null, null, null, null, null);
        List<Order> orderList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Order order = new Order();
            order.setOrderId(cursor.getInt(cursor.getColumnIndex("order_id")));
            order.setIdUser(cursor.getInt(cursor.getColumnIndex("user_id")));
            order.setIdUser(cursor.getString(cursor.getColumnIndex("order_date")));
            order.setTotalMoney(cursor.getDouble(cursor.getColumnIndex("total_money")));
            order.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            orderList.add(order);
        }
        cursor.close();
        db.close();
        return orderList;
    }

    public int updateOrder(Order order) {
        ContentValues values = new ContentValues();
        values.put("user_id", order.getIdUser());
        values.put("orderDate", order.getDateOder());
        values.put("totalMoney", order.getTotalMoney());
        values.put("status", order.getStatus());
        String whereClause = "order_id = ?";
        String[] whereArgs = {String.valueOf(order.getOrderId())};
        int rowsAffected = db.update("Order", values, whereClause, whereArgs);
        db.close();
        return rowsAffected;
    }

    public int deleteOrder(long orderId) {
        String whereClause = "order_id = ?";
        String[] whereArgs = {String.valueOf(orderId)};
        int rowsAffected = db.delete("Order", whereClause, whereArgs);
        db.close();
        return rowsAffected;
    }
}
