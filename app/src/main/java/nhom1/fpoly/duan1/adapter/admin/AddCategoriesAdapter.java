package nhom1.fpoly.duan1.adapter.admin;

import android.content.Context;
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

public class AddCategoriesAdapter extends RecyclerView.Adapter<AddCategoriesAdapter.ViewHolder> {

    List<Categories> categoriesList;
    Context context;


    public AddCategoriesAdapter(List<Categories> categoriesList, Context context) {
        this.categoriesList = categoriesList;
        this.context = context;
    }

    @NonNull
    @Override
    public AddCategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddCategoriesAdapter.ViewHolder holder, int position) {
        Categories categories = categoriesList.get(position);
        holder.txt_name_category.setText(categories.getName_categories());
        Picasso.get().load(categories.getImg_categories()).into(holder.img_category, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                Log.e("Error", e.getMessage());
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_category;
        TextView txt_name_category;
        ProgressBar progressBar;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_category = (ImageView) itemView.findViewById(R.id.category_image);
            txt_name_category = (TextView) itemView.findViewById(R.id.category_name);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressbar_category);
            cardView = (CardView) itemView.findViewById(R.id.card_view_category);
        }
    }
}
