package nhom1.fpoly.duan1.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nhom1.fpoly.duan1.database.CreateDatabase;
import nhom1.fpoly.duan1.model.Cart;

public class CartDao {
    private CreateDatabase dbHelper;
    private SQLiteDatabase db;

    public CartDao(Context context) {
        dbHelper = new CreateDatabase(context);
        db = dbHelper.getWritableDatabase();
    }

    public long addCart(Cart cart) {
        ContentValues values = new ContentValues();
        values.put("user_id", cart.getUserId());
        values.put("product_id", cart.getProductId());
        values.put("totaItem", cart.getTotalTems());
        long cartId = db.insert("Cart", null, values);
        db.close();
        return cartId;
    }

    @SuppressLint("Range")
    public Cart getCartById(long cartId) {
        String[] projection = {"cart_id", "user_id", "product_id", "totaItem"};
        String selection = "cart_id = ?";
        String[] selectionArgs = {String.valueOf(cartId)};
        Cursor cursor = db.query("Cart", projection, selection, selectionArgs, null, null, null);

        Cart cart = null;
        if (cursor.moveToFirst()) {
            cart = new Cart();
            cart.setCartId(cursor.getInt(cursor.getColumnIndex("cart_id")));
            cart.setUserId(cursor.getInt(cursor.getColumnIndex("user_id")));
            cart.setProductId(cursor.getInt(cursor.getColumnIndex("product_id")));
            cart.setTotalTems(cursor.getInt(cursor.getColumnIndex("totaItem")));
        }
        cursor.close();
        db.close();
        return cart;
    }

    @SuppressLint("Range")
    public List<Cart> getAllCarts() {
        String[] projection = {"cart_id", "user_id", "product_id", "totaItem"};
        Cursor cursor = db.query("Cart", projection, null, null, null, null, null);
        List<Cart> cartList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Cart cart = new Cart();
            cart.setCartId(cursor.getInt(cursor.getColumnIndex("cart_id")));
            cart.setUserId(cursor.getInt(cursor.getColumnIndex("user_id")));
            cart.setProductId(cursor.getInt(cursor.getColumnIndex("product_id")));
            cart.setTotalTems(cursor.getInt(cursor.getColumnIndex("totaItem")));
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
        values.put("totaItem", cart.getTotalTems());
        String whereClause = "cart_id = ?";
        String[] whereArgs = {String.valueOf(cart.getCartId())};
        int rowsAffected = db.update("Cart", values, whereClause, whereArgs);
        db.close();
        return rowsAffected;
    }

    public int deleteCart(int cartId) {
        String whereClause = "cart_id = ?";
        String[] whereArgs = {String.valueOf(cartId)};
        int rowsAffected = db.delete("Cart", whereClause, whereArgs);
        db.close();
        return rowsAffected;
    }

    @SuppressLint("Range")
    public List<Cart> getAllCartItemsWithProductInfo() {
        List<Cart> cartItems = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT c.cart_id, p.id_product, p.name, p.price, c.totaItem " +
                "FROM Cart c " +
                "INNER JOIN Products p ON c.id_product = p.id_product";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int cartId = cursor.getInt(cursor.getColumnIndex("cart_id"));
                int productId = cursor.getInt(cursor.getColumnIndex("id_product"));
                String productName = cursor.getString(cursor.getColumnIndex("name"));
                int price = cursor.getInt(cursor.getColumnIndex("price"));
                int quantity = cursor.getInt(cursor.getColumnIndex("totaItem"));

                Cart cartItem = new Cart(cartId, productId, productName, price, quantity);
                cartItems.add(cartItem);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return cartItems;
    }

    public void updateCartItem(Cart cartItem) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        // Update the "totalItem" column with the new quantity
        values.put("totaItem", cartItem.getTotalTems());

        // Update the cart item in the database based on its ID
        db.update("Cart", values, "cart_id=?", new String[]{String.valueOf(cartItem.getCartId())});
        db.close();
    }


}
