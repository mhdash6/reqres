package testclasses;

import PageComponents.ContactForm;
import PageComponents.LoginForm;
import PageComponents.OrderForm;
import com.demoblaze.CartPage;
import com.demoblaze.HomePage;
import com.demoblaze.ItemPage;
import org.apache.logging.log4j.ThreadContext;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utilities.common.DataUtils;
import utilities.common.DateTime;
import utilities.selenium.helperClasses.ScreenShotUtils;



import java.util.Map;

public class SimpleTests {
    private HomePage homePage= new HomePage();
    private Map<String, Object> testData = (Map<String, Object>) DataUtils.readJson("src/main/resources/testData.json");



    @BeforeClass
    public void loadTestData(  ) {
        ThreadContext.put("datetime", DateTime.getDateTime());
    }

    @Test
    public void testContactFormSubmission() {
        homePage.loadHomePage();
        SoftAssert softAssert = new SoftAssert();
        Map<String, String> contactFormData = (Map<String, String>) testData.get("contactForm");
        ContactForm<HomePage> contactForm = homePage.navBar.clickContact();
        contactForm.enterMessage(contactFormData.get("message"));
        contactForm.enterEmail(contactFormData.get("email"));
        contactForm.enterName(contactFormData.get("name"));
        homePage = contactForm.clickSendMessageBtn();
        softAssert.assertTrue(contactForm.successMsgDisplayed(), "Contact form submission failed.");
        softAssert.assertAll();
    }

    @Test
    public void testLogin() {
        homePage.loadHomePage();
        SoftAssert softAssert = new SoftAssert();
        Map<String, String> loginFormData = (Map<String, String>) testData.get("loginForm");
        LoginForm<HomePage> loginForm = homePage.navBar.clickLogin();
        loginForm.enterUserName(loginFormData.get("username"));
        loginForm.enterPassword(loginFormData.get("password"));
        homePage = loginForm.clickLogin();
        ScreenShotUtils.takeScreenShot("Login");
        softAssert.assertTrue(homePage.navBar.isLoggedIn(), "Login failed.");
        softAssert.assertAll();
    }

    @Test
    public void testProductPurchase() {
        homePage.loadHomePage();
        SoftAssert softAssert = new SoftAssert();
        String productName = (String) testData.get("product");
        Map<String, String> orderFormData = (Map<String, String>) testData.get("orderForm");
        ItemPage itemPage = homePage.addProductToCart(productName);
        itemPage.clickAddToCart();
        itemPage.acceptAlert();
        CartPage cartPage = itemPage.navBar.clickCart();
        cartPage.printItemsInCart();
        OrderForm<CartPage> orderForm = cartPage.clickPlaceOrder();
        orderForm.enterName(orderFormData.get("name"));
        orderForm.enterCountry(orderFormData.get("country"));
        orderForm.enterCity(orderFormData.get("city"));
        orderForm.enterCard(orderFormData.get("card"));
        orderForm.enterMonth(orderFormData.get("month"));
        orderForm.enterYear(orderFormData.get("year"));
        orderForm.clickPurchase();
        softAssert.assertTrue(orderForm.isSuccessful(), "Order was not successful.");
        orderForm.clickOk();
        softAssert.assertAll();
    }

    @Test
    public void testCartDeletion() {
        homePage.loadHomePage();
        SoftAssert softAssert = new SoftAssert();
        String productName = (String) testData.get("product");
        ItemPage itemPage = homePage.addProductToCart(productName);
        itemPage.clickAddToCart();
        itemPage.acceptAlert();
        CartPage cartPage = itemPage.navBar.clickCart();
        cartPage.printItemsInCart();
        cartPage.deleteItem(productName);
        cartPage.printItemsInCart();
        softAssert.assertTrue(cartPage.isCartEmpty(), "Cart is not empty after deletion.");
        softAssert.assertAll();
    }

}
