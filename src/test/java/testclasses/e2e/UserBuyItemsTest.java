package testclasses.e2e;

import PageComponents.LoginForm;
import PageComponents.OrderForm;
import com.demoblaze.CartPage;
import com.demoblaze.HomePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.models.E2eTestData;
import utils.models.LoginTestData;


public class UserBuyItemsTest {
    E2eTestData testData;
    LoginTestData validLoginCredentials;
    E2eTestData.OrderForm orderFormData;

    public UserBuyItemsTest(E2eTestData e2eTestData, LoginTestData loginTestData){
        this.testData=e2eTestData;
        this.validLoginCredentials=loginTestData;
        orderFormData = testData.orderForm;
    }


    @Test
    public void testProductPurchase() {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.load();
        LoginForm<HomePage> loginForm = homePage.navBar.clickLogin();
        homePage= loginForm.login(validLoginCredentials.username, validLoginCredentials.password);
        softAssert.assertTrue(homePage.navBar.isLoggedIn());
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
