package nhom1.fpoly.duan1.view.admin.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.dialog.Dialog_Add_Product;

public class AddProductFragment extends Fragment {

    Button btn_add_product;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);
        btn_add_product = (Button) view.findViewById(R.id.btn_add_product);

        btn_add_product.setOnClickListener(click -> {
            Dialog_Add_Product dialog_add_product = new Dialog_Add_Product();
            dialog_add_product.show(getParentFragmentManager(), dialog_add_product.getTag());
        });


        return view;
    }
}