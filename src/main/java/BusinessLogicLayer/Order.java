package BusinessLogicLayer;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Order implements Serializable{

    private int idClient;
    private LocalDateTime orderDate;
    private double totalPrice;

    public Order(int idClient, LocalDateTime dateOrder, double totalPrice) {
        this.idClient = idClient;
        this.orderDate =dateOrder;
        this.totalPrice=totalPrice;
    }

    public int hashCode(){
        return Objects.hash(idClient, orderDate);
    }
    public int getIdClient() {
        return idClient;
    }
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "clientId=" + idClient +
                ", orderDate=" + orderDate +
                ", totalPrice=" + totalPrice +
                "}\n";
    }
}
