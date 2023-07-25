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
    private final SQLiteDatabase database;
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

    public int deleteCategory(Categories categories) {
        return database.delete("Category", "id_category=?", new String[]{String.valueOf(categories.getId())});
    }
    @SuppressLint("Range")
    public ArrayList<Categories> getAllCategories() {
        ArrayList<Categories> categoriesList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from Category", null);
        if (cursor.moveToFirst()){
            do {
                Categories categories = new Categories();
                categories.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id_category"))));
                categories.setName_categories(cursor.getString(cursor.getColumnIndex("name")));
                categories.setImg_categories((cursor.getString(cursor.getColumnIndex("image_url"))));
                categoriesList.add(categories);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return  categoriesList;
    }
}
