package nhom1.fpoly.duan1.view.customer.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.adapter.customer.CartAdapter;
import nhom1.fpoly.duan1.adapter.customer.SelectedItemsAdapter;
import nhom1.fpoly.duan1.dao.CartDao;
import nhom1.fpoly.duan1.dao.CustomerDao;
import nhom1.fpoly.duan1.dao.OrderDao;
import nhom1.fpoly.duan1.dao.OrderDetailDao;
import nhom1.fpoly.duan1.dao.SessionManager;
import nhom1.fpoly.duan1.model.Cart;
import nhom1.fpoly.duan1.model.Customer;
import nhom1.fpoly.duan1.model.Order;
import nhom1.fpoly.duan1.model.OrderDetail;
import nhom1.fpoly.duan1.view.customer.fragment.order.OrderingFragment;

public class ThanhToan extends AppCompatActivity {
    private SelectedItemsAdapter selectedItemsAdapter;
    private RecyclerView recyclerViewOder;
    private EditText txtName, phoneNumber, txtaddress;
    private TextView txtTotal;
    private CustomerDao customerDao;
    private Customer customerArrayList;
    private ArrayList<Cart> selectedItems;
    private Button btnOder;
    private OrderDao orderDao;
    private OrderDetailDao orderDetailDao;
    private SessionManager sessionManager;
    private OrderDetail orderDetail;
    private CartDao cartDao;
    private double totalPrice = 0;
    private ImageView imgBack;

    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH) + 1;
    int day = calendar.get(Calendar.DAY_OF_MONTH);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        txtName = findViewById(R.id.txt_order_name);
        phoneNumber = findViewById(R.id.txt_order_phone);
        txtaddress = findViewById(R.id.txt_order_address);
        btnOder = findViewById(R.id.btn_thanh_toan_order);
        txtTotal = findViewById(R.id.txtTotal);
        imgBack = findViewById(R.id.img_back);
        recyclerViewOder = findViewById(R.id.recycler_view_order);

        customerDao = new CustomerDao(getApplication());
        orderDao = new OrderDao(getApplication());
        orderDetailDao = new OrderDetailDao(getApplication());
        cartDao = new CartDao(getApplication());
        sessionManager = new SessionManager(getApplication());
        customerArrayList = customerDao.getCustomerById(0);

imgBack.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
//        startActivity(new Intent(ThanhToan.this, CartFragment.class));
    }
});


        Intent intent = getIntent();
        if (intent != null) {
            selectedItems = intent.getParcelableArrayListExtra("SELECTED_ITEMS");
        }
        for (Cart item : selectedItems) {
            int quantity = item.getTotalTems();
            double price = item.getPrice();
            totalPrice += quantity * price;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        String formattedPrice = decimalFormat.format(totalPrice);
        txtTotal.setText(formattedPrice);


        selectedItemsAdapter = new SelectedItemsAdapter(selectedItems, this);
        recyclerViewOder.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewOder.setAdapter(selectedItemsAdapter);

        btnOder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomOrderId = generateRandomOrderId();
                String currentDateAndTime = String.format("%02d/%02d/%04d", day, month, year);
                int idUser = sessionManager.getLoggedInCustomerId();

                String status = "Đã đặt hàng";
                Order order = new Order();
                order.setOrderId(randomOrderId);
                order.setIdUser(idUser);
                order.setDateOder(currentDateAndTime);
                order.setTotalMoney(totalPrice);
                order.setStatus(status);
                order.setNameOder(txtName.getText().toString());
                order.setPhoneNumber(phoneNumber.getText().toString());
                order.setAddress(txtaddress.getText().toString());
                if (orderDao.addOrder(order) > 0) {
                    for (Cart cartItem : selectedItems) {
                        orderDetail = new OrderDetail();
                        orderDetail.setOderId(randomOrderId);
                        orderDetail.setProductId(cartItem.getId_product());
                        orderDetail.setQuantity(cartItem.getTotalTems());
                        if (orderDetailDao.addOrderDetail(orderDetail) > 0) {
                            cartDao.deleteCarts(selectedItems);
                            Toast.makeText(ThanhToan.this, "Hàng đang được giao", Toast.LENGTH_SHORT).show();

                      startActivity(new Intent(ThanhToan.this, AccountFragment.class));
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
