package nhom1.fpoly.duan1.model;

public class OrderDetail {
    private int idOderDetail, oderId, productId, quantity, price;
    private String productName;


    public OrderDetail() {
    }

    public OrderDetail(int oderId, int productId, String productName, int price, int quantity) {

        this.oderId = oderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.productName = productName;
    }

    public int getIdOderDetail() {
        return idOderDetail;
    }

    public void setIdOderDetail(int idOderDetail) {
        this.idOderDetail = idOderDetail;
    }

    public int getOderId() {
        return oderId;
    }

    public void setOderId(int oderId) {
        this.oderId = oderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
