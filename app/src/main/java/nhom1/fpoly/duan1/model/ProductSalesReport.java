package nhom1.fpoly.duan1.model;

public class ProductSalesReport {
    private int productId;
    private String productName;
    private String productImage;
    private int totalQuantity;
    private int totalRevenue;

    public ProductSalesReport(int productId, String productName, String productImage, int totalQuantity, int totalRevenue) {
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.totalQuantity = totalQuantity;
        this.totalRevenue = totalRevenue;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(int totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
