package nhom1.fpoly.duan1.view.customer.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import nhom1.fpoly.duan1.R;

import nhom1.fpoly.duan1.adapter.customer.CartAdapter;
import nhom1.fpoly.duan1.dao.CartDao;
import nhom1.fpoly.duan1.dao.OrderDao;
import nhom1.fpoly.duan1.model.Cart;
import nhom1.fpoly.duan1.model.Order;
import nhom1.fpoly.duan1.model.OrderDetail;

import java.util.Calendar;

public class CartFragment extends Fragment implements CartAdapter.OnQuantityChangeListener {

    private RecyclerView recyclerViewCart;
    private CartAdapter cartAdapter;
    private ArrayList<Cart> cartArrayList;
    private CartDao cartDao;
    private OrderDao orderDao;
    private TextView txtTotalPrice;
    private Button btnThanhToan;

    private List<Cart> selectedItems = new ArrayList<>();


    public CartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        orderDao = new OrderDao(getContext());
        recyclerViewCart = view.findViewById(R.id.recyclerView_cart);
        txtTotalPrice = view.findViewById(R.id.txt_total_price);
        btnThanhToan = view.findViewById(R.id.btn_dat_hang);
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ThanhToan.class);
                intent.putParcelableArrayListExtra("SELECTED_ITEMS", (ArrayList<Cart>) selectedItems);
                startActivity(intent);

            }
        });

        cartDao = new CartDao(getContext());
        orderDao = new OrderDao(getContext());
        cartArrayList = (ArrayList<Cart>) cartDao.getAllCartItemsWithProductInfo();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewCart.setLayoutManager(layoutManager);

        cartAdapter = new CartAdapter(cartArrayList, getContext(), this);
        recyclerViewCart.setAdapter(cartAdapter);
        updateTotalPrice();
        return view;
    }

    @Override
    public void onIncreaseClick(int position) {
        Cart cartItem = cartArrayList.get(position);
        int currentQuantity = cartItem.getTotalTems();
        currentQuantity++;
        cartItem.setTotalTems(currentQuantity);
        cartDao.updateCartItem(cartItem);
        cartAdapter.notifyItemChanged(position);
        updateTotalPrice();
    }

    @Override
    public void onDecreaseClick(int position) {
        Cart cartItem = cartArrayList.get(position);
        int currentQuantity = cartItem.getTotalTems();
        if (currentQuantity > 1) {
            currentQuantity--;
            cartItem.setTotalTems(currentQuantity);
            cartDao.updateCartItem(cartItem);
            cartAdapter.notifyItemChanged(position);
            updateTotalPrice();
        }
    }

    @Override
    public void onItemCheckedChange(int position, boolean isChecked) {
        Cart cartItem = cartArrayList.get(position);
        cartItem.setChecked(isChecked);
        Toast.makeText(getContext(), "" + cartItem.getId_product(), Toast.LENGTH_SHORT).show();
        if (cartItem.isChecked()) {
            selectedItems.add(cartItem);
        } else {
            selectedItems.remove(cartItem);

        }

    }

    private void updateTotalPrice() {
        double totalPrice = cartAdapter.calculateTotalPrice();
        txtTotalPrice.setText(String.format(Locale.getDefault(), "Total Price: %.2f", totalPrice));
    }
}

