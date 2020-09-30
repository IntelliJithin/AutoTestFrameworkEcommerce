package TestScripts;

import Commons.BaseClass;
import Commons.CommonMethods;
import DataProviders.AjioLogin_DataProvider;
import PageObjects.CartPage;
import PageObjects.ItemsPage;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_AJ_009 extends BaseClass {

    @Test(dataProvider = "AjioData_TC", dataProviderClass = AjioLogin_DataProvider.class)
    public void verifyCouponSummaryWithInvalidCode( String ITEM, String CODE) throws InterruptedException {

        ItemsPage itemsPage = new ItemsPage(driver);
        CommonMethods commonMethods = new CommonMethods(driver);
        CartPage cartPage = new CartPage(driver);

        test = extent.createTest("TC_AJ_009 - Verify coupon summary savings value with INCORRECT coupon code");

       // commonMethods.login(EMAIL, PASSWORD); //login method for signing in

        commonMethods.searchFunctionality(ITEM); //method to search an item

        commonMethods.addToCart(); //method to add the item to the cart

        itemsPage.viewCart(); //method to enter the cart page and view the added item(s)
        test.log(Status.INFO, "Viewing the cart to verify");

        cartPage.addInvalidCouponCode(CODE);
        test.log(Status.INFO, "Entering the invalid coupon code");

        Assert.assertTrue(cartPage.getErrorMessage()); //assert

    }
}