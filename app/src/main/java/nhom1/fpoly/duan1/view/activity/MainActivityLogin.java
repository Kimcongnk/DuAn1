package nhom1.fpoly.duan1.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.adapter.customer.LoginViewAdapter;

public class MainActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_login);
        ViewPager2 viewPager2 = (ViewPager2) findViewById(R.id.viewpager2_login);
        LoginViewAdapter loginViewAdapter = new LoginViewAdapter(MainActivityLogin.this);
        viewPager2.setAdapter(loginViewAdapter);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Đăng nhập");

                } else if (position == 1) {
                    tab.setText("Tạo tài khoản");
                }
            }
        }).attach();

    }
}