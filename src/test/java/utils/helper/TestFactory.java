package utils.helper;

import org.testng.annotations.Factory;
import testclasses.e2e.GuestBuyItemsTest;
import testclasses.e2e.UserBuyItemsTest;
import testclasses.unit.*;
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


public class TestFactory {
    CartTestData cartTestData = JsonUtils.readJson("src/test/resources/cartTestData.json",CartTestData.class);
    E2eTestData e2eTestData = JsonUtils.readJson("src/test/resources/e2e.json",E2eTestData.class);
    OrderFormTestData orderFormTestData = JsonUtils.readJson("src/test/resources/orderFormTestData.json",OrderFormTestData.class);
    ExcelUtils excelUtils = new ExcelUtils("src/test/resources/homePageTestData.xlsx","Sheet1");
    List<LoginTestData> loginTestData =     CsvUtils.readCsv("src/test/resources/loginTestData.csv", false, LoginTestData.class);
    SqlServerUtils sqlServerUtils = new SqlServerUtils();




    @Factory
    public Object[] factory() {
        return new Object[] {
                new GuestBuyItemsTest(e2eTestData),
                new UserBuyItemsTest(e2eTestData, loginTestData.getFirst()),
                new CartTests(cartTestData),
                new ContactUsTests(sqlServerUtils.readQuery("SELECT * FROM new_message")),
                new HomePageTests(excelUtils),
                new LoginTests(loginTestData),
                new OrderFormTests(orderFormTestData),
                new AboutUsTests()
        };
    }
}
