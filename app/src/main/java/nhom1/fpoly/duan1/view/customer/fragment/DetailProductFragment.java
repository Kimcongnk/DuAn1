package nhom1.fpoly.duan1.view.customer.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.dao.CartDao;
import nhom1.fpoly.duan1.model.Cart;
import nhom1.fpoly.duan1.model.Product;

public class DetailProductFragment extends Fragment {

    TextView txt_name_product_detail, txt_description, txt_price;
    ImageView img_detail;
    Button btn_add_cart, btn_by_now;

    private CartDao cartDao;
    private Cart cart ;
private  Product product;
    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_product, container, false);
        txt_name_product_detail = (TextView) view.findViewById(R.id.txt_name_product_detail);
        txt_description = (TextView) view.findViewById(R.id.txt_desc_product_detail);
        txt_price = (TextView) view.findViewById(R.id.txt_price_product_detail);
        btn_add_cart = view.findViewById(R.id.btn_add_cart);


        Bundle bundleResult = getArguments();
         product = (Product) bundleResult.get("product");
        txt_name_product_detail.setText(product.getName_product());
        txt_description.setText(product.getDesc_product());
        txt_price.setText(product.getPrice() + "đ");
        cartDao = new CartDao(getContext());
        cart = new Cart();
        cart.setId_product(product.getId_product());
        cart.setTotalTems(1);
        if(cartDao.addCart(cart) >0){
            Toast.makeText(getContext(), "Sản phẩm đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
        }

        return view;
    }
}