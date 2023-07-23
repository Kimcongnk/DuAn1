package nhom1.fpoly.duan1.adapter.customer;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

    private CartAdapter cartAdapter; // Replace CartAdapter with your actual RecyclerView adapter

    public SwipeToDeleteCallback(CartAdapter adapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT); // Drag directions = 0 (not used here)
        cartAdapter = adapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        // Not used in swipe-to-delete functionality
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        cartAdapter.deleteCartItem(position);
        cartAdapter.notifyItemChanged(position);
        cartAdapter.notifyDataSetChanged();
    }
}
