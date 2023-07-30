package nhom1.fpoly.duan1.view.customer.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.dao.CustomerDao;
import nhom1.fpoly.duan1.dao.SessionManager;
import nhom1.fpoly.duan1.view.activity.LoginFragment;
import nhom1.fpoly.duan1.view.activity.MainActivityLogin;

public class AccountFragment extends Fragment {

private Button dangXuat;
LinearLayout layoutDoiMK;
private SessionManager sessionManager;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_account, container, false);
        dangXuat = view.findViewById(R.id.dang_xuat);
        layoutDoiMK = view.findViewById(R.id.layoutDoiMK);
        sessionManager = new SessionManager(getContext());
        dangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logoutCustomer();
                startActivity(new Intent(getContext(), MainActivityLogin.class));
            }
        });
        layoutDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ShowDialogDoiMK();
            }
        });
        return view;
    }

    private void ShowDialogDoiMK() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setNegativeButton("Cập nhật",null)
                .setPositiveButton("Hủy",null);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_doimk,null);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText edPassCu = view.findViewById(R.id.edPassCu);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText edPassMoi = view.findViewById(R.id.edPassMoi);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText edPassNhapLai = view.findViewById(R.id.edPassMoiNhapLai);


        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passCu = edPassCu.getText().toString();
                String passMoi = edPassMoi.getText().toString();
                String passMoiNhapLai = edPassNhapLai.getText().toString();
                if (passCu.equals("")||passMoi.equals("")||passMoiNhapLai.equals("")){
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                if (passMoi.equals(passMoiNhapLai)){

                    int id_customer = sessionManager.getLoggedInCustomerId();

                    // cap nhat

                    CustomerDao customerDao = new CustomerDao(getContext());
                    int check = customerDao.capNhatMK(id_customer,passCu,passMoi);
                    if (check==1){
                        Toast.makeText(getContext(), "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getContext(),DangNhapActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(intent);
                        sessionManager.logoutCustomer();
                        startActivity(new Intent(getContext(), MainActivityLogin.class));
                    }else if (check ==0){
                        Toast.makeText(getContext(), "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(getContext(), "Cập nhật mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Nhập mật khẩu mới không trùng khớp", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}