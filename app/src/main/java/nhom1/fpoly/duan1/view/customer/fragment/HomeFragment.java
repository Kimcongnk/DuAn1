package nhom1.fpoly.duan1.view.customer.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.adapter.admin.AddCategoriesAdapter;
import nhom1.fpoly.duan1.adapter.customer.CategoryHomeAdapter;
import nhom1.fpoly.duan1.adapter.customer.ProductHomeAdapter;
import nhom1.fpoly.duan1.dao.CategoryDao;
import nhom1.fpoly.duan1.dao.ProductsDao;
import nhom1.fpoly.duan1.dao.SessionManager;
import nhom1.fpoly.duan1.dialog.Dialog_Add_Category;
import nhom1.fpoly.duan1.dialog.Dialog_Add_Product;
import nhom1.fpoly.duan1.model.Categories;
import nhom1.fpoly.duan1.model.Product;
import nhom1.fpoly.duan1.my_interface.CategoriesInterface;
import nhom1.fpoly.duan1.my_interface.ProductInterface;


// Imports are here...

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView, recyclerView_category;
    private ProductHomeAdapter productHomeAdapter;
    private CategoryHomeAdapter categoryHomeAdapter;
    private String checkProduct;
    private ImageSlider imageViewSlider;
    private ProductsDao productsDao;
    private CategoryDao categoryDao;
    private SimpleAdapter simpleAdapter;
    private List<Product> products = new ArrayList<>();
    ArrayList<Categories> list;

    private SessionManager sessionManager;



    private ImageView themMuc, themSanPham;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_san_pham_home);
        recyclerView_category = view.findViewById(R.id.recycler_view_danh_muc_home);
        imageViewSlider = view.findViewById(R.id.imageSlider);
        themMuc = view.findViewById(R.id.addMuc);

        themSanPham = view.findViewById(R.id.addSanPham);
        sessionManager = new SessionManager(getContext());
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
        List<Categories> categories = categoryDao.getAllCategories();


        categoryHomeAdapter = new CategoryHomeAdapter(requireActivity(), categories);
        recyclerView_category.setAdapter(categoryHomeAdapter);
setData();

        if (sessionManager.isLoggedIn() == true) {
            themMuc.setVisibility(View.GONE);
            themSanPham.setVisibility(View.GONE);
        } else {
            themMuc.setVisibility(View.VISIBLE);
            themSanPham.setVisibility(View.VISIBLE);
        }
        themSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });
        themMuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCategory();
            }
        });
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
                        if (sessionManager.isLoggedIn() == true) {
                            goToDetails(product);
                        } else {
                            updateProduct(product);
                        }


                    }
                });
            }
        });


        productHomeAdapter.showProduct(new ProductInterface() {
            @Override
            public void showDetails(Product product) {
                if (sessionManager.isLoggedIn() == true) {
                    goToDetails(product);
                } else {
                    Toast.makeText(requireActivity(), "click item home", Toast.LENGTH_SHORT).show();
                    updateProduct(product);
                }
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

    private ArrayList<HashMap<String, Object>> getDSLoaiSanPham() {
        CategoryDao categoryDao = new CategoryDao(getContext());
        ArrayList<Categories> list = categoryDao.getAllCategories();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (Categories loai : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("id_category", loai.getId());
            hs.put("name", loai.getName_categories());

            listHM.add(hs);
        }
        return listHM;
    }

    //Thêm loại sản phẩm
    private void addCategory() {
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.dialog_add_category);


        EditText tenLoai = dialog.findViewById(R.id.edt_name_category);
        EditText urlImg = dialog.findViewById(R.id.img_select);
        Button themLoai = dialog.findViewById(R.id.btn_add);

        ImageView imageView = dialog.findViewById(R.id.img_Url);

//    Picasso.get().load(url).into(imageView);
        themLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categories categories = new Categories();
                String tenL = tenLoai.getText().toString();
                String url = urlImg.getText().toString();
                categories.setName_categories(tenL);
                categories.setImg_categories(url);
                if (tenL.isEmpty()) {
                    tenLoai.setError("Không được để trống");
                    return;
                }
                if (url.isEmpty()) {
                    urlImg.setError("Không được để trống");
                    return;
                }
                list = new ArrayList<>();
                list.add(categories);
                if (categoryDao.addCategory(categories)) {
                    Toast.makeText(getContext(), "add successfully", Toast.LENGTH_SHORT).show();

                    getActivity().recreate();

                } else {
                    Toast.makeText(getContext(), "add failed", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void updateCategory() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_category, null);
        builder.setView(view);

        EditText tenLoai = view.findViewById(R.id.edt_name_category);
        Button themLoai = view.findViewById(R.id.btn_add);


        themLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categories categories = new Categories();
                String tenL = tenLoai.getText().toString();

                if (tenL.isEmpty()) {
                    tenLoai.setError("Không được để trống");
                    return;
                }
                categories.setName_categories(tenL);
//        categories.setImg_categories(ImagePath);
                list = new ArrayList<>();
                list.add(categories);
                if (categoryDao.updateCategory(categories)) {
                    Toast.makeText(getContext(), "add successfully", Toast.LENGTH_SHORT).show();

                    getActivity().recreate();

                } else {
                    Toast.makeText(getContext(), "add failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //Thêm sản phẩm
    public void addProduct() {
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.dialog_add_product);

        EditText edt_name_product = dialog.findViewById(R.id.edt_name_product);
        EditText edt_desc_product = dialog.findViewById(R.id.edt_desc_product);
        EditText edt_price_product = dialog.findViewById(R.id.edt_price_product);

        Spinner spnLoai = dialog.findViewById(R.id.spin_loai_sp);
        Button btn_add_product = dialog.findViewById(R.id.btn_add);
        EditText img_select = dialog.findViewById(R.id.img_select);
        CheckBox chkOutOfStock = dialog.findViewById(R.id.chk_out_of_stock);
        chkOutOfStock.setVisibility(View.GONE);
        simpleAdapter = new SimpleAdapter(
                getContext(),
                getDSLoaiSanPham(),
                android.R.layout.simple_list_item_1,
                new String[]{"name"},
                new int[]{android.R.id.text1}

        );
        spnLoai.setAdapter(simpleAdapter);

        btn_add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edt_name_product.getText().toString();
                String priceString = edt_price_product.getText().toString();
                String desc = edt_desc_product.getText().toString();
                String imagePath = img_select.getText().toString();

                if (name.trim().isEmpty()) {
                    edt_name_product.setError("Không được để trống");
                    return;
                }
                if (priceString.trim().isEmpty()) {
                    edt_price_product.setError("Không được để trống");
                    return;
                }
                if (desc.trim().isEmpty()) {
                    edt_desc_product.setError("Không được để trống");
                    return;
                }
                if (imagePath.trim().isEmpty()) {
                    img_select.setError("Không được để trống");
                    return;
                }
                HashMap<String, Object> hs = (HashMap<String, Object>) spnLoai.getSelectedItem();
                int maloai = (int) hs.get("id_category");

                checkProduct = "Còn hàng";
                boolean check = productsDao.themProduct(name, imagePath, maloai, Integer.parseInt(priceString), desc, checkProduct);
                if (check) {
                    Toast.makeText(getContext(), "thanh cong", Toast.LENGTH_SHORT).show();
                    getActivity().recreate();
                } else {
                    Toast.makeText(getContext(), "khong thanh cong", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    public void updateProduct(Product product) {
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.dialog_add_product);

        EditText edt_name_product = dialog.findViewById(R.id.edt_name_product);
        EditText edt_desc_product = dialog.findViewById(R.id.edt_desc_product);
        EditText edt_price_product = dialog.findViewById(R.id.edt_price_product);

        Spinner spnLoai = dialog.findViewById(R.id.spin_loai_sp);
        Button btn_add_product = dialog.findViewById(R.id.btn_add);
        EditText img_select = dialog.findViewById(R.id.img_select);
        CheckBox chkOutOfStock = dialog.findViewById(R.id.chk_out_of_stock);

        simpleAdapter = new SimpleAdapter(
                getContext(),
                getDSLoaiSanPham(),
                android.R.layout.simple_list_item_1,
                new String[]{"name"},
                new int[]{android.R.id.text1}

        );
        edt_name_product.setText(product.getName_product());
        edt_price_product.setText(product.getPrice());

        edt_desc_product.setText(product.getDesc_product());
        chkOutOfStock.setChecked(product.getConHang().equals("Hết hàng"));
        if (product.getConHang().equals("Hết hàng")) {
            checkProduct = "Hết hàng";
        } else {
            checkProduct = "Còn hàng";
        }
        chkOutOfStock.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checkProduct = isChecked ? "Hết hàng" : "Còn hàng";
        });


        spnLoai.setAdapter(simpleAdapter);
        btn_add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt_name_product.getText().toString();
                String priceString = edt_price_product.getText().toString();
                String desc = edt_desc_product.getText().toString();
                String imagePath = img_select.getText().toString();

                if (name.trim().isEmpty()) {
                    edt_name_product.setError("Không được để trống");
                    return;
                }
                if (priceString.trim().isEmpty()) {
                    edt_price_product.setError("Không được để trống");
                    return;
                }
                if (desc.trim().isEmpty()) {
                    edt_desc_product.setError("Không được để trống");
                    return;
                }
                if (imagePath.trim().isEmpty()) {
                    img_select.setError("Không được để trống");
                    return;
                }
                HashMap<String, Object> hs = (HashMap<String, Object>) spnLoai.getSelectedItem();
                int maloai = (int) hs.get("id_category");


                boolean check = productsDao.capNhatProduct(product.getId_product(), priceString, imagePath, maloai, Integer.parseInt(priceString), desc, checkProduct);
                if (check) {
                    Toast.makeText(getContext(), "sua thanh cong", Toast.LENGTH_SHORT).show();

                    getActivity().recreate();
                } else {
                    Toast.makeText(getContext(), "khong thanh cong", Toast.LENGTH_SHORT).show();
                }


                dialog.dismiss(); // Dismiss the dialog after updating the product
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    public void setData() {

        products = productsDao.getAllProducts();
        productHomeAdapter = new ProductHomeAdapter(requireActivity(), products);
        recyclerView.setAdapter(productHomeAdapter);

        productHomeAdapter.notifyDataSetChanged();
    }

}
