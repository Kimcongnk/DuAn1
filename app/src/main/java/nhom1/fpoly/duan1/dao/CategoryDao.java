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

    public boolean updateCategoryById(int categoryId, String newName, String newImage) {
        ContentValues values = new ContentValues();
        values.put("name", newName);
        values.put("image_url", newImage);

        int check = database.update("Category", values, "id_category=?", new String[]{String.valueOf(categoryId)});
        return check > 0;
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
