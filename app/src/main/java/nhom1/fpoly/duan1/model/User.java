package nhom1.fpoly.duan1.model;

public class User {
    private int userId;
    private String nameUser, password, address, phone;

    public  User() {
    }

    public User(int userId, String nameUser, String password, String address, String phone) {
        this.userId = userId;
        this.nameUser = nameUser;
        this.password = password;
        this.address = address;
        this.phone = phone;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
