package Pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import Config.*;

public class CheckOutPage extends BasePOMpage {
    private DriverSetup ds;

    @AndroidFindBy(xpath="//android.widget.Button[contains(@resource-id,'a-autoid-0-announce')]")
    private AndroidElement clickOnDeliverBtn;

    @AndroidFindBy(xpath="//android.view.View[@index='0']/android.widget.ListView[@index='1']")
    private AndroidElement getProductData;

    public CheckOutPage (AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }
    //This method is used to click on Delivery button
    public String setClickOnDeliverButton() throws InterruptedException {
        DriverSetup.waitForElement(driver,10,clickOnDeliverBtn);
        clickOnDeliverBtn.click();
        ds = new DriverSetup();
        ds.takeScreenshot(driver);
        return null;

    }
    //This method used to get product description
    public String getProductData() throws InterruptedException {
        DriverSetup.waitForElement(driver,10,getProductData);
        ds.takeScreenshot(driver);
        return getProductData.getText();

    }
}
