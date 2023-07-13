package nhom1.fpoly.duan1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CreateDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "Delivery.db";
    private static final int DB_VERSION = 1;

    public CreateDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb_user = "create table User ( " +
                "id_user integer primary key autoincrement, " +
                "fullName text, " +
                "username text, " +
                "password text, " +
                "email text, " +
                "address text, " +
                "phone text )";
        db.execSQL(tb_user);

        String tb_admin = "create table Admin ( " +
                "id_admin integer primary key autoincrement, " +
                "fullName text, " +
                "username text, " +
                "password text )";
        db.execSQL(tb_admin);

        String tb_category = "create table Category ( " +
                "id_category integer primary key autoincrement, " +
                "name text, " +
                "description text, " +
                "image_url text )";
        db.execSQL(tb_category);

        String tb_product = "create table Products ( " +
                "id_product integer primary key autoincrement, " +
                "category_id integer references Category(id_category), " +
                "name text, " +
                "description text, " +
                "image_url text, " +
                "price integer )";
        db.execSQL(tb_product);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
