package nhom1.fpoly.duan1.model;

public class Product {
    private int productId, menuId;
    private String nameProduct, imageProduct, describe, price;

    public Product() {
    }

    public Product(int productId, int menuId, String nameProduct, String imageProduct, String describe, String price) {
        this.productId = productId;
        this.menuId = menuId;
        this.nameProduct = nameProduct;
        this.imageProduct = imageProduct;
        this.describe = describe;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
