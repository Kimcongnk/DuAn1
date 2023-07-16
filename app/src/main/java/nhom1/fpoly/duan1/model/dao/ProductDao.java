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
        values.put("name", product.getNameProduct());
        values.put("price", product.getPrice());
        values.put("menu_id", product.getMenuId());
        values.put("img", product.getImage());
        long productId = db.insert("Product", null, values);
        db.close();
        return productId;
    }
    @SuppressLint("Range")
    public Product getProductById(long productId) {
        String[] projection = {"product_id", "name", "price", "category_id", "img"};
        String selection = "product_id = ?";
        String[] selectionArgs = {String.valueOf(productId)};
        Cursor cursor = db.query("Product", projection, selection, selectionArgs, null, null, null);

        Product product = null;
        if (cursor.moveToFirst()) {
            product = new Product();
            product.setProductId(cursor.getInt(cursor.getColumnIndex("product_id")));
            product.setNameProduct(cursor.getString(cursor.getColumnIndex("name")));
            product.setPrice(cursor.getString(cursor.getColumnIndex("price")));
            product.setMenuId(cursor.getInt(cursor.getColumnIndex("category_id")));
            product.setImage(cursor.getString(cursor.getColumnIndex("img")));
        }
        cursor.close();
        db.close();
        return product;
    }
    @SuppressLint("Range")
    public List<Product> getAllProducts() {
        String[] projection = {"product_id", "name", "price", "category_id", "img"};
        Cursor cursor = db.query("Product", projection, null, null, null, null, null);
        List<Product> productList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Product product = new Product();
            product.setProductId(cursor.getInt(cursor.getColumnIndex("product_id")));
            product.setNameProduct(cursor.getString(cursor.getColumnIndex("name")));
            product.setPrice(cursor.getString(cursor.getColumnIndex("price")));
            product.setMenuId(cursor.getInt(cursor.getColumnIndex("category_id")));
            product.setImage(cursor.getString(cursor.getColumnIndex("img")));
            productList.add(product);
        }
        cursor.close();
        db.close();
        return productList;
    }

    public int updateProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put("name", product.getNameProduct());
        values.put("price", product.getPrice());
        values.put("menu_id", product.getMenuId());
        values.put("img", product.getImage());
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
