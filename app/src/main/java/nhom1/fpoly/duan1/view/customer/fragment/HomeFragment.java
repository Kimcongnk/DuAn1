package nhom1.fpoly.duan1.view.customer.fragment;

import android.os.Bundle;
import android.util.Log;
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


// Imports are here...

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView, recyclerView_category;
    private ProductHomeAdapter productHomeAdapter;
    private CategoryHomeAdapter categoryHomeAdapter;
    private ImageSlider imageViewSlider;
    private ProductsDao productsDao;
    private CategoryDao categoryDao;
    private List<Product> products = new ArrayList<>();

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_san_pham_home);
        recyclerView_category = view.findViewById(R.id.recycler_view_danh_muc_home);
        imageViewSlider = view.findViewById(R.id.imageSlider);

        recyclerView_category.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
        recyclerView.setHasFixedSize(true);

        productsDao = new ProductsDao(getContext());
        categoryDao = new CategoryDao(getContext());

        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.img1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img5, ScaleTypes.FIT));
        imageViewSlider.setImageList(slideModels, ScaleTypes.FIT);

        products = productsDao.getAllProducts();
        List<Categories> categories = categoryDao.getAllCategories();

        productHomeAdapter = new ProductHomeAdapter(requireActivity(), products);
        recyclerView.setAdapter(productHomeAdapter);
        productHomeAdapter.notifyDataSetChanged();

        categoryHomeAdapter = new CategoryHomeAdapter(requireActivity(), categories);
        recyclerView_category.setAdapter(categoryHomeAdapter);
        categoryHomeAdapter.showProduct(new CategoriesInterface() {
            @Override
            public void showDetails(Categories categories1) {
                products = productsDao.getProductsByCategoryId(categories1.getId());

                productHomeAdapter = new ProductHomeAdapter(requireActivity(), products);
                recyclerView.setAdapter(productHomeAdapter);
                productHomeAdapter.notifyDataSetChanged();
                productHomeAdapter.showProduct(new ProductInterface() {
                    @Override
                    public void showDetails(Product product) {
                        goToDetails(product);
                    }
                });
            }
        });

        productHomeAdapter.showProduct(new ProductInterface() {
            @Override
            public void showDetails(Product product) {
                goToDetails(product);
            }
        });

        return view;
    }

    private void goToDetails(Product product) {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        DetailProductFragment detailProductFragment = new DetailProductFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("product", product);
        detailProductFragment.setArguments(bundle);
        transaction.addToBackStack(HomeFragment.class.getName());
        transaction.replace(R.id.fragment_customer, detailProductFragment);
        transaction.commit();
    }
}
