package testclasses.unit;

import PageComponents.AboutUs;
import com.demoblaze.HomePage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import static utilities.common.assertions.AssertionManager.assertFalse;
import static utilities.common.assertions.AssertionManager.assertTrue;

@Feature("About Us Feature")
public class AboutUsTests {

    @Story("Open About Us Page And Find No Errors")
    @Test(groups = {"Smoke","Unit"})
    public void AboutUsNoErrors(){
        HomePage homePage = new HomePage();
        homePage.load();
        AboutUs<HomePage> aboutUs= homePage.navBar.clickAboutUs();
        String errorMsg=aboutUs.getErrorMsg();
        assertTrue(errorMsg.isEmpty(),errorMsg);
    }

    @Story("Close About Us Page")
    @Test(groups = {"Smoke","Unit"})
    public void AboutUsClosable(){
        HomePage homePage = new HomePage();
        homePage.load();
        AboutUs<HomePage> aboutUs= homePage.navBar.clickAboutUs();
        aboutUs.clickClose();
        assertFalse(aboutUs.isDisplayed(),"About us is still displayed.");
    }

}
