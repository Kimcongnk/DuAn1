package nhom1.fpoly.duan1.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "mydatabase.db";
    static final int DB_VERSION = 1;
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tb_user = "create table User ( " +
                "id_user integer primary key autoincrement, " +
                "nameUser text, " +
                "phone text, " +
                "address text, " +
                "password text)";
        sqLiteDatabase.execSQL(tb_user);

        String tb_admin = "create table Admin ( " +
                "id_admin integer primary key autoincrement, " +
                "nameAdmin text, " +
                "password text )";
        sqLiteDatabase.execSQL(tb_admin);
        String insert_admin = "insert into Admin values (0,'admin', 'admin') ";
        sqLiteDatabase.execSQL(insert_admin);

        String tb_category = "create table Category ( " +
                "id_menu integer primary key autoincrement, " +
                "imageCategory text, " +
                "nameType text)";
        sqLiteDatabase.execSQL(tb_category);

        String tb_product = "create table Products ( " +
                "id_product integer primary key autoincrement, " +
                "id_menu integer references Category(id_menu), " +
                "nameProducts text, " +
                "description text, " +
                "imageProduct text, " +
                "price integer )";
        sqLiteDatabase.execSQL(tb_product);
        String insert_product = "insert into Products values " +
                "(0, 0, 'Cà phê sữa đá', 'Cà phê sữa đá là một loại thức uống thông dụng ở Việt Nam. Cà phê sữa đá truyền thống được làm từ cà phê nguyên chất đựng trong phin với sữa đặc có đường và bỏ đá vào trong một cái ly bằng thủy tinh rồi thưởng thức.', 'https://conducmegiadinh.myharavan.com/products/ca-phe-sua', 150000), " +
                "(1, 1, 'Trà sữa chân châu đường đen', 'Trà sữa trân châu đường đen được yêu thích nhờ sự kết hợp hết sức hoàn hảo giữa vị trà sữa thơm béo và trân châu đường đen mềm, ngọt.', 'https://tiki.vn/tra-sua-tran-chau-duong-den-leader-22g-6-goi-bot-tran-chau-an-lien-50g-6-goi-hop-2-hop-p93748472.html', 250000), " +
                "(2,2, 'Sting', 'Sting là sản phẩm nước tăng lực với mùi vị thơm ngon, sảng khoái. Nước tăng lực Sting giúp cơ thể bù đắp nước, bổ sung năng lượng, vitamin C và E, giúp xua tan cơn khát và cảm giác mệt mỏi.', 'https://sieuthiducthanh.com/nn-s-ting-dau-pet-330ml', 10000), " +
                "(3,2, 'Coca cola', 'Coca-Cola (hay còn gọi là Coca, Coke) là một thương hiệu nước ngọt có ga chứa nước cacbon dioxide bão hòa được sản xuất bởi Công ty Coca-Cola. Coca-Cola được điều chế bởi dược sĩ John Pemberton vào cuối thế kỷ XIX với mục đích ban đầu là trở thành một loại biệt dược.', 'https://losupply.vn/3133838-nuoc-ngot/66821253-nuoc-ngot-coca-cola-lon-320ml-24-lon', 10000), " +
                "(4,2, 'trà ô long Tea+ plus', 'Trà Ô Long TEA+ Plus là sản phẩm mang thương hiệu Suntory đầu tiên được ra mắt tại thị trường Việt Nam từ tháng 8 từ tháng 8 năm 2013. OTPP trong Trà Ô Long TEA+ (Plus) hạn chế hấp thu chất béo, sẽ giúp bạn luôn cảm thấy người nhẹ nhàng, dáng thanh tao. - Vị trà Ô Long thanh mát đem lại cho bạn cảm giác nhẹ nhàng.', 'https://www.suntory.vn/vi/what_we_do/tea_plus_oolong_tea.html', 10000), " +
                "(5,2, 'Pepsi', 'Thức uống có gas Pepsi có hương vị lôi cuốn, vị ngọt nhẹ, không gắt, mang đến cảm giác sảng khoái trong những ngày hè nóng bức và sau những giờ hoạt động mạnh.', 'https://farmersmarket.vn/products/nuoc-ngot-pepsi', 10000)";
        sqLiteDatabase.execSQL(insert_product);

        String tb_cart = "create table Cart ( " +
                "cart_id integer primary key autoincrement, " +
                "id_product integer references Products(id_product), " +
                "id_user integer references User(id_user), " +
                "totaItem text)";
        sqLiteDatabase.execSQL(tb_cart);

        String tb_Oder = "create table Oder ( " +
                "id_oder integer primary key autoincrement, " +
                "id_user integer references User(id_user), " +
                "dateOder text, " +
                "totalMoney text, " +
                "status text )";
        sqLiteDatabase.execSQL(tb_Oder);


        String tb_OderDetail = "create table OrderDetail ( " +
                "id_oderDetail integer primary key autoincrement, " +
                "id_oder integer references Oder(id_product), " +
                "id_product integer references Products(id_product), " +
                "quantyti integer)";
        sqLiteDatabase.execSQL(tb_OderDetail);




    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
