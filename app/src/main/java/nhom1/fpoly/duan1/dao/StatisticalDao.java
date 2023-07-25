package nhom1.fpoly.duan1.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nhom1.fpoly.duan1.database.CreateDatabase;
import nhom1.fpoly.duan1.model.ProductSalesReport;

public class StatisticalDao {
    CreateDatabase dbHelper;
    SQLiteDatabase sqLiteDatabase;
    public StatisticalDao(Context context){
        dbHelper= new CreateDatabase(context);
    }
    public int getDoanhThu(String ngaybatdau,String ngayketthuc){
        ngaybatdau = ngaybatdau.replace("-","");
        ngayketthuc = ngayketthuc.replace("-","");
         sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM (totalMoney) FROM Oder WHERE substr(dateOder,7)||substr(dateOder,4,2)||substr(dateOder,1,2) BETWEEN ? AND ?",new String[]{ngaybatdau,ngayketthuc});
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }
    @SuppressLint("Range")
    public HashMap<String, Integer> calculateRevenueByProduct() {
        HashMap<String, Integer> revenueByProduct = new HashMap<>();

        String query = "SELECT Products.name, SUM(Products.price * OrderDetail.quantyti) AS totalRevenue " +
                "FROM Products " +
                "JOIN OrderDetail ON Products.id_product = OrderDetail.id_product " +
                "JOIN Oder ON OrderDetail.id_oder = Oder.id_oder " +
                "WHERE Oder.status = 'Đã giao hàng' " +
                "GROUP BY Products.name";

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String productName = cursor.getString(cursor.getColumnIndex("name"));
                int totalRevenue = cursor.getInt(cursor.getColumnIndex("totalRevenue"));
                revenueByProduct.put(productName, totalRevenue);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return revenueByProduct;
    }
    @SuppressLint("Range")
    public List<ProductSalesReport> getProductSalesReportAll() {
        List<ProductSalesReport> salesReportList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT od.id_product, p.name, p.image_url, " +
                "SUM(od.quantyti) AS totalQuantity, " +
                "SUM(p.price * od.quantyti) AS totalRevenue " +
                "FROM OrderDetail od " +
                "INNER JOIN Products p ON od.id_product = p.id_product " +
                "INNER JOIN Oder o ON od.id_oder = o.id_oder " +
                "WHERE o.status = 'Đã thanh toán' " + // Filter products with status 'Đã giao hàng'
                "GROUP BY od.id_product, p.name, p.image_url";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int productId = cursor.getInt(cursor.getColumnIndex("id_product"));
                String productName = cursor.getString(cursor.getColumnIndex("name"));
                String productImage = cursor.getString(cursor.getColumnIndex("image_url"));
                int totalQuantity = cursor.getInt(cursor.getColumnIndex("totalQuantity"));
                int totalRevenue = cursor.getInt(cursor.getColumnIndex("totalRevenue"));

                ProductSalesReport salesReport = new ProductSalesReport(productId, productName, productImage, totalQuantity, totalRevenue);
                salesReportList.add(salesReport);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return salesReportList;
    }
    @SuppressLint("Range")
    public List<ProductSalesReport> getProductSalesReport(String fromDate, String toDate) {
        List<ProductSalesReport> salesReportList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT od.id_product, p.name, p.image_url, " +
                "SUM(od.quantyti) AS totalQuantity, " +
                "SUM(p.price * od.quantyti) AS totalRevenue " +
                "FROM OrderDetail od " +
                "INNER JOIN Products p ON od.id_product = p.id_product " +
                "INNER JOIN Oder o ON od.id_oder = o.id_oder " +
                "WHERE o.status = 'Đã thanh toán' " + // Filter products with status 'Đã thanh toán'
                "AND o.dateOder >= '" + fromDate + "' AND o.dateOder <= '" + toDate + "' " +
                "GROUP BY od.id_product, p.name, p.image_url";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int productId = cursor.getInt(cursor.getColumnIndex("id_product"));
                String productName = cursor.getString(cursor.getColumnIndex("name"));
                String productImage = cursor.getString(cursor.getColumnIndex("image_url"));
                int totalQuantity = cursor.getInt(cursor.getColumnIndex("totalQuantity"));
                int totalRevenue = cursor.getInt(cursor.getColumnIndex("totalRevenue"));

                ProductSalesReport salesReport = new ProductSalesReport(productId, productName, productImage, totalQuantity, totalRevenue);
                salesReportList.add(salesReport);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return salesReportList;
    }


}
