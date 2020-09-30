package Commons;

import PageObjects.HomePage;
import PageObjects.ItemsPage;
import PageObjects.LoginWindowPage;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import java.util.Set;

public class CommonMethods extends BaseClass {
    public CommonMethods(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) throws InterruptedException {

        String parent = driver.getWindowHandle();

        HomePage homePage = new HomePage(driver);
        homePage.loginClick();
        log.debug("Click Sign In / Join AJIO");
        test.log(Status.INFO, "Click - Sign In / Join AJIO");

        homePage.googleSignIn();
        test.log(Status.INFO, "Clicked on Google");
        log.debug("Click - Sign in with Google");

        log.debug("Switching to the newly opened window to initialize Login");
        Set<String> s = driver.getWindowHandles();

        for (String childWindow : s) {

            if (!parent.equalsIgnoreCase(childWindow)) {
                driver.switchTo().window(childWindow);
                LoginWindowPage loginWindowPage = new LoginWindowPage(driver);
                loginWindowPage.enterUsernameandPassword(username, password);

                test.log(Status.INFO, "Username and Password entered");
                log.debug("Enter the Username and Password");
            }
        }

        driver.switchTo().window(parent);
        Thread.sleep(10000);
        log.debug("Switch back to the parent window");

    }

    public void searchFunctionality(String item)
    {
        HomePage homePage = new HomePage(driver);
        homePage.setSearch_bar(item);
        test.log(Status.INFO, "Entered the item name: "+item);
        homePage.setSubmit_button();
        test.log(Status.INFO, "Click the search button to initiate search");

    }

    public void addToCart() throws InterruptedException {
        BaseClass.scroll("window.scrollBy(0,200)");

        ItemsPage itemsPage = new ItemsPage(driver);
        itemsPage.mouseActionToSelectSpecification(); //method for mouse events
        test.log(Status.INFO, "Product specification opened");

        itemsPage.sizeSelectandAddToCart();
        test.log(Status.INFO, "Size selected and added to Cart");
        Thread.sleep(3000);
    }
}
