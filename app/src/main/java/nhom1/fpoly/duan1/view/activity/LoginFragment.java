package nhom1.fpoly.duan1.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.dao.AdminDao;
import nhom1.fpoly.duan1.dao.CustomerDao;
import nhom1.fpoly.duan1.view.customer.CustomerActivity;

public class LoginFragment extends Fragment {
    EditText edt_login_username, edt_login_password;
    Button btn_login;
    TextView txt_register;
    AdminDao adminDao;
    CustomerDao customerDao;

    @SuppressLint("SuspiciousIndentation")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        edt_login_username = view.findViewById(R.id.edt_login_username);
        edt_login_password = view.findViewById(R.id.edt_login_password);

        btn_login = view.findViewById(R.id.btn_login);

        btn_login.setOnClickListener(login -> {
            String username = edt_login_username.getText().toString().trim();
            String password = edt_login_password.getText().toString().trim();
            if (username.isEmpty()) {
              edt_login_username.setError("Không để trống");
            } if (password.isEmpty()) {
              edt_login_password.setError("Không để trống");
            } else {
                adminDao = new AdminDao(getContext());
                customerDao = new CustomerDao(getContext());
                if (adminDao.checkUserPasswordAdmin(username, password)) {
                    Toast.makeText(requireActivity(), "login admin success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(requireActivity(), CustomerActivity.class));
                } else if (customerDao.checkUserPasswordCustomer(username, password)){
                    Toast.makeText(requireActivity(), "login customer success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(requireActivity(), CustomerActivity.class));
                }else {
                    Toast.makeText(requireActivity(), "login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }
}