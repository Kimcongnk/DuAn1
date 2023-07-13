package nhom1.fpoly.duan1.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.fragment.LoginFragment;

public class LoginAndRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_register);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.login_and_register, new LoginFragment());
        transaction.commit();
    }
}