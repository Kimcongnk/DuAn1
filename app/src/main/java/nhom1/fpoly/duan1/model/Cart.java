package nhom1.fpoly.duan1.model;

public class Cart {
private int cartId, productId, userId, totalTems, price;
private String productName;

    public Cart() {
    }
    public Cart(int cartId, String productName, int price, int totalTems) {
        this.cartId = cartId;
        this.productName = productName;
        this.price = price;
        this.totalTems = totalTems;
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
}
