package PageObjects;

import Commons.BaseClass;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShippingPage extends BaseClass {

    @FindBy(className = "change-address-section")
    private WebElement shippingAddress;
    @FindBy(name = "addressPoc")
    private WebElement txt_name;
    @FindBy(name = "phone")
    private WebElement txt_mobile;
    @FindBy(name = "postalCode")
    private WebElement txt_pincode;
    @FindBy(name = "line2")
    private WebElement txt_locality;
    @FindBy(name = "line1")
    private WebElement txt_houseName;
    @FindBy(xpath = "//button[text()='Save']")
    private WebElement txt_saveButton;
    @FindBy(className = "address-info-wrapper")
    private WebElement txt_address;
    @FindBy(className = "change-address-section")
    private WebElement txt_changeAddress;
    @FindBy(className = "mobaddr-editc")
    private WebElement txt_editAddress;
    @FindBy(xpath = "//div[@class='address-info']/div[contains(text(), 'homename')]")
    private WebElement txt_streetAndHouse;
    @FindBy(xpath = "//div[@class='address-info']/div[contains(text(), 'homenameEdited')]")
    private WebElement txt_editedStreetAndHouse;

    public ShippingPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    public String initialStreetAndHouseName()
    {
        return txt_streetAndHouse.getText();
    }

    public String editedAddress()
    {
        return txt_editedStreetAndHouse.getText();
    }

    public void clickChangeAddress()
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(txt_changeAddress));
        txt_changeAddress.click();
    }
    public void clickEditAddress() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(txt_editAddress));
        txt_editAddress.click();
    }

    public void clickAddShippingAddress()
    {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", shippingAddress);
        //shippingAddress.click();
    }
    public void addName(String name) {
        txt_name.clear();
        txt_name.sendKeys(name);
    }
    public void addMobile(String mobile) {
        txt_mobile.clear();
        txt_mobile.sendKeys(mobile);
    }
    public void addPincode(String pincode)
    {
        txt_pincode.sendKeys(pincode);
    }
    public void clearLocality()
    {
        txt_locality.sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
    }
    public void addLocality(String locality) {

        txt_locality.sendKeys(locality);
    }
    public void clearHouseName()
    {
        txt_houseName.sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
    }
    public void addHouseName(String housename) {

        txt_houseName.sendKeys(housename);
    }
    public void clickSave()
    {
        txt_saveButton.click();
    }

    public boolean address() {
        txt_address.isDisplayed();
        return true;
    }
}

