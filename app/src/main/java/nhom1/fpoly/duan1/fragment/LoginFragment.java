package nhom1.fpoly.duan1.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.admin.AdminActivity;
import nhom1.fpoly.duan1.customer.CustomerActivity;
import nhom1.fpoly.duan1.model.Admin;
import nhom1.fpoly.duan1.model.User;

public class LoginFragment extends Fragment {
    EditText edt_login_username, edt_login_password;
    Button btn_login;
    TextView txt_register;
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

        });

        txt_register.setOnClickListener(register -> {
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.login_and_register, new RegisterFragment());
            transaction.commit();
        });

        return view;
    }
}