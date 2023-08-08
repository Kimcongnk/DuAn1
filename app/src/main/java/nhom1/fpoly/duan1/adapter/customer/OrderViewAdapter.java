package nhom1.fpoly.duan1.adapter.customer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import nhom1.fpoly.duan1.view.customer.fragment.statusorder.CancelledFrangment;
import nhom1.fpoly.duan1.view.customer.fragment.statusorder.DeliveredFragment;
import nhom1.fpoly.duan1.view.customer.fragment.statusorder.OrderingFragment;

public class OrderViewAdapter extends FragmentStateAdapter {
    public OrderViewAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new OrderingFragment();
        }
        else if(position == 1){
            return new DeliveredFragment();
        }
        return new CancelledFrangment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
