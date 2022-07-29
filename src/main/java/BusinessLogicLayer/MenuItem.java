package BusinessLogicLayer;
import java.io.Serializable;
import java.util.Objects;

public abstract class MenuItem implements Serializable{

    protected String title;
    protected double rating;
    protected double calories;
    protected double protein;
    protected double fat;
    protected double sodium;
    protected double price;

    public MenuItem(String title, double rating, double calories, double protein, double fat, double sodium, double price){
        this.title=title;
        this.rating=rating;
        this.calories=calories;
        this.protein=protein;
        this.fat=fat;
        this.sodium=sodium;
        this.price=price;
    }
    protected MenuItem(){}
    public abstract double computePrice();
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public double getCalories() {
        return calories;
    }
    public void setCalories(double calories) {
        this.calories = calories;
    }
    public double getProtein() {
        return protein;
    }
    public void setProtein(double protein) {
        this.protein = protein;
    }
    public double getFat() {
        return fat;
    }
    public void setFat(double fat) {
        this.fat = fat;
    }
    public double getSodium() {
        return sodium;
    }
    public void setSodium(double sodium) {
        this.sodium = sodium;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return Objects.equals(title, menuItem.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
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
