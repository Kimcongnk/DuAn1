package nhom1.fpoly.duan1.view.customer.fragment.Status;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.adapter.customer.OrderDetailAdapter;
import nhom1.fpoly.duan1.dao.OrderDao;
import nhom1.fpoly.duan1.dao.OrderDetailDao;
import nhom1.fpoly.duan1.dao.SessionManager;
import nhom1.fpoly.duan1.model.Order;
import nhom1.fpoly.duan1.model.OrderDetail;

public class SuccessOderActivity extends AppCompatActivity {
    private ListView listView;
    private OrderDetailDao orderDetailDao;
    private TextView txtName, phoneNumber, txtaddress, txtTotal;
    private OrderDao orderDao;
    private OrderDetailAdapter adapter;
    private Button xoaOder;
    private ArrayList<Order> orderArrayList = new ArrayList<>();
    private  int id_Order = 0;
    private SessionManager sessionManager;
    private ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oderok);
        txtName = findViewById(R.id.txt_order_name);
        phoneNumber = findViewById(R.id.txt_order_phone);
        txtaddress = findViewById(R.id.txt_order_address);
        txtTotal = findViewById(R.id.txtTotal);
        xoaOder = findViewById(R.id.button);
        imgBack = findViewById(R.id.img_back);
        listView = findViewById(R.id.lisview_view_order);
        orderDetailDao = new OrderDetailDao(this);
        orderDao = new OrderDao(this);
        sessionManager = new SessionManager(getApplicationContext());
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();    }
        });
        if(sessionManager.isLoggedIn() == true){
            xoaOder.setVisibility(View.GONE);

        }else {
            xoaOder.setVisibility(View.GONE);

        }

        Intent intent = getIntent();

        if (intent != null) {
            id_Order = intent.getIntExtra("order_id", -1);
        }xoaOder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(sessionManager.isLoggedIn() == true){
//
//                    Toast.makeText(DangGiaoHang.this, "Cảm ơn bạn đã ủng hô shop", Toast.LENGTH_SHORT).show();
//                }else {
//
//                    orderDao.updateOrderStatus(id_Order, "Đã thanh toán");
//                    Toast.makeText(DangGiaoHang.this, "Admin", Toast.LENGTH_SHORT).show();
//
//                }


            }
        });



        orderArrayList = (ArrayList<Order>) orderDao.getAllOrdersWithDetailsById(id_Order);
        for (Order order : orderArrayList) {
            txtName.setText("Tên: " + order.getNameOder());
            phoneNumber.setText("SDT: " + order.getPhoneNumber());
            txtaddress.setText("Địa chỉ" + order.getAddress());
        }
        List<OrderDetail> orderDetailList = orderDetailDao.getOrderDetailsByOrderId(id_Order);

        // Create and set the custom adapter to the ListView
        adapter = new OrderDetailAdapter(this, orderDetailList);
        listView.setAdapter(adapter);
        double totalAmount = adapter.totalOrderDetail();



        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        String formattedPrice = decimalFormat.format(totalAmount);
        txtTotal.setText( formattedPrice + "VND");    }

}