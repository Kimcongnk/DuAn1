package nhom1.fpoly.duan1.dialog;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.adapter.admin.AddCategoriesAdapter;
import nhom1.fpoly.duan1.dao.CategoryDao;
import nhom1.fpoly.duan1.model.Categories;

public class Dialog_Edit_Category extends DialogFragment {
    private final int PICK_IMAGE_REQUEST = 22;
    EditText edt_name;
    Button btn_edit_category;
    ImageView img_select;
    String imgURL;
    Uri uri;
    CategoryDao categoryDao;
    String imagePath;
    AddCategoriesAdapter adapter;



    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_edit_category, container, false);
        edt_name = (EditText) view.findViewById(R.id.edt_name_category_edit);
        btn_edit_category = (Button) view.findViewById(R.id.btn_update);
        img_select = (ImageView) view.findViewById(R.id.img_select_edit);

        categoryDao = new CategoryDao(getContext());

        img_select.setOnClickListener(img -> {
            SelectImage();
        });
        btn_edit_category.setOnClickListener(add -> {
            // add a new category to Database
//            addDatabase(imagePath);

            Log.d("123","path: "+ img_select.toString());
            dismiss();
        });

        return view;
    }

    private void addDatabase(String ImagePath) {
        String name = edt_name.getText().toString();
        Categories categories = new Categories();
        categories.setName_categories(name);
        categories.setImg_categories(ImagePath);

        if (categoryDao.addCategory(categories)) {
            Toast.makeText(getContext(), "add successfully", Toast.LENGTH_SHORT).show();
            dismiss();
            getActivity().recreate();
        } else {
            Toast.makeText(getContext(), "add failed", Toast.LENGTH_SHORT).show();
        }

    }


    // pick a image from the library
    public void SelectImage() {
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
            Log.i("path", "onActivityResult: "+imagePath);


        }
    }
}
