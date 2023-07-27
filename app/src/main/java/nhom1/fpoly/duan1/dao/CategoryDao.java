package nhom1.fpoly.duan1.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nhom1.fpoly.duan1.database.CreateDatabase;
import nhom1.fpoly.duan1.model.Categories;

public class CategoryDao {
    private final   SQLiteDatabase database;
    CreateDatabase createDatabase;

    public CategoryDao(Context context) {
        createDatabase = new CreateDatabase(context);
        database = createDatabase.getWritableDatabase();
    }

    public boolean addCategory(Categories categories) {
        ContentValues values = new ContentValues();
        values.put("name", categories.getName_categories());
        values.put("image_url", categories.getImg_categories());
        long check = database.insert("Category", null, values);
        return check != -1;
    }

    public boolean updateCategory(Categories categories) {
        ContentValues values = new ContentValues();
        values.put("id_category", categories.getId());
        values.put("name", categories.getName_categories());
        values.put("image_url", categories.getImg_categories());
        long check = database.update("Category", values, "id_category=?", new String[]{String.valueOf(categories.getId())});
        if (check==-1){
            return false;
        }
        return true;
    }

    public int deleteCategory(int id) {
        Cursor cursor = database.rawQuery("SELECT *FROM Products WHERE category_id =?",new String[]{String.valueOf(id)});
        if (cursor.getCount()!=0){
            return -1;
        }
        long check = database.delete("Category","id_category=?",new String[]{String.valueOf(id)} );
        if (check==-1)
            return 0;
        return 1;
    }

    public ArrayList<Categories> getAllCategories() {
        ArrayList<Categories> categoriesList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = createDatabase.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Category", null);
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
                categoriesList.add(new Categories(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return  categoriesList;
    }
}
