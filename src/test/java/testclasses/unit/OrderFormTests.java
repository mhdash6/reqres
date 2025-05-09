package testclasses.unit;

import PageComponents.OrderForm;
import com.demoblaze.CartPage;
import com.demoblaze.HomePage;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.models.OrderFormTestData;

import static utilities.common.assertions.AssertionManager.*;

@Feature("Order Form Feature")
public class OrderFormTests {
    OrderFormTestData testData;
    OrderFormTestData.OrderForm buyer;

    public OrderFormTests(OrderFormTestData orderFormTestData){
        testData=orderFormTestData;
        buyer = orderFormTestData.orderForm;
    }


    @Story("Place order with complete details")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verifies that the order can be placed successfully when all buyer details are provided.")
    @Test(groups = {"Smoke", "Unit"})
    public void OrderFormWithValidData(){
        HomePage homePage = new HomePage();
        homePage.load();
        homePage.addItemsToCart(testData.getItemsNames());
        CartPage cartPage= homePage.navBar.clickCart();
        OrderForm orderForm= cartPage.clickPlaceOrder();
        orderForm.fillOrderForm(buyer.name, buyer.country, buyer.city, buyer.card, buyer.month, buyer.year );
        orderForm.clickPurchase();
        assertEquals(orderForm.getAmount(),testData.getTotalCost());
        assertTrue(orderForm.isSuccessful(),"Order was not successful.");
    }

    @Story("Show error when buyer info missing")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifies that an error message is shown when the buyer's information is incomplete.")
    @Test(groups = "Unit")
    public void OrderFormWithNoData(){
        HomePage homePage = new HomePage();
        homePage.load();
        homePage.addItemsToCart(testData.getItemsNames()[0]);
        CartPage cartPage= homePage.navBar.clickCart();
        OrderForm orderForm= cartPage.clickPlaceOrder();
        orderForm.clickPurchase();
        assertTrue(orderForm.IsCorrectErrorMsgDisplayed(),"Error message is not displayed.");
        orderForm.acceptAlert();
    }

    @Story("Prevent order when cart is empty")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verifies that the order cannot be placed when the cart is empty.")
    @Test(groups = "Unit")
    public void OrderFormWithEmptyCart(){
        HomePage homePage = new HomePage();
        homePage.load();
        CartPage cartPage = homePage.navBar.clickCart();
        assertTrue(cartPage.isCartEmpty(),"Cart is not empty at the start.");
        OrderForm orderForm= cartPage.clickPlaceOrder();
        orderForm.fillOrderForm(buyer.name, buyer.country, buyer.city, buyer.card, buyer.month, buyer.year );
        orderForm.clickPurchase();
        assertFalse(orderForm.isSuccessful(),"Order was successful but should not be as cart is empty.");
    }

    @Story("Allow order submission without address")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifies that an order can be placed even if the buyer's address is missing.")
    @Test(groups = "Unit")
    public void OrderFormWithNoAddress(){
        HomePage homePage = new HomePage();
        homePage.load();
        homePage.addItemsToCart(testData.getItemsNames());
        CartPage cartPage= homePage.navBar.clickCart();
        OrderForm orderForm= cartPage.clickPlaceOrder();
        orderForm.fillOrderForm(buyer.name, "", "", buyer.card, buyer.month, buyer.year );
        orderForm.clickPurchase();
        assertTrue(orderForm.isSuccessful(),"Order was not successful.");
    }
}
