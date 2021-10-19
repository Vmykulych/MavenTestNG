package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

public class AccountPage {
    private final WebDriver driver;

    public AccountPage(WebDriver driver) {
        this.driver = driver;

    }

    //Web Elements
    By header = By.xpath("//header[@id='store-header-compact']//div[@class='container']");
    By userAccount = By.xpath("//ul[@class='nav navbar-nav navbar-right']//a[@href='/Members/MyOrders']");
    By accountList = By.xpath("//ul[@class='nav nav-pills nav-stacked']/li");
    By orders = By.xpath("//aside[@class='sidebar']//a[@href='/Members/MyOrders']");
    By addressBook = By.xpath("//aside[@class='sidebar']//a[@href='/Members/MyAddressBook']");
    By dataList = By.xpath("//aside[@class='sidebar']//a[@href='/Members/MyDataList']");
    By approvalRequests = By.xpath("//aside[@class='sidebar']//a[@href='/Admin/Orders/ApprovalRequests']");
    By ordersElements = By.xpath("//div[@id='mainColumn']//div[@class='widget-wrapper']");
    By addressBookElements = By.xpath("//div[@id='mainColumn']//div[@class='widget-wrapper']");
    By dataListElements = By.xpath("//div[@id='mainColumn']//div[@class='widget-wrapper']");
    By ordersTableHeaderNames = By.xpath("//table[@class='table table-bordered']//th");
    By browseButton = By.xpath("//div[@class='upload-inputs']//label[text()='Browse']");
    By ordersTable = By.xpath("//table/tbody");
    By uploadButton = By.xpath("//div[@class='upload-inputs']//button[@id='btnFileUpload']");
    By dataListCsvFile = By.xpath("//table/tbody/tr/td[@style='text-align: center'][1]");
    //By dataListDropDown = By.xpath("//button[@class='btn dropdown-toggle btn-default']");

    public void AccountPageElements(String myApprovalsUrl) {

        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Verify User's Account elements size, check they are correct
        //Wait until header is loaded
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(userAccount));

        //Find user's account page and click on it
        driver.findElement(userAccount).click();

        //Find the list if User's Account elements, check text, click one of them
        List<WebElement> accountListElements = driver.findElements(accountList);
        int s = accountListElements.size();
        System.out.println("Qty of account list elements:" + s);

        for (int i = 0; i < s; i++) {
            System.out.println("Account element name:" + accountListElements.get(i).getText());
            if (accountListElements.get(i).getText().equals("Approval Requests")) {
                System.out.println("Approval Requests exists");
                accountListElements.get(i).click();
            }
        }
        //Wait Approval request page is loaded
        wait.until(ExpectedConditions.urlToBe(myApprovalsUrl));
        driver.navigate().back();

    }

    public void MyOrders() {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Find My Orders, click on it, verify page title
        driver.findElement(orders).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ordersElements));

        //Find the Orders table header names
        List<WebElement> orderTableHeaderElements = driver.findElements(ordersTableHeaderNames);
        int s = orderTableHeaderElements.size();
        System.out.println("Header names qty" + s);

        for (int i = 0; i < s; i++) {
            System.out.println("Order table name:" + orderTableHeaderElements.get(i).getText());
            if (orderTableHeaderElements.get(i).getText().equals("Order")) {
                System.out.println("Order column exists");
                orderTableHeaderElements.get(i).isDisplayed();
            }
        }

    }

    public void MyAddressBook() {
        WebDriverWait wait = new WebDriverWait(driver, 15);


        //Find Address Book, click on it, verify page title
        driver.findElement(addressBook).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(addressBookElements));

    }

    public void openMyDataList() throws InterruptedException, AWTException {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Find Data List, click on it, verify page title
        driver.findElement(dataList).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(uploadButton));


    }

    public void uploadDataList(String dataListFilePath) throws InterruptedException, AWTException {
        //Upload file
        JavascriptExecutor js = (JavascriptExecutor) driver; // Scroll operation using Js Executor
        js.executeScript("window.scrollBy(0,200)"); // Scroll Down(+ve) upto browse option
        Thread.sleep(2000); // suspending execution for specified time period

        WebElement browse = driver.findElement(browseButton);
        // using linkText, to click on browse element
        browse.click(); // Click on browse option on the webpage
        Thread.sleep(2000); // suspending execution for specified time period

        // creating object of Robot class
        Robot rb = new Robot();

        // copying File path to Clipboard
        StringSelection str = new StringSelection(dataListFilePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

        // press Contol+V for pasting
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);

        // release Contol+V for pasting
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
        Thread.sleep(3000);

        // for pressing and releasing Enter
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(3000);
        driver.findElement(uploadButton).click();

        /*//Switching to alert
        Alert alert = driver.switchTo().alert();
        //Capture alert message
        String alertMessage = driver.switchTo().alert().getText();
        //Display alert message
        System.out.println(alertMessage);
        Thread.sleep(5000);
        //Dismiss alert
        alert.accept();
        Thread.sleep(5000);*/
    }

    public void approvalRequests(String myApprovalsUrl) {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Find Approval Requests, click on it, navigate back
        driver.findElement(approvalRequests);
        wait.until(ExpectedConditions.elementToBeClickable(approvalRequests));
        driver.findElement(approvalRequests).click();
        wait.until(ExpectedConditions.urlToBe(myApprovalsUrl));

    }

    public void navigateBack() {
        driver.navigate().back();
    }

}
