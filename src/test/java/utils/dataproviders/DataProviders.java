package utils.dataproviders;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;
import utilities.common.CsvUtils;
import utilities.common.ExcelUtils;
import utilities.common.JsonUtils;
import utilities.common.SqlServerUtils;
import utils.models.CartTestData;
import utils.models.E2eTestData;
import utils.models.LoginTestData;
import utils.models.OrderFormTestData;

import java.util.List;
import java.util.Map;

public class DataProviders {
    private static final String BASE = "src/test/resources/";

    @DataProvider(name = "cartData")
    public static Object[][] cartData() {
        CartTestData data = JsonUtils.readJson(
                BASE + "cartTestData.json", CartTestData.class);
        return new Object[][] {{ data }};
    }

    @DataProvider(name = "e2eGuestBuyItemsData")
    public static Object[][] e2eData() {
        E2eTestData data = JsonUtils.readJson(BASE + "e2e.json", E2eTestData.class
        );
        return new Object[][] {{ data }};
    }

    @DataProvider(name = "e2eUserBuyItemsData")
    public static Object[][] e2eUserBuyItemsData() {
        E2eTestData data = JsonUtils.readJson(BASE + "e2e.json", E2eTestData.class);
        List<LoginTestData> list = CsvUtils.readCsv(
                BASE + "loginTestData.csv", false, LoginTestData.class);
        return new Object[][] {{ data,list.getFirst() }};
    }

    @DataProvider(name = "orderFormData")
    public static Object[][] orderFormData() {
        OrderFormTestData data = JsonUtils.readJson(
                BASE + "orderFormTestData.json", OrderFormTestData.class
        );
        return new Object[][] {{ data }};
    }

    @DataProvider(name = "homePageData")
    public static Object[][] homePageData() {
        ExcelUtils excel = new ExcelUtils(BASE + "homePageTestData.xlsx", "Sheet1");
        return new Object[][] {{ excel }};
    }

    @DataProvider(name = "loginData")
    public static Object[][] loginData() {
        List<LoginTestData> list = CsvUtils.readCsv(
                BASE + "loginTestData.csv", false, LoginTestData.class
        );
        return new Object[][] {{ list }};
    }

    @DataProvider(name = "contactData")
    public static Object[][] contactData() {
        SqlServerUtils db = new SqlServerUtils();
        List<Map<String,String>> messages = db.readQuery("SELECT * FROM new_message");
        return new Object[][] {{messages}};
    }
    @DataProvider(name = "signUpData")
    public static Object[][] signUpData() {
        return new Object[][] {
                { new Faker() }
            };
        }



}
