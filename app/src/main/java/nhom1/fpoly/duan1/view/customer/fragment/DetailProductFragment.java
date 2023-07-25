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

import com.squareup.picasso.Picasso;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.dao.CartDao;
import nhom1.fpoly.duan1.dao.SessionManager;
import nhom1.fpoly.duan1.model.Cart;
import nhom1.fpoly.duan1.model.Product;

public class DetailProductFragment extends Fragment {

    TextView txt_name_product_detail, txt_description, txt_price;
    ImageView img_detail;
    Button btn_add_cart, btn_by_now;

    private CartDao cartDao;
    private Cart cart;
    private Product product;
    private SessionManager sessionManager;
    private ImageView imageView;

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_product, container, false);
        txt_name_product_detail = (TextView) view.findViewById(R.id.txt_name_product_detail);
        txt_description = (TextView) view.findViewById(R.id.txt_desc_product_detail);
        txt_price = (TextView) view.findViewById(R.id.txt_price_product_detail);
        imageView = view.findViewById(R.id.image_product_detail);
        btn_add_cart = view.findViewById(R.id.btn_add_cart);
        sessionManager = new SessionManager(getContext());
// Inside the onClick method of btn_add_cart
        btn_add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartDao = new CartDao(getContext());

                if (cartDao.cartExists(sessionManager.getLoggedInCustomerId(), product.getId_product())) {

                    Toast.makeText(getContext(), "Sản phẩm này đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
                } else {
                    cart = new Cart();
                    cart.setUserId(sessionManager.getLoggedInCustomerId());
                    cart.setId_product(product.getId_product());
                    cart.setTotalTems(1);

                    if (cartDao.addCart(cart) > 0) {

                        Toast.makeText(getContext(), "Sản phẩm"  + product.getName_product() + " đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(getContext(), "Failed to add product to cart.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        Bundle bundleResult = getArguments();
        product = (Product) bundleResult.get("product");
        txt_name_product_detail.setText(product.getName_product());
        txt_description.setText(product.getDesc_product());
        txt_price.setText(product.getPrice() + "đ");

        String imageUrl = product.getImg_product();
        Picasso.get().load(imageUrl).into(imageView);

        return view;
    }
}