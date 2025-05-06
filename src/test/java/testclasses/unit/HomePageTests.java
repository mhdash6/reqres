package testclasses.unit;

import com.demoblaze.HomePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.common.ExcelUtils;

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

    @Test
    public void validateFirstPageItemsCount(){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.load();
        int count= homePage.getProductCount();
        excelSheet.setCellData(1,2,count);
        excelSheet.save();
        softAssert.assertEquals(count,firstPageItemsCount);
        softAssert.assertAll();
    }

    @Test
    public void validateSecondPageItemsCount(){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.load();
        homePage.clickNextPage();
        int count= homePage.getProductCount();
        excelSheet.setCellData(2,2,count);
        excelSheet.save();
        softAssert.assertEquals(count,secondPageItemsCount);
        softAssert.assertAll();
    }

    @Test
    public void validatePhonesCount(){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.load();
        homePage.clickPhonesLink();
        int count= homePage.getProductCount();
        excelSheet.setCellData(3,2,count);
        excelSheet.save();
        softAssert.assertEquals(count,phonesCount);
        softAssert.assertAll();
    }

    @Test
    public void validateLaptopsCount(){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.load();
        homePage.clickLaptopsLink();
        int count= homePage.getProductCount();
        excelSheet.setCellData(4,2,count);
        excelSheet.save();
        softAssert.assertEquals(count,laptopsCount);
        softAssert.assertAll();
    }
    @Test
    public void validateMonitorsCount(){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.load();
        homePage.clickMonitorsLink();
        int count= homePage.getProductCount();
        excelSheet.setCellData(5,2,count);
        excelSheet.save();
        softAssert.assertEquals(count,monitorsCount);
        softAssert.assertAll();
    }

}
