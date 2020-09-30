package PageObjects;

import Commons.BaseClass;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginWindowPage extends BaseClass
{
    @FindBy(id = "identifierId")
    private WebElement txt_username;
    @FindBy(className = "VfPpkd-RLmnJb")
    private WebElement nextButton;
    @FindBy(name = "password")
    private WebElement txt_password;

    public LoginWindowPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    public void enterUsernameandPassword(String username, String password)
    {
        txt_username.sendKeys(username);
        nextButton.click();
        txt_password.sendKeys(password);

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(nextButton));
        nextButton.click();
    }
}
