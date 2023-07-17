package nhom1.fpoly.duan1.view.admin.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.adapter.admin.AddProductAdapter;
import nhom1.fpoly.duan1.dao.ProductsDao;
import nhom1.fpoly.duan1.dialog.Dialog_Add_Product;
import nhom1.fpoly.duan1.model.Categories;
import nhom1.fpoly.duan1.model.Product;

public class AddProductFragment extends Fragment {

    Button btn_add_product;
    RecyclerView recyclerView;
    ProductsDao productsDao;
    AddProductAdapter addProductAdapter;
    List<Product> productList;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);
        btn_add_product = (Button) view.findViewById(R.id.btn_add_product);
        recyclerView = view.findViewById(R.id.recyclerView_product_admin);
        recyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), 2)); // set layout recycler view
        recyclerView.setHasFixedSize(true);



        productsDao = new ProductsDao(getContext());
        productList = new ArrayList<>();
        productList = productsDao.getAllProducts();
        addProductAdapter = new AddProductAdapter(productList, getContext());
        recyclerView.setAdapter(addProductAdapter);
        addProductAdapter.notifyDataSetChanged();


        btn_add_product.setOnClickListener(click -> {
            Dialog_Add_Product dialog_add_product = new Dialog_Add_Product();
            dialog_add_product.show(getParentFragmentManager(), dialog_add_product.getTag());
        });


        return view;
    }
}