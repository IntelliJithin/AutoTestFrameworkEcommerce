package PageObjects;

import Commons.BaseClass;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CartPage extends BaseClass
{
    @FindBy(xpath = "//div[@class='mybag-section']/span[2]")
    private WebElement txt_noOfItems;
    private final By item_list = By.xpath("//div[@class='card-offer']");
    @FindBy(xpath = "//div[@class='cartqty']/div")
    private WebElement quantity;
    @FindBy(xpath = "//div[@class='cartqty-section']//button[2]")
    private WebElement increment;
    @FindBy(id = "updateQuantity")
    private WebElement update_button;
    @FindBy(className = "price-value")
    private WebElement priceForOne;
    @FindBy(xpath = "//div[@id='orderSummary']//section[1]//span[2]")
    private WebElement bagTotal;
    @FindBy(xpath = "//div[@id='orderSummary']//section[2]//span[2]")
    private WebElement bagDiscount;
    @FindBy(xpath = "//div[@id='orderSummary']//section[3]//span[2]")
    private WebElement estimatedGst;
    @FindBy(xpath = "//div[@id='orderSummary']//section[4]//span[2]")
    private WebElement delivery;
    @FindBy(xpath = "//div[@id='orderSummary']//section[5]//span[2]")
    private WebElement totalSum;
    @FindBy(xpath = "//div[@class='voucher-wrapper']/div/div[3]/div[1]//ul//li[1]")
    private WebElement validCoupons;
    @FindBy(xpath = "//div[@class='voucher-wrapper']/div/div[3]/div[2]")
    private WebElement invalidCoupons;
    @FindBy(xpath = "//div[@class='voucher-wrapper']/div/div[3]/div[1]//ul//li//p[2]")
    private WebElement txt_availableCoupon;
    @FindBy(id = "couponCodeInput")
    private WebElement enterCouponCode;
    @FindBy(xpath = "//div[@class='input-box-div']//button")
    private WebElement button_apply;
    @FindBy(xpath = "//div[@class='voucher-wrapper']/div/div[3]/div[1]//ul//li/div/div[2]//span")
    private WebElement txt_savings;
    @FindBy(xpath = "//div[@class='voucher-wrapper']/div/div[2]//span/following::span[1]")
    private WebElement txt_invalidcode;
    @FindBy(xpath = "//div[@class='product-card']/div[2]")
    private WebElement clickSavings;
    @FindBy(xpath = "//div[@class='discount-section']/span")
    private WebElement discountSection;
    @FindBy(xpath = "//div[@class='coupon-discount-section']/span")
    private WebElement couponDiscountSection;
    @FindBy(xpath = "//div[@class='total-saving']/span")
    private WebElement totalSaving;
    @FindBy(id = "saving-popup-btn")
    private WebElement doneButton;
    @FindBy(xpath = "//div[@class='discount-info']//span")
    private WebElement txt_couponSavings;
    private final By txt_delete = By.className("delete-btn");
    @FindBy(xpath = "//div[text()='DELETE']")
    private WebElement Delete;
    @FindBy(xpath = "//div[@class='product-card-wrapper']/div[1]")
    private WebElement firstItemInTheList;
    @FindBy(xpath = "//button[text()='Proceed to shipping']")
    private WebElement txt_proceedToShipping;

    public CartPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    public void shipping()
    {
        txt_proceedToShipping.click();
    }

    public Object verifyDeleteFeature() {
        firstItemInTheList.isDisplayed();
        return null;
    }
    public void clickDelete() {
        List<WebElement> deleteElements = driver.findElements(txt_delete);
        deleteElements.get(0).click();
        Delete.click();
    }

    public void applyButton() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(button_apply));
        button_apply.click();
        Thread.sleep(3000);
    }
    public void doneSavingsButton()
    {
        doneButton.click();
    }
    public boolean selectCoupon() {
        if (validCoupons.isDisplayed())
        {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(validCoupons));
            validCoupons.click();
        }
        else
        {
            test.log(Status.INFO, "No applicable coupon codes");
        }
        return true;
    }

    public boolean getErrorMessage() {
        return txt_invalidcode.isDisplayed();
    }

    public String savings() {
        String value = txt_savings.getText();
        System.out.println(value);
        return value;
    }
    public String couponSavings(){
        String couponSavingsValue = savings().substring(savings().indexOf('-')+1);
        System.out.println(couponSavingsValue);
        return couponSavingsValue;
    }

    public String discountInfo() {
        return txt_couponSavings.getText();
    }
    public String couponSavingPopUp() {
        return couponDiscountSection.getText();
    }

    public double discount() {

        String discountText = discountSection.getText();
        String discountTextValue = discountText.substring(discountText.indexOf('.') + 1);
        discountTextValue = discountTextValue.replaceAll(",","");

        double discount = 0;
        discount = Double.parseDouble(discountTextValue);
        System.out.println("Discount: " + discount);
        return discount;
    }
    public double couponSaving() {

        String couponDiscountText = couponDiscountSection.getText();
        String couponDiscountTextValue = couponDiscountText.substring(couponDiscountText.indexOf('.') + 1);
        couponDiscountTextValue=couponDiscountTextValue.replaceAll(",","");

        double couponSavings = 0;
        couponSavings = Double.parseDouble(couponDiscountTextValue);
        System.out.println("Coupon savings: " + couponSavings);
        return couponSavings;
    }
    public double totalSaving() {
        String totalSavingsText = totalSaving.getText();
        String totalSavingsTextValue = totalSavingsText.substring(totalSavingsText.indexOf('.') + 1);
        totalSavingsTextValue=totalSavingsTextValue.replaceAll(",","");

        double totalSavings = 0;
        totalSavings = Double.parseDouble(totalSavingsTextValue);
        System.out.println("Total savings: " + totalSavings);
        return totalSavings;
    }

    public void setClickSavings()
    {
        clickSavings.click();
    }

    public void addValidCouponCode() {
        String couponCode = txt_availableCoupon.getText();
        enterCouponCode.sendKeys(couponCode);
        button_apply.click();
    }
    public void addInvalidCouponCode(String code) {
        enterCouponCode.sendKeys(code);
        button_apply.click();
    }

    public int bagTot() {
        String BT = bagTotal.getText();
        String value = BT.substring(4,7);
        int bagtotal = 0;
        bagtotal = Integer.parseInt(value);
        System.out.println("The bag total is: "+bagtotal);
        return bagtotal;
    }
    public int bagDis() {
        String BD = bagDiscount.getText();
        String value = BD.substring(4,7);
        int bagdiscount = 0;
        bagdiscount = Integer.parseInt(value);
        System.out.println("The bag discount is: "+bagdiscount);
        return bagdiscount;
    }
    public int EGST() {
        String estGST = estimatedGst.getText();
        String value = estGST.substring(4,6);
        int GST = 0;
        GST = Integer.parseInt(value);
        System.out.println("The applicable GST is: "+GST);
        return GST;
    }
    public int del() {
        String deli = delivery.getText();
        String value = deli.substring(4,7);
        int delivery = 0;
        delivery = Integer.parseInt(value);
        System.out.println("The Delivery cost is: "+delivery);
        return delivery;
    }
    public int ordTot() {
        String total = totalSum.getText();
        String value = total.substring(4,7);
        int totalSum = 0;
        totalSum = Integer.parseInt(value);
        System.out.println("The order total is: "+totalSum);
        return totalSum;
    }

    public int sumOfBag() {
        return bagTot() - bagDis() + EGST() + del();
    }
    public int orderTotal(){
        return ordTot();
    }

    public double verifyPrice() {
        String iP = priceForOne.getText();
      /*  System.out.println("iP: "+iP);
        Pattern pattern = Pattern.compile("\\d+");
        System.out.println("pattern: "+pattern);
        Matcher matcher = pattern.matcher(iP);
        System.out.println("matcher: " +matcher);
        int value=0;
        matcher.find();
        //System.out.println(matcher.group());
        String price = matcher.group();
        value = Integer.parseInt(price);
        System.out.println("initial price value: "+value);
        return value;*/
        String iPValue = iP.substring(iP.indexOf('.')+1);
        double price = 0;
        price = Double.parseDouble(iPValue);
        return price;
    }

    public int verifyItemQuantity() {
        String q = quantity.getText();
        System.out.println("q: "+q);
        int value = Integer.parseInt(q);
        System.out.println("value: "+value);
        return value;
    }
    public void updateItemQuantity() {
        quantity.click();
        increment.click();
        update_button.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(priceForOne));
    }

    public int itemsList() {
        List<WebElement> count = driver.findElements(item_list);
        return count.size();
    }

    public int bagCount(){
        String myBagCount = txt_noOfItems.getText();
        String[] parts = myBagCount.split("");
        String myBC = parts[1];
        return Integer.parseInt(myBC);
    }

}
