package DataAccessLayer;
import BusinessLogicLayer.MenuItem;
import java.io.*;
import java.util.HashSet;

public class MenuSerializer {

    public void serialize(HashSet<MenuItem> items) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("Menu");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(items);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashSet<MenuItem> deserialize() {
        HashSet<MenuItem> items = new HashSet<>();
        try {
            FileInputStream fileInputStream = new FileInputStream("Menu");
            if (fileInputStream.available() > 0) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                items = (HashSet<MenuItem>) objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return items;
    }

}