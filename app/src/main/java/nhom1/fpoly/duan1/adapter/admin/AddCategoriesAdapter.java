package nhom1.fpoly.duan1.adapter.admin;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.dao.CategoryDao;
import nhom1.fpoly.duan1.model.Categories;

public class AddCategoriesAdapter extends RecyclerView.Adapter<AddCategoriesAdapter.ViewHolder> {

    ArrayList<Categories> categoriesList;
    Context context;
    int id_category;



    public AddCategoriesAdapter(ArrayList<Categories> categoriesList, Context context) {
        this.categoriesList = categoriesList;
        this.context = context;
    }

    @NonNull
    @Override
    public AddCategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_category_admin,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddCategoriesAdapter.ViewHolder holder, int position) {
        Categories categories = categoriesList.get(position);
        holder.txt_name_category.setText(categories.getName_categories());
        Picasso.get().load(categories.getImg_categories()).into(holder.img_category
     , new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                Log.e("Error", e.getMessage());
            }
        });
    holder.img_edit_category.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Categories categories = categoriesList.get(holder.getAdapterPosition());
            showDialogEditCategory(categories);
        }
    });
    holder.img_del_category.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CategoryDao categoryDao = new CategoryDao(context);
            int check = categoryDao.deleteCategory(categoriesList.get(holder.getAdapterPosition()).getId());
            switch (check){
                case 1:
                    //load data;
                    categoriesList.clear();
                    categoriesList = categoryDao.getAllCategories();
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                    break;
                case -1:
                    Toast.makeText(context, "Khong the xoa vi co san pham thuoc loai nay", Toast.LENGTH_SHORT).show();
                    break;
                case 0:
                    Toast.makeText(context, "Xoa loai sach khong thanh cong", Toast.LENGTH_SHORT).show();
                default:
                    break;
            }
        }
    });

    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_category,img_edit_category,img_del_category;
        TextView txt_name_category;
        ProgressBar progressBar;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_category = (ImageView) itemView.findViewById(R.id.category_image_admin);
            txt_name_category = (TextView) itemView.findViewById(R.id.category_name_admin);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressbar_category_admin);
            cardView = (CardView) itemView.findViewById(R.id.card_view_category_admin);
            img_edit_category = itemView.findViewById(R.id.img_edit_category_admin);
            img_del_category = itemView.findViewById(R.id.img_del_category_admin);
        }
    }
    private void showDialogEditCategory(Categories categories){
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setNegativeButton("Cap nhat",null)
                .setPositiveButton("Huy",null);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_edit_category,null);
        EditText edSuaLoai = view.findViewById(R.id.edt_name_category_edit);
        edSuaLoai.setText(categories.getName_categories());

        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenloai = edSuaLoai.getText().toString();
                id_category = categories.getId();
                String img = categories.getImg_categories();


                Categories categories1 = new Categories(id_category,tenloai,img);
                CategoryDao categoryDao = new CategoryDao(context);
                if (categoryDao.updateCategory(categories1)){
                    categoriesList = categoryDao.getAllCategories();
                    notifyDataSetChanged();
                    alertDialog.cancel();
                }else{
                    Toast.makeText(context, "Cap nhat khong thanh cong", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}
