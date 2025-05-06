package testclasses.unit;

import PageComponents.AboutUs;
import com.demoblaze.HomePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AboutUsTests {

    @Test
    public void AboutUsNoErrors(){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.load();
        AboutUs<HomePage> aboutUs= homePage.navBar.clickAboutUs();
        String errorMsg=aboutUs.getErrorMsg();
        softAssert.assertTrue(errorMsg.isEmpty(),errorMsg);
        softAssert.assertAll();
    }

    @Test
    public void AboutUsClosable(){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.load();
        AboutUs<HomePage> aboutUs= homePage.navBar.clickAboutUs();
        aboutUs.clickClose();
        softAssert.assertFalse(aboutUs.isDisplayed(),"About us is still displayed.");
        softAssert.assertAll();
    }


}
