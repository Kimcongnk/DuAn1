package nhom1.fpoly.duan1.model.dao;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

import nhom1.fpoly.duan1.database.DBHelper;
import nhom1.fpoly.duan1.model.Cart;

public class CartDao {
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public CartDao(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long addCart(Cart cart) {
        ContentValues values = new ContentValues();
        values.put("user_id", cart.getUserId());
        values.put("product_id", cart.getProductId());
        values.put("total_items", cart.getTotalTems());
        long cartId = db.insert("Cart", null, values);
        db.close();
        return cartId;
    }
    @SuppressLint("Range")
    public Cart getCartById(long cartId) {
        String[] projection = {"cart_id", "user_id", "product_id", "total_items"};
        String selection = "cart_id = ?";
        String[] selectionArgs = {String.valueOf(cartId)};
        Cursor cursor = db.query("Cart", projection, selection, selectionArgs, null, null, null);

        Cart cart = null;
        if (cursor.moveToFirst()) {
            cart = new Cart();
            cart.setCartId(cursor.getInt(cursor.getColumnIndex("cart_id")));
            cart.setUserId(cursor.getInt(cursor.getColumnIndex("user_id")));
            cart.setProductId(cursor.getInt(cursor.getColumnIndex("product_id")));
            cart.setTotalTems(cursor.getInt(cursor.getColumnIndex("total_items")));
        }
        cursor.close();
        db.close();
        return cart;
    }
    @SuppressLint("Range")
    public List<Cart> getAllCarts() {
        String[] projection = {"cart_id", "user_id", "product_id", "total_items"};
        Cursor cursor = db.query("Cart", projection, null, null, null, null, null);
        List<Cart> cartList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Cart cart = new Cart();
            cart.setCartId(cursor.getInt(cursor.getColumnIndex("cart_id")));
            cart.setUserId(cursor.getInt(cursor.getColumnIndex("user_id")));
            cart.setProductId(cursor.getInt(cursor.getColumnIndex("product_id")));
            cart.setTotalTems(cursor.getInt(cursor.getColumnIndex("total_items")));
            cartList.add(cart);
        }
        cursor.close();
        db.close();
        return cartList;
    }

    public int updateCart(Cart cart) {
        ContentValues values = new ContentValues();
        values.put("user_id", cart.getUserId());
        values.put("product_id", cart.getProductId());
        values.put("total_items", cart.getTotalTems());
        String whereClause = "cart_id = ?";
        String[] whereArgs = {String.valueOf(cart.getCartId())};
        int rowsAffected = db.update("Cart", values, whereClause, whereArgs);
        db.close();
        return rowsAffected;
    }

    public int deleteCart(long cartId) {
        String whereClause = "cart_id = ?";
        String[] whereArgs = {String.valueOf(cartId)};
        int rowsAffected = db.delete("Cart", whereClause, whereArgs);
        db.close();
        return rowsAffected;
    }
}
