package nhom1.fpoly.duan1.view.customer.fragment.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.adapter.customer.OrderDetailAdapter;
import nhom1.fpoly.duan1.dao.OrderDao;
import nhom1.fpoly.duan1.dao.OrderDetailDao;
import nhom1.fpoly.duan1.model.Order;
import nhom1.fpoly.duan1.model.OrderDetail;

public class OrderDetailActivity extends AppCompatActivity {

    private ListView listView;
    private OrderDetailDao orderDetailDao;
    private TextView txtName, phoneNumber, txtaddress;
    private OrderDao orderDao;
private ArrayList<Order> orderArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        txtName = findViewById(R.id.txt_order_name);
        phoneNumber = findViewById(R.id.txt_order_phone);
        txtaddress = findViewById(R.id.txt_order_address);
        listView = findViewById(R.id.lisview_view_order);
        orderDetailDao = new OrderDetailDao(this);
        orderDao = new OrderDao(this);

        orderArrayList = (ArrayList<Order>) orderDao.getAllOrdersWithDetails();
        for (Order order : orderArrayList) {
            txtName.setText(order.getNameOder());
            phoneNumber.setText(order.getPhoneNumber());
            txtaddress.setText(order.getAddress());
        }

        Intent intent = getIntent();
        int id_Order = 0;
        if (intent != null) {
            id_Order = intent.getIntExtra("order_id", -1);
        }
        // Get the list of OrderDetail items with product information
        List<OrderDetail> orderDetailList = orderDetailDao.getOrderDetailsByOrderId(id_Order);

        // Create and set the custom adapter to the ListView
        OrderDetailAdapter adapter = new OrderDetailAdapter(this, orderDetailList);
        listView.setAdapter(adapter);
    }

}