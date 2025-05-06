package utils.models;

import java.util.List;

public class CartTestData {
   public List<Item> items;
   public OrderForm orderForm;


    public static class OrderForm {
        public String name;
        public String country;
        public String city;
        public String card;
        public String month;
        public String year;
    }
   public static class Item{
       public String name;
       public double price;
   }
   public double getTotalCost(){
        return items.stream().mapToDouble(item -> item.price).sum();
    }
    public List<String> getItemsNames(){
        return  items.stream().map(item -> item.name).toList();
    }
}
