package nhom1.fpoly.duan1.view.customer.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import nhom1.fpoly.duan1.R;

import nhom1.fpoly.duan1.adapter.customer.CartAdapter;
import nhom1.fpoly.duan1.adapter.customer.SwipeToDeleteCallback;
import nhom1.fpoly.duan1.dao.CartDao;
import nhom1.fpoly.duan1.dao.SessionManager;
import nhom1.fpoly.duan1.model.Cart;
import nhom1.fpoly.duan1.view.customer.CustomerActivity;

public class CartFragment extends Fragment implements CartAdapter.OnQuantityChangeListener {

    private RecyclerView recyclerViewCart;
    private CartAdapter cartAdapter;
    private ArrayList<Cart> cartArrayList;
    private CartDao cartDao;

    private TextView txtTotalPrice, txtVuotXoa;
    private Button btnThanhToan, btbMuaNgay;

    private List<Cart> selectedItems = new ArrayList<>();
    private Cart cartItem;
    private View view;
    private SessionManager sessionManager;

private Context context;
    public CartFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerViewCart = view.findViewById(R.id.recyclerView_cart);
        txtTotalPrice = view.findViewById(R.id.txt_total_price);
        txtVuotXoa = view.findViewById(R.id.txtVuotXoa);
        btnThanhToan = view.findViewById(R.id.btn_dat_hang);
        btbMuaNgay = view.findViewById(R.id.muasp);
        cartDao = new CartDao(getContext());
        sessionManager = new SessionManager(getContext());
        Animation fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);

        txtVuotXoa.setAnimation(fadeOut);
        txtVuotXoa.setVisibility(View.INVISIBLE);
        txtVuotXoa.startAnimation(fadeIn);
        txtVuotXoa.setVisibility(View.VISIBLE);

showData();
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PayOder.class);
                if(selectedItems.isEmpty()){
                    Toast.makeText(getContext(), "Vui lòng chọn sản phẩm để mua", Toast.LENGTH_SHORT).show();
                }else {
                    intent.putParcelableArrayListExtra("SELECTED_ITEMS", (ArrayList<Cart>) selectedItems);
                    startActivity(intent);
                }
            }
        });
        btbMuaNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CustomerActivity.class));
            }
        });
        return view;
    }
    public void showData(){

        cartArrayList = (ArrayList<Cart>) cartDao.getAllCartItemsWithProductInfo(sessionManager.getLoggedInCustomerId());
        if (cartArrayList.isEmpty()) {
            view.findViewById(R.id.layout_dat_hang).setVisibility(View.GONE);
            view.findViewById(R.id.layout_sum).setVisibility(View.GONE);
            view.findViewById(R.id.layout_cart_null).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.layout_dat_hang).setVisibility(View.VISIBLE);
            view.findViewById(R.id.layout_sum).setVisibility(View.VISIBLE);
            view.findViewById(R.id.layout_cart_null).setVisibility(View.GONE);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewCart.setLayoutManager(layoutManager);

        cartAdapter = new CartAdapter(cartArrayList, getContext(), this);
        recyclerViewCart.setAdapter(cartAdapter);

// Set up swipe-to-delete functionality using SwipeToDeleteCallback
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(cartAdapter);
        swipeToDeleteCallback.setSwipeActionListener(new SwipeToDeleteCallback.SwipeActionListener() {
            @Override
            public void onItemDeleted() {
                showData();
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchHelper.attachToRecyclerView(recyclerViewCart);


    }

    @Override
    public void onIncreaseClick(int position) {
         cartItem = cartArrayList.get(position);
        int currentQuantity = cartItem.getTotalTems();
        currentQuantity++;
        cartItem.setTotalTems(currentQuantity);
        cartDao.updateCartItem(cartItem);
        cartAdapter.notifyDataSetChanged();
        updateTotalPrice();
    }

    @Override
    public void onDecreaseClick(int position) {
         cartItem = cartArrayList.get(position);
        int currentQuantity = cartItem.getTotalTems();
        if (currentQuantity > 1) {
            currentQuantity--;
            cartItem.setTotalTems(currentQuantity);
            cartDao.updateCartItem(cartItem);
            updateTotalPrice();
            cartAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemCheckedChange(int position, boolean isChecked) {
        cartItem = cartArrayList.get(position);
        cartItem.setChecked(isChecked);

        if (cartItem.isChecked()) {
            selectedItems.add(cartItem);
        } else {
            selectedItems.remove(cartItem);
        }
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        double totalPrice = 0;
        for (Cart item : selectedItems) {
            int quantity = item.getTotalTems();
            int price = item.getPrice();
            totalPrice += quantity * price;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        String formattedPrice = decimalFormat.format(totalPrice);
        txtTotalPrice.setText( formattedPrice+ "VND");

    }

}

