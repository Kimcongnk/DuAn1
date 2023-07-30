package nhom1.fpoly.duan1.adapter.customer;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import nhom1.fpoly.duan1.view.admin.fragment.ThongKeFragment;

import nhom1.fpoly.duan1.view.customer.fragment.order.CancelledFrangment;
import nhom1.fpoly.duan1.view.customer.fragment.order.DeliveredFragment;
import nhom1.fpoly.duan1.view.customer.fragment.order.OrderingFragment;

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
