package BusinessLogicLayer;
import java.io.Serializable;
import java.util.Random;

public class User implements Serializable {

    private String type;
    private final String username;
    private final String password;
    private int Id;

    public User(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.Id = new Random().nextInt();
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public int getId() {
        return Id;
    }
    public void setUserId(int Id) {
        this.Id = Id;
    }

    @Override
    public String toString() {
        return  username +" "+ Id;
    }
}