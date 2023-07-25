package nhom1.fpoly.duan1.view.customer.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import nhom1.fpoly.duan1.R;
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
import nhom1.fpoly.duan1.view.activity.MainActivityLogin;
import nhom1.fpoly.duan1.view.activity.SplashActivity;

public class PayOder extends AppCompatActivity {
    private SelectedItemsAdapter selectedItemsAdapter;
    private RecyclerView recyclerViewOder;
    private EditText txtName, phoneNumber, txtaddress;
    private TextView txtTotal, txtTotalSl;
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
    private CartFragment cartFragment;

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
        txtTotalSl = findViewById(R.id.totalSl);
        imgBack = findViewById(R.id.img_back);
        recyclerViewOder = findViewById(R.id.recycler_view_order);

        customerDao = new CustomerDao(getApplication());
        orderDao = new OrderDao(getApplication());
        orderDetailDao = new OrderDetailDao(getApplication());
        cartDao = new CartDao(getApplication());
        sessionManager = new SessionManager(getApplication());
        customerArrayList = customerDao.getCustomerById(0);


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
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


        selectedItemsAdapter = new SelectedItemsAdapter(selectedItems, this);
        recyclerViewOder.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewOder.setAdapter(selectedItemsAdapter);
        selectedItemsAdapter.notifyDataSetChanged();
        int Sl = selectedItemsAdapter.getTotalQuantity();
        txtTotalSl.setText("Tổng số tiền(" + Sl + "sản phẩm):");

        btnOder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtName.getText().toString().trim();
                String phone = phoneNumber.getText().toString().trim();
                String address = txtaddress.getText().toString().trim();
                String phonePattern = "^[0-9]{10}$";
                if (name.isEmpty()) {
                    txtName.setError("Không để trống tên");
                    return;
                }

                if (phone.isEmpty()) {
                    phoneNumber.setError("Điện thoại không được để trống");
//                }else if( phone.matches(phonePattern)){
//                    phoneNumber.setError("Số không đúng định dạng");
                    return;
                }

                if (address.isEmpty()) {
                    txtaddress.setError("Địa chỉ không được để trống");
                    return;
                }

                Dialog dialog = new Dialog(PayOder.this);


                dialog.setContentView(R.layout.dialog_xac_nhan);
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                TextView nameOder = dialog.findViewById(R.id.edt_name_product);
                TextView addres = dialog.findViewById(R.id.edt_price_product);
                TextView phoneNub = dialog.findViewById(R.id.edt_desc_product);
                TextView toatal = dialog.findViewById(R.id.img_select);
                Button xacNhan = dialog.findViewById(R.id.btn_add);
                TextView time = dialog.findViewById(R.id.time);

                nameOder.setText("Tên: " + name);
                addres.setText("Địa chỉ: " + address);
                phoneNub.setText("Phone: " + phone);
                toatal.setText("Tổng số tiền bạn phải thanh toán khi nhận hàng" + formattedPrice);

                xacNhan.setOnClickListener(new View.OnClickListener() {
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

                                    startActivity(new Intent(getApplication(), SuccessOder.class));
                                }
                            }

                        }
                    }
                });

                dialog.show();
//                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnimation;
//                dialog.getWindow().setGravity(Gravity.BOTTOM);
                time.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                new CountDownTimer(6000, 700) {
                    int i = 9;
                    @Override
                    public void onTick(long millisUntilFinished) {
                        time.setText("Hủy (" + (i) + ")");
                        i--;
                    }

                    @Override
                    public void onFinish() {
                        dialog.dismiss();
                    }
                }.start();
            }
        });


    }

    private int generateRandomOrderId() {
        int minOrderId = 1000;
        int maxOrderId = 9999;
        Random random = new Random();
        return random.nextInt(maxOrderId - minOrderId + 1) + minOrderId;
    }

    public void reLoat() {
        selectedItemsAdapter = new SelectedItemsAdapter(selectedItems, this);
        recyclerViewOder.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewOder.setAdapter(selectedItemsAdapter);
        selectedItemsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        selectedItems.clear();
        Toast.makeText(this, "back", Toast.LENGTH_SHORT).show();
    }

    public void dialogXacNhan() {

    }
}
