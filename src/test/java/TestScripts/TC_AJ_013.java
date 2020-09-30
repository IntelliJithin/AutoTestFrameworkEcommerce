package TestScripts;

import Commons.BaseClass;
import Commons.CommonMethods;
import DataProviders.AjioLogin_DataProvider;
import PageObjects.CartPage;
import PageObjects.ItemsPage;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_AJ_013 extends BaseClass {

    @Test(dataProvider = "AjioData_TC_013", dataProviderClass = AjioLogin_DataProvider.class)
    public void deleteFeature( String ITEM1, String ITEM2) throws InterruptedException {

        ItemsPage itemsPage = new ItemsPage(driver);
        CommonMethods commonMethods = new CommonMethods(driver);
        CartPage cartPage = new CartPage(driver);

        test = extent.createTest("TC_AJ_013 - Verify delete feature");

       // commonMethods.login(EMAIL, PASSWORD); //login method for signing in

        String[] items = {ITEM1, ITEM2};
        // for loop to run the search and add to cart steps 3 times
        for (String j : items)
        {
            commonMethods.searchFunctionality(j); //search method for item searching
            commonMethods.addToCart(); //add to cart method
        }

        itemsPage.viewCart();

        cartPage.clickDelete();

        test.log(Status.INFO, "The first item has been deleted: "+cartPage.verifyDeleteFeature());
        Assert.assertNull(cartPage.verifyDeleteFeature());
    }
}