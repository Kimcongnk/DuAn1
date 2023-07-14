package nhom1.fpoly.duan1.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nhom1.fpoly.duan1.database.DBHelper;
import nhom1.fpoly.duan1.model.Category;

public class CategoryDao {
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public CategoryDao(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long addCategory(Category category) {
        ContentValues values = new ContentValues();
        values.put("name", category.getNameType());
        long categoryId = db.insert("Category", null, values);
        db.close();
        return categoryId;
    }
    @SuppressLint("Range")
    public Category getCategoryById(long categoryId) {
        String[] projection = {"menu_id", "nameType"};
        String selection = "menu_id = ?";
        String[] selectionArgs = {String.valueOf(categoryId)};
        Cursor cursor = db.query("Category", projection, selection, selectionArgs, null, null, null);

        Category category = null;
        if (cursor.moveToFirst()) {
            category = new Category();
            category.setMenuId(cursor.getInt(cursor.getColumnIndex("menu_id")));
            category.setNameType(cursor.getString(cursor.getColumnIndex("nameType")));
        }
        cursor.close();
        db.close();
        return category;
    }
    @SuppressLint("Range")
    public List<Category> getAllCategories() {
        String[] projection = {"menu_id", "nameType"};
        Cursor cursor = db.query("Category", projection, null, null, null, null, null);
        List<Category> categoryList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Category category = new Category();
            category.setMenuId(cursor.getInt(cursor.getColumnIndex("menu_id")));
            category.setNameType(cursor.getString(cursor.getColumnIndex("nameType")));
            categoryList.add(category);
        }
        cursor.close();
        db.close();
        return categoryList;
    }

    public int updateCategory(Category category) {
        ContentValues values = new ContentValues();
        values.put("name", category.getNameType());
        String whereClause = "menu_id = ?";
        String[] whereArgs = {String.valueOf(category.getMenuId())};
        int rowsAffected = db.update("Category", values, whereClause, whereArgs);
        db.close();
        return rowsAffected;
    }

    public int deleteCategory(int categoryId) {
        String whereClause = "menu_id = ?";
        String[] whereArgs = {String.valueOf(categoryId)};
        int rowsAffected = db.delete("Category", whereClause, whereArgs);
        db.close();
        return rowsAffected;
    }
}
