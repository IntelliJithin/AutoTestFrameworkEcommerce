package TestScripts;

import Commons.BaseClass;
import Commons.CommonMethods;
import DataProviders.AjioLogin_DataProvider;
import PageObjects.CartPage;
import PageObjects.ItemsPage;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_AJ_010 extends BaseClass {

    @Test(dataProvider = "AjioData_TC_000", dataProviderClass = AjioLogin_DataProvider.class)
    public void verifySelectionOfApplicableCode( String ITEM) throws InterruptedException {

        ItemsPage itemsPage = new ItemsPage(driver);
        CommonMethods commonMethods = new CommonMethods(driver);
        CartPage cartPage = new CartPage(driver);
        test = extent.createTest("TC_AJ_010 - Verify by selection of applicable coupon codes");

       // commonMethods.login(EMAIL, PASSWORD); //login method for signing in

        commonMethods.searchFunctionality(ITEM); //method to search an item

        commonMethods.addToCart(); //method to add the item to the cart

        itemsPage.viewCart(); //method to enter the cart page and view the added item(s)
        test.log(Status.INFO, "Viewing the cart to verify");

        Assert.assertTrue(cartPage.selectCoupon()); //assert method
    }
}