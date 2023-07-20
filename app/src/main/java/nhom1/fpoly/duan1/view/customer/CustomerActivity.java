package nhom1.fpoly.duan1.view.customer;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.view.customer.fragment.AccountFragment;
import nhom1.fpoly.duan1.view.customer.fragment.CartFragment;
import nhom1.fpoly.duan1.view.customer.fragment.HomeFragment;
import nhom1.fpoly.duan1.view.customer.fragment.OrderFragment;

public class CustomerActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView_customer);

        if (savedInstanceState == null) {
            replaceFragment(new HomeFragment());
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.nav_cart) {
                replaceFragment(new CartFragment());
            } else if (item.getItemId() == R.id.nav_order) {
                replaceFragment(new OrderFragment());
            } else if (item.getItemId() == R.id.nav_account) {
                replaceFragment(new AccountFragment());
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_customer, fragment);
        fragmentTransaction.commit();
    }

}