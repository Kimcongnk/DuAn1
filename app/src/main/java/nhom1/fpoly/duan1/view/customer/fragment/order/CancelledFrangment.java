package nhom1.fpoly.duan1.view.customer.fragment.order;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.adapter.customer.OrderAdapter;
import nhom1.fpoly.duan1.dao.OrderDao;
import nhom1.fpoly.duan1.model.Order;


public class CancelledFrangment extends Fragment implements OrderAdapter.OnItemClickListener {

    RecyclerView recyclerView_order;
    OrderAdapter adapter;
    OrderDao orderDao;
    private Order order;
    private ArrayList<Order> orderList = new ArrayList<Order>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ordering, container, false);
        recyclerView_order = view.findViewById(R.id.recycler_view_ordering);
        recyclerView_order.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView_order.setHasFixedSize(true);

        orderDao = new OrderDao(getContext());


        orderList = orderDao.getOrdersByStatus("Đã hủy hàng");

        adapter = new OrderAdapter(orderList, getContext(), this);
        recyclerView_order.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onItemClick(int position) {
        order = orderList.get(position);
        Intent intent = new Intent(getContext(), OrderDetailActivity.class);
        intent.putExtra("order_id", order.getOrderId());
        startActivity(intent);
    }
}