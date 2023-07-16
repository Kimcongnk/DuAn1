package nhom1.fpoly.duan1.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.dao.UserDao;
import nhom1.fpoly.duan1.model.Customer;
import nhom1.fpoly.duan1.model.User;

public class RegisterFragment extends Fragment {

    public static final String TAG = "RegisterFragment";


    EditText edt_register_username, edt_register_password, edt_register_fullName, edt_register_confirmPassword;
    Button btn_register;
    TextView txt_login;
    ImageView img_back;
    UserDao customerDao;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
//        edt_register_fullName = view.findViewById(R.id.edt_register_fullName);
        edt_register_username = view.findViewById(R.id.edt_register_fullName);
        edt_register_password = view.findViewById(R.id.edt_register_password);
        edt_register_confirmPassword = view.findViewById(R.id.edt_register_confirm_pass);
        txt_login = (TextView) view.findViewById(R.id.txt_register_login);
        btn_register = view.findViewById(R.id.btn_register);
        img_back = view.findViewById(R.id.img_register_back);

        customerDao = new UserDao(getContext());


        btn_register.setOnClickListener(register -> {
            doRegister();
        });
        txt_login.setOnClickListener(toLogin -> {
            getParentFragmentManager().popBackStack();
        });
        img_back.setOnClickListener(imgBack -> {
            getParentFragmentManager().popBackStack();
        });


        return view;
    }

    private void doRegister() {
//        String fullName = edt_register_fullName.getText().toString();
        String username = edt_register_username.getText().toString();
        String password = edt_register_password.getText().toString();
        String confirmPass = edt_register_confirmPassword.getText().toString();

        if ( username.isEmpty() || password.isEmpty() || confirmPass.isEmpty()) {
            Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show();
        } else if (!confirmPass.equals(password)) {
            Toast.makeText(requireContext(), "password do not match", Toast.LENGTH_SHORT).show();
        } else {
            User customer = new User();
            customer.setNameUser(username);
            customer.setPassword(password);
            if (customerDao.addUser(customer)) {
                Toast.makeText(requireContext(), "Register successfully", Toast.LENGTH_SHORT).show();
                getParentFragmentManager().popBackStack();
            } else {
                Toast.makeText(requireContext(), "Register failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}