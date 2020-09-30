package TestScripts;

import Commons.BaseClass;
import Commons.CommonMethods;
import DataProviders.AjioLogin_DataProvider;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;

public class TC_AJ_004 extends BaseClass {

    @Test(dataProvider = "AjioData_TC_004", dataProviderClass = AjioLogin_DataProvider.class)
    public void MultiAddToCartTestCase(String ITEM1, String ITEM2, String ITEM3) throws InterruptedException {

        CommonMethods commonMethods = new CommonMethods(driver);
        test = extent.createTest("TC_AJ_004 - Multiple item selection and adding to Cart");

       // commonMethods.login(EMAIL, PASSWORD); //login method for signing in

        String[] items = {ITEM1, ITEM2, ITEM3};

            for (String j: items)  // for loop to run the search and add to cart steps 3 times
            {
                commonMethods.searchFunctionality(j); //search method for item searching
                commonMethods.addToCart(); //add to cart method
            }

        test.log(Status.INFO, "Multiple items added successfully");
    }
}
