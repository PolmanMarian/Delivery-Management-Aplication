package BusinessLogicLayer;

import java.io.Serializable;

public class BaseProduct extends MenuItem implements Serializable {

    public BaseProduct(String title, double rating, double calories, double protein, double fat, double sodium, double price){
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    @Override
    public double computePrice() {
        return getPrice();
    }

    public BaseProduct(String[] str){
        super(str[0], Double.parseDouble(str[1]), Double.parseDouble(str[2]), Double.parseDouble(str[3]), Double.parseDouble(str[4]), Double.parseDouble(str[5]), Double.parseDouble(str[6]));
    }

    @Override
    public String toString() {
        return  "  Product=" + title + "\n|" +
                "  rating=" + rating +
                "  calories=" + calories +
                "  protein=" + protein +
                "  fat=" + fat +
                "  sodium=" + sodium +
                "| price=" + price;
    }
}

