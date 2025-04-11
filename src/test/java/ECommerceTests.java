import BasePage.BasePage;
import PageComponents.AboutUs;
import PageComponents.ContactForm;
import PageComponents.OrderForm;
import com.demoblaze.CartPage;
import com.demoblaze.HomePage;
import com.demoblaze.ItemPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utilities.Alert;
import utilities.BaseUtility;

public class ECommerceTests {

    private HomePage homePage;
    private WebDriver driver;
    private SoftAssert softAssert;



    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        BasePage.setDriver(driver);
        BaseUtility.setDriver(driver);
        softAssert = new SoftAssert();
        homePage = new HomePage();
        homePage.loadHomePage();
    }

    @Test
    public void testContactFormSubmission() {
        ContactForm<HomePage> contactForm = homePage.navBar.clickContact();
        contactForm.enterMessage("Hello, this is a test message.");
        contactForm.enterEmail("test@example.com");
        contactForm.enterName("Test User");
        homePage = contactForm.clickSendMessageBtn();
        softAssert.assertTrue(contactForm.successMsgDisplayed());
        Alert.clickOK();
        softAssert.assertAll();
    }


    @Test
    public void testProductPurchase() {
        ItemPage itemPage = homePage.addProductToCart("Nokia lumia 1520");
        itemPage.clickAddToCart();
        itemPage.acceptAlert();
        CartPage cartPage = itemPage.navBar.clickCart();
        cartPage.printItemsInCart();
        cartPage.deleteItem("Nokia lumia 1520");
        cartPage.printItemsInCart();
        OrderForm<CartPage> orderForm = cartPage.clickPlaceOrder();
        orderForm.enterName("John Doe");
        orderForm.enterCountry("USA");
        orderForm.enterCity("New York");
        orderForm.enterCard("1234567812345678");
        orderForm.enterMonth("12");
        orderForm.enterYear("2025");
        orderForm.clickPurchase();
        softAssert.assertTrue(orderForm.isSuccessful(), "Order was successful.");
        orderForm.clickOk();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}