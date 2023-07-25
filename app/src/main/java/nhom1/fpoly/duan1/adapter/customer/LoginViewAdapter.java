package nhom1.fpoly.duan1.adapter.customer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import nhom1.fpoly.duan1.view.activity.LoginFragment;
import nhom1.fpoly.duan1.view.activity.RegisterFragment;
import nhom1.fpoly.duan1.view.customer.fragment.order.CancelledFrangment;
import nhom1.fpoly.duan1.view.customer.fragment.order.DeliveredFragment;
import nhom1.fpoly.duan1.view.customer.fragment.order.OrderingFragment;

public class LoginViewAdapter extends FragmentStateAdapter {
    public LoginViewAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new LoginFragment();
        }
        return new RegisterFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
