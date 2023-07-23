package nhom1.fpoly.duan1.dialog;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.HashMap;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.adapter.admin.AddProductAdapter;
import nhom1.fpoly.duan1.dao.CategoryDao;
import nhom1.fpoly.duan1.dao.ProductsDao;
import nhom1.fpoly.duan1.model.Categories;
import nhom1.fpoly.duan1.model.Product;
import nhom1.fpoly.duan1.view.admin.fragment.AddProductFragment;

public class Dialog_Add_Product extends DialogFragment {

    private final int PICK_IMAGE_REQUEST = 22;
    EditText edt_name_product,edt_price_product,edt_desc_product;
    Button btn_add_product;
    ImageView img_select;
    String imgURL;
    Spinner spnLoai;
    Uri uri;
    ProductsDao productsDao;



    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_add_product, container, false);
        edt_name_product = view.findViewById(R.id.edt_name_product);
        edt_desc_product = view.findViewById(R.id.edt_desc_product);
        edt_price_product=view.findViewById(R.id.edt_price_product);
        spnLoai = view.findViewById(R.id.spin_loai_sp);
        productsDao = new ProductsDao(getContext());


        btn_add_product = (Button) view.findViewById(R.id.btn_add);
        img_select = (ImageView) view.findViewById(R.id.img_select);

        img_select.setOnClickListener(img -> {
            SelectImage();
        });
        getDataLoaiSP(spnLoai);
        btn_add_product.setOnClickListener(add -> {
            // add a new product to Database
            HashMap<String,Object> hsLoai = (HashMap<String, Object>) spnLoai.getSelectedItem();
            int id_loai = (int) hsLoai.get("id_category");
            themSP(id_loai);




        });

        return view;
    }
    private void themSP(int id_Loai ){
        String name = edt_name_product.getText().toString();
        Integer price = Integer.parseInt(edt_price_product.getText().toString());
        String desc = edt_desc_product.getText().toString();
        Product product = new Product();
        product.setName_product(name);
        product.setPrice(price);
        product.setId_category(id_Loai);
        product.setDesc_product(desc);
        if (productsDao.themProduct(product)) {
            Toast.makeText(getContext(), "add successfully", Toast.LENGTH_SHORT).show();
            dismiss();
            getActivity().recreate();



        } else {
            Toast.makeText(getContext(), "add failed", Toast.LENGTH_SHORT).show();
        }



    }
    private void getDataLoaiSP(Spinner spnLoai){
        CategoryDao categoryDao= new CategoryDao(getContext());
        ArrayList<Categories> list = categoryDao.getAllCategories();
        ArrayList<HashMap<String,Object>> listHash = new ArrayList<>();
        for (Categories loai:list){
            HashMap<String,Object> hs = new HashMap<>();
            hs.put("id_category",loai.getId());
            hs.put("name",loai.getName_categories());
            listHash.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),listHash, android.R.layout.simple_list_item_1,new String[]{"name"},new int[]{android.R.id.text1}

        );
        spnLoai.setAdapter(simpleAdapter);
    }


    // pick a image from the library
    private void SelectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Get the Uri of data
            uri = data.getData();
            img_select.setImageURI(uri);
        }
    }



}
