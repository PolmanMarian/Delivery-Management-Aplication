package DataAccessLayer;
import BusinessLogicLayer.MenuItem;
import BusinessLogicLayer.Order;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderSerializer {

    public void serialize(Map<Order, List<MenuItem>> items) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("Orders");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(items);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Order, List<MenuItem>> deserialize() {
        Map<Order, List<MenuItem>> orders = new HashMap<>();
        try {
            FileInputStream fileInputStream = new FileInputStream("Orders");
            if (fileInputStream.available() > 0) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                orders = (Map<Order, List<MenuItem>>) objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return orders;
    }
}