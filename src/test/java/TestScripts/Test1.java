package TestScripts;

import Config.DriverSetup;
import Pages.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;



public class Test1 extends DriverSetup {
    private LoginPage login;
    private Homepage home;
    private ProductPage srp;
    SoftAssert softAssert = new SoftAssert();
    @Test(priority = 1, description = "signIn into app")
    //This method is used to signIn into app
    public void signIntoAPP() throws Exception {
        login = new LoginPage(driver);
        softAssert.equals(login.loginIntoApp());
        System.out.println("Successfully loggedIn to app");
        softAssert.assertAll();
    }

    @Test(priority = 2, description = "set country to Australia")
    //This method is used to set country to Australia
    public void setCountryToAustralia() throws InterruptedException {
        home = new Homepage(driver);
        home.selectCountryToAustralia();
        System.out.println("Country been set to Australia");
        softAssert.assertAll();
    }

    @Test(priority = 3, description = "search product and do checkout")
    //This method is used to search product and do checkout
    public void searchForTV() throws InterruptedException {
        srp = new ProductPage(driver);
        softAssert.equals(srp.checkoutItem());
    }
}
