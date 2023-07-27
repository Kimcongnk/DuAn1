package nhom1.fpoly.duan1.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nhom1.fpoly.duan1.database.CreateDatabase;
import nhom1.fpoly.duan1.model.Product;

public class ProductsDao {

    CreateDatabase createDatabase;

    public ProductsDao(Context context) {
        createDatabase = new CreateDatabase(context);

    }

    @SuppressLint("Range")
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> productList = new ArrayList<Product>();
        SQLiteDatabase sqLiteDatabase = createDatabase.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Products", null);
        if (cursor.moveToFirst()) {
            do {
                Product products = new Product();
                products.setId_product(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id_product"))));
                products.setName_product(cursor.getString(cursor.getColumnIndex("name")));
                products.setDesc_product(cursor.getString(cursor.getColumnIndex("description")));
                products.setImg_product(cursor.getString(cursor.getColumnIndex("image_url")));
                products.setPrice(Integer.parseInt(cursor.getString(cursor.getColumnIndex("price"))));
                productList.add(products);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return productList;
    }
    public boolean themProduct(String name_product,String image,int category_id,int price,String desc){
        SQLiteDatabase sqLiteDatabase = createDatabase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put("name",name_product);
        contentValues.put("image_url",image);
        contentValues.put("category_id",category_id);
        contentValues.put("description",desc);
        contentValues.put("price",price);

        long check = sqLiteDatabase.insert("Products",null,contentValues);
        if (check==-1){
            return false;
        }
        return true;

    }
    public int xoaProduct(int id){
        SQLiteDatabase sqLiteDatabase = createDatabase.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT *FROM Cart WHERE id_product=?",new String[]{String.valueOf(id)});
        if (cursor.getCount()!=0){
            return -1;
        }
        long check = sqLiteDatabase.delete("Products","id_product=?",new String[]{String.valueOf(id)});
        if (check==-1){
            return 0;

        }
        return 1;
    }
    public boolean capNhatProduct(int id_product,String name_product,String image,int category_id,int price,String desc){
        SQLiteDatabase sqLiteDatabase = createDatabase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name_product);
        contentValues.put("image_url",image);
        contentValues.put("category_id",category_id);
        contentValues.put("description",desc);
        contentValues.put("price",price);
        long check = sqLiteDatabase.update("Products" ,contentValues,"id_product=?",new String[]{String.valueOf(id_product)});
        if (check ==-1){
            return false;
        }
        return true;
    }
}
