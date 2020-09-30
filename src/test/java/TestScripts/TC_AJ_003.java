package TestScripts;

import Commons.BaseClass;
import Commons.CommonMethods;
import DataProviders.AjioLogin_DataProvider;
import PageObjects.ItemsPage;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_AJ_003 extends BaseClass {

    @Test(dataProvider = "AjioData_TC_003", dataProviderClass = AjioLogin_DataProvider.class)
    public void AddToCart(String ITEM, String MESSAGE) throws InterruptedException {

        ItemsPage itemsPage = new ItemsPage(driver);

        test = extent.createTest("TC_AJ_003 - Item selection and adding to Cart");

        CommonMethods commonMethods = new CommonMethods(driver);
        //commonMethods.login(EMAIL, PASSWORD); //login method for signing in
        Thread.sleep(15000);

        commonMethods.searchFunctionality(ITEM); //search method for item searching

        commonMethods.addToCart(); //add to cart method

        Assert.assertEquals(itemsPage.cartConfirmation(), MESSAGE);
        test.log(Status.INFO, "Item added successfully");
    }
}
