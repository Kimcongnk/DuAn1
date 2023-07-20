package nhom1.fpoly.duan1.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Cart implements Parcelable {
    private int cartId, productId, userId, totalTems, price, id_product;
    private String productName;
    private boolean isChecked;

    public Cart() {
    }

    public Cart(int cartId, int id_product, String productName, int price, int totalTems) {
        this.cartId = cartId;
        this.id_product = id_product;
        this.productName = productName;
        this.price = price;
        this.totalTems = totalTems;
    }

    public Cart(String productName, int price) {
        this.price = price;
        this.productName = productName;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTotalTems() {
        return totalTems;
    }

    public void setTotalTems(int totalTems) {
        this.totalTems = totalTems;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    // Implement the Parcelable methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(cartId);
        dest.writeInt(productId);
        dest.writeInt(userId);
        dest.writeInt(totalTems);
        dest.writeInt(price);
        dest.writeString(productName);
        dest.writeByte((byte) (isChecked ? 1 : 0));
    }

    public static final Parcelable.Creator<Cart> CREATOR = new Parcelable.Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel source) {
            return new Cart(source);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    private Cart(Parcel in) {
        cartId = in.readInt();
        productId = in.readInt();
        userId = in.readInt();
        totalTems = in.readInt();
        price = in.readInt();
        productName = in.readString();
        isChecked = in.readByte() != 0;
    }
}
