package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class AdminPage {
    private final WebDriver driver;

    public AdminPage(WebDriver driver) {
        this.driver = driver; }

    //Web elements
    By admin = By.xpath("//li[@class='admin']//a[1]");
    By adminElements = By.xpath("//ul[@class='nav navbar-top-links navbar-right top-nav-bar']//span");
    By orders = By.xpath("(//a[@href='/Admin/Orders'])[3]");
    By users = By.xpath("(//a[@href='/Admin/People/Users'])[2]");
    By catalog = By.xpath("(//a[@href='/Admin/Category'])[2]");
    By products = By.xpath("(//a[@href='/Admin/Product/ManageProducts'])[2]");
    By storefront = By.xpath("(//a[@href='/'])");

    public void openAdminPage() {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        wait.until(ExpectedConditions.elementToBeClickable(admin));

        //Find Admin element and click
        driver.findElement(admin).click();

    }

    public String getAdminPageTitle() {


        //Verify Admin page is opened
        String adminPageTitle = driver.getTitle();
        System.out.println("Admin landing page title is: " + adminPageTitle);
        return adminPageTitle;

    }

    public String verifyOrdersPage() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Verify Orders page is correct
        driver.findElement(orders).click();
        wait.until(ExpectedConditions.elementToBeClickable(orders));
        Thread.sleep(2000);
        String ordersTitle = driver.getTitle();
        System.out.println("Orders page title is: " + ordersTitle);
        return ordersTitle;

    }

    public String verifyUsersPage() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Verify Users page is correct
        driver.findElement(users).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(users));
        Thread.sleep(2000);
        String usersTitle = driver.getTitle();
        System.out.println("Users page title is: " + usersTitle);
        return usersTitle;

    }

    public String verifyCatalogPage() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Verify Catalog page is correct
        driver.findElement(catalog).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(catalog));
        Thread.sleep(2000);
        String catalogTitle = driver.getTitle();
        System.out.println("Catalog page title is: " + catalogTitle);
        return catalogTitle;

    }

    public String verifyProductsPage() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Verify Products page is correct
        driver.findElement(products).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(products));
        Thread.sleep(2000);
        String productsTitle = driver.getTitle();
        System.out.println("Products page title is: " + productsTitle);
        return productsTitle;

    }

    public void getStorefrontPage() throws InterruptedException {

        //Verify Storefront page is correct, redirects to Home Page
        driver.findElement(storefront).click();
        Thread.sleep(3000);

    }

    public void getTabsList() {

        //Get the list of window tabs
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        System.out.println("The number of opened tabs is: " + tabs.size());
        //Move to Home Page
        driver.switchTo().window(tabs.get(1));

    }

    public String getStorefrontPageTitle() {

        //Verify the page title
        String storefrontTitle = driver.getTitle();
        System.out.println("Storefront page title is: " + storefrontTitle);
        return storefrontTitle;

    }

    public void closeTab() {

        //Close the new tab
        driver.close();

    }

    public void switchToPrevTab() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

        //Move back to the old tab
        driver.switchTo().window(tabs.get(0));

    }

    public void verifyLogout() throws InterruptedException {

        //Verify header elements list and click Logout
        List<WebElement> headerOptions = driver.findElements(adminElements);
        int adminElementsSize = headerOptions.size();
        System.out.println("Admin Header Top Right elements qty is: " + adminElementsSize);

        for (int i = 0; i < adminElementsSize; i++) {
            System.out.println("Admin Elements:" + headerOptions.get(i).getText());
            if (headerOptions.get(i).getText().equals("Logout")) {
                headerOptions.get(i).click();
            }
        }
        Thread.sleep(2000);
    }


}









