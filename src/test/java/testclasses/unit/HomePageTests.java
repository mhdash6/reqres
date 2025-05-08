package testclasses.unit;

import com.demoblaze.HomePage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.common.ExcelUtils;

import static utilities.common.assertions.AssertionManager.assertEquals;

@Feature("Home Page Feature")
public class HomePageTests {
    int firstPageItemsCount;
    int secondPageItemsCount;
    int phonesCount;
    int laptopsCount;
    int monitorsCount;
    ExcelUtils excelSheet;

    public HomePageTests(ExcelUtils excelUtils){
        excelSheet=excelUtils;
        firstPageItemsCount = Integer.parseInt(excelUtils.getCellData(1,1));
        secondPageItemsCount = Integer.parseInt(excelUtils.getCellData(2,1));
        phonesCount = Integer.parseInt(excelUtils.getCellData(3,1));
        laptopsCount = Integer.parseInt(excelUtils.getCellData(4,1));
        monitorsCount = Integer.parseInt(excelUtils.getCellData(5,1));
    }

    @Story("View Correct Item Count For First Page")
    @Test(groups = {"Smoke","Unit"})
    public void validateFirstPageItemsCount(){
        HomePage homePage = new HomePage();
        homePage.load();
        int count= homePage.getProductCount();
        excelSheet.setCellData(1,2,count);
        excelSheet.save();
        assertEquals(count,firstPageItemsCount);
    }

    @Story("View Correct Item Count For Second Page")
    @Test(groups = {"Smoke","Unit"})
    public void validateSecondPageItemsCount(){
        HomePage homePage = new HomePage();
        homePage.load();
        homePage.clickNextPage();
        int count= homePage.getProductCount();
        excelSheet.setCellData(2,2,count);
        excelSheet.save();
        assertEquals(count,secondPageItemsCount);
    }

    @Story("View Correct Item Count for Phones category")
    @Test(groups = "Unit")
    public void validatePhonesCount(){
        HomePage homePage = new HomePage();
        homePage.load();
        homePage.clickPhonesLink();
        int count= homePage.getProductCount();
        excelSheet.setCellData(3,2,count);
        excelSheet.save();
        assertEquals(count,phonesCount);
    }

    @Story("View Correct Item Count for Laptops category")
    @Test(groups = "Unit")
    public void validateLaptopsCount(){
        HomePage homePage = new HomePage();
        homePage.load();
        homePage.clickLaptopsLink();
        int count= homePage.getProductCount();
        excelSheet.setCellData(4,2,count);
        excelSheet.save();
        assertEquals(count,laptopsCount);
    }

    @Story("View Correct Item Count for Monitors category")
    @Test(groups = "Unit")
    public void validateMonitorsCount(){
        HomePage homePage = new HomePage();
        homePage.load();
        homePage.clickMonitorsLink();
        int count= homePage.getProductCount();
        excelSheet.setCellData(5,2,count);
        excelSheet.save();
        assertEquals(count,monitorsCount);
    }

}
