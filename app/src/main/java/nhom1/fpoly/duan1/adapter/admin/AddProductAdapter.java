package nhom1.fpoly.duan1.adapter.admin;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.model.Categories;
import nhom1.fpoly.duan1.model.Product;

public class AddProductAdapter extends RecyclerView.Adapter<AddProductAdapter.ViewHolder> {

    List<Product> productList;
    Context context;


    public AddProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public AddProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddProductAdapter.ViewHolder holder, int position) {
        Product product = productList.get(position);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_product;
        TextView txt_name_product;
        ProgressBar progressBar;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_product = (ImageView) itemView.findViewById(R.id.image_product);
            txt_name_product = (TextView) itemView.findViewById(R.id.txt_name_product);
//            progressBar = (ProgressBar) itemView.findViewById(R.id.progressbar_product);
        }
    }
}
