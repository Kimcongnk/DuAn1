package nhom1.fpoly.duan1.model;

import java.io.Serializable;

public class Product implements Serializable {
    private int id_product, status, id_category;
    private String  name_product, category, desc_product, img_product, price, conHang;

    public Product() {
    }

    public Product(int id_product, int status, String name_product, String category, String desc_product, String img_product, String price) {
        this.id_product = id_product;
        this.status = status;
        this.name_product = name_product;
        this.category = category;
        this.desc_product = desc_product;
        this.img_product = img_product;
        this.price = price;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getConHang() {
        return conHang;
    }

    public void setConHang(String conHang) {
        this.conHang = conHang;
    }
}
