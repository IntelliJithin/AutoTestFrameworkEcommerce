package TestScripts;

import Commons.BaseClass;
import Commons.CommonMethods;
import DataProviders.AjioLogin_DataProvider;
import PageObjects.CartPage;
import PageObjects.ItemsPage;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_AJ_012 extends BaseClass {

    @Test(dataProvider = "AjioData_TC_000", dataProviderClass = AjioLogin_DataProvider.class)
    public void verifyTotalCouponSavingsAndCouponSavingsPopUp(String ITEM) throws InterruptedException {

        ItemsPage itemsPage = new ItemsPage(driver);
        CommonMethods commonMethods = new CommonMethods(driver);
        CartPage cartPage = new CartPage(driver);

        test = extent.createTest("TC_AJ_012 - Verify Total Coupon Savings Value vs Coupons Savings in popup");

       // commonMethods.login(EMAIL, PASSWORD); //login method for signing in
        commonMethods.searchFunctionality(ITEM); //method to search an item
        commonMethods.addToCart(); //method to add the item to the cart

        itemsPage.viewCart(); //method to enter the cart page and view the added item(s)
        test.log(Status.INFO, "Viewing the cart to verify");

        cartPage.selectCoupon();
        cartPage.applyButton();
        cartPage.setClickSavings();

        test.log(Status.INFO, "Total coupon savings on this product is: "+cartPage.discountInfo()+" and matches the Coupon Savings Value in the Savings Pop-up: "+cartPage.couponSavingPopUp());
        Assert.assertEquals(cartPage.discountInfo(), cartPage.couponSavingPopUp());
    }
}