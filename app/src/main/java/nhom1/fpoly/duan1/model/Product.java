package nhom1.fpoly.duan1.model;

public class Product {
    private int id_product, price,id_category;
    private String  name_product, category, desc_product, img_product;

    public Product(int id_product, int price, String name_product, String category, String desc_product, String img_product,Integer id_category) {
        this.id_product = id_product;
        this.id_category = id_category;
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

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }
}
