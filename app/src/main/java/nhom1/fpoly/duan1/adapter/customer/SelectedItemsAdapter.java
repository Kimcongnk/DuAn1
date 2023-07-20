package nhom1.fpoly.duan1.adapter.customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.model.Cart;

public class SelectedItemsAdapter extends RecyclerView.Adapter<SelectedItemsAdapter.SelectedItemViewHolder> {

    private List<Cart> selectedItems;
    private Context context;

    public SelectedItemsAdapter(List<Cart> selectedItems, Context context) {
        this.selectedItems = selectedItems;
        this.context = context;
    }

    @NonNull
    @Override
    public SelectedItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new SelectedItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedItemViewHolder holder, int position) {
        Cart item = selectedItems.get(position);
        holder.txtSelectedItemName.setText(item.getProductName());
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        String formattedPrice = decimalFormat.format(item.getPrice());
        holder.txtSelectedItemPrice.setText(formattedPrice);
        holder.txtTotalItems.setText(String.valueOf(item.getTotalTems()));


    }



    @Override
    public int getItemCount() {
        return selectedItems.size();
    }

    public static class SelectedItemViewHolder extends RecyclerView.ViewHolder {

        TextView txtSelectedItemName, txtSelectedItemPrice, txtTotalItems;

        public SelectedItemViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSelectedItemName = itemView.findViewById(R.id.tvProductName);
            txtSelectedItemPrice = itemView.findViewById(R.id.tvProductPrice);
            txtTotalItems = itemView.findViewById(R.id.tvQuantity);

        }
    }
}

