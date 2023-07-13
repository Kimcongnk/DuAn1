package nhom1.fpoly.duan1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;

    // Tạo bảng User
    private static final String CREATE_TABLE_USER = "CREATE TABLE User (" +
            "user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "username TEXT," +
            "email TEXT)";

    // Tạo bảng Category
    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE Category (" +
            "menu_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nameType TEXT)";

    // Tạo bảng Product
    private static final String CREATE_TABLE_PRODUCT = "CREATE TABLE Product (" +
            "product_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nameProduct TEXT," +
            "price TEXT," +
            "menu_id INTEGER," +
            "img TEXT," +
            "FOREIGN KEY(category_id) REFERENCES Category(category_id))";

    // Tạo bảng Order
    private static final String CREATE_TABLE_ORDER = "CREATE TABLE Orderr (" +
            "order_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "user_id INTEGER," +
            "orderDate TEXT," +
            "totalMoney REAL," +
            "status TEXT," +
            "FOREIGN KEY(user_id) REFERENCES User(user_id))";

    // Tạo bảng Order Detail
    private static final String CREATE_TABLE_ORDER_DETAIL = "CREATE TABLE OrderDetail (" +
            "detail_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "order_id INTEGER," +
            "product_id INTEGER," +
            "quantity INTEGER," +
            "FOREIGN KEY(order_id) REFERENCES Orderr(order_id)," +
            "FOREIGN KEY(product_id) REFERENCES Product(product_id))";

    private static final String CREATE_TABLE_CART = "CREATE TABLE Cart (" +
            "cart_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "user_id INTEGER," +
            "product_id INTEGER," +
            "quantity INTEGER," +
            "FOREIGN KEY(user_id) REFERENCES User(user_id)," +
            "FOREIGN KEY(product_id) REFERENCES Product(product_id))";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo các bảng trong cơ sở dữ liệu
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_PRODUCT);
        db.execSQL(CREATE_TABLE_ORDER);
        db.execSQL(CREATE_TABLE_ORDER_DETAIL);
        db.execSQL(CREATE_TABLE_CART);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa các bảng cũ nếu tồn tại và tạo lại
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS Category");
        db.execSQL("DROP TABLE IF EXISTS Product");
        db.execSQL("DROP TABLE IF EXISTS Orderr");
        db.execSQL("DROP TABLE IF EXISTS OrderDetail");
        db.execSQL("DROP TABLE IF EXISTS Cart");
        onCreate(db);
    }
}
