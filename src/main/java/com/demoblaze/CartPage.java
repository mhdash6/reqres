package com.demoblaze;
import static  utilities.Tables.*;
import BasePage.BasePage;

import PageComponents.NavBar;
import PageComponents.OrderForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Waits;

import java.util.List;


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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String [][] rows=getTableRows(tableRows, tableCell );
        if(rows.length==0){
            System.out.println("Empty Cart");
        }
        for(int i=0; i< rows.length; i++ ){
         String [] row= rows[i];
         for(int j =0; j<row.length ; j++){
             System.out.print(row[j]+" ");
         }
            System.out.println();
        }
    }

    public OrderForm<CartPage> clickPlaceOrder(){
        Waits.waitForVisibility(tableRows,2);
        click(placeOrderBtn);
        return new OrderForm<>(this);
    }

    public void deleteItem(String deleteThis){
        Waits.waitForVisibility(tableRows,2);
        List<WebElement> rows = getDriver().findElements(tableRows);
        for(WebElement row: rows){
            String itemName = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
            if(itemName.equals(deleteThis)){
                row.findElement(By.tagName("a")).click();
            }
        }
    }


}
