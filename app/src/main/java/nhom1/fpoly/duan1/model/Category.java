package nhom1.fpoly.duan1.model;

public class Category {
    private int menuId;
    private String nameType,imageCategory;

    public Category() {
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public String getImageCategory() {
        return imageCategory;
    }

    public void setImageCategory(String imageCategory) {
        this.imageCategory = imageCategory;
    }
}
