package nhom1.fpoly.duan1.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Cart implements Parcelable {
    private int cartId, userId, totalTems, price, id_product ;
    private String productName,img_product;
    private boolean isChecked;

    public Cart() {
    }

    public Cart(int cartId, int id_product, String productName, int price,String img_product,  int totalTems) {
        this.cartId = cartId;
        this.id_product = id_product;
        this.productName = productName;
        this.price = price;
        this.img_product = img_product;
        this.totalTems = totalTems;
    }


    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
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

    public String getImg_product() {
        return img_product;
    }

    public void setImg_product(String img_product) {
        this.img_product = img_product;
    }

    // Implement the Parcelable methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(cartId);
        dest.writeInt(id_product);
        dest.writeInt(userId);
        dest.writeInt(totalTems);
        dest.writeInt(price);
        dest.writeString(img_product);
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
        id_product = in.readInt();
        userId = in.readInt();
        totalTems = in.readInt();
        price = in.readInt();
        img_product = in.readString();
        productName = in.readString();

        isChecked = in.readByte() != 0;
    }
}
