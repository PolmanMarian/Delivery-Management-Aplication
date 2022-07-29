package BusinessLogicLayer;
import DataAccessLayer.WriteFile;
import DataAccessLayer.OrderSerializer;
import DataAccessLayer.MenuSerializer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeliveryService implements IdDeliveryServiceProcessing {

    private final MenuSerializer menuSerializer = new MenuSerializer();
    private final HashSet<MenuItem> items;
    private Map<Order, List<MenuItem>> orders = new HashMap<>();

    public DeliveryService(){
        items = menuSerializer.deserialize();
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders.keySet());
    }
    public List<MenuItem> getAll() {
        return menuSerializer.deserialize().stream().toList();
    }

    @Override
    public List<MenuItem> filterByKeyWord(String keyWord) {

        String finalKeyWord = keyWord.toLowerCase(Locale.ROOT);
        return items.stream()
               .filter(item ->(item.getTitle().toLowerCase(Locale.ROOT)
                    .contains(finalKeyWord))).collect(Collectors.toList());

    }

    @Override
    public List<MenuItem> ratingFilter(double rating) {
        return items.stream().
                filter(item ->(item.getRating() == rating))
                    .collect(Collectors.toList());
    }

    @Override
    public List<MenuItem> caloriesFilter(double calories) {
        return items.stream()
                .filter(item ->(item.getCalories() == calories))
                    .collect(Collectors.toList());
    }

    @Override
    public List<MenuItem> proteinFilter(double protein) {
        return items.stream()
                .filter(item ->(item.getProtein() == protein))
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuItem> fatFilter(double fat) {
        return items.stream()
                .filter(item ->(item.getFat() == fat))
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuItem> sodiumFilter(double sodium) {
        return items.stream()
                .filter(item ->(item.getSodium() == sodium))
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuItem> searchByPrice(double price) {
        return items.stream()
                .filter(item ->(item.getPrice() == price))
                .collect(Collectors.toList());
    }

    @Override
    public void addProduct(MenuItem item){
        items.add(item);
        menuSerializer.serialize(items);
    }

    @Override
    public void addByPath(String path){
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(path));
            lines.remove(0);
        } catch (IOException e) { e.printStackTrace();}

        for(String line:lines){
            String[] values = line.split(",");
            items.add(new BaseProduct(
                            values[0],
                            Float.parseFloat(values[1]),
                            Integer.parseInt(values[2]),
                            Integer.parseInt(values[3]),
                            Integer.parseInt(values[4]),
                            Integer.parseInt(values[5]),
                            Integer.parseInt(values[6])
                    )
            );
        }
        menuSerializer.serialize(items);
    }

    @Override
    public void deleteProduct(String title) {
        items.removeIf(l -> l.getTitle().contentEquals(title));
        menuSerializer.serialize(items);
    }

    @Override
    public void modifyProduct(MenuItem entry){
        items.removeIf(l -> l.getTitle().contentEquals(entry.getTitle()));
        items.add(entry);
        menuSerializer.serialize(items);
    }

    public void makeOrder(Order order, List<MenuItem> items){
        OrderSerializer orderSerializer = new OrderSerializer();
        orders = new OrderSerializer().deserialize();
        orders.put(order,items);
        orderSerializer.serialize(orders);
    }

    public void todayOrders(){

        Stream<Order> stream = orders.keySet().stream();
        Set<Order> orders = stream.filter(order ->(order.getOrderDate().getDayOfYear() == LocalDateTime.now().getDayOfYear())).collect(Collectors.toSet());

        try {
            WriteFile writeFile = new WriteFile("TodayOrders");
            writeFile.writeFile("TodayOrders",orders.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}