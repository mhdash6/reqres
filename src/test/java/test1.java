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






public class test1 {
    HomePage homePage;
    WebDriver driver;
    SoftAssert softAssert;

    @BeforeMethod
    public void setup() {

        driver =new ChromeDriver();
        BasePage.setDriver(driver);
        BaseUtility.setDriver(driver);
        softAssert = new SoftAssert();
        homePage = new HomePage();
        homePage.loadHomePage();
    }
//    @Test
//    public void testContact() {
//        ContactForm<HomePage> newMessage = homePage.navBar.clickContact();
//        newMessage.enterMessage("hi");
//        newMessage.enterEmail("ash");
//        newMessage.enterName("ash");
//        homePage = newMessage.clickSendMessageBtn();
//        Alert.clickOK();
//    }
//
//
//    @Test
//    public void cartTable(){
//        CartPage cartPage= homePage.navBar.clickCart();
//        AboutUs<CartPage> aboutUs = cartPage.navBar.clickAboutUs();
//        System.out.println(aboutUs.getErrorMsg());
//        aboutUs.clickExit();
//        cartPage=aboutUs.clickClose();
//        System.out.println(cartPage.getItemsCount());
//        cartPage.printItemsInCart();
//    }

    @Test
    public void purchase(){
       ItemPage itemPage= homePage.addProductToCart();
       itemPage.clickAddToCart();
       itemPage.acceptAlert();
       CartPage cartPage= itemPage.navBar.clickCart();
       cartPage.printItemsInCart();
       cartPage.deleteItem("Nokia lumia 1520");
       cartPage.printItemsInCart();
//       OrderForm orderForm= cartPage.clickPlaceOrder();
//       orderForm.enterCard("12");
//       orderForm.enterCountry("eg");
//       orderForm.enterCity("as");
//       orderForm.enterMonth("12");
//       orderForm.enterYear("12");
//       orderForm.enterName("ash");
//       orderForm.clickPurchase();
//       softAssert.assertTrue(orderForm.isSuccesfull());
//       orderForm.clickOk();
    }



    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}

