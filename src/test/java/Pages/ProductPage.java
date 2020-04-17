package Pages;

import Utility.ExcelUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import Config.*;


public class ProductPage extends BasePOMpage{
    private DriverSetup ds;
    private CheckOutPage cp;

    @AndroidFindBy(id="com.amazon.mShop.android.shopping:id/rs_search_src_text")
    private AndroidElement searchTxt;

    @AndroidFindBy(xpath ="//android.webkit.WebView[@index='0']/android.view.View[@index='0']/android.view.View[@index='4']")
    private AndroidElement clickOnTV;

    @AndroidFindBy(xpath= "//android.webkit.WebView[@index='0']/android.view.View[@index='0']/android.view.View[@index='2']")
    private AndroidElement getTitle;

    @AndroidFindBy(xpath ="//android.widget.Button[@content-desc='See All Buying Options See All Buying Options']")
    private AndroidElement buyOption;

    @AndroidFindBy(xpath ="//android.widget.Button[@content-desc,'Add to cart  Add to cart']")
    private AndroidElement addToCart;

    @AndroidFindBy(xpath="//android.widget.TextView[contains(@resource-id,'com.amazon.mShop.android.shopping:id/chrome_action_bar_cart_count')]")
    private AndroidElement clickOnCart;

    @AndroidFindBy(xpath="//android.widget.Button[contains(@resource-id,'a-autoid-1-announce']")
    private AndroidElement proceedToCheckout;

    @AndroidFindBy(xpath="//android.widget.Button[contains(@resource-id,'add-to-cart-button')]")
    private AndroidElement addToCartBtn;

    @AndroidFindBy(xpath = "//android.widget.Button[(@content-desc,'Continue')]")
    private AndroidElement clickOnContinue;


    public ProductPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }
    //This method used to search for a product
    public void searchTxt() throws InterruptedException {
        ExcelUtils excel = new ExcelUtils("C:/Users/NoorAhmed/IdeaProjects/AppiumAssignmnet/src/test/resources/testdata/testdata.xlsx");
        System.out.println(excel.getData(1,1,0));
        DriverSetup.waitForElement(driver,40,searchTxt);
        searchTxt.sendKeys(excel.getData(1,1,0));
        ds = new DriverSetup();
        ds.takeScreenshot(driver);
        driver.pressKeyCode(66);
    }
    //This method used to click on searched product except 1st product in search result page
    public void clickOnTv() throws InterruptedException {

        ds.swipeScreen(driver);
        DriverSetup.waitForElement(driver,10,clickOnTV);
        DriverSetup.waitForElement(driver,10,clickOnTV);
            clickOnTV.click();
            ds.takeScreenshot(driver);
        }
    //This method used to get the product description
    public String getDescriptionOfItem() throws InterruptedException {
        DriverSetup.waitForElement(driver,10,getTitle);
        ds.takeScreenshot(driver);
        return getTitle.getAttribute("name");
    }
    //This method used to add the item into cart
    public void clickOnBuyOption() throws InterruptedException {

        ds.swipeScreen(driver);
        DriverSetup.waitForElement(driver,10,addToCartBtn);
        try {
            if (addToCartBtn.isDisplayed()) {
                addToCartBtn.click();
                ds.takeScreenshot(driver);
            }
        }catch(Exception ex) {
                buyOption.click();
                ds.takeScreenshot(driver);
                addToCart();
                ds.takeScreenshot(driver);

        }
        }

    //This method used to add product to cart
    public void addToCart() throws InterruptedException {
        DriverSetup.waitForElement(driver,10,addToCart);
        addToCart.click();
        ds.takeScreenshot(driver);
    }
    //This method used to click on cart
    public void clickOnBasket() throws InterruptedException {
        DriverSetup.waitForElement(driver,10,clickOnCart);
        clickOnCart.click();
        ds.takeScreenshot(driver);
    }
    //This method used to click on proceed to checkout button
    public void clickOnCheckout() throws InterruptedException {
        DriverSetup.waitForElement(driver,10,proceedToCheckout);
        proceedToCheckout.click();
        ds.takeScreenshot(driver);
    }
    //This method used to click on continue button
    public void clickOnContinueBtn(){
        DriverSetup.waitForElement(driver,10,proceedToCheckout);
        ds.takeScreenshot(driver);
        clickOnContinue.click();
    }
    //This method used to search an item and proceed for checkout and compare the product details
    public String checkoutItem() throws InterruptedException {
        searchTxt();
        clickOnTv();
        String s1 = getDescriptionOfItem();
        System.out.println(s1);
        clickOnBuyOption();
        clickOnBasket();
        clickOnCheckout();
        cp = new CheckOutPage(driver);
        cp.setClickOnDeliverButton();
        DriverSetup.waitForElement(driver,10,clickOnContinue);
        try {
            if (clickOnContinue.isDisplayed()) {
                ds.takeScreenshot(driver);
                clickOnContinueBtn();
                String s2 = cp.getProductData();
                System.out.println(s2);
                if (s1.equals(s2)) {
                    System.out.println("Pass: Product description matched with checkout page product");
                } else {
                    System.out.println("Fail: Product description didn't matched with checkout page product");
                }
            }
        }catch(Exception ex)
        {
           System.out.println("Fail: no product data on checkout page ");

        }

        return null;

    }

}
