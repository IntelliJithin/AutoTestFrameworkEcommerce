package TestScripts;

import Commons.BaseClass;
import Commons.CommonMethods;
import DataProviders.AjioLogin_DataProvider;
import PageObjects.HomePage;
import PageObjects.ItemsPage;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TC_AJ_002 extends BaseClass {

    //(groups = {"SearchFunctTestCase.TC_AJ_002"},dependsOnGroups = {"LoginTestCase.TC_AJ_001"})
    @Test(dataProvider = "AjioData_TC_000", dataProviderClass = AjioLogin_DataProvider.class)
    public void SearchFunctTestCase(String ITEM) throws InterruptedException {

        /*CommonMethods commonMethods = new CommonMethods(driver);
        commonMethods.login(EMAIL, PASSWORD);*/
        test = extent.createTest("TC_AJ_002 - Item search and verification");

        HomePage homePage = new HomePage(driver);
        homePage.setSearch_bar(ITEM);
        test.log(Status.INFO, "Entered the item name");

        homePage.setSubmit_button();
        test.log(Status.INFO, "Click the search button to initiate search");

        ItemsPage itemsPage = new ItemsPage(driver);
        Assert.assertEquals(itemsPage.verifyPagetitle(), ITEM);
        test.log(Status.INFO, "Item title has been verified");
    }
}
