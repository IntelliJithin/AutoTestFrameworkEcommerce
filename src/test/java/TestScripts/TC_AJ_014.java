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

public class TC_AJ_014 extends BaseClass {

    @Test(dataProvider = "AjioLoginData", dataProviderClass = AjioLogin_DataProvider.class)
    public void verifyHomeAddressIsAdded(String EMAIL, String PASSWORD, String ITEM, String NAME, String MOBILE, String PINCODE, String STREET, String HOUSENAME) throws InterruptedException{

        ItemsPage itemsPage = new ItemsPage(driver);
        CommonMethods commonMethods = new CommonMethods(driver);
        CartPage cartPage = new CartPage(driver);
        ShippingPage shippingPage = new ShippingPage(driver);

        test = extent.createTest("TC_AJ_014 - To verify the User is able to add Home Shipping Address");

        commonMethods.login(EMAIL, PASSWORD); //login method for signing in
        commonMethods.searchFunctionality(ITEM); //method to search an item
        commonMethods.addToCart(); //method to add the item to the cart

        itemsPage.viewCart(); //method to enter the cart page and view the added item(s)
        test.log(Status.INFO, "Viewing the cart to verify");

        cartPage.shipping();

        shippingPage.clickAddShippingAddress();
        shippingPage.addName(NAME);
        shippingPage.addMobile(MOBILE);
        shippingPage.addPincode(PINCODE);
        shippingPage.addLocality(STREET);
        shippingPage.addHouseName(HOUSENAME);
        shippingPage.clickSave();

        Assert.assertTrue(shippingPage.address()); //assert method to verify the presence of address
    }
}