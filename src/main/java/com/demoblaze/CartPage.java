package com.demoblaze;
import static  utilities.Tables.*;
import BasePage.BasePage;

import PageComponents.NavBar;
import PageComponents.OrderForm;
import org.openqa.selenium.By;


public class CartPage extends BasePage<CartPage> {

    public NavBar<CartPage> navBar;

    private final By placeOrderBtn = By.cssSelector(".btn.btn-success");
    private final By tableHeaders = By.cssSelector("thead th");
    private final By tableRows = By.cssSelector("tbody tr");
    private final By tableCell = By.cssSelector("tbody td");


    public CartPage(){
        navBar= new NavBar<CartPage>(this);
    }


    public int getItemsCount(){
         return getDriver().findElements(tableRows).size();
    }

    public void printItemsInCart(){
        String [][] rows=getTableRows(tableRows, tableCell );
        for(int i=0; i< rows.length; i++ ){
         String [] row= rows[i];
         for(int j =0; j<row.length ; j++){
             System.out.print(row[j]+" ");
         }
            System.out.println();
        }
    }

    public OrderForm<CartPage> clickPlaceOrder(){
        click(placeOrderBtn);
        return new OrderForm<>(this);
    }


}
