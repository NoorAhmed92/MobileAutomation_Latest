package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

    public class BasePOMpage {

        public AndroidDriver<MobileElement> driver;

        public BasePOMpage(AndroidDriver<MobileElement> driver) {
            this.driver = driver;
            PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        }

        public BasePOMpage() {

        }
    }

