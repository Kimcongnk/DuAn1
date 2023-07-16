package nhom1.fpoly.duan1.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nhom1.fpoly.duan1.database.DBHelper;
import nhom1.fpoly.duan1.model.Categories;
import nhom1.fpoly.duan1.model.Category;

public class CategoryDao {
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public CategoryDao(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean addCategory(Categories category) {
        ContentValues values = new ContentValues();
        values.put("nameType", category.getName_categories());
        values.put("imageCategory", category.getImg_categories());
        long check = db.insert("Category", null, values);
        return check != -1;
    }
    @SuppressLint("Range")
    public Category getCategoryById(long categoryId) {
        String[] projection = {"menu_id", "nameType", "imageCategory"};
        String selection = "menu_id = ?";
        String[] selectionArgs = {String.valueOf(categoryId)};
        Cursor cursor = db.query("Category", projection, selection, selectionArgs, null, null, null);

        Category category = null;
        if (cursor.moveToFirst()) {
            category = new Category();
            category.setMenuId(cursor.getInt(cursor.getColumnIndex("menu_id")));
            category.setNameType(cursor.getString(cursor.getColumnIndex("nameType")));
            category.setImageCategory(cursor.getString(cursor.getColumnIndex("imageCategory")));
        }
        cursor.close();
        db.close();
        return category;
    }
    @SuppressLint("Range")
    public List<Category> getAllCategories() {
        String[] projection = {"menu_id", "nameType", "imageCategory"};
        Cursor cursor = db.query("Category", projection, null, null, null, null, null);
        List<Category> categoryList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Category category = new Category();
            category.setMenuId(cursor.getInt(cursor.getColumnIndex("menu_id")));
            category.setNameType(cursor.getString(cursor.getColumnIndex("nameType")));
            category.setImageCategory(cursor.getString(cursor.getColumnIndex("imageCategory")));
            categoryList.add(category);
        }
        cursor.close();
        db.close();
        return categoryList;
    }

    public int updateCategory(Category category) {
        ContentValues values = new ContentValues();
        values.put("nameType", category.getNameType());
        values.put("imageCategory", category.getImageCategory());
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
