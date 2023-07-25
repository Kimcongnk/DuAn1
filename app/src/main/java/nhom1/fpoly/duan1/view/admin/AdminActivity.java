package nhom1.fpoly.duan1.view.admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.dao.SessionManager;
import nhom1.fpoly.duan1.view.activity.MainActivityLogin;
import nhom1.fpoly.duan1.view.admin.fragment.AddCategoriesFragment;
import nhom1.fpoly.duan1.view.admin.fragment.AddProductFragment;
import nhom1.fpoly.duan1.view.admin.fragment.StatisticalFragment;
import nhom1.fpoly.duan1.view.admin.fragment.ViewOrderFragment;
import nhom1.fpoly.duan1.view.customer.CustomerActivity;

public class AdminActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    ActionBarDrawerToggle toggle;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
//        setContentView(R.layout.activity_admin2);
//        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView_admin);
        initViews();
        if (savedInstanceState == null) {
            replaceFragment(new StatisticalFragment());
        }
        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_statistic) {
                replaceFragment(new StatisticalFragment());
            } else if (item.getItemId() == R.id.nav_view_order) {
                replaceFragment(new ViewOrderFragment());
            } else if (item.getItemId() == R.id.nav_add_product) {
                replaceFragment(new AddProductFragment());
            }else if (item.getItemId() == R.id.nav_add_category) {
                replaceFragment(new AddCategoriesFragment());
            } else if (item.getItemId() == R.id.nav_login_out) {
                sessionManager = new SessionManager(getApplication());
                sessionManager.logoutCustomer();
                startActivity(new Intent(getApplication(), MainActivityLogin.class));            }
            setTitle(item.getTitle());
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

//        bottomNavigationView.setOnItemSelectedListener(item -> {
//            if (item.getItemId() == R.id.nav_statistic){
//                replaceFragment(new ThongKeFragment());
//            } else if (item.getItemId() == R.id.nav_view_order){
//                replaceFragment(new ViewOrderFragment());
//            } else if (item.getItemId() == R.id.nav_add_product){
//                replaceFragment(new AddProductFragment());
//            } else if (item.getItemId() == R.id.nav_add_category){
//                replaceFragment(new AddCategoriesFragment());
//            }
//            return true;
//        });
    }

    private void initViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.draw_layout_admin);
        navigationView = findViewById(R.id.navigation_view_admin);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager;
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_admin, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        } else {
            super.onBackPressed();

//        }
    }
}