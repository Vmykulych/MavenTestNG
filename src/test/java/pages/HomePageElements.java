package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;


public class HomePageElements {
    private final WebDriver driver;

    public HomePageElements(WebDriver driver) {
        this.driver = driver;
    }

    //Web Elements
    By logo = By.xpath("//img[@alt='PCA Logo']");
    By headerSearch = By.xpath("//a[@id='search-collapser']//i[1]");
    By headerSrchInput = By.xpath("//div[@id='header-store-search']//input[@id='Keywords']");
    By itemSku2 = By.xpath("//div[@class='product-overlay']//a[1]");
    By cart = By.xpath("//i[contains(@class,'p3 fa')]");
    By catalog = By.xpath("//li[@class='hidden-sm']//a[1]");
    By userAccount = By.xpath("//li[@class='acct']//a[1]");
    By admin = By.xpath("//li[@class='admin']//a[1]");
    By logout = By.xpath("//li[@class='login']//a[text()='Logout']");
    By headerCatalog = By.xpath("//h2[text()='Catalog']");
    By catalogTile1 = By.xpath("//div[@class='card rounded']//span[text()='PCA Printed Materials']");
    By catalogTile2 = By.xpath("//div[@class='card rounded']//span[text()='PCA Promotional Items, Clothing and Golf']");
    By catalogTile3 = By.xpath("//div[@class='card rounded']//span[text()='Onboarding-Compass Program']");
    By catalogTile4 = By.xpath("//div[@class='card rounded']//span[text()='To Be Released']");
    By widgetBody = By.xpath("//div[@class='widget-body']");
    By widgetPcaPrintMat = By.xpath("//div[@class='widget-body']//span[text()='PCA Printed Materials']");
    By widgetPcaPrintMatQty = By.xpath("//a[@href='/?CategoryId=57']//span[@class='badge']");
    By widgetPcaPromItm = By.xpath("//div[@class='widget-body']//span[text()='PCA Promotional Items, Clothing and Golf']");
    By widgetOnbrdComProg = By.xpath("//div[@class='widget-body']//span[text()='Onboarding-Compass Program']");
    By widgetToBeReleased = By.xpath("//div[@class='widget-body']//span[text()='To Be Released']");
    By widgetGreenChoice = By.xpath("//div[@class='widget-body']//span[text()='Green Choice']");
    By catalogSearch = By.xpath("//div[@class='input-group']//input[@id='Keywords']");
    By catalogSrchBtn = By.xpath("//div[@class='widget-body']//span[@class='input-group-btn']");
    By catalogList = By.xpath("//div[@id='page-wrapper']//div[@class='row align-items-center']/div");

    public void HomepageHeader(String validSku2) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        //Verify Home Page Header Elements
        //Check Brand Logo
        driver.findElement(logo).click();
        //Check header search option expand > input valid SKU > click
        driver.findElement(headerSearch).click();
        driver.findElement(headerSrchInput).sendKeys(validSku2 + Keys.ENTER);
        //Wait until item 120X is displayed
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(itemSku2));
        //Find Cart icon, open Cart and return to HomePage
        driver.findElement(cart).click();
        //Find Catalog and click
        driver.findElement(catalog).click();
        //Find User's Account page
        driver.findElement(userAccount).click();
        //Find and open Admin page and return back to Home Page
        driver.findElement(admin).click();
        driver.navigate().back();
        //Logout
        driver.findElement(logout);

    }

    public void HomePageCatalogTiles() {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Check Catalog header is present by default
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(headerCatalog));
        String catalog = driver.findElement(headerCatalog).getText();
        Assert.assertEquals("Catalog", catalog);
        System.out.println(catalog);

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(catalogTile1));

        //Find Catalog categories tiles and verify they are correct
        WebElement tile1 = driver.findElement(catalogTile1);
        Assert.assertTrue(tile1.isDisplayed());

        WebElement tile2 = driver.findElement(catalogTile2);
        Assert.assertTrue(tile2.isDisplayed());

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(catalogTile3));

        WebElement tile3 = driver.findElement(catalogTile3);
        Assert.assertTrue(tile3.isDisplayed());

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(catalogTile4));

        WebElement tile4 = driver.findElement(catalogTile4);
        Assert.assertTrue(tile4.isDisplayed());

    }

    public void CatalogWidgetElements() {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Wait until catalog section appears
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(widgetBody));

        //Find "Printed Materials" category, open, navigate back and assert the name
        driver.findElement(widgetPcaPrintMat);
        driver.findElement(widgetPcaPrintMatQty).click();
        driver.navigate().back();

        String catalog1 = driver.findElement(widgetPcaPrintMat).getText();
        Assert.assertEquals("PCA Printed Materials", catalog1);
        System.out.println(catalog1);

        //Find "PCA Promotional Items, Clothing and Golf" category, open, navigate back and assert the name
        driver.findElement(widgetPcaPromItm).click();
        driver.navigate().back();

        String catalog2 = driver.findElement(widgetPcaPromItm).getText();
        Assert.assertEquals("PCA Promotional Items, Clothing and Golf", catalog2);
        System.out.println(catalog2);

        //Find "Onboarding-Compass Program" category, open, navigate back and assert the name
        driver.findElement(widgetOnbrdComProg).click();
        driver.navigate().back();

        String catalog3 = driver.findElement(widgetOnbrdComProg).getText();
        Assert.assertEquals("Onboarding-Compass Program", catalog3);
        System.out.println(catalog3);

        //Find "To Be Released" category, open, navigate back and assert the name
        driver.findElement(widgetToBeReleased).click();
        driver.navigate().back();

        String catalog4 = driver.findElement(widgetToBeReleased).getText();
        Assert.assertEquals("To Be Released", catalog4);
        System.out.println(catalog4);

    }

    public void CatalogWidgetSearch(String validSku3) {
        WebDriverWait wait = new WebDriverWait(driver, 15);


        //Check Catalog widget search (valid data)
        driver.findElement(catalogSearch).sendKeys(validSku3);
        driver.findElement(catalogSrchBtn).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(widgetBody));
        driver.navigate().back();

        //Find the list if catalog elements, check text, click one of them
        List<WebElement> catalogListElements = driver.findElements(catalogList);
        int s = catalogListElements.size();
        System.out.println("Amount catalog list elements:" + s);

        for (WebElement element : catalogListElements) {
            System.out.println("Amount catalog list:" + element.getText());
        }
        for (int i = 0; i < s; i++) {
            System.out.println("Amount catalog list2:" + catalogListElements.get(i).getText());
            if (catalogListElements.get(i).getText().equals("To Be Released")) {
                System.out.println("To Be Released exists");
                catalogListElements.get(i).click();
            }
        }

    }



}
