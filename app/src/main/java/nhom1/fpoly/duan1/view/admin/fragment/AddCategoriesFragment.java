package nhom1.fpoly.duan1.view.admin.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.adapter.admin.AddCategoriesAdapter;
import nhom1.fpoly.duan1.dao.CategoryDao;
import nhom1.fpoly.duan1.dialog.Dialog_Add_Category;
import nhom1.fpoly.duan1.model.Categories;

public class AddCategoriesFragment extends Fragment {


    RecyclerView recyclerView_category_admin;
    Button btn_add_category;
    AddCategoriesAdapter adapter;
    CategoryDao categoryDao;
    List<Categories> categoriesList;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_categories, container, false);

        recyclerView_category_admin = view.findViewById(R.id.recyclerView_category_admin);
        btn_add_category = view.findViewById(R.id.btn_add_category);
        recyclerView_category_admin.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)); // set layout recycler view
        recyclerView_category_admin.setHasFixedSize(true);

        categoryDao = new CategoryDao(requireContext());

        categoriesList = new ArrayList<>();
//        categoriesList = categoryDao.getAllCategories();
        // fake data for category
        List<Categories> categoriesList = new ArrayList<>();
        categoriesList.add(new Categories(0, "Cà phê", "https://vi.m.wikipedia.org/wiki/T%E1%BA%ADp_tin:Caf%C3%A9_con_leche.jpg"));
        categoriesList.add(new Categories(1, "Trà sữa", "https://www.bachhoaxanh.com/kinh-nghiem-hay/hoc-cach-pha-tra-sua-o-long-dai-loan-thom-ngon-chuan-vi-ai-cung-me-1374160"));
        categoriesList.add(new Categories(2, "Nước ngọt", "https://ecare.vn/blogs/bai-blog-moi/anh-huong-cua-nuoc-ngot-voi-suc-khoe-rang-mieng"));

        adapter = new AddCategoriesAdapter(categoriesList, requireContext());
        recyclerView_category_admin.setAdapter(adapter);
        adapter.notifyDataSetChanged(); // update categories adapter


        btn_add_category.setOnClickListener(click -> {
            // show dialog add category
            Dialog_Add_Category dialog_add_category = new Dialog_Add_Category();
            dialog_add_category.show(getParentFragmentManager(), dialog_add_category.getTag());
            adapter.notifyDataSetChanged();
        });

        return view;
    }
}