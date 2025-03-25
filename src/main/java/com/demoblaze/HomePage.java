package com.demoblaze;

import BasePage.BasePage;
import PageComponents.NavBar;
import org.openqa.selenium.By;

public class HomePage extends BasePage {
    public NavBar<HomePage> navBar = new NavBar<>(this);
    private final String url = "https://www.demoblaze.com/";
    private final By phone = By.cssSelector("body > div:nth-child(6) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > h4:nth-child(1) > a:nth-child(1)");
    public void loadHomePage() {
        driver.get(url);
    }

    public void closeAlert(){
        driver.switchTo().alert().accept();
    }

//    public void add(){
//        click(phone);
//        click(By.cssSelector(".btn.btn-success.btn-lg"));
//    }

}

