package nhom1.fpoly.duan1.model;

public class Customer {
    private int id_customer;
    private String fullName, username, password, email, phoneNumber, address;

    public Customer(int id_customer, String fullName, String username, String password, String email, String phoneNumber, String address) {
        this.id_customer = id_customer;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Customer() {

    }

    public int getid_customer() {
        return id_customer;
    }

    public void setid_customer(int id_customer) {
        this.id_customer = id_customer;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
