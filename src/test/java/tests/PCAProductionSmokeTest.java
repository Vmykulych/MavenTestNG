package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.logging.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import pages.*;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

//comment the above line and uncomment below line to use Chrome
//import org.openqa.selenium.chrome.ChromeDriver;

/*****************************************************************************
 * Author:
 * Description: It opens PCA login form and performs login action.
 *******************************************************************************/

public class PCAProductionSmokeTest {
    //PageFactory pages = new PageFactory();


    //-----------------------------------Global Variables-----------------------------------
    //Declare a Webdriver variable
    public WebDriver driver;
    //POM
    LoginPage loginPage;
    HomePageElements homePageElements;
    AccountPage accountPage;
    VDPPage vdpPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    AdminPage adminPage;
    RandomData randomPassword;


    //Declare test Strings
    public String pcaURL = "https://pcafulfillment.iprint.visionps.com/login?useACLogin=true";

    //LoginPage
    String userName = "vmykulych@visionps.com";
    String password = "Vision124!/Z";
    String incorrectPassword = "Test123";
    //HomePageElements
    String validSku2 = "1207X";
    String validSku3 = "2001";
    //AccountPage
    String myApprovalsUrl = "https://pcafulfillment.iprint.visionps.com/Admin/Orders/ApprovalRequests";
    String dataListFilePath = "C:\\Users\\viktor.mykulych\\IdeaProjects\\MavenTestNG\\src\\test\\Data\\DataList_Sample_Planer List _50.csv";
    //VDPPage
    String validSku4 = "1207X";
    String titleValue = "Vision";
    String firstNameValue = "John";
    String lastNameValue = "Malkovich";
    String qtyInputValue = "0";
    //CartPage
    //Checkout
    String cardData = "John Malkovich";
    String masterCardNumber = "2222420000001113";
    String masterCardCvv = "123";




    //-----------------------------------Test Setup-----------------------------------
    @BeforeMethod
    public void setupTest() {
        //Create a new FireFoxDriver
        /*System.setProperty("webdriver.gecko.driver", "C:\\Users\\viktor.mykulych\\Desktop\\Vision\\Automation\\geckodriver.exe");
        driver = new FirefoxDriver();*/
        //Create a new ChromeDriver
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\viktor.mykulych\\Desktop\\Vision\\Automation\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //launch Browser and direct it to the test URL
        driver.get(pcaURL);
        driver.manage().window().maximize();


    }


    //-----------------------------------Tests-----------------------------------
    @Test //Login and verification of header elements
    public void PCATest1() throws InterruptedException {

        loginPage = new LoginPage(driver);

        //Login negative scenario with incorrect password - expected result: user is not logged in

        loginPage.loginError(userName, incorrectPassword);
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

    }

    @Test //Verification of Home Page header elements
    public void PCATest2() throws InterruptedException {

        loginPage = new LoginPage(driver);
        homePageElements = new HomePageElements(driver);


        //Login
        loginPage.loginSuccessful(userName, password);

        //Verify Home Page Header Elements (Search, Cart, Catalog, User's Account, Admin, Logout)
        homePageElements.HomepageHeader(validSku2);

    }

    @Test //Verification of Home Page body elements
    public void PCATest3() throws InterruptedException {

        loginPage = new LoginPage(driver);
        homePageElements = new HomePageElements(driver);

        //Login again
        loginPage.loginSuccessful(userName, password);

        //Verify presence of Catalog body elements, tiles
        homePageElements.HomePageCatalogTiles();

    }

    @Test //Verification of Home Page Catalog drop-down elements
    public void PCATest4() throws InterruptedException {

        loginPage = new LoginPage(driver);
        homePageElements = new HomePageElements(driver);


        //Login
        loginPage.loginSuccessful(userName, password);

        //Check all Catalog widget categories, names, click and return back
        homePageElements.CatalogWidgetElements();

//        Check Catalog Widget search functionality, run through categories and if category text coincides with the
//        stated one, perform click
        homePageElements.CatalogWidgetSearch(validSku3);


    }

    @Test //Account page
    public void PCATest5() throws InterruptedException, AWTException {

        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);


        //Login
        loginPage.loginSuccessful(userName, password);

        //Verify User's Account elements size, check they are correct. Approval Requests visible only for Admins
        accountPage.AccountPageElements(myApprovalsUrl);

        //Verify Orders category
        accountPage.MyOrders();

        //Find Address Book, click on it, verify page title
        accountPage.MyAddressBook();
        Assert.assertEquals("My AddressBook", driver.getTitle());

        //Find Data List, click on it, verify page title, upload CSV file
        accountPage.openMyDataList();
        Assert.assertEquals("My Data List", driver.getTitle());

        //Check Data List uploading
        accountPage.uploadDataList(dataListFilePath);

        //Find Approval Requests, click on it, verify page title is correct
        accountPage.approvalRequests(myApprovalsUrl);
        Assert.assertEquals("AbleCommerce | Approval Requests", driver.getTitle());

        //Go to the previous page
        accountPage.navigateBack();

    }

    @Test //Find VDP item, customize and add to the cart
    public void PCATest6() throws InterruptedException {
        loginPage = new LoginPage(driver);
        vdpPage = new VDPPage(driver);

        //Login
        loginPage.loginSuccessful(userName, password);

        //Search for a VDP item, open its PDP and verify the page is correct
        vdpPage.VDPItemSearch(validSku4);
        //Verify the VDP page is opened and correct
        Assert.assertEquals(driver.getTitle(), "PCA Business Cards - Expedited");
        //Print page's title
        System.out.println("Page Title is: " + driver.getTitle());

        //Customize the item and perform the following verifications:
        //Plant drop-down, necessary fields are pre-populated, required fields filling
        vdpPage.ItemCustomization(firstNameValue, lastNameValue, titleValue);

        //Verify Preview and Download PDF functionality
        //Find and hover over preview and download pdf buttons
        //Get button color in hover state and compare
        //Get preview button color, compare with actual result
        //Find Background drop-down get size
        //Select the las background element
        //Find reference field element, verify it is not empty
        //Increase, decrease qty, set defined value
        //Get the defined qty value and print
        vdpPage.hoverOver();

        //Check Preview button color in on-hover over state
        vdpPage.getPreviewBtnColor();
        Assert.assertEquals("rgba(255, 255, 255, 1)", vdpPage.getPreviewBtnColor());
        System.out.println("RGB Color:" + vdpPage.getPreviewBtnColor());

        //Check Download PDF buttons color in on-hover state
        vdpPage.getDwnlBtnColor();
        Assert.assertEquals("rgba(255, 255, 255, 1)", vdpPage.getDwnlBtnColor());
        System.out.println("RGB Color:" + vdpPage.getDwnlBtnColor());

        //Check item background option
        vdpPage.getItemBackground();

        //Verify item reference id is not null
        vdpPage.getReferenceId();
        Assert.assertNotNull(vdpPage.getReferenceId());
        System.out.println("Reference ID:" + vdpPage.getReferenceId());

        //Verify item qty changing functionality
        vdpPage.changeQuantity(qtyInputValue);

        //Get qty value on the item detail page
        vdpPage.getQtyValue();

        //Verification Preview / Download PDF functionality, adding item to cart
        vdpPage.rendering();

    }

    @Test //Verify Cart page
    public void PCATest7() throws InterruptedException, AWTException {
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);


        //Login
        loginPage.loginSuccessful(userName, password);

        //Open cart page, check page is correct
        cartPage.openCart();

        //Check the page is correct, get title and make assertion
        cartPage.getPageTitle();
        System.out.println("The Cart Page Title is: " + cartPage.getPageTitle());
        Assert.assertEquals(cartPage.getPageTitle(), "Basket", "Title assertion is failed!");

        //Open Item PDP from Cart
        cartPage.getToPdpFromCart();

        //Verify previously set up fields are pre-populated
        cartPage.getPlantName();

        System.out.println("Plant Name is:" + cartPage.getPlantName());
        Assert.assertNotNull(cartPage.getPlantName());

        //Get Item base price from PDP to compare with the one on Cart page
        cartPage.getItemBasePrice();
        System.out.println("PCA Business Cards - Expedited price is: " + cartPage.getItemBasePrice());
        String basePrice = cartPage.getItemBasePrice();

        //Verify Add To Cart button is now changed to Update Cart
        cartPage.updateCart();
        System.out.println(cartPage.updateCart());
        Assert.assertEquals("UpdateCart", cartPage.updateCart());

        //Verify Preview functionality after item update
        cartPage.getUpdatedItemPreview();

        //Confirm the preview was successful, check success message
        cartPage.getSuccessMessage();
        Assert.assertEquals("Your preview is ready. Hover over image to zoom.", cartPage.getSuccessMessage());
        Thread.sleep(2000);

        //Check item qty on PDP to compare with the one on Cart page
        cartPage.getItemQtyOnPdp();
        String pdpItemQuantity = cartPage.getItemQtyOnPdp();

        //Check user can move back to cart after successful cart update
        cartPage.clickUpdateCart();

        //Check Item base price in cart
        cartPage.getItemPriceInCartUsingRegEx();
//        itemPriceInCart.getItemPriceInCart();
//        System.out.println("Item price in cart without $: " + itemPriceInCart.getItemPriceInCart());
        System.out.println("Item price in cart without $: " + cartPage.getItemPriceInCartUsingRegEx());

        //Verify the Base prices on PDP and in the Cart are the same
        Assert.assertEquals(basePrice, cartPage.getItemPriceInCart());

        //Check item qty on the cart page to compare with the one on PDP
        cartPage.getItemQtyOnCartPage();

        //Compare PDP and Cart qty - expected result: Qty is the same
        Assert.assertEquals(pdpItemQuantity, cartPage.getItemQtyOnCartPage());

        //Get cart final qty
        cartPage.getCartQty();
        System.out.println("Quantity in cart value: " + cartPage.getCartQty());

        //Verify total value in cart
        cartPage.getTotalValueInCart();
        //Convert String totalIndexOff to double value
        double totIndOff = Double.parseDouble(cartPage.getTotalValueInCart());
        //Convert String quantity to double value
        double qtyInCrt = Double.parseDouble(cartPage.getCartQty());
        //Convert String priceInCartIndexOff to double value
        double prcIndOff = Double.parseDouble(cartPage.getItemPriceInCart());

        //Multiply Price by Quantity
        double total = prcIndOff * qtyInCrt;
        System.out.println("Total is: " + total);
        //Compare Price * Quantity result and total
        Assert.assertEquals(totIndOff, total);

        //Search for another item
        cartPage.searchAnotherItem(validSku3);

        //Add another item to the cart
        cartPage.addToCart();

        //Verify View cart functionality
        cartPage.clickViewCartButton();

        //Get second item total
        cartPage.getSecondItemTotal();
        System.out.println("Second item price without $: " + cartPage.getSecondItemTotal());

        //Get subtotal value
        cartPage.getSubtotalValue();

        //Add 1st and 2nd items totals and compare with subtotal > the result should be the same
        double overAllTotal = totIndOff + cartPage.getSecondItemTotal();
        System.out.println("1st and 2nd items total sum is: " + overAllTotal);
        Assert.assertEquals(overAllTotal, cartPage.getSubtotalValue());
        Thread.sleep(3000);

        //Verify user can delete item from cart - delete the second item
        cartPage.deleteSecondItem();

        //Find Continue Shopping Button verify it works correctly (redirects to the Home Page)
        cartPage.verifyContinueShopping();

        //Return to shopping cart page
        cartPage.navigateBack();

        //Verify upper checkout button is clickable
        cartPage.clickUpperCheckoutBtn();

        //Verify once upper checkout is clicked, user is redirected to correct page "Checkout - Edit Billing Address"
        cartPage.getUserProfileTitle();
        //Assert page is the one as expected
        Assert.assertEquals(cartPage.getUserProfileTitle(), "Checkout - Edit Billing Address");
        Thread.sleep(2000);

        //Return to shopping cart page
        cartPage.navigateBack();
        Thread.sleep(3000);

        //Verify lower checkout button is clickable
        cartPage.clickLowerCheckoutBtn();

        //Verify once upper checkout is clicked, user is redirected to correct page "Checkout - Edit Billing Address"
        cartPage.getUserProfileTitle();
        //Assert page is the one as expected
        Assert.assertEquals(cartPage.getUserProfileTitle(), "Checkout - Edit Billing Address");
        Thread.sleep(2000);

        //Return to shopping cart page
        cartPage.navigateBack();
        Thread.sleep(3000);

        //Verify user can clean cart using "Clean Cart" button
        cartPage.verifyCleanCart();

        //Verify the cart is empty
        cartPage.getCartEmptyMessage();
        Assert.assertEquals(cartPage.getCartEmptyMessage(), "Your shopping cart is empty.");
        Thread.sleep(3000);

        //Add item to the cart again to bring the cart to initial state
        cartPage.getItemToCart(validSku2, firstNameValue, lastNameValue, titleValue);

    }

    @Test //Verify Checkout (user profile, shipping, payment)
    public void PCATest8() throws InterruptedException, AWTException {

        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        //Login
        loginPage.loginSuccessful(userName, password);

        //Open cart page, check page is correct
        cartPage.openCart();

        //Open cart page, verify title, get subtotal, convert to boolean
        checkoutPage.getCartSubtotal();
        //Convert String cartSubIndexOff to double
        double dCartSubIndexOff = Double.parseDouble(checkoutPage.getCartSubtotal());

        //Verify user is able to proceed to Checkout page
        checkoutPage.proceedToCheckout();

        //Verify user is on the right page
        checkoutPage.getPageTitle();
        Assert.assertEquals(checkoutPage.getPageTitle(), "Checkout - Edit Billing Address");

        //Check User Profile page
        checkoutPage.verifyUserProfileAddress();

        //Check Shipping Address widget functionality
        checkoutPage.verifyShippingAddressWidget();

        //Check Order Summary widget functionality
        checkoutPage.verifyOrderSummaryWidget();

        //Check Ship to a different address functionality
        checkoutPage.verifyShipToDifferentAddress();

        //Check Shipping Page
        checkoutPage.verifyShippingPage();

        //Move to Payment page and check it is correct
        checkoutPage.getPaymentPageTitle();
        Assert.assertEquals(checkoutPage.getPaymentPageTitle(), "Checkout - Confirm and Pay");

        //Get order summary list
        checkoutPage.verifyOrderSummaryList();

        //Verify Payment subtotal is correct and the same as in the cart
        checkoutPage.getPaymentSubtotal();
        //Convert String cartSubIndexOff to double
        double dPaymentSubIndexOff = Double.parseDouble(checkoutPage.getPaymentSubtotal());
        //Assert two booleans cart subtotal equals payment subtotal
        Assert.assertEquals(dCartSubIndexOff, dPaymentSubIndexOff);

        //Verify Complete Order functionality, CC information
        //Make sure order was not complete due to CC invalidity
        checkoutPage.verifyCompleteOrder(cardData, masterCardNumber, masterCardCvv);

    }

    @Test //Verify Admin main pages)
    public void PCATest9() throws InterruptedException, AWTException {

        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Login
        loginPage.loginSuccessful(userName, password);

        //Verify user with admin rights can navigate to admin side
        adminPage.openAdminPage();

        //Verify admin page is correct - verify page title
        adminPage.getAdminPageTitle();
        Assert.assertEquals(adminPage.getAdminPageTitle(), "AbleCommerce | Dashboard");

        //Verify Orders page is correct on the admin side - check page title
        adminPage.verifyOrdersPage();
        Assert.assertEquals(adminPage.verifyOrdersPage(), "AbleCommerce | Orders");

        //Verify Users page is correct on the admin side - check page title
        adminPage.verifyUsersPage();
        Assert.assertEquals(adminPage.verifyUsersPage(), "AbleCommerce | Users");

        //Verify Catalog page is correct on the admin side - check page title
        adminPage.verifyCatalogPage();
        Assert.assertEquals(adminPage.verifyCatalogPage(), "AbleCommerce | Manage Catalog");

        //Verify Products page is correct on the admin side - check page title
        adminPage.verifyProductsPage();
        Assert.assertEquals(adminPage.verifyProductsPage(), "AbleCommerce | Manage Products");

        //Verify Storefront Page - it opens in a new tab
        adminPage.getStorefrontPage();

        //Get the list of tabs, switch tabs
        adminPage.getTabsList();

        //Verify user is redirected to the Home page after clicking the Storefront in admin
        adminPage.getStorefrontPageTitle();

        //Close Storefront tab in browser - expected result the tab is closed
        adminPage.closeTab();

        //Return to the previous tab
        adminPage.switchToPrevTab();

        //Verify Admin header elements and click logout
        adminPage.verifyLogout();

    }

    @Test //Test randomizers)
    public void PCATest10() throws InterruptedException, AWTException {

        randomPassword = new RandomData(driver);

        WebDriverWait wait = new WebDriverWait(driver, 15);
        System.out.println(randomPassword.getRandomPassword());



    }


    //-----------------------------------Test TearDown-----------------------------------
    @AfterMethod
    public void teardownTest() {
        //Close browser
        driver.close();

    }

}
