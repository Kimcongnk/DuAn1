package nhom1.fpoly.duan1.adapter.admin;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.dao.ProductsDao;
import nhom1.fpoly.duan1.model.Product;

public class AddProductAdapter extends RecyclerView.Adapter<AddProductAdapter.ViewHolder> {

    ArrayList<Product> productList;
    Context context;
    private  ArrayList<HashMap<String,Object>> listHm;
    private ProductsDao productsDao;


    public AddProductAdapter(ArrayList<Product> productList, Context context, ArrayList<HashMap<String, Object>> listHm, ProductsDao productsDao) {
        this.productList = productList;
        this.context = context;
        this.listHm = listHm;
        this.productsDao = productsDao;
    }

    @NonNull
    @Override
    public AddProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_admin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddProductAdapter.ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.txt_name_product.setText(product.getName_product());
        holder.txt_price_product.setText(String.valueOf(product.getPrice()));
        Picasso.get().load(product.getImg_product()).into(holder.img_product
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
        holder.img_edit_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialogEditProduct( productList.get(holder.getAdapterPosition()));
            }
        });
        holder.img_del_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductsDao productsDao = new ProductsDao(context);
                int check = productsDao.xoaProduct(productList.get(holder.getAdapterPosition()).getId_product());
                switch (check){
                    case 1:
                        Toast.makeText(context, "xoa thanh cong", Toast.LENGTH_SHORT).show();
                        productList.clear();
                        productList = productsDao.getAllProducts();
                        notifyDataSetChanged();
                        break;
                    case 0:
                        Toast.makeText(context, "xoa ko thanh cong", Toast.LENGTH_SHORT).show();

                        break;
                    case -1:
                        Toast.makeText(context, "xoa ko thanh cong vi san pham co trong gio hang", Toast.LENGTH_SHORT).show();

                        break;
                    default:
                        break;


                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_product,img_edit_product,img_del_product;
        TextView txt_name_product,txt_price_product,txt_loai_product;
        ProgressBar progressBar;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_product = (ImageView) itemView.findViewById(R.id.image_product_admin);
            txt_name_product = (TextView) itemView.findViewById(R.id.txt_name_product_admin);
            txt_price_product = (TextView) itemView.findViewById(R.id.txt_price_product_admin);
            txt_loai_product = (TextView) itemView.findViewById(R.id.txt_loai_product_admin);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressbar_product_admin);
            img_edit_product = itemView.findViewById(R.id.img_edit_product);
            img_del_product = itemView.findViewById(R.id.img_del_product);
        }
    }
    private void showDialogEditProduct(Product product){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_edit_product,null);
        builder.setView(view);


        EditText edName = view.findViewById(R.id.edt_name_product_edit);
        EditText edPrice = view.findViewById(R.id.edt_price_product_edit);
        EditText edDesc = view.findViewById(R.id.edt_desc_product_edit);
        Spinner spnLoai = view.findViewById(R.id.spin_loai_sp_edit);
        edDesc.setText(product.getDesc_product());
        edName.setText(product.getName_product());
        edPrice.setText(String.valueOf(product.getPrice()));


        SimpleAdapter simpleAdapter = new SimpleAdapter(
                context,
                listHm,
                android.R.layout.simple_list_item_1,
                new String[]{"name"},
                new int[]{android.R.id.text1}
        );
        spnLoai.setAdapter(simpleAdapter);

        int index =0;
        int position=-1;
        for (HashMap<String,Object> item :listHm){
            if ((int)item.get("id_category") == product.getId_category()){
                position = index;
            }
            index++;
        }
        spnLoai.setSelection(position);

        builder.setNegativeButton("Cap nhat", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = edName.getText().toString();
                String desc = edDesc.getText().toString();
                String img = product.getImg_product();

                int price = Integer.parseInt(edPrice.getText().toString());
                HashMap<String,Object> hs  = (HashMap<String, Object>) spnLoai.getSelectedItem();
                int maloai = (int) hs.get("id_category");
//
//                boolean check = productsDao.capNhatProduct(product.getId_product(),name,img,maloai,price,desc);
//                if (check){
//                    Toast.makeText(context, "thanh cong", Toast.LENGTH_SHORT).show();
//                    productList.clear();
//                    productList = productsDao.getAllProducts();
//                    notifyDataSetChanged();
//                }else{
//                    Toast.makeText(context, "khong thanh cong", Toast.LENGTH_SHORT).show();
//                }
            }
        });


        AlertDialog alertDialog = builder.create();

        alertDialog.show();

    }
}