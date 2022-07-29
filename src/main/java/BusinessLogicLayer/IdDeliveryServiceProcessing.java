package BusinessLogicLayer;
import java.util.List;

public interface IdDeliveryServiceProcessing {

    List<MenuItem> filterByKeyWord(String name);
    List<MenuItem> ratingFilter(double rating);
    List<MenuItem> caloriesFilter(double calories);
    List<MenuItem> proteinFilter(double protein);
    List<MenuItem> fatFilter(double fat);
    List<MenuItem> sodiumFilter(double sodium);
    List<MenuItem> searchByPrice(double price);

    void addProduct(MenuItem item);

    void deleteProduct(String title);

    void addByPath(String path);

    void modifyProduct(MenuItem item);

}