package com.example.pt_2022_30222_polman_marian_assignment_4;

import BusinessLogicLayer.User;
import DataAccessLayer.UserSerializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {

    public static Stage primaryStage;
    public static User user;

    public static User getUser(){
        return user;
    }

    public static void setUser(User user){
        HelloApplication.user = user;
    }

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("FoodMenuManager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        User us1= new User("Polman","Marian","Admin");
        User us2= new User("Cezar","Zbughin","Client");
        User us3= new User("Nedelcu","Andrei","Employee");

        List<User> users=new ArrayList<>();
        users.add(us1);
        users.add(us2);
        users.add(us3);

        new UserSerializer().serialize(users);
        launch();

    }
}