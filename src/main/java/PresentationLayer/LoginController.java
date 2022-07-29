package PresentationLayer;

import BusinessLogicLayer.User;
import DataAccessLayer.UserSerializer;
import com.example.pt_2022_30222_polman_marian_assignment_4.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    ObservableList<String> types= FXCollections.observableArrayList("Admin","Client","Employee");

    @FXML
    private ComboBox<String> typeSelect;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    void loadScene(String view){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(view));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HelloApplication.primaryStage.setScene(scene);
        HelloApplication.primaryStage.show();
    }

    private boolean existentUser(){
        List<User> users = new UserSerializer().deserialize();

        for (User user : users) {
            if (user.getUsername().contentEquals(username.getText())) {
                return true;
            }
        }
        return false;
    }

    private User findUser(){
        List<User> users = new UserSerializer().deserialize();

        for (User user : users) {
            if (user.getUsername().contentEquals(username.getText()) &&
                    user.getPassword().contentEquals(password.getText())) {
                    return user;
            }
        }
        return null;
    }

    private boolean checkEmpty(){

        if (username.getText().isBlank() || password.getText().isBlank()) {
            username.setText("Insert username");
            return false;
        }
        else if (password.getText().isEmpty()){
            password.setText("Insert password");
            return false;
        }
        return true;
    }

    @FXML
    protected void onLogin() {

        if (this.checkEmpty()){
            User user=this.findUser();

            if (user == null) {
                username.setText("Invalid User");
                return;
            }

            if(user.getType().contentEquals("Admin")){
                HelloApplication.setUser(user);
                this.loadScene("AdminWindow.fxml");
                System.out.println("Admin");
            }
            else if(user.getType().contentEquals("Employee")){
                HelloApplication.setUser(user);
                this.loadScene("EmployeeWindow.fxml");
                System.out.println("Employee");
            }
            else {
                HelloApplication.setUser(user);
                this.loadScene("ClientWindow.fxml");
                System.out.println("Client");
            }
        }

    }

    @FXML
    protected void onRegister() {

        if(this.checkEmpty()){
            if(this.existentUser())
                username.setText("User allready created");
            else if(typeSelect.getValue().equals("UserTypes"))
                username.setText("Select a user type");
            else{
                User entry=new User(username.getText(),password.getText(),typeSelect.getValue());
                List<User> users = null;
                UserSerializer userSerializator = new UserSerializer();
                users = userSerializator.deserialize();
                users.add(entry);
                userSerializator.serialize(users);
            }
        }
        System.out.println("Register");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeSelect.setValue("UserTypes");
        typeSelect.setItems(types);
    }
}
