package nhom1.fpoly.duan1.view.customer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.adapter.customer.OrderViewAdapter;

public class OrderFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_order);
        ViewPager2 viewPager2 = (ViewPager2) view.findViewById(R.id.viewpager2_order);
        OrderViewAdapter adapter = new OrderViewAdapter(requireActivity());
        viewPager2.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Đang giao");
                } else if (position == 1){
                    tab.setText("Đã giao");
                } else if (position == 2){
                    tab.setText("Đã hủy");
                }
            }
        }).attach();
        return view;
    }
}