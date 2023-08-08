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
                "username text, " +
                "password text )";
        db.execSQL(tb_user);
        String insert_user = "insert into Customer values " +
                "(0, 'c', '1' ) ";
        db.execSQL(insert_user);

        String tb_admin = "create table Admin ( " +
                "id_admin integer primary key autoincrement, " +
                "username text, " +
                "password text )";
        db.execSQL(tb_admin);
        String insert_admin = "insert into Admin values (0, 'admin', 'admin') ";
        db.execSQL(insert_admin);

        String tb_category = "create table Category ( " +
                "id_category integer primary key autoincrement, " +
                "name text, " +
                "image_url text )";
        db.execSQL(tb_category);
        String insert_category = "insert into Category values " +
                "(0, 'Cà phê', 'https://product.hstatic.net/1000405326/product/1-ca-phe-sua-1586320543_db6b2889d0754ecdbd256999a539985c_abdd8d86faeb4c57b99c40c4845c929a_1024x1024.jpg'), " +
                "(1, 'Trà sữa', 'https://xingfutangvietnam.com/wp-content/uploads/2021/10/Hoa-la%CC%80i-1-e1634878229267-768x737.png'), " +
                "(2, 'Trà chanh', 'https://bizweb.dktcdn.net/thumb/1024x1024/100/373/845/products/trachanh.png?v=1576484788183') ";
        db.execSQL(insert_category);

        String tb_product = "create table Products ( " +
                "id_product integer primary key autoincrement, " +
                "category_id integer references Category(id_category), " +
                "name text, " +
                "description text, " +
                "image_url text, " +
                "hetHang text, " +
                "price integer )";
        db.execSQL(tb_product);
        String insert_product = "insert into Products values " +
                "(0, 0, 'Cà phê sữa đá', 'Cà phê sữa đá là một loại thức uống thông dụng ở Việt Nam. Cà phê sữa đá truyền thống được làm từ cà phê nguyên chất đựng trong phin với sữa đặc có đường và bỏ đá vào trong một cái ly bằng thủy tinh rồi thưởng thức.', 'https://product.hstatic.net/1000405326/product/1-ca-phe-sua-1586320543_db6b2889d0754ecdbd256999a539985c_abdd8d86faeb4c57b99c40c4845c929a_1024x1024.jpg','Còn hàng', 15000), " +
                "(1, 1, 'Trà sữa chân châu đường đen', 'Trà sữa trân châu đường đen được yêu thích nhờ sự kết hợp hết sức hoàn hảo giữa vị trà sữa thơm béo và trân châu đường đen mềm, ngọt.', 'https://xingfutangvietnam.com/wp-content/uploads/2021/10/Hoa-la%CC%80i-1-e1634878229267-768x737.png','Còn hàng', 25000), " +
                "(2,2, 'Trà chanh mật ong', 'Trà chanh mật ong là sự kết hợp hoàn hảo giữa những nguyên liệu tự nhiên, giàu vitamin và dưỡng chất, không chỉ mang đến hương vị sảng khoái mà còn rất tốt cho sức khỏe. Với các công thức dưới đây bạn có thể pha trà chanh mật ong một cách dễ dàng chỉ trong 5-10 phút thao tác. Xem ngay cách làm chi tiết và bắt tay thực hiện nhé! ', 'https://file.hstatic.net/1000135323/file/c1_a8c0faa291c84594bf0939034d1b5bdd_grande.jpg','Còn Hàng', 10000), " +
                "(3,2, 'Trà tắc nha đam', 'Trà tắc nha đam Sài Gòn là món đồ uống đơn giản, bình dân nhưng mang hương vị chua ngọt thanh mát cực kỳ đã khát. Mùa hè này nhất định bạn phải thử thưởng thức ngay đấy nhé.', 'https://getngo.vn/wp-content/uploads/2023/04/tra-tac-nha-dam-sai-gon-1.jpg','Còn Hàng', 10000), " +
                "(4,2, 'Trà đào', 'Chút biến tấu từ vị đậm của trà, vị chua nhẹ của chanh, và thơm thanh của đào làm nên thức uống vạn người mê. Cùng khám phá Trà Đào thơm ngon, mát rượi này nhé.', 'https://www.unileverfoodsolutions.com.vn/dam/global-ufs/mcos/phvn/vietnam/calcmenu/recipes/VN-recipes/other/fresh-peach-tea/main-header.jpg','Hết hàng', 10000), " +
                "(5,1, 'Trà sữa mattcha', 'Trà sữa matcha mát lạnh, thơm mùi trà xanh cùng vị béo ngậy và ngọt của sữa hòa quyện tạo nên một ly trà sữa ngon tuyệt kích thích mọi giác quan.', 'https://lh3.googleusercontent.com/-jaAQV4KVV-Q/Y1K1do-Nn3I/AAAAAAAAhLI/sZK_VQZ4rNUFXgLrW7KZ4fzf5SSMzxHXACNcBGAsYHQ/s16000/sp-tra-sua-matcha.webp','Còn hàng', 10000), " +
                "(6,0, 'Bạc xỉu', 'Bạc xỉu cà phê đá là thức uống rất quen thuộc với người Việt mỗi khi đến quán cà phê. Đồ uống này có nhiều cách pha chế với những hương vị.', 'https://mccoffeetanphu.com/uploads/source/mccoffee/anh-chup-man-hinh-2021-01-25-luc-132339-4855.png','Còn hàng', 10000)";
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
                "(0, 0,'Lê cân','0854754764','12-Xuân Phương-Hà Nội', '2023-07-17', 30000, 'Đã đặt hàng'), " +
                "(1, 0,'Hồng anh','0987654332','43-Mỹ Đình-Đà Nam', '2023-07-16', 45000, 'Đã giao hàng'), " +
                "(2, 0,'Tel My','0987654323','Lê Nam-Thái Hòa', '2023-07-16', 45000, 'Đã thanh toán'), " +
                "(3, 0,'Uyen My','0987654334','Hà Thành-Hà Lội', '2023-07-15', 20000, 'Đang giao hàng') ";
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
                "(4, 2, 1, 4), " +
                "(5, 3, 2, 2) ";
        db.execSQL(insertOrderDetail);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
