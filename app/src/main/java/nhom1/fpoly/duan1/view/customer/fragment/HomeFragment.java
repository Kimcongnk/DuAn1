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
import nhom1.fpoly.duan1.dao.ProductDao;
import nhom1.fpoly.duan1.model.Product;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    ProductHomeAdapter productHomeAdapter;
ProductDao productDao;
private ArrayList<Product> productArrayList ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_san_pham_home);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(requireContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
productDao = new ProductDao(getContext());
productArrayList = (ArrayList<Product>) productDao.getAllProducts();

//        List<Product> products = new ArrayList<>();
//        products.add(new Product(0, 1,  "abc", "q", "ầ", "1"));
//        products.add(new Product(1, 1,  "abc", "q", "ầ", "200"));
//        products.add(new Product(2, 1,  "abc", "q", "ầ", "500"));
//        products.add(new Product(3, 1,  "abc", "q", "ầ", "600"));
//        products.add(new Product(4, 1,  "abc", "q", "ầ", "900"));
//        products.add(new Product(5, 1, "abc", "q", "ầ", "900"));
        productHomeAdapter = new ProductHomeAdapter(requireActivity(), productArrayList);
        recyclerView.setAdapter(productHomeAdapter);
        productHomeAdapter.notifyDataSetChanged();

        return view;
    }
}