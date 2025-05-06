package testclasses.unit;

import PageComponents.OrderForm;
import com.demoblaze.CartPage;
import com.demoblaze.HomePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.models.OrderFormTestData;

public class OrderFormTests {
    OrderFormTestData testData;
    OrderFormTestData.OrderForm buyer;

    public OrderFormTests(OrderFormTestData orderFormTestData){
        testData=orderFormTestData;
        buyer = orderFormTestData.orderForm;
    }


    @Test
    public void OrderFormWithValidData(){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.load();
        homePage.addItemsToCart(testData.getItemsNames());
        CartPage cartPage= homePage.navBar.clickCart();
        OrderForm orderForm= cartPage.clickPlaceOrder();
        orderForm.fillOrderForm(buyer.name, buyer.country, buyer.city, buyer.card, buyer.month, buyer.year );
        orderForm.clickPurchase();
        softAssert.assertEquals(orderForm.getAmount(),testData.getTotalCost());
        softAssert.assertTrue(orderForm.isSuccessful(),"Order was not successful.");
        softAssert.assertAll();
    }

    @Test
    public void OrderFormWithNoData(){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.load();
        homePage.addItemsToCart(testData.getItemsNames()[0]);
        CartPage cartPage= homePage.navBar.clickCart();
        OrderForm orderForm= cartPage.clickPlaceOrder();
        orderForm.clickPurchase();
        softAssert.assertTrue(orderForm.IsCorrectErrorMsgDisplayed(),"Error message is not displayed.");
        orderForm.acceptAlert();
        softAssert.assertAll();
    }

    @Test
    public void OrderFormWithEmptyCart(){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.load();
        CartPage cartPage = homePage.navBar.clickCart();
        softAssert.assertTrue(cartPage.isCartEmpty(),"Cart is not empty at the start.");
        OrderForm orderForm= cartPage.clickPlaceOrder();
        orderForm.fillOrderForm(buyer.name, buyer.country, buyer.city, buyer.card, buyer.month, buyer.year );
        orderForm.clickPurchase();
        softAssert.assertFalse(orderForm.isSuccessful(),"Order was successful but should not be as cart is empty.");
        softAssert.assertAll();
    }

    @Test
    public void OrderFormWithNoAddress(){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.load();
        homePage.addItemsToCart(testData.getItemsNames());
        CartPage cartPage= homePage.navBar.clickCart();
        OrderForm orderForm= cartPage.clickPlaceOrder();
        orderForm.fillOrderForm(buyer.name, "", "", buyer.card, buyer.month, buyer.year );
        orderForm.clickPurchase();
        softAssert.assertTrue(orderForm.isSuccessful(),"Order was not successful.");
        softAssert.assertAll();
    }


}
