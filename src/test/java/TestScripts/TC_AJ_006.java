package TestScripts;

import Commons.BaseClass;
import Commons.CommonMethods;
import DataProviders.AjioLogin_DataProvider;
import PageObjects.CartPage;
import PageObjects.ItemsPage;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_AJ_006 extends BaseClass
{
    @Test(dataProvider = "AjioData_TC_000", dataProviderClass = AjioLogin_DataProvider.class)
    public void verifyOrderSummary(String ITEM) throws InterruptedException
    {
        ItemsPage itemsPage = new ItemsPage(driver);
        CommonMethods commonMethods = new CommonMethods(driver);
        CartPage cartPage = new CartPage(driver);
        test = extent.createTest("TC_AJ_006 - To verify the order summary");

        //commonMethods.login(EMAIL, PASSWORD); //login method for signing in

        commonMethods.searchFunctionality(ITEM); //method to search an item

        commonMethods.addToCart(); //method to add the item to the cart

        itemsPage.viewCart(); //method to enter the cart page and view the added item(s)
        test.log(Status.INFO, "Viewing the cart to verify");

        double initialValue =  cartPage.verifyPrice(); //method to get the price of one
        System.out.println(initialValue);

        cartPage.updateItemQuantity(); //method to update the quantity

        double updatedValue = cartPage.verifyPrice();
        System.out.println(updatedValue);

        Assert.assertTrue(updatedValue!=initialValue, "The value: " +updatedValue+ " is not equal to: "+ initialValue);
    }


}
