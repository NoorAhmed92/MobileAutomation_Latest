package Config;


import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


public class DriverSetup {

    public AndroidDriver<MobileElement>  driver;

    @BeforeTest
    @Parameters({"deviceName","platformVersion","UDID"})
    public void setup(String deviceName,String platformVersion,String UDID) throws MalformedURLException {
       try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            caps.setCapability(MobileCapabilityType.UDID, UDID);
            caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 600);
            caps.setCapability(MobileCapabilityType.NO_RESET, true);

            File appDir = new File("C:/Users/NoorAhmed/IdeaProjects/AppiumAssignmnet/src/test/resources/Apps"); //only path to apk file directory
            File app = new File(appDir, "Amazon_shopping.apk");
            caps.setCapability("app",app.getAbsolutePath());
            caps.setCapability("appPackage","com.amazon.mShop.android.shopping");
            caps.setCapability("appActivity","com.amazon.mShop.splashscreen.StartupActivity");
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            driver = new AndroidDriver<MobileElement> (url, caps);


        }catch(Exception ex)
        {
            System.out.println("Cause is:"+ ex.getCause());
            System.out.println("Message is" + ex.getMessage());
            ex.printStackTrace();
        }
    }
    //This method used to take screenshot of the page
    public void takeScreenshot(AndroidDriver<MobileElement> driver)     {

        File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
        try {
            FileUtils.copyFile(src, new File( "C:/Users/NoorAhmed/IdeaProjects/AppiumAssignmnet/target/Screenshots/screenpage"+timestamp+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }     }
        //This method used to swipe up the screen
        public void swipeScreen(AndroidDriver<MobileElement> driver){
            Dimension size = driver.manage().window().getSize();
            int startY = (int)(size.height*0.8);
            int endY = (int)(size.height *0.2);
            int startX = size.width/2;
            TouchAction action = new TouchAction(driver);
            action.press(startX,startY).waitAction(Duration.ofSeconds(5)).moveTo(startX,endY).release().perform();
        }
        //This method used to wait the page till the given element load
        public static String waitForElement(AndroidDriver<MobileElement> driver,int timeLimit,MobileElement elementName){
            WebDriverWait wait = new WebDriverWait(driver, timeLimit);
            wait.until(ExpectedConditions.visibilityOf(elementName));
        return  null;
        }
  @AfterTest
  public void teardown()
    {
        driver.quit();
     }
}

