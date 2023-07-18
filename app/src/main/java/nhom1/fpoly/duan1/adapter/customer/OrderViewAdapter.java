package nhom1.fpoly.duan1.adapter.customer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import nhom1.fpoly.duan1.view.admin.fragment.ThongKeFragment;
import nhom1.fpoly.duan1.view.customer.fragment.ThanhToanFragment;

public class OrderViewAdapter extends FragmentStateAdapter {
    public OrderViewAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new ThanhToanFragment();
        }
        return new ThongKeFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
