package Pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import Utility.*;
import Config.*;

public class LoginPage extends BasePOMpage{
    public DriverSetup ds;

    public LoginPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    @AndroidFindBy(xpath="//android.widget.EditText[contains(@resource-id,'ap_password')]")
    private AndroidElement passwordTxt;

    @AndroidFindBy(xpath= "//android.widget.Button[contains(@resource-id,'signInSubmit')]")
    private  AndroidElement loginBtn;

    @AndroidFindBy(xpath="//android.widget.Button[contains(@resource-id,'com.amazon.mShop.android.shopping:id/sign_in_button')]")
    public AndroidElement signInButton;

    @AndroidFindBy(xpath = "//android.widget.EditText[contains(@text,'ap_ra_email_or_phone')]")
    private AndroidElement emailIdTxt;

    @AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id,'continue')]")
    private AndroidElement continueBtn;


    //This method is used to click on Already a customer? SignIn button
    public void clickOnSignInButton() throws InterruptedException {
        DriverSetup.waitForElement(driver,10,signInButton);
        ds = new DriverSetup();
        ds.takeScreenshot(driver);
        signInButton.click();



    }
    //This method used to send EmailId/Phone number
    public void EmailID() throws Exception {
        ExcelUtils excel = new ExcelUtils("C:/Users/NoorAhmed/IdeaProjects/AppiumAssignmnet/src/test/resources/testdata/testdata.xlsx");
        System.out.println(excel.getData(0,1,1));
        DriverSetup.waitForElement(driver,10,emailIdTxt);
        emailIdTxt.sendKeys(excel.getData(0,1,1));
        ds.takeScreenshot(driver);
    }
    //This method used to click on Continue button
    public void clickOnContinue() throws InterruptedException {
        DriverSetup.waitForElement(driver,10,continueBtn);
        continueBtn.click();
        ds.takeScreenshot(driver);
    }
    //This method used to send password value
    public void enterPassword() throws Exception {
        ExcelUtils excel = new ExcelUtils("C:/Users/NoorAhmed/IdeaProjects/AppiumAssignmnet/src/test/resources/testdata/testdata.xlsx");
        System.out.println(excel.getData(0,1,2));
        DriverSetup.waitForElement(driver,10,passwordTxt);
        passwordTxt.sendKeys(excel.getData(0,1,2));
        ds.takeScreenshot(driver);
    }
    //This method used to click on Login button
    public void clickOnLogin() throws InterruptedException {
        DriverSetup.waitForElement(driver,10,loginBtn);
        loginBtn.click();
        ds.takeScreenshot(driver);
    }
    //This method is used to login into app
    public String loginIntoApp() throws Exception {
        clickOnSignInButton();
        EmailID();
        clickOnContinue();
        enterPassword();
        clickOnLogin();
        return null;

    }


}
