package PresentationLayer;
import BusinessLogicLayer.DeliveryService;
import BusinessLogicLayer.MenuItem;
import BusinessLogicLayer.Order;
import com.example.pt_2022_30222_polman_marian_assignment_4.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;


public class ClientController implements Initializable {

    ObservableList<String> types= FXCollections.observableArrayList("Title","Rating","Calories","Protein","Fat","Sodium");
    private DeliveryService deliveryService = new DeliveryService();
    private List<MenuItem> products = new ArrayList<>();
    private List<MenuItem> orderItems = new ArrayList<>();
    private Double totalPrice = 0.0;

    @FXML
    private TextField filterValue;
    @FXML
    private ComboBox<String> filter;
    @FXML
    private Label OrderPrice;
    @FXML
    private TableView<MenuItem> productsTable;
    @FXML
    private TableColumn<MenuItem, String> Title;
    @FXML
    private TableColumn<MenuItem, Double> Rating;
    @FXML
    private TableColumn<MenuItem, Double> Calories;
    @FXML
    private TableColumn<MenuItem, Double> Protein;
    @FXML
    private TableColumn<MenuItem, Double> Fat;
    @FXML
    private TableColumn<MenuItem, Double> Sodium;
    @FXML
    private TableColumn<MenuItem, Double> Price;

    public void initializeTable(){

        productsTable.setEditable(true);
        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Rating.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        Calories.setCellValueFactory(new PropertyValueFactory<>("Calories"));
        Protein.setCellValueFactory(new PropertyValueFactory<>("Protein"));
        Fat.setCellValueFactory(new PropertyValueFactory<>("Fat"));
        Sodium.setCellValueFactory(new PropertyValueFactory<>("Sodium"));
        Price.setCellValueFactory(new PropertyValueFactory<>("Price"));

        productsTable.setItems(FXCollections.observableList(products));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        filter.setValue("Filters");
        filter.setItems(types);
        products= deliveryService.getAll().stream().toList();
        initializeTable();
    }

    @FXML void onApplyFilters(){

        String input = filterValue.getText();
        String filterValue = filter.getValue();

        if(filterValue.equals("Filters"))
            products = deliveryService.getAll();
        else if(filterValue.equals("Title"))
            products = deliveryService.filterByKeyWord(input);
        else if(filterValue.equals("Rating"))
            products = deliveryService.ratingFilter(Double.parseDouble(input));
        else if(filterValue.equals("Calories"))
            products = deliveryService.caloriesFilter(Double.parseDouble(input));
        else if(filterValue.equals("Protein"))
            products = deliveryService.proteinFilter(Double.parseDouble(input));
        else if(filterValue.equals("Fat"))
            products = deliveryService.proteinFilter(Double.parseDouble(input));
        else if(filterValue.equals("Sodium"))
            products = deliveryService.sodiumFilter(Double.parseDouble(input));
        else
            products = deliveryService.searchByPrice(Double.parseDouble(input));

        this.initializeTable();
    }

    @FXML
    public void onAddProduct(){

        ObservableList<MenuItem> singleProduct;
        singleProduct=productsTable.getSelectionModel().getSelectedItems();

        for(MenuItem product: singleProduct){
            orderItems.add(product);
            totalPrice+=product.getPrice();
            OrderPrice.setText(totalPrice+"$");
        }
    }

    @FXML
    public void onDelete(){
        totalPrice = 0.0;
        orderItems.clear();
        OrderPrice.setText("OrderPrice");
    }

    @FXML
    public void onFinish() throws IOException {
        Integer billId = new Random().nextInt();
        FileWriter fileWriter = new FileWriter("Bills/BillNr"+ billId);
        fileWriter.write("Bill - "+billId + " " + HelloApplication.getUser() + "\n");
        for(MenuItem entry: orderItems){
            fileWriter.write(entry.toString()+"\n");
        }
        fileWriter.write("Price: "+totalPrice+"$\n");
        fileWriter.close();

        Order order = new Order(HelloApplication.getUser().getId(),LocalDateTime.now(),totalPrice);
        deliveryService.makeOrder(order,
                this.orderItems);

        this.orderItems.clear();
        totalPrice = 0.0;
        OrderPrice.setText("OrderPrice");
    }
}
