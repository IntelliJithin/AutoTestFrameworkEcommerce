package TestScripts;

import Commons.BaseClass;
import Commons.CommonMethods;
import DataProviders.AjioLogin_DataProvider;
import PageObjects.CartPage;
import PageObjects.ItemsPage;
import PageObjects.ShippingPage;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_AJ_015 extends BaseClass {

    @Test(dataProvider = "AjioEditedData", dataProviderClass = AjioLogin_DataProvider.class)
    public void verifyHomeAddressIsAdded(String EMAIL, String PASSWORD, String ITEM, String STREET, String HOUSENAME) throws InterruptedException{

        ItemsPage itemsPage = new ItemsPage(driver);
        CommonMethods commonMethods = new CommonMethods(driver);
        CartPage cartPage = new CartPage(driver);
        ShippingPage shippingPage = new ShippingPage(driver);

        test = extent.createTest("TC_AJ_015 - To verify the User is able to edit Home Shipping Address");

        commonMethods.login(EMAIL, PASSWORD); //login method for signing in
        commonMethods.searchFunctionality(ITEM); //method to search an item
        commonMethods.addToCart(); //method to add the item to the cart

        itemsPage.viewCart(); //method to enter the cart page and view the added item(s)
        test.log(Status.INFO, "Viewing the cart to verify");

        cartPage.shipping();

        String initialAddress = shippingPage.initialStreetAndHouseName();

        shippingPage.clickChangeAddress();
        shippingPage.clickEditAddress();
        shippingPage.clearLocality();
        shippingPage.addLocality(STREET);//editing the current street name
        shippingPage.clearHouseName();
        shippingPage.addHouseName(HOUSENAME); //editing the current house name
        shippingPage.clickSave();

        String editedAddress = shippingPage.editedAddress();

        Assert.assertNotEquals(initialAddress, editedAddress); //Assert to verify the editing feature

    }
}