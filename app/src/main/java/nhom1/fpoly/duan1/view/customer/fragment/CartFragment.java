package nhom1.fpoly.duan1.view.customer.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import nhom1.fpoly.duan1.R;
//import nhom1.fpoly.duan1.adapter.admin.CartAdapter;
import nhom1.fpoly.duan1.dao.CartDao;
import nhom1.fpoly.duan1.model.Cart;


public class CartFragment extends Fragment {
private RecyclerView recyclerViewCart;
private CartDao cartDao;
private ArrayList<Cart> cartArrayList;
//private CartAdapter cartAdapter;
    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment newInstance() {
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
//        recyclerViewCart = view.findViewById(R.id.recyclerView_cart);
//        cartDao = new CartDao(getContext());
//        cartArrayList = (ArrayList<Cart>) cartDao.getAllCarts();
//        cartAdapter = new CartAdapter(cartArrayList, getContext());
//        recyclerViewCart.setAdapter(cartAdapter);

        return view;
    }
}