package nhom1.fpoly.duan1.view.customer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.adapter.customer.CategoryHomeAdapter;
import nhom1.fpoly.duan1.adapter.customer.ProductHomeAdapter;
import nhom1.fpoly.duan1.dao.CategoryDao;
import nhom1.fpoly.duan1.dao.ProductsDao;
import nhom1.fpoly.duan1.model.Categories;
import nhom1.fpoly.duan1.model.Product;
import nhom1.fpoly.duan1.my_interface.CategoriesInterface;
import nhom1.fpoly.duan1.my_interface.ProductInterface;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView, recyclerView_category;
    ProductHomeAdapter productHomeAdapter;
    CategoryHomeAdapter categoryHomeAdapter;
    ImageSlider imageViewSlider;
    ProductsDao productsDao;
    CategoryDao categoryDao;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_san_pham_home);
        recyclerView_category = view.findViewById(R.id.recycler_view_danh_muc_home);
        recyclerView_category.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), 2)); // set layout recycler view
        recyclerView.setHasFixedSize(true);
        productsDao = new ProductsDao(getContext());
        categoryDao = new CategoryDao(getContext());
        imageViewSlider = view.findViewById(R.id.imageSlider);

        ArrayList<SlideModel> slideModels = new ArrayList<SlideModel>();
        slideModels.add(new SlideModel(R.drawable.img_select, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_select, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_select, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_select, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_select, ScaleTypes.FIT));
        imageViewSlider.setImageList(slideModels, ScaleTypes.FIT);

        List<Product> products = new ArrayList<>();
        List<Categories> categories = new ArrayList<>();
        products = productsDao.getAllProducts();
        categories = categoryDao.getAllCategories();
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
        productHomeAdapter.showProduct(new ProductInterface() {
            @Override
            public void showDetails(Product product) {
                Toast.makeText(getContext(), product.getName_product(), Toast.LENGTH_SHORT).show();
                goToDetails(product);
            }
        });
        categoryHomeAdapter = new CategoryHomeAdapter(requireActivity(), categories);
        recyclerView_category.setAdapter(categoryHomeAdapter);
        categoryHomeAdapter.notifyDataSetChanged();
        categoryHomeAdapter.showProduct(new CategoriesInterface() {
            @Override
            public void showDetails(Categories categories1) {
                Toast.makeText(getContext(), categories1.getName_categories(), Toast.LENGTH_SHORT).show();
                goToDetails2(categories1);
            }
        });

        return view;
    }

    private void goToDetails(Product product){
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        DetailProductFragment detailProductFragment = new DetailProductFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("product", product);
        detailProductFragment.setArguments(bundle);
        transaction.addToBackStack(HomeFragment.class.getName());
        transaction.replace(R.id.fragment_customer, detailProductFragment);
        transaction.commit();
    }
    private void goToDetails2(Categories categories){
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        CategoriesFragment categoriesFragment = new CategoriesFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("categories",categories);
        categoriesFragment.setArguments(bundle);
        transaction.addToBackStack(HomeFragment.class.getName());
        transaction.replace(R.id.fragment_customer, categoriesFragment);
        transaction.commit();
    }
}