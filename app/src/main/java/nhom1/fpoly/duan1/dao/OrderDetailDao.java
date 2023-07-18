package nhom1.fpoly.duan1.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nhom1.fpoly.duan1.database.CreateDatabase;
import nhom1.fpoly.duan1.model.OrderDetail;

public class OrderDetailDao {
    private CreateDatabase dbHelper;
    private SQLiteDatabase db;

    public OrderDetailDao(Context context) {
        dbHelper = new CreateDatabase(context);
        db = dbHelper.getWritableDatabase();
    }

    public long addOrderDetail(OrderDetail orderDetail) {
        ContentValues values = new ContentValues();
        values.put("id_oder", orderDetail.getOderId());
        values.put("id_product", orderDetail.getProductId());
        values.put("quantity", orderDetail.getQuantity());
        long detailId = db.insert("OrderDetail", null, values);
        db.close();
        return detailId;
    }
    @SuppressLint("Range")
    public OrderDetail getOrderDetailById(long detailId) {
        String[] projection = {"id_oderDetail", "id_oder", "id_product", "quantity"};
        String selection = "id_oderDetail = ?";
        String[] selectionArgs = {String.valueOf(detailId)};
        Cursor cursor = db.query("OrderDetail", projection, selection, selectionArgs, null, null, null);

        OrderDetail orderDetail = null;
        if (cursor.moveToFirst()) {
            orderDetail = new OrderDetail();
            orderDetail.setIdOderDetail(cursor.getInt(cursor.getColumnIndex("detail_id")));
            orderDetail.setOderId(cursor.getInt(cursor.getColumnIndex("id_oder")));
            orderDetail.setProductId(cursor.getInt(cursor.getColumnIndex("id_product")));
            orderDetail.setQuantity(cursor.getInt(cursor.getColumnIndex("quantity")));
        }
        cursor.close();
        db.close();
        return orderDetail;
    }
    @SuppressLint("Range")
    public List<OrderDetail> getAllOrderDetails() {
        String[] projection = {"id_oderDetail", "id_oder", "id_product", "quantity"};
        Cursor cursor = db.query("OrderDetail", projection, null, null, null, null, null);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        while (cursor.moveToNext()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setIdOderDetail(cursor.getInt(cursor.getColumnIndex("detail_id")));
            orderDetail.setOderId(cursor.getInt(cursor.getColumnIndex("id_oder")));
            orderDetail.setProductId(cursor.getInt(cursor.getColumnIndex("id_product")));
            orderDetail.setQuantity(cursor.getInt(cursor.getColumnIndex("quantity")));
            orderDetailList.add(orderDetail);
        }
        cursor.close();
        db.close();
        return orderDetailList;
    }

    public int updateOrderDetail(OrderDetail orderDetail) {
        ContentValues values = new ContentValues();
        values.put("order_id", orderDetail.getOderId());
        values.put("id_product", orderDetail.getProductId());
        values.put("quantity", orderDetail.getQuantity());
        String whereClause = "id_oderDetail = ?";
        String[] whereArgs = {String.valueOf(orderDetail.getIdOderDetail())};
        int rowsAffected = db.update("OrderDetail", values, whereClause, whereArgs);
        db.close();
        return rowsAffected;
    }

    public int deleteOrderDetail(long detailId) {
        String whereClause = "id_oderDetail = ?";
        String[] whereArgs = {String.valueOf(detailId)};
        int rowsAffected = db.delete("OrderDetail", whereClause, whereArgs);
        db.close();
        return rowsAffected;
    }
}
