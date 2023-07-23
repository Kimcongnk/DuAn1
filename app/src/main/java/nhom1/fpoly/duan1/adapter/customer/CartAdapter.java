package nhom1.fpoly.duan1.adapter.customer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.dao.CartDao;
import nhom1.fpoly.duan1.model.Cart;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Cart> cartItems;
    private Context context;
    private OnQuantityChangeListener onQuantityChangeListener;
private CartDao cartDao;

    public CartAdapter() {
        notifyDataSetChanged();
    }

    public CartAdapter(List<Cart> cartItems, Context context, OnQuantityChangeListener listener) {
        this.cartItems = cartItems;
        this.context = context;
        this.onQuantityChangeListener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart item = cartItems.get(position);
        holder.txtTotalItems.setText(String.valueOf(item.getTotalTems()));
        holder.txtItemName.setText(item.getProductName());
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        String formattedPrice = decimalFormat.format(item.getPrice());
        holder.txtItemPrice.setText(formattedPrice);

        holder.increase.setOnClickListener(view -> {
            if (onQuantityChangeListener != null) {
                onQuantityChangeListener.onIncreaseClick(position);

            }
        });

        holder.decrease.setOnClickListener(view -> {
            if (onQuantityChangeListener != null) {
                onQuantityChangeListener.onDecreaseClick(position);
            }
        });
        holder.checkODer.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (onQuantityChangeListener != null) {
                onQuantityChangeListener.onItemCheckedChange(position, isChecked);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }


    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage, delete, decrease, increase;
        TextView txtItemName, txtItemPrice, txtTotalItems;
        CheckBox checkODer;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.image);
            decrease = itemView.findViewById(R.id.tru);
            increase = itemView.findViewById(R.id.cong);
            txtItemName = itemView.findViewById(R.id.txt_item_cart_name);
            txtItemPrice = itemView.findViewById(R.id.tienx1);
            txtTotalItems = itemView.findViewById(R.id.sl);
            checkODer = itemView.findViewById(R.id.checkbox_item);
        }
    }

    // Interface to handle quantity change events
    public interface OnQuantityChangeListener {
        void onIncreaseClick(int position);

        void onDecreaseClick(int position);

        void onItemCheckedChange(int position, boolean isChecked);
    }
    public void deleteCartItem(int position) {
        cartDao = new CartDao(context);
        cartDao.deleteCart(position);
        notifyItemRemoved(position);
    }
}
