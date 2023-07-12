package nhom1.fpoly.duan1.model;

public class Cart {
private int cartId, productId, userId, totalTems;

    public Cart() {
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
}
