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

import java.util.ArrayList;
import java.util.Locale;

import nhom1.fpoly.duan1.R;

import nhom1.fpoly.duan1.adapter.customer.CartAdapter;
import nhom1.fpoly.duan1.dao.CartDao;
import nhom1.fpoly.duan1.model.Cart;

public class CartFragment extends Fragment implements CartAdapter.OnQuantityChangeListener {

    private RecyclerView recyclerViewCart;
    private CartAdapter cartAdapter;
    private ArrayList<Cart> cartArrayList;
    private CartDao cartDao;
    private TextView txtTotalPrice;
private Button btnThanhToan;
    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerViewCart = view.findViewById(R.id.recyclerView_cart);
         txtTotalPrice = view.findViewById(R.id.txt_total_price);
        btnThanhToan = view.findViewById(R.id.btn_dat_hang);
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ThanhToan.class));
            }
        });

        cartDao = new CartDao(getContext());
        cartArrayList = (ArrayList<Cart>) cartDao.getAllCartItemsWithProductInfo();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewCart.setLayoutManager(layoutManager);

        // Initialize the CartAdapter with the cartArrayList and this fragment as the listener
        cartAdapter = new CartAdapter(cartArrayList, getContext(), this);
        recyclerViewCart.setAdapter(cartAdapter);
        updateTotalPrice();
        return view;
    }

    @Override
    public void onIncreaseClick(int position) {
        // Handle the increase click event for the cart item at the given position
        Cart cartItem = cartArrayList.get(position);
        int currentQuantity = cartItem.getTotalTems();
        // Update the quantity as needed, for example:
        currentQuantity++;
        cartItem.setTotalTems(currentQuantity);
        cartDao.updateCartItem(cartItem); // Assuming you have a method to update the cart item in the database.
        cartAdapter.notifyItemChanged(position);
        updateTotalPrice();
    }

    @Override
    public void onDecreaseClick(int position) {
        // Handle the decrease click event for the cart item at the given position
        Cart cartItem = cartArrayList.get(position);
        int currentQuantity = cartItem.getTotalTems();
        // Update the quantity as needed, for example:
        if (currentQuantity > 1) {
            currentQuantity--;
            cartItem.setTotalTems(currentQuantity);
            cartDao.updateCartItem(cartItem); // Assuming you have a method to update the cart item in the database.
            cartAdapter.notifyItemChanged(position);
            updateTotalPrice();
        }
    }
    private void updateTotalPrice() {
        double totalPrice = cartAdapter.calculateTotalPrice();
        txtTotalPrice.setText(String.format(Locale.getDefault(), "Total Price: %.2f", totalPrice));
    }
}

