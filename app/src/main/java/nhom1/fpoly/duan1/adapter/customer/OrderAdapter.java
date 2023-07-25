package nhom1.fpoly.duan1.adapter.customer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.model.Order;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private ArrayList<Order> orders;
    private Context context;

    private OnItemClickListener itemClickListener;


    public OrderAdapter(ArrayList<Order> orders, Context context, OnItemClickListener listener) {
        this.orders = orders;
        this.context = context;
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);

        // Set order data to the views in the layout
        holder.tvOrderId.setText("ID: " + order.getOrderId());
        holder.tvDate.setText("Ngày: " + order.getDateOder());
        holder.tvTotalMoney.setText("Tổng tiền: " + order.getTotalMoney());
        holder.tvStatus.setText("Trạng thái: " + order.getStatus());

        holder.itemView.setOnClickListener(view -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(position);
            }

        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderId, tvCustomerName, tvDate, tvTotalMoney, tvStatus;

        OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderId = itemView.findViewById(R.id.tvOrderId);

            tvDate = itemView.findViewById(R.id.tvDate);
            tvTotalMoney = itemView.findViewById(R.id.tvTotalMoney);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}
