package utils.models;

import java.util.List;

public class CartTestData {
   public List<Item> items;

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
