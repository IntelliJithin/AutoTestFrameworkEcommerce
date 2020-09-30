package PageObjects;

import Commons.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ItemsPage extends BaseClass {

    @FindBy(xpath = "//div[text()='T shirt']")
    private WebElement txt_itemTitle;
    @FindBy(xpath = "//div[@id='products']/div[3]/div/div/div[1]")
    private WebElement item;
    @FindBy(xpath = "//div[@id='products']/div[3]/div/div/div[1]/a/div/div[1]/div")
    private WebElement popUp;
    @FindBy(xpath = "//div[text()='XS']")
    private WebElement txt_sizeXS;
    @FindBy(xpath = "//div[text()='S']")
    private WebElement txt_sizeS;
    @FindBy(xpath = "//div[text()='M']")
    private WebElement txt_sizeM;
    @FindBy(xpath = "//div[text()='L']")
    private WebElement txt_sizeL;
    @FindBy(xpath = "//div[text()='XL']")
    private WebElement txt_sizeXL;
    @FindBy(xpath = "//span[text()='ADD TO BAG']")
    private WebElement txt_addToCart;
    @FindBy(xpath = "//div[@class='scb']/div[2]")
    private WebElement getCart;
    @FindBy(xpath = "//div[text()=' Item(s) added to your bag ']")
    private WebElement txt_Confirmation;

    public ItemsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String verifyPagetitle() {
        return txt_itemTitle.getText();
    }

    public WebElement Item() {
        return item;
    }

    public WebElement getPopUp() {
        return popUp;
    }

    public void sizeSelectandAddToCart() {

        /*if (txt_sizeXS.isEnabled()){
            txt_sizeXS.click();
        }
        else if (txt_sizeS.isEnabled()){
            txt_sizeS.click();
        }
        else if (txt_sizeM.isEnabled()){
            txt_sizeM.click();
        }
        else if (txt_sizeL.isEnabled()){
            txt_sizeL.click();
        }
        else{
            if (txt_sizeXL.isEnabled()) {
                txt_sizeXL.click();
            }
        }*/
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(txt_sizeM));
        txt_sizeM.click();
        txt_addToCart.click();
    }

    public void viewCart() {
        getCart.click();
    }

    public void mouseActionToSelectSpecification() {
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(item));
        actions.moveToElement(item);
        
        WebDriverWait wait1 = new WebDriverWait(driver, 10);
        wait1.until(ExpectedConditions.elementToBeClickable(popUp));
        actions.moveToElement(popUp);
        actions.click().build().perform();
    }

    public String cartConfirmation() {
        return txt_Confirmation.getText();
    }
}
