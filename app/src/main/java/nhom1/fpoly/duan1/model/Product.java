package nhom1.fpoly.duan1.model;

public class Product {
    private int id_product, status, price;
    private String  name_product, category, desc_product, img_product;

    public Product(int id_product, int status, int price, String name_product, String category, String desc_product, String img_product) {
        this.id_product = id_product;
        this.status = status;
        this.price = price;
        this.name_product = name_product;
        this.category = category;
        this.desc_product = desc_product;
        this.img_product = img_product;
    }

    public Product() {

    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDesc_product() {
        return desc_product;
    }

    public void setDesc_product(String desc_product) {
        this.desc_product = desc_product;
    }

    public String getImg_product() {
        return img_product;
    }

    public void setImg_product(String img_product) {
        this.img_product = img_product;
    }
}
