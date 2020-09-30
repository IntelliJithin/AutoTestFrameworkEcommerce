package TestScripts;

import Commons.BaseClass;
import Commons.CommonMethods;
import DataProviders.AjioLogin_DataProvider;
import PageObjects.CartPage;
import PageObjects.ItemsPage;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_AJ_008 extends BaseClass
{
    @Test(dataProvider = "AjioData_TC_000", dataProviderClass = AjioLogin_DataProvider.class)
    public void verifyCouponSummaryWithValidCode( String ITEM) throws InterruptedException {

        ItemsPage itemsPage = new ItemsPage(driver);
        CommonMethods commonMethods = new CommonMethods(driver);
        CartPage cartPage = new CartPage(driver);
        test = extent.createTest("TC_AJ_008 - Verify coupon summary savings value with CORRECT coupon code");

        //commonMethods.login(EMAIL, PASSWORD); //login method for signing in

        commonMethods.searchFunctionality(ITEM); //method to search an item

        commonMethods.addToCart(); //method to add the item to the cart

        itemsPage.viewCart(); //method to enter the cart page and view the added item(s)
        test.log(Status.INFO, "Viewing the cart to verify");

        cartPage.addValidCouponCode();
        Assert.assertEquals(cartPage.savings(), cartPage.couponSavings()); //assert
        test.log(Status.INFO, "The coupon value applied: " +cartPage.savings()+ " matches with the coupon savings value: "+cartPage.couponSavings()+ " in the order summary.");

    }
}
