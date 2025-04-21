import BasePage.BasePage;
import PageComponents.ContactForm;
import PageComponents.LoginForm;
import PageComponents.OrderForm;
import com.demoblaze.CartPage;
import com.demoblaze.HomePage;
import com.demoblaze.ItemPage;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utilities.DataUtil;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Map;

public class SimpleTests {

    private HomePage homePage;
    private WebDriver driver;
    private Map<String, Object> testData;

    @BeforeClass
    public void loadTestData(  ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        ThreadContext.put("datetime", formattedDateTime);
        testData = DataUtil.readJson("src/main/resources/testData.json");

    }
    @Parameters("browser")
    @BeforeMethod
    public void setup( String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }


        ThreadContext.put("browser", browser);
        driver.manage().window().maximize();
        BasePage.setDriver(driver);
        homePage = new HomePage();
        homePage.loadHomePage();
    }

    @Test
    public void testContactFormSubmission() {
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
        SoftAssert softAssert = new SoftAssert();
        Map<String, String> loginFormData = (Map<String, String>) testData.get("loginForm");
        LoginForm<HomePage> loginForm = homePage.navBar.clickLogin();
        loginForm.enterUserName(loginFormData.get("username"));
        loginForm.enterPassword(loginFormData.get("password"));
        homePage = loginForm.clickLogin();
        softAssert.assertTrue(homePage.navBar.isLoggedIn(), "Login failed.");
        softAssert.assertAll();
    }

    @Test
    public void testProductPurchase() {
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

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterClass(alwaysRun = true)
    public void closeDriver() {
        ThreadContext.clearMap();
    }
}
