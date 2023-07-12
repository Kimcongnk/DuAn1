package nhom1.fpoly.duan1.model;

public class Order {
    private int orderId, idUser;
    private String dateOder, Status;
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
}
