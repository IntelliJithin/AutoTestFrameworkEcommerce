package TestScripts;

import Commons.BaseClass;
import Commons.CommonMethods;
import DataProviders.AjioLogin_DataProvider;
import PageObjects.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_AJ_001 extends BaseClass {

    @Test(dataProvider = "AjioLogin", dataProviderClass = AjioLogin_DataProvider.class)
    public void LoginTestCase(String EMAIL, String PASSWORD) throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        CommonMethods commonMethods = new CommonMethods(driver);

        test = extent.createTest("TC_AJ_001 - Login Verification......");

        commonMethods.login(EMAIL, PASSWORD); //login method

        Assert.assertTrue(homePage.loginMessage()); //assert method to verify successful log-in
    }
}
