package nhom1.fpoly.duan1.adapter.admin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
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
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.dialog.Dialog_Add_Category;
import nhom1.fpoly.duan1.model.Categories;
import okhttp3.Call;
import okhttp3.OkHttpClient;

public class AddCategoriesAdapter extends RecyclerView.Adapter<AddCategoriesAdapter.ViewHolder> {

    ArrayList<Categories> categoriesList;
    Context context;



    public AddCategoriesAdapter(ArrayList<Categories> categoriesList, Context context) {
        this.categoriesList = categoriesList;
        this.context = context;
    }

    @NonNull
    @Override
    public AddCategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_admin, parent, false);
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
            img_edit_category = itemView.findViewById(R.id.img_edit_category);
            img_del_category = itemView.findViewById(R.id.img_del_category);
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
        ImageView img_select_edit = view.findViewById(R.id.img_select_edit);
        img_select_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_Add_Category dialog_add_category = new Dialog_Add_Category();

            }
        });


        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
//        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String tenloai = edSuaLoai.getText().toString();
//                int namxb = Integer.parseInt(edSuaNamXB.getText().toString());
//                maloai = loaiSach.getId();
//                LoaiSach loaiSach1 = new LoaiSach(maloai,tenloai,namxb);
//                LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
//                if (loaiSachDAO.suaLoaiSach(loaiSach1)){
//                    list = loaiSachDAO.getDSLoaiSach();
//                    notifyDataSetChanged();
//                    alertDialog.cancel();
//                }else{
//                    Toast.makeText(context, "Cap nhat khong thanh cong", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
    }


}
