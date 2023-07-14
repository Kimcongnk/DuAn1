package nhom1.fpoly.duan1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.admin.fragment.ThongKeFragment;
import nhom1.fpoly.duan1.fragment.LoginFragment;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        if (savedInstanceState == null) {
            replaceFragment(new ThongKeFragment());
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_admin, fragment);
        fragmentTransaction.commit();
    }
}