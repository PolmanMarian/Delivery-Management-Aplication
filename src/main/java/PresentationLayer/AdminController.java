package PresentationLayer;
import BusinessLogicLayer.BaseProduct;
import BusinessLogicLayer.DeliveryService;
import BusinessLogicLayer.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    private final DeliveryService deliveryService = new DeliveryService();

    @FXML
    private TextField path;
    @FXML
    private TextField titleField;
    @FXML
    private TextField ratingField;
    @FXML
    private TextField caloriesField;
    @FXML
    private TextField proteinField;
    @FXML
    private TextField fatField;
    @FXML
    private TextField sodiumField;
    @FXML
    private TextField priceField;
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
        Title.setCellFactory(TextFieldTableCell.forTableColumn());
        Rating.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        Rating.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        Calories.setCellValueFactory(new PropertyValueFactory<>("Calories"));
        Calories.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        Protein.setCellValueFactory(new PropertyValueFactory<>("Protein"));
        Protein.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        Fat.setCellValueFactory(new PropertyValueFactory<>("Fat"));
        Fat.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        Sodium.setCellValueFactory(new PropertyValueFactory<>("Sodium"));
        Sodium.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        Price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        Price.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        productsTable.setItems(FXCollections.observableList(deliveryService.getAll()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
    }

    @FXML
    public void onAddProduct(){
        MenuItem singleProduct = new BaseProduct(titleField.getText(),Double.parseDouble(ratingField.getText()),Double.parseDouble(caloriesField.getText()),Double.parseDouble(proteinField.getText()),Double.parseDouble(fatField.getText()),Double.parseDouble(sodiumField.getText()),Double.parseDouble(priceField.getText()));
        System.out.println(singleProduct);
        deliveryService.addProduct(singleProduct);
        initializeTable();
    }
    @FXML void onImport(){
        System.out.println("OnImport\n");
        deliveryService.addByPath(path.getText());
        initializeTable();
    }

    @FXML void onReportOrders(){
        deliveryService.todayOrders();
    }

    @FXML
    public void onEditTitle(TableColumn.CellEditEvent<MenuItem,String> productStringCellEditEvent){
        MenuItem product = productsTable.getSelectionModel().getSelectedItem();
        product.setTitle(productStringCellEditEvent.getNewValue());
        deliveryService.modifyProduct(product);
    }

    @FXML
    public void onEditRating(TableColumn.CellEditEvent<MenuItem,Double> productStringCellEditEvent){
        MenuItem product = productsTable.getSelectionModel().getSelectedItem();
        product.setRating(productStringCellEditEvent.getNewValue());
        deliveryService.modifyProduct(product);
    }

    @FXML
    public void onEditCalories(TableColumn.CellEditEvent<MenuItem,Double> productStringCellEditEvent){
        MenuItem product = productsTable.getSelectionModel().getSelectedItem();
        product.setCalories(productStringCellEditEvent.getNewValue());
        deliveryService.modifyProduct(product);
    }

    @FXML
    public void onEditProtein(TableColumn.CellEditEvent<MenuItem,Double> productStringCellEditEvent){
        MenuItem product = productsTable.getSelectionModel().getSelectedItem();
        product.setProtein(productStringCellEditEvent.getNewValue());
        deliveryService.modifyProduct(product);
    }

    @FXML
    public void onEditFat(TableColumn.CellEditEvent<MenuItem,Double> productStringCellEditEvent){
        MenuItem product = productsTable.getSelectionModel().getSelectedItem();
        product.setFat(productStringCellEditEvent.getNewValue());
        deliveryService.modifyProduct(product);
    }

    @FXML
    public void onEditSodium(TableColumn.CellEditEvent<MenuItem,Double> productStringCellEditEvent){
        MenuItem product = productsTable.getSelectionModel().getSelectedItem();
        product.setSodium(productStringCellEditEvent.getNewValue());
        deliveryService.modifyProduct(product);
    }

    @FXML
    public void onEditPrice(TableColumn.CellEditEvent<MenuItem,Double> productStringCellEditEvent){
        MenuItem product = productsTable.getSelectionModel().getSelectedItem();
        product.setSodium(productStringCellEditEvent.getNewValue());
        deliveryService.modifyProduct(product);
    }

    @FXML
    public void onDeleteSelected(){
        ObservableList<MenuItem> singleProduct;
        singleProduct=productsTable.getSelectionModel().getSelectedItems();
        for(MenuItem product: singleProduct){
            deliveryService.deleteProduct(product.getTitle());
        }
        initializeTable();
    }

}
