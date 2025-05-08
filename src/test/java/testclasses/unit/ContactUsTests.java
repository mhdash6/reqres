package testclasses.unit;

import PageComponents.ContactForm;
import com.demoblaze.HomePage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.common.ExcelUtils;
import utilities.common.SqlServerUtils;

import java.util.List;
import java.util.Map;

import static utilities.common.assertions.AssertionManager.assertFalse;
import static utilities.common.assertions.AssertionManager.assertTrue;

@Feature("Contact Us Feature")
public class ContactUsTests {
    Map<String,String> contactInEnglish;
    Map<String,String> contactInArabic;
    Map<String,String> contactWithEmojis;
    Map<String,String> contactWithEmptyFields;
    public ContactUsTests(List<Map<String,String>> contactTestData ){
        contactInEnglish=  contactTestData.getFirst();
        contactWithEmojis= contactTestData.get(1);
        contactWithEmptyFields = contactTestData.get(2);
        contactInArabic = contactTestData.getLast();
    }

    @Story("Submit contact form in English successfully")
    @Test(groups = {"Smoke","Unit"})
    public void ContactInEnglish(){
        HomePage homePage = new HomePage();
        homePage.load();
        ContactForm<HomePage> contactForm= homePage.navBar.clickContact();
        contactForm.fillContactForm(contactInEnglish.get("email"),
                contactInEnglish.get("contact_name"),
                contactInEnglish.get("message"));
        contactForm.clickSendMessageBtn();
        assertTrue(contactForm.successMsgDisplayed());
        contactForm.acceptSucessAlert();
    }

    @Story("Submit contact form in Arabic successfully")
    @Test(groups = "Unit")
    public void ContactInArabic(){
        HomePage homePage = new HomePage();
        homePage.load();
        ContactForm<HomePage> contactForm= homePage.navBar.clickContact();
        contactForm.fillContactForm(contactInArabic.get("email"),
                contactInArabic.get("contact_name"),
                contactInArabic.get("message"));
        contactForm.clickSendMessageBtn();
        assertTrue(contactForm.successMsgDisplayed());
        contactForm.acceptSucessAlert();
    }

    @Story("Send contact message with emojis successfully")
    @Test(groups = "Unit")
    public void ContactWithEmojis(){
        HomePage homePage = new HomePage();
        homePage.load();
        ContactForm<HomePage> contactForm= homePage.navBar.clickContact();
        contactForm.fillContactForm(contactWithEmojis.get("email"),
                contactWithEmojis.get("contact_name"),
                contactWithEmojis.get("message"));
        contactForm.clickSendMessageBtn();
        assertTrue(contactForm.successMsgDisplayed());
        contactForm.acceptSucessAlert();
    }

    @Story("Allow message submission with only message body")
    @Test(groups = "Unit")
    public void ContactEmptyEmailAndNameFields(){
        HomePage homePage = new HomePage();
        homePage.load();
        ContactForm<HomePage> contactForm= homePage.navBar.clickContact();
        contactForm.fillContactForm(contactWithEmptyFields.get("email"),
                contactWithEmptyFields.get("contact_name"),
                contactWithEmptyFields.get("message"));
        contactForm.clickSendMessageBtn();
        assertTrue(contactForm.successMsgDisplayed(),"Success message is not displayed.");
        contactForm.acceptSucessAlert();
    }

    @Story("Prevent empty contact form submission")
    @Test(groups = "Unit")
    public void ContactWithAllFieldsEmpty(){
        HomePage homePage = new HomePage();
        homePage.load();
        ContactForm<HomePage> contactForm= homePage.navBar.clickContact();
        contactForm.clickSendMessageBtn();
        assertFalse(contactForm.successMsgDisplayed(),"Success message is displayed." +
                " Contact form should not be submitted if all fields are empty.");
    }

}
