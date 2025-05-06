package utils.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class E2eTestData {
    @JsonProperty("products")
    public List<String> productsNames;
    @JsonProperty("orderForm")
    public OrderForm orderForm;

    public static class OrderForm {
        public String name;
        public String country;
        public String city;
        public String card;
        public String month;
        public String year;
    }

    public String [] getProductsNames(){
        return  productsNames.toArray( new String[0]);
    }
}
