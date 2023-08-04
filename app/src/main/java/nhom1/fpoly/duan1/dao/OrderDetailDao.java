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
import nhom1.fpoly.duan1.model.Product;

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
        values.put("quantyti", orderDetail.getQuantity());
        long detailId = db.insert("OrderDetail", null, values);

        return detailId;
    }

    @SuppressLint("Range")
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT od.id_oderDetail, od.id_oder, od.id_product, od.quantyti, " +
                "p.name, p.price,p.image_url " +
                "FROM OrderDetail od " +
                "INNER JOIN Products p ON od.id_product = p.id_product " +
                "WHERE od.id_oder = " + orderId;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int orderDetailId = cursor.getInt(cursor.getColumnIndex("id_oderDetail"));
                int productId = cursor.getInt(cursor.getColumnIndex("id_product"));
                String productName = cursor.getString(cursor.getColumnIndex("name"));
                int price = cursor.getInt(cursor.getColumnIndex("price"));
                int quantity = cursor.getInt(cursor.getColumnIndex("quantyti"));
                String img = cursor.getString(cursor.getColumnIndex("image_url"));

                OrderDetail orderDetail = new OrderDetail(orderDetailId,productId,productName,price,img,quantity);


                orderDetailList.add(orderDetail);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return orderDetailList;
    }
}
