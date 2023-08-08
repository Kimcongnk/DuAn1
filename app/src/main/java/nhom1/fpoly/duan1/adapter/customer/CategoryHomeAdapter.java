package nhom1.fpoly.duan1.adapter.customer;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.model.Categories;
import nhom1.fpoly.duan1.model.Product;
import nhom1.fpoly.duan1.my_interface.CategoriesInterface;
import nhom1.fpoly.duan1.my_interface.ProductInterface;

public class CategoryHomeAdapter extends RecyclerView.Adapter<CategoryHomeAdapter.ViewHolder> {
    Context context;
    List<Categories> categories;
    List<Categories> mCategories;

    CategoriesInterface detailOnClick;
    public void showProduct(CategoriesInterface detailOnClick){
        this.detailOnClick = detailOnClick;
    }
    public CategoryHomeAdapter(Context context, List<Categories> categories) {
        this.context = context;
        this.categories = categories;
        this.mCategories = categories;
    }

    @NonNull
    @Override
    public CategoryHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_category_home, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHomeAdapter.ViewHolder holder, int position) {
        Categories categories1 = categories.get(position);
        holder.txt_name_category_home.setText(categories1.getName_categories());
        Picasso.get().load(categories1.getImg_categories()).into(holder.img_category_home);
        holder.itemView.setOnClickListener(click -> {
            detailOnClick.showDetails(categories1);
        });
        holder.itemView.setOnLongClickListener(longClick -> {
            detailOnClick.editCategories(categories1);
            Toast.makeText(context, "loia", Toast.LENGTH_SHORT).show();
            return true; // Consume the long click event
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name_category_home;
        ImageView img_category_home;
        CardView cardView;
        ProgressBar progressBar;
        public ViewHolder(@NonNull View view) {
            super(view);
            txt_name_category_home = view.findViewById(R.id.category_name);
            img_category_home = view.findViewById(R.id.category_image);
            cardView = view.findViewById(R.id.card_view_category);

        }
    }
}
