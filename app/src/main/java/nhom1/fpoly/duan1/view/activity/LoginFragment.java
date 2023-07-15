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
import androidx.fragment.app.FragmentTransaction;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.dao.AdminDao;
import nhom1.fpoly.duan1.view.admin.AdminActivity;

public class LoginFragment extends Fragment {
    EditText edt_login_username, edt_login_password;
    Button btn_login;
    TextView txt_register;
    AdminDao adminDao;

    @SuppressLint("SuspiciousIndentation")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        edt_login_username = view.findViewById(R.id.edt_login_username);
        edt_login_password = view.findViewById(R.id.edt_login_password);
        txt_register = view.findViewById(R.id.txt_login_register);
        btn_login = view.findViewById(R.id.btn_login);

        btn_login.setOnClickListener(login -> {
            String username = edt_login_username.getText().toString().trim();
            String password = edt_login_password.getText().toString().trim();
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireActivity(), "", Toast.LENGTH_SHORT).show();
            } else
                adminDao = new AdminDao(getContext());
            if (adminDao.checkUserPassword(username, password)) {
                Toast.makeText(requireActivity(), "login admin success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(requireActivity(), AdminActivity.class));
            } else {
                Toast.makeText(requireActivity(), "login admin failed", Toast.LENGTH_SHORT).show();
            }
        });

        txt_register.setOnClickListener(register -> {
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.login_and_register, new RegisterFragment());
            transaction.commit();
        });

        return view;
    }
}