package Pages;


import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import Config.*;

public class Homepage extends BasePOMpage {
    private DriverSetup ds;

    @AndroidFindBy(xpath ="//android.widget.RelativeLayout[@index='0']/android.widget.FrameLayout[@index='0']")
    private AndroidElement menuIcon;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Settings')]")
    private AndroidElement clickOnSetting;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Country & Language')]")
    private AndroidElement clickOnCountry;

    @AndroidFindBy(xpath = "//android.view.View[@index='2']")
    private AndroidElement clickOnCountryRegion;

    @AndroidFindBy(xpath ="//android.widget.RadioButton[@index='4']")
    private AndroidElement clickOnAustralia;

    @AndroidFindBy(xpath = "//android.widget.Button[@index='0']")
    private AndroidElement clickOnDoneBtn;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@content-desc='English - EN']")
    private AndroidElement selectEnglish;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='सेव करें']")
    private AndroidElement clickSave;




    public Homepage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);

    }
    //This method used to click on English radio button
    public void clickOnEnglish() throws InterruptedException {
        DriverSetup.waitForElement(driver,20,selectEnglish);
        selectEnglish.click();
        ds = new DriverSetup();
        ds.takeScreenshot(driver);
    }
    //This method used to click on save button
    public void clickOnSave() throws InterruptedException {
        DriverSetup.waitForElement(driver,10,clickSave);
        clickSave.click();
        ds.takeScreenshot(driver);
    }
    //This method used to click on menu icon
    public void clickOnMenu() throws InterruptedException {
        DriverSetup.waitForElement(driver,10,menuIcon);
        ds = new DriverSetup();
        if (!menuIcon.isDisplayed()) {
            clickOnEnglish();
            clickOnSave();
            ds.takeScreenshot(driver);
        }
        menuIcon.click();
        ds.takeScreenshot(driver);

    }
    //This method used to click on Setting Element
    public void clickOnSetting() throws InterruptedException {
        ds.swipeScreen(driver);
        DriverSetup.waitForElement(driver,10,clickOnSetting);
        clickOnSetting.click();
        ds.takeScreenshot(driver);
    }
    //This method used to click on country/Language button
    public void clickOnCountryAndLanguage() throws InterruptedException {
        DriverSetup.waitForElement(driver,20,clickOnCountry);
        clickOnCountry.click();
        ds.takeScreenshot(driver);
    }
    //This method used to click on country/region button
    public void clickOnCountryAndRegion() throws InterruptedException {
        DriverSetup.waitForElement(driver,20,clickOnCountryRegion);
        clickOnCountryRegion.click();
        ds.takeScreenshot(driver);

    }
    //This method used to select Australia radio button
    public void selectAustralia() throws InterruptedException {
        DriverSetup.waitForElement(driver,10,clickOnAustralia);
        clickOnAustralia.click();
        ds.takeScreenshot(driver);
    }
    //This method used to click on Done button
    public void clickOnDoneBtn() throws InterruptedException {
        DriverSetup.waitForElement(driver,10,clickOnDoneBtn);
        clickOnDoneBtn.click();
        ds.takeScreenshot(driver);
    }
    //This method used to set country as Australia
    public String selectCountryToAustralia() throws InterruptedException {
        clickOnMenu();
        clickOnSetting();
        clickOnCountryAndLanguage();
        clickOnCountryAndRegion();
        selectAustralia();
        clickOnDoneBtn();
        return null;

    }
}
