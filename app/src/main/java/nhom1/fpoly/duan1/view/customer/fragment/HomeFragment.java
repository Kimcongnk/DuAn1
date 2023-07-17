package nhom1.fpoly.duan1.view.customer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.adapter.customer.ProductHomeAdapter;
import nhom1.fpoly.duan1.dao.ProductsDao;
import nhom1.fpoly.duan1.model.Product;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    ProductHomeAdapter productHomeAdapter;
    ProductsDao productsDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_san_pham_home);
        recyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), 2)); // set layout recycler view
        recyclerView.setHasFixedSize(true);
        recyclerView.setHasFixedSize(true);
        productsDao = new ProductsDao(getContext());


        List<Product> products = new ArrayList<>();
        products = productsDao.getAllProducts();
//
//        products.add(new Product(1, 1, 1500, "abc", "q", "ầ", ""));
//        products.add(new Product(1, 1, 1500, "abc", "q", "ầ", ""));
//        products.add(new Product(1, 1, 1500, "abc", "q", "ầ", ""));
//        products.add(new Product(1, 1, 1500, "abc", "q", "ầ", ""));
//        products.add(new Product(1, 1, 1500, "abc", "q", "ầ", ""));
//        products.add(new Product(1, 1, 1500, "abc", "q", "ầ", ""));
        productHomeAdapter = new ProductHomeAdapter(requireActivity(), products);
        recyclerView.setAdapter(productHomeAdapter);
        productHomeAdapter.notifyDataSetChanged();

        return view;
    }
}