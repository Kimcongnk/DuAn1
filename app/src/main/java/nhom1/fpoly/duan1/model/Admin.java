package nhom1.fpoly.duan1.model;

public class Admin {
    private int id_Admin;
    private String  nameAdmin, password;

    public Admin() {
    }

    public Admin(int id_Admin, String nameAdmin, String password) {
        this.id_Admin = id_Admin;
        this.nameAdmin = nameAdmin;
        this.password = password;
    }

    public int getId_Admin() {
        return id_Admin;
    }

    public void setId_Admin(int id_Admin) {
        this.id_Admin = id_Admin;
    }

    public String getNameAdmin() {
        return nameAdmin;
    }

    public void setNameAdmin(String nameAdmin) {
        this.nameAdmin = nameAdmin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
