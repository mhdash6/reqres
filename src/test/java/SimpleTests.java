import BasePage.BasePage;
import PageComponents.ContactForm;
import PageComponents.LoginForm;
import PageComponents.OrderForm;
import com.demoblaze.CartPage;
import com.demoblaze.HomePage;
import com.demoblaze.ItemPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utilities.BaseUtility;
import utilities.DataUtil;

import java.util.Map;

public class SimpleTests {

    private HomePage homePage;
    private WebDriver driver;
    private SoftAssert softAssert;
    private Map<String, Object> testData;

    @BeforeClass
    public void loadTestData() {
        testData = DataUtil.readJson("src/main/resources/testData.json");
        softAssert = new SoftAssert();
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        BasePage.setDriver(driver);
        BaseUtility.setDriver(driver);
        homePage = new HomePage();
        homePage.loadHomePage();
    }

    @Test(priority = 4)
    public void testContactFormSubmission() {
        Map<String, String> contactFormData = (Map<String, String>) testData.get("contactForm");
        ContactForm<HomePage> contactForm = homePage.navBar.clickContact();
        contactForm.enterMessage(contactFormData.get("message"));
        contactForm.enterEmail(contactFormData.get("email"));
        contactForm.enterName(contactFormData.get("name"));
        homePage = contactForm.clickSendMessageBtn();
        softAssert.assertTrue(contactForm.successMsgDisplayed());
    }

    @Test(priority = 3)
    public void testLogin() {
        Map<String, String> loginFormData = (Map<String, String>) testData.get("loginForm");
        LoginForm<HomePage> loginForm = homePage.navBar.clickLogin();
        loginForm.enterUserName(loginFormData.get("username"));
        loginForm.enterPassword(loginFormData.get("password"));
        homePage = loginForm.clickLogin();
        softAssert.assertTrue(homePage.navBar.isLoggedIn());
    }

    @Test(priority = 1)
    public void testProductPurchase() {
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
    }

    @Test(priority = 2)
    public void testCartDeletion() {
        String productName = (String) testData.get("product");
        ItemPage itemPage = homePage.addProductToCart(productName);
        itemPage.clickAddToCart();
        itemPage.acceptAlert();
        CartPage cartPage = itemPage.navBar.clickCart();
        cartPage.printItemsInCart();
        cartPage.deleteItem(productName);
        cartPage.printItemsInCart();
        softAssert.assertTrue(cartPage.isCartEmpty(), "Cart is not empty after deletion.");
    }

    @AfterMethod
    public void tearDown() {
        softAssert.assertAll();
        driver.quit();
    }
}