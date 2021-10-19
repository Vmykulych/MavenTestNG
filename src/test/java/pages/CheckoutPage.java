package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class CheckoutPage {
    private final WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    //Web elements
    By headerSearch = By.xpath("//a[@id='search-collapser']//i[1]");
    By cart = By.xpath("//i[contains(@class,'p3 fa')]");
    By cartSubtotal = By.xpath("//ul[@class='list-unstyled order-total']//span[1]");
    By lowerCheckout = By.xpath("//div[@class='clearfix']//button[@type='submit']");
    By progressBar = By.xpath("//article[@class='account-content checkout-steps']");
    By progBarElements = By.xpath("//div[@class='flex-row']//div[@style]");
    By addressList1 = By.xpath("//div[@class='col-lg-6'][1]//input");
    By addresslist2 = By.xpath("//div[@class='col-lg-6'][2]//select");
    By addressList3 = By.xpath("//div[@class='col-lg-6'][2]//input");
    By shippingAddrCollapser = By.xpath("//div[@class='widget widget-shipping-options']//a[@role='button']");
    By shipAddrOptions = By.xpath("//div[@class='widget-body']//div[@class='options']");
    By shipOption1 = By.xpath("//div[@class='radio']//input[@id='ShipToOption1']");
    By shipOption2 = By.xpath("//div[@class='radio']//input[@id='ShipToOption2']");
    By orderSummaryCollapser = By.xpath("//div[@class='widget boxed-widget widget-basket-summary']//a[@role='button']");
    By orderSummaryWidget = By.xpath("//div[@class='widget-body']//ul");
    By shippToDiffAdd = By.xpath("//div[@class='radio']//label[@for='ShipToOption2']");
    By continueCheckout = By.xpath("//div[@class='actions-area']//button[@id='continue-checkout-button']");
    By shipHere = By.xpath("(//a[@data-ajax-method='GET'])[1]");
    By shipMethodElements = By.id("checkout_shipMethodPage");
    By continueCheckout2 = By.xpath("//div[@class='actions-area']//button[@type='submit']");
    By shipMethod = By.xpath("//div[@class='radio']//label[1]");
    By orderSummary = By.xpath("//div[@id='widget-basket-summary-collapse']//ul[@class='list-unstyled']//li");
    By paymentSubtotal = By.xpath("(//ul[@class='list-unstyled']//span)[2]");
    By cardType = By.xpath("//select[@id='CardType']");
    By cardType2 = By.xpath("//select[@id='CardType']//option[2]");
    By nameOnCArd = By.xpath("//div[@class='required-field-block']//input[@id='CardName']");
    By cardNumber = By.xpath("//div[@class='required-field-block']//input[@id='CardNumber']");
    By monthOption = By.xpath("//select[@id='ExpirationMonth']//option");
    By monthToSelect = By.xpath("//select[@id='ExpirationMonth']");
    By yearOption = By.xpath("//select[@id='ExpirationYear']//option");
    By yearToSelect = By.xpath("//select[@id='ExpirationYear']");
    By cvv = By.xpath("//div[@class='securityCodeInput']//input[@id='SecurityCode']");
    By completeOrder = By.xpath("//button[@id='complete-form-btn']");
    By errorCheckoutMessage = By.xpath("//div[@class='validation-summary-errors']//li");
    By emptyCart = By.xpath("//a[text()='Empty Cart']");
    By alertDltBtn = By.xpath("//button[@class='confirm']");
    By productList = By.id("basketItemsTable");

    public String getCartSubtotal() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Wait until header is loaded
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(headerSearch));

        //Check Subtotal, declare and convert to Boolean
        String cartSubtotalValue = driver.findElement(cartSubtotal).getText();
        System.out.println("Cart subtotal is: " + cartSubtotalValue);
        int cartSubIndOff = cartSubtotalValue.indexOf("$");
        String cartSubIndexOff = cartSubtotalValue.substring(cartSubIndOff + 1);
        System.out.println("Cart Subtotal value without $ is: " + cartSubIndexOff);
        return cartSubIndexOff;

    }

    public void proceedToCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Click Checkout Button (lower) to proceed to User profile page
        driver.findElement(lowerCheckout).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(progressBar));

    }

    public String getPageTitle() {

        //Check page title, confirm it's correct
        String title = driver.getTitle();
        System.out.println("Page title is: " + title);
        return title;

    }

    public void verifyUserProfileAddress() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Find the Address elements, verify the fields are in Readonly format
        //List 1
        List<WebElement> addressElements1 = driver.findElements(addressList1);
        int s1 = addressElements1.size();
        System.out.println("Fields amount in list 1: " + s1);

        for (int i = 0; i < s1; i++) {
            System.out.println("Field is in readonly format - list 1: " + addressElements1.get(i).getAttribute("readonly"));
            Assert.assertTrue(addressElements1.get(i).getAttribute("readonly").equals("true"));


        }

        //List 2
        List<WebElement> addressElements2 = driver.findElements(addresslist2);
        int s2 = addressElements2.size();
        System.out.println("Fields amount in list 2: " + s2);

        for (int i = 0; i < s2; i++) {
            System.out.println("Field is in readonly format - list 2: " + addressElements2.get(i).getAttribute("readonly"));
            Assert.assertTrue(addressElements2.get(i).getAttribute("readonly").equals("true"));
        }

        //List 3
        List<WebElement> addressElements3 = driver.findElements(addressList3);
        int s3 = addressElements3.size();
        System.out.println("Fields amount in list 3: " + s3);

        for (int i = 0; i < s3; i++) {
            System.out.println("Field is in readonly format - list 3: " + addressElements3.get(i).getAttribute("readonly"));
            Assert.assertTrue(addressElements3.get(i).getAttribute("readonly").equals("true"));
        }

        //Check progress bar elements
        List<WebElement> progressBarElements = driver.findElements(progBarElements);
        int progBsize = progressBarElements.size();
        System.out.println("Progress Bar elements size: " + progBsize);
        for (int i = 0; i < progBsize; i++) {
            System.out.println("Progress Bar numbers and names: " + progressBarElements.get(i).getText());
        }

    }

    public void verifyShippingAddressWidget() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Check Shipping Address widget collapse works correctly
        driver.findElement(shippingAddrCollapser).click();
        Thread.sleep(2000);
        WebElement widgetContent = driver.findElement(shipAddrOptions);
        wait.until(ExpectedConditions.invisibilityOf(widgetContent));
        Assert.assertFalse(widgetContent.isDisplayed());
        Thread.sleep(2000);

        //Expand the Collapse back Shipping Address widget
        driver.findElement(shippingAddrCollapser).click();
        Thread.sleep(2000);

        //Verify "Ship to this User Profile address" is checked by default
        Boolean shipOption1Checked = driver.findElement(shipOption1).isSelected();
        Assert.assertTrue(shipOption1Checked == true);
        System.out.println("Ship to this User Profile address is checked by default - " + shipOption1Checked);


        //Verify "Ship to a different address" is not checked by default
        Boolean shipOption2Checked = driver.findElement(shipOption2).isSelected();
        Assert.assertTrue(shipOption2Checked == false);
        System.out.println("Ship to this User Profile address is checked by default - " + shipOption2Checked);

    }

    public void verifyOrderSummaryWidget() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Check Order Summary widget collapse works correctly
        driver.findElement(orderSummaryCollapser).click();
        Thread.sleep(2000);
        WebElement orderSummaryContent = driver.findElement(orderSummaryWidget);
        wait.until(ExpectedConditions.invisibilityOf(orderSummaryContent));
        Assert.assertFalse(orderSummaryContent.isDisplayed());
        Thread.sleep(2000);

        //Expand Order Summary to view its content
        driver.findElement(orderSummaryCollapser).click();
        Thread.sleep(2000);

    }

    public void verifyShipToDifferentAddress() throws InterruptedException {

        //Click Ship to a different address
        driver.findElement(shippToDiffAdd).click();
        Thread.sleep(2000);

        //Click Continue Checkout to move to step 2 "Shipping"
        driver.findElement(continueCheckout).click();

    }

    public void verifyShippingPage() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Select address and click "Ship Here"
        wait.until(ExpectedConditions.elementToBeClickable(shipHere));
        driver.findElement(shipHere).click();

        //Verify Select Ship Method page is displayed
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(shipMethodElements));
        String title2 = driver.getTitle();
        Assert.assertEquals(title2, "Checkout - Select Ship Method");

        //Verify Shipping Method is selected
        String shippingMethod = driver.findElement(shipMethod).getText();
        Assert.assertNotNull(shippingMethod);

        //Click Continue Checkout
        driver.findElement(continueCheckout2).click();
        Thread.sleep(2000);

    }

    public String getPaymentPageTitle() {

        //Confirm Checkout - Confirm and Pay page title is correct
        String title = driver.getTitle();
        return title;


    }

    public void verifyOrderSummaryList() {

        //Get order summary elements list
        List<WebElement> orderSummaryElements = driver.findElements(orderSummary);
        int orderSumSize = orderSummaryElements.size();
        System.out.println("Order Summary widget elements qty is: " + orderSumSize);

        for (int i = 0; i < orderSumSize; i++) {
            System.out.println("Order Summary:" + orderSummaryElements.get(i).getText());
            if (orderSummaryElements.get(i).getText().equals("Subtotal")) {
                System.out.print("Subtotal value is: " + orderSummaryElements);
            }
        }
    }

    public String getPaymentSubtotal() {

        //Get Subtotal value
        String subtotalValue = driver.findElement(paymentSubtotal).getText();
        System.out.println("Order Summary Subtotal value is: " + subtotalValue);

        //Cut "$" index off
        int paymentSubIndOff = subtotalValue.indexOf("$");
        String paymentSubIndexOff = subtotalValue.substring(paymentSubIndOff + 1);
        System.out.println("Payment Subtotal value without $ is: " + paymentSubIndexOff);
        return  paymentSubIndexOff;


    }

    public void verifyCompleteOrder(String cardData, String masterCardNumber, String masterCardCvv)
            throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Verify Card Type drop-down, select any (for ex: "MasterCard")
        driver.findElement(cardType);
        Select cardName = new Select(driver.findElement(cardType));
        cardName.selectByIndex(1);
        Thread.sleep(2000);

        //Verify Card Type is selected correctly
        String selectedCardName = driver.findElement(cardType2).getText();
        System.out.println("Selected CArd Type is: " + selectedCardName);
        Assert.assertEquals(selectedCardName, "MasterCard");

        //Find Name on Card, clear it, input First & Last names
        driver.findElement(nameOnCArd).clear();
        Thread.sleep(1000);
        driver.findElement(nameOnCArd).sendKeys(cardData);
        Thread.sleep(1000);

        //Find Card Number, input the testing number
        driver.findElement(cardNumber).sendKeys(masterCardNumber);
        Thread.sleep(1000);

        //Get valid expiration date "Month", the last one
        List<WebElement> monthElements = driver.findElements(monthOption);
        int monthSize = monthElements.size();
        System.out.println("Number of month elements:" + monthSize);

        //Select the last Month element
        Select monthName = new Select(driver.findElement(monthToSelect));
        monthName.selectByIndex(monthSize - 1);
        Thread.sleep(2000);

        //Get valid expiration date "Year", the last one
        List<WebElement> yearElements = driver.findElements(yearOption);
        int yearSize = yearElements.size();
        System.out.println("Number of year elements: " + yearSize);

        //Select the last Year elements
        Select yearName = new Select(driver.findElement(yearToSelect));
        yearName.selectByIndex(yearSize - 1);
        Thread.sleep(1000);

        //Find CVV element, input the 3 digit number
        driver.findElement(cvv).sendKeys(masterCardCvv);
        Thread.sleep(2000);

        //Click Complete Order button
        driver.findElement(completeOrder).click();

        //Verify order was not placed, as it is prod, check the error message is present and correct
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(errorCheckoutMessage));
        String errorMessageText = driver.findElement(errorCheckoutMessage).getText();
        System.out.println("Complete Order error message is: " + errorMessageText);
        Assert.assertEquals(errorMessageText, "There was a problem processing your payment: This transaction has been declined.");
        Thread.sleep(4000);

        //Clear the Cart to bring the test suite to initial condition
        driver.findElement(cart).click();
        wait.until(ExpectedConditions.elementToBeClickable(emptyCart));
        //Click Empty Cart button
        driver.findElement(emptyCart).click();
        wait.until(ExpectedConditions.elementToBeClickable(alertDltBtn));
        Thread.sleep(4000);
        //Confirm empty cart
        driver.findElement(alertDltBtn).click();
        Thread.sleep(4000);
        //Verify tha Cart is empty
        Boolean cartEmpty = ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(productList)).apply(driver);
        Assert.assertTrue(cartEmpty);


    }


}
