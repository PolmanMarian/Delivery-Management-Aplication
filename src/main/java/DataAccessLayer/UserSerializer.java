package DataAccessLayer;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import BusinessLogicLayer.User;

public class UserSerializer {

    public void serialize(List<User> items) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("Users");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(items);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<User> deserialize() {
        List<User> users = new ArrayList<>();

        try {
            FileInputStream fileInputStream = new FileInputStream("Users");
            if (fileInputStream.available() > 0) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                users = (List<User>) objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }
}