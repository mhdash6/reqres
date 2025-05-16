package testclasses.features;

import static org.apache.commons.collections4.CollectionUtils.isEqualCollection;
import static utilities.common.assertions.AssertionManager.assertEquals;
import static utilities.common.assertions.AssertionManager.assertTrue;

import com.demoblaze.CartPage;
import com.demoblaze.HomePage;
import com.demoblaze.ItemPage;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import utils.models.CartTestData;

import java.util.List;


@Feature("Cart Feature")
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

    @Story("Add single item to cart")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verifies that a single item can be added to the cart and the item details (name and price) are correct.")
    @Test(groups = {"Smoke", "Unit"})
    public void addOneItemToCart(){
        HomePage homePage = new HomePage();
        homePage.load();
        ItemPage itemPage = homePage.clickOnProduct(item1.name);
        itemPage.clickAddToCart();
        itemPage.acceptAlert();
        CartPage cartPage = itemPage.navBar.clickCart();
        String ItemInCart =  cartPage.getItemsNames().getFirst();
        assertEquals(ItemInCart,item1.name);
        assertEquals(cartPage.getTotal(),item1.price);
    }

    @Story("Add multiple items to cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verifies that multiple selected items can be added to the cart and their total cost and names match the expected values.")
    @Test(groups = {"Unit"})
    public void addMultipleItemsToCart(){
        HomePage homePage = new HomePage();
        homePage.load();
        homePage.addItemsToCart(item1.name, item2.name,item3.name);
        CartPage cartPage = homePage.navBar.clickCart();
        List<String> itemsInCart = cartPage.getItemsNames();
        assertTrue(isEqualCollection(itemsInCart,items.getItemsNames()));
        assertEquals(cartPage.getTotal(),items.getTotalCost());
    }

    @Story("Remove item from cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verifies that an item can be added to the cart and successfully removed, resulting in an empty cart.")
    @Test(groups = {"Smoke", "Unit"})
    public void addItemToCartAndDelete(){
        HomePage homePage = new HomePage();
        homePage.load();
        ItemPage itemPage = homePage.clickOnProduct(item1.name);
        itemPage.clickAddToCart();
        itemPage.acceptAlert();
        CartPage cartPage = itemPage.navBar.clickCart();
        cartPage.deleteItem(item1.name);
        assertTrue(cartPage.isCartEmpty(), "Cart is not empty after deletion.");
    }

    @Story("View empty cart on first load")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifies that the shopping cart is initially empty when the homepage is first loaded.")
    @Test(groups = {"Unit"})
    public void CartStartedWithNoItems(){
        HomePage homePage = new HomePage();
        homePage.load();
        CartPage cartPage = homePage.navBar.clickCart();
        assertTrue(cartPage.isCartEmpty(), "Cart is not empty at the start.");
    }
}
