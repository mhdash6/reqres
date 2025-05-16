package utils.testFactories;

import com.github.javafaker.Faker;
import org.testng.annotations.Factory;
import testclasses.e2e.GuestBuyItemsTest;
import testclasses.e2e.UserBuyItemsTest;
import testclasses.features.*;
import utilities.common.ExcelUtils;
import utils.dataproviders.DataProviders;
import utils.models.CartTestData;
import utils.models.E2eTestData;
import utils.models.LoginTestData;
import utils.models.OrderFormTestData;

import java.util.List;
import java.util.Map;


public class TestFactory {
    @Factory(dataProvider = "cartData", dataProviderClass = DataProviders.class)
    public Object[] createCartTests(CartTestData cartData) {
        return new Object[]{ new CartTests(cartData) };
    }

    @Factory(dataProvider = "e2eGuestBuyItemsData", dataProviderClass = DataProviders.class)
    public Object[] createE2eGuestTest(E2eTestData e2eData) {
        return new Object[]{
                new GuestBuyItemsTest(e2eData),
        };
    }
    @Factory(dataProvider = "e2eUserBuyItemsData", dataProviderClass = DataProviders.class)
    public Object[] createE2eUserTest(E2eTestData e2eData, LoginTestData validLoginCredentials) {
        return new Object[]{
                new UserBuyItemsTest(e2eData,validLoginCredentials),
        };
    }

    @Factory(dataProvider = "orderFormData", dataProviderClass = DataProviders.class)
    public Object[] createOrderFormTests(OrderFormTestData orderFormData) {
        return new Object[]{ new OrderFormTests(orderFormData) };
    }

    @Factory(dataProvider = "homePageData", dataProviderClass = DataProviders.class)
    public Object[] createHomePageTests(ExcelUtils excelUtils) {
        return new Object[]{ new HomePageTests(excelUtils) };
    }

    @Factory(dataProvider = "loginData", dataProviderClass = DataProviders.class)
    public Object[] createLoginTests(List<LoginTestData> loginList) {
        return new Object[]{ new LoginTests(loginList) };
    }

    @Factory(dataProvider = "contactData", dataProviderClass = DataProviders.class)
    public Object[] createContactUsTests(List<Map<String,String>> messages) {
        return new Object[]{ new ContactUsTests(messages) };
    }

    @Factory
    public Object[] createAboutUsTests() {
        return new Object[]{ new AboutUsTests() };
    }

    @Factory(dataProvider = "signUpData", dataProviderClass = DataProviders.class)
    public Object[] createSignUpTests(Faker faker) {
        return new Object[]{ new SignUpTests(faker)};}
}
