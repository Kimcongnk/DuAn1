package nhom1.fpoly.duan1.view.admin.fragment;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
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
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.adapter.admin.AddProductAdapter;
import nhom1.fpoly.duan1.dao.CategoryDao;
import nhom1.fpoly.duan1.dao.ProductsDao;
import nhom1.fpoly.duan1.model.Categories;
import nhom1.fpoly.duan1.model.Product;

public class AddProductFragment extends Fragment {

    FloatingActionButton float_add_product;
    RecyclerView recyclerView;
    ProductsDao productsDao;
    AddProductAdapter addProductAdapter;
    ArrayList<Product> productList;
    private final int PICK_IMAGE_REQUEST = 22;
    EditText edt_name_product,edt_price_product,edt_desc_product;
    Button btn_add_product;
    ImageView img_select;
    String imgURL;

    Uri uri;

    String imagePath;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);
        float_add_product =  view.findViewById(R.id.floatAddProduct);
        recyclerView = view.findViewById(R.id.recyclerView_product_admin);
        productsDao = new ProductsDao(getContext());
        loadData();

        float_add_product.setOnClickListener(click -> {
            showDialog();

        });


        return view;
    }
    private ArrayList<HashMap<String,Object>> getDSLoaiSanPham(){
        CategoryDao categoryDao = new CategoryDao(getContext());
        ArrayList<Categories> list = categoryDao.getAllCategories();
        ArrayList<HashMap<String,Object>> listHM = new ArrayList<>();
        for (Categories loai:list){
            HashMap<String,Object> hs = new HashMap<>();
            hs.put("id_category",loai.getId());
            hs.put("name",loai.getName_categories());

            listHM.add(hs);
        }
        return  listHM;
    }
    public void loadData(){
        productsDao = new ProductsDao(getContext());
        productList = productsDao.getAllProducts();
        recyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), 2)); // set layout recycler view
        recyclerView.setHasFixedSize(true);
        addProductAdapter = new AddProductAdapter(productList,getContext(),getDSLoaiSanPham(),productsDao);
        recyclerView.setAdapter(addProductAdapter);
    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_product,null);
        builder.setView(view);
        edt_name_product = view.findViewById(R.id.edt_name_product);
        edt_desc_product = view.findViewById(R.id.edt_desc_product);
        edt_price_product=view.findViewById(R.id.edt_price_product);
           Spinner spnLoai = view.findViewById(R.id.spin_loai_sp);
        btn_add_product = (Button) view.findViewById(R.id.btn_add);
        img_select = (ImageView) view.findViewById(R.id.img_select);

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                getDSLoaiSanPham(),
                android.R.layout.simple_list_item_1,
                new String[]{"name"},
                new int[]{android.R.id.text1}

        );
        spnLoai.setAdapter(simpleAdapter);
        img_select.setOnClickListener(img -> {
            SelectImage();
        });

        builder.setNegativeButton("Them", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = edt_name_product.getText().toString();
                int price = Integer.parseInt(edt_price_product.getText().toString());
                String desc = edt_desc_product.getText().toString();
                HashMap<String,Object> hs  = (HashMap<String, Object>) spnLoai.getSelectedItem();
                int maloai = (int) hs.get("id_category");

                boolean check = productsDao.themProduct(name,imagePath,maloai,price,desc);
                if (check){
                    Toast.makeText(getContext(), "thanh cong", Toast.LENGTH_SHORT).show();
                    loadData();
                }else{
                    Toast.makeText(getContext(), "khong thanh cong", Toast.LENGTH_SHORT).show();
                }



            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


    // pick a image from the library
    private void SelectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Get the Uri of data
            uri = data.getData();

            img_select.setImageURI(uri);
            imagePath = uri.toString();
            Log.i("path", "onActivityResult: " + imagePath);
        }

    }
}