package nhom1.fpoly.duan1.model;

public class Order {
    private int orderId, idUser;
    private String dateOder, Status, nameOder, phone, address;
    private double totalMoney;

    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int oderId) {
        this.orderId = oderId;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getDateOder() {
        return dateOder;
    }

    public void setDateOder(String dateOder) {
        this.dateOder = dateOder;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getNameOder() {
        return nameOder;
    }

    public void setNameOder(String nameOder) {
        this.nameOder = nameOder;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
