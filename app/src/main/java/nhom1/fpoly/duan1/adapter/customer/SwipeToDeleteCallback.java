package nhom1.fpoly.duan1.adapter.customer;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

    private CartAdapter cartAdapter;
private Context context;
    private SwipeActionListener swipeActionListener;

    public interface SwipeActionListener {
        void onItemDeleted();
    }
    public void setSwipeActionListener(SwipeActionListener listener) {
        this.swipeActionListener = listener;
    }
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
        // Delete the item from the adapter
        cartAdapter.notifyItemChanged(position);
        cartAdapter.notifyItemRemoved(position);
        cartAdapter.notifyDataSetChanged();
        swipeActionListener.onItemDeleted();
    }

}
