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
import nhom1.fpoly.duan1.dao.CustomerDao;
import nhom1.fpoly.duan1.model.Customer;

public class RegisterFragment extends Fragment {

    public static final String TAG = "RegisterFragment";


    EditText edt_register_username, edt_register_password, edt_register_fullName, edt_register_confirmPassword;
    Button btn_register;

    ImageView img_back;
    CustomerDao customerDao;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        edt_register_username = view.findViewById(R.id.edt_register_username);
        edt_register_password = view.findViewById(R.id.edt_register_password);
        edt_register_confirmPassword = view.findViewById(R.id.edt_register_confirm_pass);

        btn_register = view.findViewById(R.id.btn_register);
        

        customerDao = new CustomerDao(getContext());


        btn_register.setOnClickListener(register -> {
            doRegister();
        });




        return view;
    }

    private void doRegister() {
        String fullName = edt_register_fullName.getText().toString();
        String username = edt_register_username.getText().toString();
        String password = edt_register_password.getText().toString();
        String confirmPass = edt_register_confirmPassword.getText().toString();

        if (fullName.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPass.isEmpty()) {
            Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show();
        } else if (!confirmPass.equals(password)) {
            Toast.makeText(requireContext(), "password do not match", Toast.LENGTH_SHORT).show();
        } else {
            Customer customer = new Customer();
            customer.setFullName(fullName);
            customer.setUsername(username);
            customer.setPassword(password);
            if (customerDao.insertCustomer(customer)) {
                Toast.makeText(requireContext(), "Register successfully", Toast.LENGTH_SHORT).show();
                getParentFragmentManager().popBackStack();
            } else {
                Toast.makeText(requireContext(), "Register failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}