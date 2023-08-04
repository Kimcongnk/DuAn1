package nhom1.fpoly.duan1.adapter.admin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.model.ProductSalesReport;

public class SalesReportAdapter extends RecyclerView.Adapter<SalesReportAdapter.ViewHolder> {
    private List<ProductSalesReport> salesReportList;
    private Context context;

    public SalesReportAdapter(Context context, List<ProductSalesReport> salesReportList) {
        this.context = context;
        this.salesReportList = salesReportList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sales_report, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductSalesReport salesReport = salesReportList.get(position);
        holder.textViewProductName.setText(salesReport.getProductName());
        holder.textViewQuantitySold.setText("x " + salesReport.getTotalQuantity());
        holder.textViewTotalRevenue.setText("$" + salesReport.getTotalRevenue());

        // Load the product image using a library like Picasso or Glide
        // For example, using Picasso:
        Picasso.get().load(salesReport.getProductImage()).into(holder.imageViewProduct);
    }

    @Override
    public int getItemCount() {
        return salesReportList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewProductName;
        TextView textViewQuantitySold;
        TextView textViewTotalRevenue;
        ImageView imageViewProduct;

        ViewHolder(View itemView) {
            super(itemView);
            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            textViewQuantitySold = itemView.findViewById(R.id.textViewQuantitySold);
            textViewTotalRevenue = itemView.findViewById(R.id.textViewTotalRevenue);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
        }
    }
    public int getTotalRevenue() {
        int totalRevenue = 0;
        for (ProductSalesReport salesReport : salesReportList) {
            totalRevenue += salesReport.getTotalRevenue();
        }
        return totalRevenue;
    }
}
