package nhom1.fpoly.duan1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CreateDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "Delivery.db";
    private static final int DB_VERSION = 1;

    public CreateDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb_user = "create table Customer ( " +
                "id_customer integer primary key autoincrement, " +
                "fullName text, " +
                "username text, " +
                "password text, " +
                "email text, " +
                "address text, " +
                "phone text )";
        db.execSQL(tb_user);
        String insert_user = "insert into Customer values " +
                "(0, 'Nguyễn Văn A', 'user_test', 'test@123', 'user@gmail.com', 'Hà Nội', '012345687' ), " +
                "(1, 'Nguyễn Văn B', 'user_test1', 'test@123', 'user1@gmail.com', 'Hà Nội', '012345687' ), " +
                "(2, 'Nguyễn Văn C', 'user_test2', 'test@123', 'user2@gmail.com', 'Hà Nội', '012345687' ), " +
                "(3, 'Nguyễn Văn D', 'user_test3', 'test@123', 'user3@gmail.com', 'Hà Nội', '012345687' ) ";
        db.execSQL(insert_user);

        String tb_admin = "create table Admin ( " +
                "id_admin integer primary key autoincrement, " +
                "fullName text, " +
                "username text, " +
                "password text )";
        db.execSQL(tb_admin);
        String insert_admin = "insert into Admin values (0, 'Nguyễn Văn A', 'admin', 'admin') ";
        db.execSQL(insert_admin);

        String tb_category = "create table Category ( " +
                "id_category integer primary key autoincrement, " +
                "name text, " +
                "image_url text )";
        db.execSQL(tb_category);
        String insert_category = "insert into Category values " +
                "(0, 'Cà phê', 'https://vi.m.wikipedia.org/wiki/T%E1%BA%ADp_tin:Caf%C3%A9_con_leche.jpg'), " +
                "(1, 'Trà sữa', 'https://www.bachhoaxanh.com/kinh-nghiem-hay/hoc-cach-pha-tra-sua-o-long-dai-loan-thom-ngon-chuan-vi-ai-cung-me-1374160'), " +
                "(2, 'Nước ngọt', 'https://ecare.vn/blogs/bai-blog-moi/anh-huong-cua-nuoc-ngot-voi-suc-khoe-rang-mieng') ";
        db.execSQL(insert_category);

        String tb_product = "create table Products ( " +
                "id_product integer primary key autoincrement, " +
                "category_id integer references Category(id_category), " +
                "name text, " +
                "description text, " +
                "image_url text, " +
                "price integer )";
        db.execSQL(tb_product);
        String insert_product = "insert into Products values " +
                "(0, 0, 'Cà phê sữa đá', 'Cà phê sữa đá là một loại thức uống thông dụng ở Việt Nam. Cà phê sữa đá truyền thống được làm từ cà phê nguyên chất đựng trong phin với sữa đặc có đường và bỏ đá vào trong một cái ly bằng thủy tinh rồi thưởng thức.', 'https://conducmegiadinh.myharavan.com/products/ca-phe-sua', 150000), " +
                "(1, 1, 'Trà sữa chân châu đường đen', 'Trà sữa trân châu đường đen được yêu thích nhờ sự kết hợp hết sức hoàn hảo giữa vị trà sữa thơm béo và trân châu đường đen mềm, ngọt.', 'https://tiki.vn/tra-sua-tran-chau-duong-den-leader-22g-6-goi-bot-tran-chau-an-lien-50g-6-goi-hop-2-hop-p93748472.html', 25000), " +
                "(2,2, 'Sting', 'Sting là sản phẩm nước tăng lực với mùi vị thơm ngon, sảng khoái. Nước tăng lực Sting giúp cơ thể bù đắp nước, bổ sung năng lượng, vitamin C và E, giúp xua tan cơn khát và cảm giác mệt mỏi.', 'https://sieuthiducthanh.com/nn-s-ting-dau-pet-330ml', 10000), " +
                "(3,2, 'Coca cola', 'Coca-Cola (hay còn gọi là Coca, Coke) là một thương hiệu nước ngọt có ga chứa nước cacbon dioxide bão hòa được sản xuất bởi Công ty Coca-Cola. Coca-Cola được điều chế bởi dược sĩ John Pemberton vào cuối thế kỷ XIX với mục đích ban đầu là trở thành một loại biệt dược.', 'https://losupply.vn/3133838-nuoc-ngot/66821253-nuoc-ngot-coca-cola-lon-320ml-24-lon', 10000), " +
                "(4,2, 'trà ô long Tea+ plus', 'Trà Ô Long TEA+ Plus là sản phẩm mang thương hiệu Suntory đầu tiên được ra mắt tại thị trường Việt Nam từ tháng 8 từ tháng 8 năm 2013. OTPP trong Trà Ô Long TEA+ (Plus) hạn chế hấp thu chất béo, sẽ giúp bạn luôn cảm thấy người nhẹ nhàng, dáng thanh tao. - Vị trà Ô Long thanh mát đem lại cho bạn cảm giác nhẹ nhàng.', 'https://www.suntory.vn/vi/what_we_do/tea_plus_oolong_tea.html', 10000), " +
                "(5,2, 'Pepsi', 'Thức uống có gas Pepsi có hương vị lôi cuốn, vị ngọt nhẹ, không gắt, mang đến cảm giác sảng khoái trong những ngày hè nóng bức và sau những giờ hoạt động mạnh.', 'https://farmersmarket.vn/products/nuoc-ngot-pepsi', 10000)";
        db.execSQL(insert_product);


        String tb_cart = "create table Cart ( " +
                "cart_id integer primary key autoincrement, " +
                "id_product integer references Products(id_product), " +
                "id_customer integer references Customer(id_customer), " +
                "totaItem text)";
        db.execSQL(tb_cart);
        String insert_Cart = "insert into Cart values " +
                "(0, 2, 0, 1), " +
                "(1, 3, 0, 1), " +
                "(2, 1, 0, 1) ";
        db.execSQL(insert_Cart);

        String tb_Oder = "create table Oder ( " +
                "id_oder integer primary key , " +
                "id_customer integer references Customer(id_customer), " +
                "nameCustomerOder text, " +
                "phoneNumber text, " +
                "address text, " +
                "dateOder text, " +
                "totalMoney integer, " +
                "status text )";
        db.execSQL(tb_Oder);
        String insertOder = "INSERT INTO Oder VALUES " +
                "(0, 0,'Lê cân','0854754764','fg', '2023-07-17', 300000, 'Đang giao hàng'), " +
                "(1, 0,'Hồng anh','0987654332','gf', '2023-07-16', 450000, 'Đã giao hàng'), " +
                "(2, 0,'Tel My','0987654323','ff', '2023-07-16', 450000, 'Đã giao hàng'), " +
                "(3, 0,'Uyen My','0987654334','đ', '2023-07-15', 200000, 'Đang giao hàng') ";
        db.execSQL(insertOder);

        String tb_OderDetail = "create table OrderDetail ( " +
                "id_oderDetail integer primary key autoincrement, " +
                "id_oder integer references Oder(id_oder), " +
                "id_product integer references Products(id_product), " +
                "quantyti integer)";
        db.execSQL(tb_OderDetail);
        String insertOrderDetail = "INSERT INTO OrderDetail  VALUES " +
                "(0, 0, 2, 1), " +
                "(1, 0, 1, 1), " +
                "(2, 1, 3, 4), " +
                "(3, 1, 1, 4), " +
                "(4, 3, 2, 2) ";
        db.execSQL(insertOrderDetail);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
