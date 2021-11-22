package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class LoginPage {
       private final WebDriver driver;
    RandomData randomPassword;

    public LoginPage (WebDriver driver) {
        this.driver=driver;
    }

    //Web elements
    By userName = By.id("UserName");
    By passwordElement = By.xpath("//*[@id='Password']");
    By loginButton = By.xpath("//button[@class='btn btn-primary btn-lg btn-block']");
    By validationError = By.xpath("//div[@class='validation-summary-errors']//li");

    public String loginError(String login, String inincorrectPassword) throws InterruptedException {
        //Find login form for user iD input
        driver.findElement(userName).sendKeys(login);
        //Find login form for Password input
        driver.findElement(passwordElement).sendKeys(inincorrectPassword);
        //Find login button
        driver.findElement(loginButton).click();
        Thread.sleep(3000);
        return driver.getTitle();

    }

    public void clearLoginFields() {
        driver.findElement(userName).clear();
        driver.findElement(passwordElement).clear();
    }

    public String getLoginErrorMsg() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(validationError));
        String errorMessage = driver.findElement(validationError).getText();
        System.out.println("Login error message is: " + errorMessage);
        return errorMessage;

    }

    public void loginSuccessful(String login, String password) throws InterruptedException {
        randomPassword = new RandomData(driver);



        //Find login form for user iD input
        driver.findElement(userName).sendKeys(login);
        //Find login form for Password input
        //driver.findElement(passwordElement).sendKeys(password);
        driver.findElement(passwordElement).sendKeys(password);
        //Find Remember me checkbox, tick
        WebElement rememberMeCheckBox = driver.findElement(By.xpath("//label[@for='RememberMe']"));
        for (int i = 0; i < 2; i++) {
            rememberMeCheckBox.click();
            System.out.println(rememberMeCheckBox.isSelected());
        }

        //Find login button
        driver.findElement(loginButton).click();
        Thread.sleep(4000);
    }


    public String getPageTitle() {
       return driver.getTitle();
    }


}



