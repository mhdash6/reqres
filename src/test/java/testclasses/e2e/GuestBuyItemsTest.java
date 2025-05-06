package testclasses.e2e;

import PageComponents.OrderForm;
import com.demoblaze.CartPage;
import com.demoblaze.HomePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.models.E2eTestData;



public class GuestBuyItemsTest {
    E2eTestData testData;
    E2eTestData.OrderForm orderFormData;

    public GuestBuyItemsTest(E2eTestData e2eTestData){
        testData=e2eTestData;
        orderFormData = testData.orderForm;
    }

    @Test
    public void testPurchasingProducts() {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.load();
        homePage.addItemsToCart(testData.getProductsNames());
        CartPage cartPage = homePage.navBar.clickCart();
        OrderForm orderForm = cartPage.clickPlaceOrder();
        orderForm.fillOrderForm(orderFormData.name, orderFormData.country,
                orderFormData.city, orderFormData.card, orderFormData.month,
                orderFormData.year);
        orderForm.clickPurchase();
        softAssert.assertTrue(orderForm.isSuccessful(), "Order was not successful.");
        orderForm.clickOk();
        softAssert.assertAll();
    }
}
