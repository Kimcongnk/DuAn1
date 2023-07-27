package nhom1.fpoly.duan1.view.customer.fragment.Status;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.adapter.customer.OrderDetailAdapter;
import nhom1.fpoly.duan1.dao.OrderDao;
import nhom1.fpoly.duan1.dao.OrderDetailDao;
import nhom1.fpoly.duan1.dao.SessionManager;
import nhom1.fpoly.duan1.model.Order;
import nhom1.fpoly.duan1.model.OrderDetail;

public class OrderDetailActivity extends AppCompatActivity {

    private ListView listView;
    private OrderDetailDao orderDetailDao;
    private TextView txtName, phoneNumber, txtaddress, txtTotal;
    private OrderDao orderDao;
    private OrderDetailAdapter adapter;
    private Button xoaOder;
    private ArrayList<Order> orderArrayList = new ArrayList<>();
    private  int id_Order = 0;
    private SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        txtName = findViewById(R.id.txt_order_name);
        phoneNumber = findViewById(R.id.txt_order_phone);
        txtaddress = findViewById(R.id.txt_order_address);
        txtTotal = findViewById(R.id.txtTotal);
        xoaOder = findViewById(R.id.button);
        listView = findViewById(R.id.lisview_view_order);
        orderDetailDao = new OrderDetailDao(this);
        orderDao = new OrderDao(this);
        sessionManager = new SessionManager(getApplicationContext());

        if(sessionManager.isLoggedIn() == true){
            xoaOder.setText("Hủy đơn hàng");

        }else {
            xoaOder.setText("Xác nhận" + " "+ id_Order);

        }

        Intent intent = getIntent();

        if (intent != null) {
            id_Order = intent.getIntExtra("order_id", -1);
        }xoaOder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sessionManager.isLoggedIn() == true){
                    orderDao.deleteOrder(id_Order);

                    Toast.makeText(OrderDetailActivity.this, "Đã hủy đơn: " + id_Order, Toast.LENGTH_SHORT).show();
                    finish();
                }else {

                    orderDao.updateOrderStatus(id_Order, "Đang giao hàng");
                    Toast.makeText(OrderDetailActivity.this, "", Toast.LENGTH_SHORT).show();

                }


            }
        });

        orderArrayList = (ArrayList<Order>) orderDao.getAllOrdersWithDetailsById(id_Order);
        for (Order order : orderArrayList) {
            txtName.setText(order.getNameOder());
            phoneNumber.setText(order.getPhoneNumber());
            txtaddress.setText(order.getAddress());
        }
        // Get the list of OrderDetail items with product information
        List<OrderDetail> orderDetailList = orderDetailDao.getOrderDetailsByOrderId(id_Order);

        // Create and set the custom adapter to the ListView
        adapter = new OrderDetailAdapter(this, orderDetailList);
        listView.setAdapter(adapter);
        double totalAmount = adapter.totalOrderDetail();

        // Assuming you have a TextView with ID txtTotal in your layout, set the total amount to it

        txtTotal.setText(String.format(Locale.getDefault(), "%.2f", totalAmount));
    }

}