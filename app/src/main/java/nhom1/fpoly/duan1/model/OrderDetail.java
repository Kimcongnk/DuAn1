package nhom1.fpoly.duan1.model;

public class OrderDetail {
    private int idOderDetail, oderId, productId, Quantity;

    public OrderDetail() {
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
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
