package nhom1.fpoly.duan1.view.customer.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.model.Categories;
import nhom1.fpoly.duan1.model.Product;

public class DetailCategoriesFragment extends Fragment {

    TextView txt_name_Categories_detail, txt_description, txt_price;
    ImageView img_detail;
    Button btn_add_cart, btn_by_now;

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_categories, container, false);
        txt_name_Categories_detail = (TextView) view.findViewById(R.id.txt_name_Categories_detail);
        btn_add_cart = view.findViewById(R.id.btn_add_cart);
        btn_by_now = view.findViewById(R.id.btn_by);

        Bundle bundleResult = getArguments();
        Categories Categories = (Categories) bundleResult.get("categories");
        txt_name_Categories_detail.setText(Categories.getName_categories());
        return view;
    }
}