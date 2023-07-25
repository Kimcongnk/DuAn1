package nhom1.fpoly.duan1.adapter.customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.model.Cart;
import nhom1.fpoly.duan1.model.OrderDetail;

public class OrderDetailAdapter extends BaseAdapter {

    private Context context;
    private List<OrderDetail> orderDetailList;

    public OrderDetailAdapter(Context context, List<OrderDetail> orderDetailList) {
        this.context = context;
        this.orderDetailList = orderDetailList;
    }

    @Override
    public int getCount() {
        return orderDetailList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderDetailList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_order_detail, parent, false);
        }

        // Get the views from the layout
        TextView tvProductName = view.findViewById(R.id.tvProductName);
        TextView tvProductPrice = view.findViewById(R.id.tvProductPrice);
        TextView tvQuantity = view.findViewById(R.id.tvQuantity);
        ImageView itemImage = view.findViewById(R.id.img_back);

        // Get the OrderDetail at the specified position
        OrderDetail orderDetail = orderDetailList.get(position);

        // Set the data to the views
        tvProductName.setText(orderDetail.getProductName());
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        String formattedPrice = decimalFormat.format(orderDetail.getPrice());
        tvProductPrice.setText(String.valueOf(formattedPrice));
        String imageUrl = orderDetail.getImg_product();
        Picasso.get().load(imageUrl).into(itemImage);
        tvQuantity.setText("SL: " + orderDetail.getQuantity());

        return view;
    }
    public double totalOrderDetail() {
        double totalPrice = 0;
        for (OrderDetail item : orderDetailList) {
            int quantity = item.getQuantity();
            int price = item.getPrice();
            totalPrice += quantity * price;
        }


        return totalPrice;
    }
}