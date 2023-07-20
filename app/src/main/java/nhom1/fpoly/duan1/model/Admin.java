package nhom1.fpoly.duan1.model;

public class Admin {
    private int id_Admin;
    private String fullName, username, password;

    public Admin(int id_Admin, String fullName, String username, String password) {
        this.id_Admin = id_Admin;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
    }

    public Admin() {

    }

    public int getId_Admin() {
        return id_Admin;
    }

    public void setId_Admin(int id_Admin) {
        this.id_Admin = id_Admin;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
