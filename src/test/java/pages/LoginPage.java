package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage (WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    RandomData randomPassword;

    //Web elements locators
    @FindBy (id="UserName")
            WebElement userNameField;
    @FindBy (xpath = "//*[@id='Password']")
            WebElement passwordInput;
    @FindBy (xpath = "//button[@class='btn btn-primary btn-lg btn-block']")
            WebElement loginButton;
    @FindBy (xpath = "//div[@class='validation-summary-errors']//li")
            WebElement validationError;
    @FindBy (xpath = "//li[contains(text(), 'UserName')]")
            WebElement emptyUserNameErrorMsg;
    @FindBy (xpath = "//li[contains(text(), 'Password ')]")
            WebElement emptyPasswordErrorMsg;
    @FindBy (xpath = "//li[contains(text(), 'The sign in information')]")
            WebElement invalidCredentialsErrorMsg;





    public String emptyPasswordLoginValidation(String userName) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        userNameField.sendKeys(userName);
        loginButton.click();
        wait.until(ExpectedConditions.visibilityOf(emptyPasswordErrorMsg));
        String emptyPasswordErrorMessageText = emptyPasswordErrorMsg.getText();

        return emptyPasswordErrorMessageText;
    }

    public String invalidEmailLoginValidation(String invalidUserName, String validPassword) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        userNameField.sendKeys(invalidUserName);
        passwordInput.sendKeys(validPassword);
        loginButton.click();
        wait.until(ExpectedConditions.visibilityOf(invalidCredentialsErrorMsg));
        String invalidSignInInformationMessage = invalidCredentialsErrorMsg.getText();
        return invalidSignInInformationMessage;
    }



    public String loginError(String userName, String incorrectPassword) throws InterruptedException {
        //Find login form for user iD input
        userNameField.sendKeys(userName);
        //Find login form for Password input
        passwordInput.sendKeys(incorrectPassword);
        //Find login button
        loginButton.click();
        Thread.sleep(3000);
        return driver.getTitle();

    }

    public void clearLoginFields() {
        userNameField.clear();
        passwordInput.clear();
    }

    public String getLoginErrorMsg() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(validationError));
        String errorMessage = validationError.getText();
        System.out.println("Login error message is: " + errorMessage);
        return errorMessage;

    }

    public void loginSuccessful(String login, String password) throws InterruptedException {
        randomPassword = new RandomData(driver);
        //Find login form for user iD input
        userNameField.sendKeys(login);
        //Find login form for Password input
        //driver.findElement(passwordElement).sendKeys(password);
        passwordInput.sendKeys(password);
        //Find Remember me checkbox, tick
        WebElement rememberMeCheckBox = driver.findElement(By.xpath("//label[@for='RememberMe']"));
        for (int i = 0; i < 2; i++) {
            rememberMeCheckBox.click();
            System.out.println(rememberMeCheckBox.isSelected());
        }

        //Find login button
        loginButton.click();
        Thread.sleep(4000);
    }


    public String getPageTitle() {

        return driver.getTitle();
    }


}



