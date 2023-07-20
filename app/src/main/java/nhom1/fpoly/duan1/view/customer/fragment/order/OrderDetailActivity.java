package nhom1.fpoly.duan1.view.customer.fragment.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.adapter.customer.OrderDetailAdapter;
import nhom1.fpoly.duan1.dao.OrderDetailDao;
import nhom1.fpoly.duan1.model.OrderDetail;

public class OrderDetailActivity extends AppCompatActivity {

    private ListView listView;
    private OrderDetailDao orderDetailDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        listView = findViewById(R.id.lisview_view_order); // Replace 'listView' with the actual ID of your ListView
        orderDetailDao = new OrderDetailDao(this);

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