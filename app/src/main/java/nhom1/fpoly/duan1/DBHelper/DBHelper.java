package nhom1.fpoly.duan1.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "ONEONE";
    static final int DB_VERSION = 1;
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tb_user = "create table User ( " +
                "id_user integer primary key autoincrement, " +
                "namUser text, " +
                "password text)";
        sqLiteDatabase.execSQL(tb_user);

        String tb_admin = "create table Admin ( " +
                "id_admin integer primary key autoincrement, " +
                "fullName text, " +
                "username text, " +
                "password text )";
        sqLiteDatabase.execSQL(tb_admin);

        String tb_category = "create table Category ( " +
                "id_menu integer primary key autoincrement, " +
                "nameType text)";
        sqLiteDatabase.execSQL(tb_category);

        String tb_product = "create table Products ( " +
                "id_product integer primary key autoincrement, " +
                "id_menu integer references Category(id_menu), " +
                "nameProducts text, " +
                "image_url text, " +
                "price integer )";
        sqLiteDatabase.execSQL(tb_product);

        String tb_cart = "create table Cart ( " +
                "cart_id integer primary key autoincrement, " +
                "id_product integer references Category(id_product), " +
                "id_user integer references Category(id_user), " +
                "totaITEM text)";
        sqLiteDatabase.execSQL(tb_cart);

        String tb_Oder = "create table Oder ( " +
                "id_Oder integer primary key autoincrement, " +
                "id_user integer references Category(id_user), " +
                "dateOder text, " +
                "totalMoney text, " +
                "status text )";
        sqLiteDatabase.execSQL(tb_Oder);


        String tb_OderDetail = "create table Cart ( " +
                "id_OderDetail integer primary key autoincrement, " +
                "id_Oder integer references Category(id_product), " +
                "id_product integer references Category(id_product), " +
                "quantyti text)";
        sqLiteDatabase.execSQL(tb_OderDetail);




    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
