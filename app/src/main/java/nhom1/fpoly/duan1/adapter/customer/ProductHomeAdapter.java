package nhom1.fpoly.duan1.adapter.customer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.model.Product;
import nhom1.fpoly.duan1.my_interface.ProductInterface;

public class ProductHomeAdapter extends RecyclerView.Adapter<ProductHomeAdapter.ViewHolder> {
    Context context;
    List<Product> productList;

    ProductInterface detailOnClick;
    public void showProduct(ProductInterface detailOnClick){
        this.detailOnClick = detailOnClick;
    }


    public ProductHomeAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_product, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHomeAdapter.ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.txt_name_product_home.setText(product.getName_product());
        holder.txt_price_product_home.setText(String.valueOf(product.getPrice()));
        holder.img_product_home.setImageResource(R.drawable.ic_home);
        holder.cardView.setOnClickListener(click -> {
            detailOnClick.showDetails(product);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name_product_home, txt_price_product_home;
        ImageView img_product_home;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name_product_home = (TextView) itemView.findViewById(R.id.txt_name_product);
            txt_price_product_home = (TextView) itemView.findViewById(R.id.txt_price_product);
            img_product_home = (ImageView) itemView.findViewById(R.id.image_product);
            cardView = (CardView) itemView.findViewById(R.id.cardView_product);
        }
    }
}
