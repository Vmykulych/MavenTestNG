package tests.Login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import tests.DataProvider.LoginDataProvider;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
//import pages.PageFactory;

public class LoginVerification {
    public WebDriver driver;
    public static Logger logger;
    LoginPage loginPage;
    public String pcaURL = "https://pcafulfillment.iprint.visionps.com/login?useACLogin=true";
    //LoginPage
    String userName = "vmykulych@visionps.com";
    String password = "Vision124!/Z";
    String incorrectPassword = "Test123";
    //String userNameRequiredMsg = "The UserName field is required.";
    //String passwordRequiredMsg = "The Password field is required.";


    //-----------------------------------Test Setup-----------------------------------
    @BeforeMethod
    public void setupTest() {
        logger = Logger.getLogger("new logger");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //launch Browser and direct it to the test URL
        driver.get(pcaURL);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }
    //Verify user is not able to login with empty password field and the error message is correct
    @Test(dataProvider = "EmptyPasswordLogin",  dataProviderClass = LoginDataProvider.class)
    public void emptyPasswordLoginErrorMessageValidation (String userName, String passwordRequiredMsg) throws InterruptedException {
        loginPage = new LoginPage(driver);
       System.out.println("The error message is: " + passwordRequiredMsg);
       Assert.assertEquals(loginPage.emptyPasswordLoginValidation(userName),passwordRequiredMsg);
    }

    //Verify user is not able to login with incorrect email and the error message is correct
    @Test(dataProvider = "InvalidEmail",  dataProviderClass = LoginDataProvider.class)
    public void incorrectEmailLoginErrorMessageValidation (String invalidUserName, String validPassword,
                                                           String expectedErrMsg) throws InterruptedException {
        loginPage = new LoginPage(driver);
        Assert.assertEquals(loginPage.invalidEmailLoginValidation(invalidUserName, validPassword),
                expectedErrMsg);

    }




    @Test //Login and verification of header elements
    public void PCATest1() throws InterruptedException {
        logger.info("start PCATest 1");

        loginPage = new LoginPage(driver);

        //Login negative scenario with incorrect password - expected result: user is not logged in

        //loginPage.loginError(userName, incorrectPassword);
        loginPage.loginError(userName, incorrectPassword);
        Assert.assertEquals(loginPage.getPageTitle(), "Login");

        //Verify the error message after unsuccessful login is correct
        Assert.assertEquals(loginPage.getLoginErrorMsg(), "The sign in information you provided was incorrect. You have 5 tries remaining.");

        //Clear all Login fields
        loginPage.clearLoginFields();

        //Login - positive scenario - expected result: user is logged in and landed on Home Page
        loginPage.loginSuccessful(userName, password);

        //Verify page title - expected "Home Page"
        //Assert page title is correct
        Assert.assertEquals(loginPage.getPageTitle(), "Home Page", "Title assertion is failed!");
        logger.info("end PCATest 1");

    }

    //-----------------------------------Test TearDown-----------------------------------
    @AfterMethod
    public void teardownTest() {
        //Close browser
        driver.close();

    }

}
