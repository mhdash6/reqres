package testclasses.unit;

import static org.apache.commons.collections4.CollectionUtils.isEqualCollection;
import com.demoblaze.CartPage;
import com.demoblaze.HomePage;
import com.demoblaze.ItemPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.models.CartTestData;

import java.util.List;



public class CartTests {
    CartTestData.Item item1;
    CartTestData.Item item2;
    CartTestData.Item item3;
    CartTestData items;

    public CartTests(CartTestData cartTestData){
       item1=  cartTestData.items.get(0);
       item2 = cartTestData.items.get(1);
       item3 = cartTestData.items.get(2);
       items = cartTestData;
    }


    @Test
    public void addItemToCart(){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.load();
        ItemPage itemPage = homePage.clickOnProduct(item1.name);
        itemPage.clickAddToCart();
        itemPage.acceptAlert();
        CartPage cartPage = itemPage.navBar.clickCart();
        String ItemInCart =  cartPage.getItemsNames().getFirst();
        softAssert.assertEquals(ItemInCart,item1.name);
        softAssert.assertEquals(cartPage.getTotal(),item1.price);
        softAssert.assertAll();
    }

    @Test
    public void addMultipleItemsToCart(){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.load();
        homePage.addItemsToCart(item1.name, item2.name,item3.name);
        CartPage cartPage = homePage.navBar.clickCart();
        List<String> itemsInCart = cartPage.getItemsNames();
        softAssert.assertTrue(isEqualCollection(itemsInCart,items.getItemsNames()));
        softAssert.assertEquals(cartPage.getTotal(),items.getTotalCost());
        softAssert.assertAll();
    }

    @Test
    public void addItemToCartAndDelete(){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.load();
        ItemPage itemPage = homePage.clickOnProduct(item1.name);
        itemPage.clickAddToCart();
        itemPage.acceptAlert();
        CartPage cartPage = itemPage.navBar.clickCart();
        cartPage.deleteItem(item1.name);
        softAssert.assertTrue(cartPage.isCartEmpty(), "Cart is not empty after deletion.");
        softAssert.assertAll();
    }

    @Test
    public void CartStartedWithNoItems(){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.load();
        CartPage cartPage = homePage.navBar.clickCart();
        softAssert.assertTrue(cartPage.isCartEmpty(), "Cart is not empty at the start.");
        softAssert.assertAll();
    }
}
