package nhom1.fpoly.duan1.model.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nhom1.fpoly.duan1.database.DBHelper;
import nhom1.fpoly.duan1.model.Product;

public class ProductDao {
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public ProductDao(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long addProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put("nameProducts", product.getNameProduct());
        values.put("menu_id", product.getMenuId());
        values.put("price", product.getPrice());
        values.put("imageProduct", product.getImageProduct());
        values.put("description", product.getDescribe());
        long productId = db.insert("Product", null, values);
        db.close();
        return productId;
    }
    @SuppressLint("Range")
    public Product getProductById(long productId) {
        String[] projection = {"product_id", "nameProducts", "price", "category_id", "imageProduct","description"};
        String selection = "product_id = ?";
        String[] selectionArgs = {String.valueOf(productId)};
        Cursor cursor = db.query("Product", projection, selection, selectionArgs, null, null, null);

        Product product = null;
        if (cursor.moveToFirst()) {
            product = new Product();
            product.setProductId(cursor.getInt(cursor.getColumnIndex("product_id")));
            product.setNameProduct(cursor.getString(cursor.getColumnIndex("nameProducts")));
            product.setPrice(cursor.getString(cursor.getColumnIndex("price")));
            product.setMenuId(cursor.getInt(cursor.getColumnIndex("category_id")));
            product.setImageProduct(cursor.getString(cursor.getColumnIndex("imageProduct")));
            product.setDescribe(cursor.getString(cursor.getColumnIndex("description")));
        }
        cursor.close();
        db.close();
        return product;
    }
    @SuppressLint("Range")
    public List<Product> getAllProducts() {
        String[] projection = {"product_id", "nameProducts", "price", "category_id", "imageProduct","description"};
        Cursor cursor = db.query("Product", projection, null, null, null, null, null);
        List<Product> productList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Product product = new Product();
            product.setProductId(cursor.getInt(cursor.getColumnIndex("product_id")));
            product.setNameProduct(cursor.getString(cursor.getColumnIndex("nameProducts")));
            product.setPrice(cursor.getString(cursor.getColumnIndex("price")));
            product.setMenuId(cursor.getInt(cursor.getColumnIndex("category_id")));
            product.setImageProduct(cursor.getString(cursor.getColumnIndex("imageProduct")));
            product.setDescribe(cursor.getString(cursor.getColumnIndex("description")));
            productList.add(product);
        }
        cursor.close();
        db.close();
        return productList;
    }

    public int updateProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put("nameProducts", product.getNameProduct());
        values.put("menu_id", product.getMenuId());
        values.put("price", product.getPrice());
        values.put("imageProduct", product.getImageProduct());
        values.put("description", product.getDescribe());
        String whereClause = "product_id = ?";
        String[] whereArgs = {String.valueOf(product.getProductId())};
        int rowsAffected = db.update("Product", values, whereClause, whereArgs);
        db.close();
        return rowsAffected;
    }

    public int deleteProduct(long productId) {
        String whereClause = "product_id = ?";
        String[] whereArgs = {String.valueOf(productId)};
        int rowsAffected = db.delete("Product", whereClause, whereArgs);
        db.close();
        return rowsAffected;
    }
}
