package nhom1.fpoly.duan1.view.customer.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.dao.SessionManager;
import nhom1.fpoly.duan1.view.activity.LoginFragment;
import nhom1.fpoly.duan1.view.activity.MainActivityLogin;

public class AccountFragment extends Fragment {

private Button dangXuat;
private SessionManager sessionManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_account, container, false);
        dangXuat = view.findViewById(R.id.dang_xuat);
        sessionManager = new SessionManager(getContext());
        dangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logoutCustomer();
                startActivity(new Intent(getContext(), MainActivityLogin.class));
            }
        });
        return view;
    }
}