package nhom1.fpoly.duan1.view.customer.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.dao.CustomerDao;
import nhom1.fpoly.duan1.model.Customer;

public class ThanhToan extends AppCompatActivity {
private TextView txtName, phoneNumber, txtaddress;
private CustomerDao customerDao;
private Customer customerArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        txtName = findViewById(R.id.txt_order_name);
        phoneNumber = findViewById(R.id.txt_order_phone);
        txtaddress = findViewById(R.id.txt_order_address);
        customerDao = new CustomerDao(getApplication());
        customerArrayList = customerDao.getCustomerById(0);

        txtName.setText("Họ tên: " + customerArrayList.getFullName());
        phoneNumber.setText("Phone: "+ customerArrayList.getPhoneNumber());
        txtaddress.setText("Địa chỉ: " + customerArrayList.getAddress());
    }
}