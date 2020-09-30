package PageObjects;

import Commons.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BaseClass
{
    @FindBy(xpath = "//span[@class='login-form login-modal']")
    private WebElement txt_login;
    @FindBy(xpath = "//button[@class='google-login']")
    private WebElement txt_googleLogin;
    @FindBy(xpath = "//div[@class='guest-header']//span")
    private WebElement txt_message;
    @FindBy(xpath = "//input[@name='searchVal']")
    private WebElement search_bar;
    @FindBy(xpath = "//button[@class='rilrtl-button']")
    private WebElement submit_button;


    public HomePage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    public void loginClick()
    {
        txt_login.click();
    }

    public void googleSignIn()
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(txt_googleLogin));
        txt_googleLogin.click();
    }

    public boolean loginMessage()
    {
        txt_message.getText();
        return true;
    }

    public void setSearch_bar(String item) throws InterruptedException {
        Thread.sleep(5000);
        search_bar.sendKeys(item);
    }
    public void setSubmit_button()
    {

        submit_button.click();
    }
}
