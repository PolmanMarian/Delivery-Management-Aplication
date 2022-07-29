package BusinessLogicLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type CompositeProduct.
 */
public class CompositeProduct extends MenuItem implements Serializable {

    private final List<MenuItem> products;

    public CompositeProduct(String title, List<MenuItem> products) {
        this.setTitle(title);
        this.products = products;
        double prodNumber= products.size();
        double rating=0;
        double calories=0;
        double protein=0;
        double fat=0;
        double sodium=0;

        for(MenuItem entry: products){
            rating +=entry.getRating();
            calories+=entry.getCalories();
            protein+=entry.getProtein();
            fat+=entry.getFat();
            sodium+=entry.getSodium();
        }

        this.setRating(rating/prodNumber);
        this.setCalories(calories/prodNumber);
        this.setProtein(protein/prodNumber);
        this.setFat(fat/prodNumber);
        this.setSodium(sodium);
    }

    @Override
    public double computePrice() {
        double price = 0;
        for(MenuItem entry: products){
            price=price+entry.computePrice();
        }
        return price;
    }

    @Override
    public String toString() {
        return "CompositeProduct{" +
                "  title='" + title + '\'' +
                ", rating=" + rating +
                ", calories=" + calories +
                ", protein=" + protein +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", price=" + price +
                '}';
    }
}

