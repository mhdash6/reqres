package testclasses.unit;

import PageComponents.ContactForm;
import com.demoblaze.HomePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.common.ExcelUtils;
import utilities.common.SqlServerUtils;

import java.util.List;
import java.util.Map;

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

    @Test
    public void ContactInEnglish(){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.load();
        ContactForm<HomePage> contactForm= homePage.navBar.clickContact();
        contactForm.fillContactForm(contactInEnglish.get("email"),
                contactInEnglish.get("contact_name"),
                contactInEnglish.get("message"));
        contactForm.clickSendMessageBtn();
        softAssert.assertTrue(contactForm.successMsgDisplayed());
        contactForm.acceptSucessAlert();
        softAssert.assertAll();
    }

    @Test
    public void ContactInArabic(){
         SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.load();
        ContactForm<HomePage> contactForm= homePage.navBar.clickContact();
        contactForm.fillContactForm(contactInArabic.get("email"),
                contactInArabic.get("contact_name"),
                contactInArabic.get("message"));
        contactForm.clickSendMessageBtn();
        softAssert.assertTrue(contactForm.successMsgDisplayed());
        contactForm.acceptSucessAlert();
        softAssert.assertAll();
    }

    @Test
    public void ContactWithEmojis(){
         SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.load();
        ContactForm<HomePage> contactForm= homePage.navBar.clickContact();
        contactForm.fillContactForm(contactWithEmojis.get("email"),
                contactWithEmojis.get("contact_name"),
                contactWithEmojis.get("message"));
        contactForm.clickSendMessageBtn();
        softAssert.assertTrue(contactForm.successMsgDisplayed());
        contactForm.acceptSucessAlert();
        softAssert.assertAll();
    }

    @Test
    public void ContactEmptyEmailAndNameFields(){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.load();
        ContactForm<HomePage> contactForm= homePage.navBar.clickContact();
        contactForm.fillContactForm(contactWithEmptyFields.get("email"),
                contactWithEmptyFields.get("contact_name"),
                contactWithEmptyFields.get("message"));
        contactForm.clickSendMessageBtn();
        softAssert.assertTrue(contactForm.successMsgDisplayed(),"Success message is not displayed.");
        contactForm.acceptSucessAlert();
        softAssert.assertAll();
    }

    @Test
    public void ContactWithAllFieldsEmpty(){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.load();
        ContactForm<HomePage> contactForm= homePage.navBar.clickContact();
        contactForm.clickSendMessageBtn();
        softAssert.assertFalse(contactForm.successMsgDisplayed(),"Success message is displayed." +
                " Contact form should not be submitted if all fields are empty.");
        contactForm.acceptSucessAlert();
        softAssert.assertAll();
    }

}
