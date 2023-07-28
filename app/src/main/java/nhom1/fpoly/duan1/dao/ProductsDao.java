package nhom1.fpoly.duan1.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nhom1.fpoly.duan1.database.CreateDatabase;
import nhom1.fpoly.duan1.model.Product;

public class ProductsDao {
    public final SQLiteDatabase database;
    CreateDatabase createDatabase;

    public ProductsDao(Context context) {
        createDatabase = new CreateDatabase(context);
        database = createDatabase.getWritableDatabase();
    }
    @SuppressLint("Range")
    public List<Product> getProductsByCategoryId(int categoryId) {
        List<Product> productList = new ArrayList<>();

        String query = "SELECT * FROM Products WHERE category_id = ?";
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(categoryId)});

        if (cursor.moveToFirst()) {
            do {
                Product products = new Product();
                products.setId_product(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id_product"))));
                products.setName_product(cursor.getString(cursor.getColumnIndex("name")));
                products.setDesc_product(cursor.getString(cursor.getColumnIndex("description")));
                products.setImg_product(cursor.getString(cursor.getColumnIndex("image_url")));
                products.setPrice(cursor.getString(cursor.getColumnIndex("price")));
                productList.add(products);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return productList;
    }

    @SuppressLint("Range")
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<Product>();
        Cursor cursor = database.rawQuery("select * from Products", null);
        if (cursor.moveToFirst()) {
            do {
                Product products = new Product();
                products.setId_product(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id_product"))));
                products.setName_product(cursor.getString(cursor.getColumnIndex("name")));
                products.setDesc_product(cursor.getString(cursor.getColumnIndex("description")));
                products.setImg_product(cursor.getString(cursor.getColumnIndex("image_url")));
                products.setPrice(cursor.getString(cursor.getColumnIndex("price")));
                productList.add(products);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return productList;
    }
}
