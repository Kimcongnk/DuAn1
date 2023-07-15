package nhom1.fpoly.duan1.model;

public class Categories {
    private int id;
    private String name_categories, img_categories;

    public Categories(int id, String name_categories, String img_categories) {
        this.id = id;
        this.name_categories = name_categories;
        this.img_categories = img_categories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_categories() {
        return name_categories;
    }

    public void setName_categories(String name_categories) {
        this.name_categories = name_categories;
    }

    public String getImg_categories() {
        return img_categories;
    }

    public void setImg_categories(String img_categories) {
        this.img_categories = img_categories;
    }
}
