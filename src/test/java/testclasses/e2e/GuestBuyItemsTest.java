package testclasses.e2e;

import PageComponents.OrderForm;
import com.demoblaze.CartPage;
import com.demoblaze.HomePage;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import utils.models.E2eTestData;

import static utilities.common.assertions.AssertionManager.assertTrue;


@Feature("Guest Buy Items Feature")
public class GuestBuyItemsTest {
    E2eTestData testData;
    E2eTestData.OrderForm orderFormData;

    public GuestBuyItemsTest(E2eTestData e2eTestData){
        testData=e2eTestData;
        orderFormData = testData.orderForm;
    }


    @Story("Complete purchase of selected products")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verifies that products can be added to the cart and a purchase can be completed successfully without logging in.")
    @Test(groups = "E2E")
    public void testPurchasingProducts() {
        HomePage homePage = new HomePage();
        homePage.load();
        homePage.addItemsToCart(testData.getProductsNames());
        CartPage cartPage = homePage.navBar.clickCart();
        OrderForm orderForm = cartPage.clickPlaceOrder();
        orderForm.fillOrderForm(orderFormData.name, orderFormData.country,
                orderFormData.city, orderFormData.card, orderFormData.month,
                orderFormData.year);
        orderForm.clickPurchase();
        assertTrue(orderForm.isSuccessful(), "Order was not successful.");
        orderForm.clickOk();
    }
}
