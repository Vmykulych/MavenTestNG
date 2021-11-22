package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CartPage {
    private final WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;}

    //Web elements
    By headerSearch = By.xpath("//a[@id='search-collapser']//i[1]");
    By headerSrchInput = By.xpath("//div[@id='header-store-search']//input[@id='Keywords']");
    By cart = By.xpath("//i[contains(@class,'p3 fa')]");
    By itemLink = By.cssSelector("table#basketItemsTable>tbody>tr>td:nth-of-type(2)>h4>a");
    By basePrice = By.xpath("(//div[@class='price']//span)[3]");
    By selectPlant = By.xpath("(//div[@class='filter-option-inner']//div)[1]");
    By selectPlantOption = By.xpath("//select[@id='dynamicField_plantname']");
    By pdp = By.xpath("//article[@class='product-item product-single']");
    By updateCartBtn = By.xpath("//button[@class='btn btn-primary btn-block']");
    By previewBtn = By.xpath("(//button[@class='btn btn-primary'])[1]");
    By previewSuccessMsg = By.xpath("//div[text()='Your preview is ready. Hover over image to zoom.']");
    By pricePerItemInCart = By.xpath("//td[@class='col-xs-2 text-center']//span");
    By qtyInput = By.xpath("//input[@id='modal-qty']");
    By cartQtyInput = By.xpath("//input[@id='BasketItems_0__Quantity']");
    By totalQty = By.xpath("(//td[@class='col-xs-3 text-center']//span)[1]");
    By subtotal = By.xpath("//ul[@class='list-unstyled order-total']//span[1]");
    By validSku3Plp = By.xpath("//article[@class='product-item product-item-display p-t-md']");
    By itemAddToCrtBtn = By.xpath("//button[@class='btn-add-to-cart btn btn-primary btn-sm']");
    By viewCart = By.xpath("//div[@class='col-sm-12']//a[@class='btn btn-inverse']");
    By secondItemTotal = By.xpath("(//td[@class='col-xs-3 text-center']//span)[2]");
    By secondItemDlt = By.xpath("(//a[@data-ajax-method='GET'])[2]");
    By contShopBtn = By.xpath("//a[text()=' Continue Shopping']");
    By homePage = By.xpath("//div[@class='row content']");
    By cartTable = By.xpath("//table[@id='basketItemsTable']");
    By upperCheckout = By.xpath("//div[@class='pull-right m-b-sm']//button[@type='submit']");
    By lowerCheckout = By.xpath("//div[@class='clearfix']//button[@type='submit']");
    By emptyCart = By.xpath("//a[text()='Empty Cart']");
    By alertWindow = By.xpath("//div[@class='sweet-alert showSweetAlert visible']");
    By alertDltBtn = By.xpath("//button[@class='confirm']");
    By cartEmpty = By.xpath("//div[@class='table-responsive']//h3[text()='Your shopping cart is empty.']");
    By itemSku2 = By.xpath("//div[@class='product-overlay']//a[1]");
    By firstName = By.xpath("//input[@id='dynamicField_firstname']");
    By lastName = By.xpath("//input[@id='dynamicField_lastname']");
    By titleName = By.xpath("//input[@id='dynamicField_jobtitle']");
    By addToCartBtn = By.xpath("//button[@class='btn btn-primary btn-block']");

    public  void openCart() throws AWTException, InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Wait until header is loaded
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(headerSearch));

        //Open cart page
        driver.findElement(cart).click();
        driver.navigate().refresh();
        Thread.sleep(3000);
    }

    public String getPageTitle() {
        //Check cart page title
        return driver.getTitle();

    }

    public  void getToPdpFromCart() {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Click item link and return to PDP
        driver.findElement(itemLink).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(pdp));

    }

    public String getPlantName() {

        //Verify plant name is pre-populated
        String plantValue = driver.findElement(selectPlant).getText();
        return plantValue;

    }

    public String getItemBasePrice() {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(basePrice));

        //Get item base price
        String itemPrice = driver.findElement(basePrice).getText();
        return itemPrice;

    }

    public String updateCart() {

        //Verify Add To Cart is changed to Update Cart
        String btnName = driver.findElement(updateCartBtn).getAttribute("value");
        return btnName;

    }

    public void getUpdatedItemPreview() {

        //Click Preview button, verify preview is successful
        WebElement previewButton = driver.findElement(previewBtn);
        previewButton.sendKeys(Keys.TAB);
        previewButton.sendKeys(Keys.ENTER);

    }

    public String getSuccessMessage() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(previewSuccessMsg));
        String successMsgTxt = driver.findElement(previewSuccessMsg).getText();
        return successMsgTxt;

    }

    public String getItemQtyOnPdp() {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(qtyInput));
        //Get item qty value on PDP
        String pdpQty = driver.findElement(qtyInput).getAttribute("value");
        return pdpQty;

    }

    public void clickUpdateCart() throws InterruptedException {

        //Find Update Cart button, click and move to the cart
        WebElement updateCartButton = driver.findElement(updateCartBtn);
        updateCartButton.sendKeys(Keys.TAB);
        updateCartButton.sendKeys(Keys.ENTER);
        Thread.sleep(5000);

    }

    public String getItemPriceInCart() {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(pricePerItemInCart));
        //Get the Item Price in cart and compare with Base price
        String priceInCart = driver.findElement(pricePerItemInCart).getText();
        System.out.println(priceInCart);
        int indexPrice = priceInCart.indexOf("$");
        String priceInCartIndexOff = priceInCart.substring(indexPrice + 1);
        return priceInCartIndexOff;

    }

    public String getItemPriceInCartUsingRegEx() {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(pricePerItemInCart));
        //Get the Item Price in cart and compare with Base price
        String priceInCart = driver.findElement(pricePerItemInCart).getText();
        System.out.println(priceInCart);
        String RegExPrice = "(\\d+)[.]\\d{2}";
        Pattern p = Pattern.compile(RegExPrice);
        Matcher m = p.matcher(priceInCart);
        if (m.find()) {
            return m.group();
        }
        return null;



    }

    public String getItemQtyOnCartPage() {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cartQtyInput));
        //Get qty value on cart page, compare with the one on PDP
        String cartQty = driver.findElement(cartQtyInput).getAttribute("value");
        return cartQty;

    }

    public String getCartQty() {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cartQtyInput));
        //Get Quantity value
        String quantity = driver.findElement(cartQtyInput).getAttribute("value");
        return quantity;

    }

    public String getTotalValueInCart() {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(totalQty));
        //Get Total value, cut "$" off
        String totalInCart = driver.findElement(totalQty).getText();
        int totalIndex = totalInCart.indexOf("$");
        String totalIndexOff = totalInCart.substring(totalIndex + 1);
        return totalIndexOff;

    }

    public void searchAnotherItem(String validSku3) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Find another item sku2 "1201"
        driver.findElement(headerSearch).click();
        driver.findElement(headerSrchInput).sendKeys(validSku3 + Keys.ENTER);

        //Wait until the validSku3 is present
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(validSku3Plp));
        Thread.sleep(2000);

    }

    public void addToCart() {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Click Add To Cart button
        driver.findElement(itemAddToCrtBtn).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(viewCart));

    }

    public void clickViewCartButton() {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Find View Cart button and click
        driver.findElement(viewCart);
        wait.until(ExpectedConditions.elementToBeClickable(viewCart));
        driver.findElement(viewCart).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(secondItemTotal));

    }

    public double getSecondItemTotal() {

        //Get second item total
        String secondItemValue = driver.findElement(secondItemTotal).getText();
        int secItmTotalIndOff = secondItemValue.indexOf("$");
        String secItmOff = secondItemValue.substring(secItmTotalIndOff + 1);
        //Convert second item value to double
        double dSecItm = Double.parseDouble(secItmOff);
        return dSecItm;

    }

    public double getSubtotalValue() {

        //Get Subtotal value cut "$" index off
        String subtotalValue = driver.findElement(subtotal).getText();
        System.out.println("Cart subtotal is: " + subtotalValue);
        int subIndOff = subtotalValue.indexOf("$");
        String subIndexOff = subtotalValue.substring(subIndOff + 1);
        System.out.println("Subtotal value without $ is: " + subIndexOff);
        //Convert String subIndexOff to double
        double dSubIndexOff = Double.parseDouble(subIndexOff);
        return dSubIndexOff;

    }

    public void deleteSecondItem() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Delete second item from the cart
        driver.findElement(secondItemDlt).click();
        //Wait until the deleted element is not visible
        wait.until(ExpectedConditions.invisibilityOfElementLocated(secondItemDlt));
        Thread.sleep(5000);

    }

    public void verifyContinueShopping() {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Find Continue Shopping Button verify it works correctly (redirects to the Home Page)
        driver.findElement(contShopBtn).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(homePage));

    }

    public void navigateBack() {

        //Return to the Cart page
        driver.navigate().back();

    }

    public void clickUpperCheckoutBtn() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cartTable));
        //Click Upper Checkout, verify page, navigate back
        driver.findElement(upperCheckout).click();
        Thread.sleep(4000);

    }

    public String getUserProfileTitle() {
        String userProfAddr = driver.getTitle();
        //Print page's title
        System.out.println("Page Title is: " + userProfAddr);
        return userProfAddr;

    }

    public void clickLowerCheckoutBtn() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cartTable));
        //Click Lower Url, verify page, navigate back
        driver.findElement(lowerCheckout).click();
        Thread.sleep(4000);

    }

    public void verifyCleanCart() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Verify Empty Cart cleans everything
        driver.findElement(emptyCart).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(alertWindow));

        //Confirm Empty Cart
        driver.findElement(alertDltBtn).click();
        Thread.sleep(2000);

    }

    public String getCartEmptyMessage() {

        //Make sure the cart is empty
        String cartEmptyMessage = driver.findElement(cartEmpty).getText();
        System.out.println("Empty Cart Message is: " + cartEmptyMessage);
        return cartEmptyMessage;

    }

    public void getItemToCart(String validSku2, String firstNameValue, String lastNameValue, String titleValue)
            throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Add item to the cart again
        driver.findElement(headerSearch).click();
        driver.findElement(headerSrchInput).sendKeys(validSku2 + Keys.ENTER);

        //Wait until the VDP item is present, click to open the PDP
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(itemSku2));
        driver.findElement(itemSku2).click();
        Thread.sleep(2000);

        //Find plant drop-down and select "Chicago", index = 27
        driver.findElement(selectPlantOption);
        Select plantName = new Select(driver.findElement(selectPlantOption));
        plantName.selectByIndex(27);
        Thread.sleep(2000);

        //Fill required fields: First Name, Last Name, Title
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(firstNameValue);
        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(lastNameValue);
        driver.findElement(titleName).sendKeys(titleValue);
        Thread.sleep(2000);

        //Click Preview button, verify preview is successful
        driver.findElement(previewBtn).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(previewSuccessMsg));

        //Find Add To Cart button, click and move to the cart
        WebElement addToCartButton = driver.findElement(addToCartBtn);
        addToCartButton.sendKeys(Keys.TAB);
        addToCartButton.sendKeys(Keys.ENTER);
        Thread.sleep(5000);

    }

}
