package nhom1.fpoly.duan1.view.customer.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.adapter.customer.CartAdapter;
import nhom1.fpoly.duan1.adapter.customer.SelectedItemsAdapter;
import nhom1.fpoly.duan1.dao.CustomerDao;
import nhom1.fpoly.duan1.dao.OrderDao;
import nhom1.fpoly.duan1.dao.OrderDetailDao;
import nhom1.fpoly.duan1.model.Cart;
import nhom1.fpoly.duan1.model.Customer;
import nhom1.fpoly.duan1.model.Order;
import nhom1.fpoly.duan1.model.OrderDetail;

public class ThanhToan extends AppCompatActivity {
    private SelectedItemsAdapter selectedItemsAdapter;
    private RecyclerView recyclerViewOder;
    private TextView txtName, phoneNumber, txtaddress;
    private CustomerDao customerDao;
    private Customer customerArrayList;
    private List<Cart> selectedItems;
    private Button oder;
    private OrderDao orderDao;
    private OrderDetailDao orderDetailDao;

    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH) + 1;
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);
    int second = calendar.get(Calendar.SECOND);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);

        txtName = findViewById(R.id.txt_order_name);
        phoneNumber = findViewById(R.id.txt_order_phone);
        txtaddress = findViewById(R.id.txt_order_address);
        oder = findViewById(R.id.btn_thanh_toan_order);
        recyclerViewOder = findViewById(R.id.recycler_view_order); // Update the variable name for cart RecyclerView

        customerDao = new CustomerDao(getApplication());
        orderDao = new OrderDao(getApplication());
        orderDetailDao = new OrderDetailDao(getApplication());

        customerArrayList = customerDao.getCustomerById(0);

        txtName.setText("Họ tên: " + customerArrayList.getFullName());
        phoneNumber.setText("Phone: " + customerArrayList.getPhoneNumber());
        txtaddress.setText("Địa chỉ: " + customerArrayList.getAddress());


        Intent intent = getIntent();
        if (intent != null) {
            selectedItems = intent.getParcelableArrayListExtra("SELECTED_ITEMS");
        }


        selectedItemsAdapter = new SelectedItemsAdapter(selectedItems, this);
        recyclerViewOder.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewOder.setAdapter(selectedItemsAdapter);

        oder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomOrderId = generateRandomOrderId();
                String currentDateAndTime = String.format("%02d/%02d/%04d %02d:%02d:%02d", day, month, year, hour, minute, second);

                double totalPrice = selectedItemsAdapter.calculateTotalPrice();
                String status = "Đang giao";
                Order order = new Order();
                order.setOrderId(randomOrderId);
                order.setDateOder(currentDateAndTime);
                order.setTotalMoney(totalPrice);
                order.setStatus(status);
                if (orderDao.addOrder(order) > 0) {
                    for (Cart cartItem : selectedItems) {
                        OrderDetail orderDetail = new OrderDetail();
                        orderDetail.setOderId(randomOrderId);
                        orderDetail.setProductId(cartItem.getProductId());
                        orderDetail.setQuantity(cartItem.getTotalTems());
                        if (orderDetailDao.addOrderDetail(orderDetail) > 0) {
                            Toast.makeText(ThanhToan.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });


    }

    private int generateRandomOrderId() {
        int minOrderId = 1000;
        int maxOrderId = 9999;
        Random random = new Random();
        return random.nextInt(maxOrderId - minOrderId + 1) + minOrderId;
    }

}
