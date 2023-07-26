package nhom1.fpoly.duan1.view.admin.fragment;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.adapter.admin.AddCategoriesAdapter;
import nhom1.fpoly.duan1.dao.CategoryDao;
import nhom1.fpoly.duan1.dialog.Dialog_Add_Category;
import nhom1.fpoly.duan1.model.Categories;

public class AddCategoriesFragment extends Fragment {

    private final int PICK_IMAGE_REQUEST = 22;
    RecyclerView recyclerView_category_admin;
    FloatingActionButton float_add_category;
    AddCategoriesAdapter adapter;
    CategoryDao categoryDao;
    Uri uri;



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_categories, container, false);

        recyclerView_category_admin = view.findViewById(R.id.recyclerView_category_admin);
        float_add_category = view.findViewById(R.id.floatAddCategory);
        recyclerView_category_admin.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)); // set layout recycler view
//        recyclerView_category_admin.setHasFixedSize(true);

        categoryDao = new CategoryDao(getContext());

        ArrayList<Categories> categoriesList = categoryDao.getAllCategories();
         adapter = new AddCategoriesAdapter(categoriesList,getContext());
        recyclerView_category_admin.setAdapter(adapter);





        float_add_category.setOnClickListener(click -> {
            // show dialog add category
            Dialog_Add_Category dialog_add_category = new Dialog_Add_Category();
            dialog_add_category.show(getParentFragmentManager(), dialog_add_category.getTag());
            adapter.notifyDataSetChanged();
        });

        return view;
    }

}