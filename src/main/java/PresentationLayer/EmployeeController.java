package PresentationLayer;
import BusinessLogicLayer.DeliveryService;
import BusinessLogicLayer.Order;
import DataAccessLayer.OrderSerializer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class EmployeeController implements Initializable {

    private final DeliveryService deliveryService = new DeliveryService();

    @FXML
    private TableView<Order> ordersTable;
    @FXML
    private TableColumn<Order, Integer> Client;
    @FXML
    private TableColumn<Order, LocalDateTime> OrderDate;
    @FXML
    private TableColumn<Order, Double> TotalPrice;

    public void initializeTable(){

        Client.setCellValueFactory(new PropertyValueFactory<>("Title"));
        OrderDate.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        TotalPrice.setCellValueFactory(new PropertyValueFactory<>("Calories"));

        ordersTable.setItems(FXCollections.observableList(new ArrayList<Order>(new OrderSerializer().deserialize().keySet())));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initializeTable();
    }

}
