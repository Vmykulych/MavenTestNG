package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
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

//comment the above line and uncomment below line to use Chrome
//import org.openqa.selenium.chrome.ChromeDriver;

/*****************************************************************************
 * Author:
 * Description: It opens PCA login form and performs login action.
 *******************************************************************************/

public class PCAProductionSmokeTest {


    //-----------------------------------Global Variables-----------------------------------
    //Declare a Webdriver variable
    public WebDriver driver;
    //POM
    LoginPage loginPage;
    LoginPage unsuccessfulLogin;
    LoginPage emptyLoginFields;
    LoginPage loginErrorMessage;
    HomePageElements homePageHeaderElements;
    HomePageElements close;
    HomePageElements openBrowser;
    HomePageElements homePageCatalog;
    HomePageElements catalogWidget;
    HomePageElements catalogWidgetSearch;
    AccountPage accountOptions;
    AccountPage myOrdersPage;
    AccountPage addressBookPage;
    AccountPage dataListPage;
    AccountPage dataListUploading;
    AccountPage approvalRequestsPage;
    AccountPage gettingToPreviousPage;
    VDPPage vdpItem;
    VDPPage vdpItemCustomization;
    VDPPage hoverOverPrwDwld;
    VDPPage previewBtnColor;
    VDPPage previewAddToCart;
    VDPPage downloadBtnColor;
    VDPPage backgroundOption;
    VDPPage referenceIdValue;
    VDPPage itemQuantity;
    VDPPage quantityValue;
    CartPage navigateToCart;
    CartPage verifyCartPageTitle;
    CartPage cartUpdateVerification;
    CartPage productDetailPage;
    CartPage checkPlantName;
    CartPage checkItemBasePrice;
    CartPage updateCartButton;
    CartPage updateItemPreview;
    CartPage previewSuccessful;
    CartPage pdpItemQty;
    CartPage moveToCartAfterUpdate;
    CartPage itemPriceInCart;
    CartPage cartItemQty;
    CartPage finalQty;
    CartPage totalValueInCart;
    CartPage anotherItemToCart;
    CartPage addAnotherItemToCart;
    CartPage viewCartButton;
    CartPage secondItemTotal;
    CartPage subtotalValue;
    CartPage deleteItemFromCart;
    CartPage continueShopping;
    CartPage returnToCart;
    CartPage upperCheckoutBtn;
    CartPage userProfilePage;
    CartPage lowerCheckoutBtn;
    CartPage cleanCartFunctionality;
    CartPage cartEmpty;
    CartPage cartContainsItem;
    CheckoutPage cartSubtotal;
    CheckoutPage navigateToCheckout;
    CheckoutPage verifyCheckoutPageTitle;
    CheckoutPage userProfileAddress;
    CheckoutPage shippingAddressWidget;
    CheckoutPage orderSummaryWidget;
    CheckoutPage shipToDifferentAddress;
    CheckoutPage shippingPage;
    CheckoutPage verifyPaymentPage;
    CheckoutPage orderSummaryList;
    CheckoutPage paymentSubtotal;
    CheckoutPage completeOrder;
    AdminPage navigateToAdminPage;
    AdminPage adminPageTitle;
    AdminPage ordersPage;
    AdminPage usersPage;
    AdminPage catalogPage;
    AdminPage productsPage;
    AdminPage clickStorefrontPage;
    AdminPage switchTabsList;
    AdminPage storefrontPageTitle;
    AdminPage closeStorefrontTab;
    AdminPage moveToPrevTab;
    AdminPage logout;


    //Declare test Strings
    public String pcaURL = "https://pcafulfillment.iprint.visionps.com/login?useACLogin=true";

    //LoginPage
    String login = "vmykulych@visionps.com";
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

        unsuccessfulLogin = new LoginPage(driver);
        emptyLoginFields = new LoginPage(driver);
        loginErrorMessage = new LoginPage(driver);
        loginPage = new LoginPage(driver);

        //Login negative scenario with incorrect password - expected result: user is not logged in
        unsuccessfulLogin.loginError(login, incorrectPassword);
        Assert.assertEquals(unsuccessfulLogin.getPageTitle(), "Login");

        //Verify the error message after unsuccessful login is correct
        Assert.assertEquals(loginErrorMessage.getLoginErrorMsg(), "The sign in information you provided was incorrect. You have 5 tries remaining.");

        //Clear all Login fields
        emptyLoginFields.clearLoginFields();

        //Login - positive scenario - expected result: user is logged in and landed on Home Page
        loginPage.loginSuccessful(login, password);

        //Verify page title - expected "Home Page"
        //Assert page title is correct
        Assert.assertEquals(loginPage.getPageTitle(), "Home Page", "Title assertion is failed!");

    }

    @Test //Verification of Home Page header elements
    public void PCATest2() throws InterruptedException {

        loginPage = new LoginPage(driver);
        homePageHeaderElements = new HomePageElements(driver);


        //Login
        loginPage.loginSuccessful(login, password);

        //Verify Home Page Header Elements (Search, Cart, Catalog, User's Account, Admin, Logout)
        homePageHeaderElements.HomepageHeader(validSku2);

    }

    @Test //Verification of Home Page body elements
    public void PCATest3() throws InterruptedException {

        loginPage = new LoginPage(driver);
        homePageCatalog = new HomePageElements(driver);

        //Login again
        loginPage.loginSuccessful(login, password);

        //Verify presence of Catalog body elements, tiles
        homePageCatalog.HomePageCatalogTiles();

    }

    @Test //Verification of Home Page Catalog drop-down elements
    public void PCATest4() throws InterruptedException {

        loginPage = new LoginPage(driver);
        catalogWidget = new HomePageElements(driver);
        catalogWidgetSearch = new HomePageElements(driver);

        //Login
        loginPage.loginSuccessful(login, password);

        //Check all Catalog widget categories, names, click and return back
        catalogWidget.CatalogWidgetElements();

        /*Check Catalog Widget search functionality, run through categories and if category text coincides with the
        stated one, perform click*/
        catalogWidgetSearch.CatalogWidgetSearch(validSku3);


    }

    @Test //Account page
    public void PCATest5() throws InterruptedException, AWTException {

        loginPage = new LoginPage(driver);
        accountOptions = new AccountPage(driver);
        myOrdersPage = new AccountPage(driver);
        addressBookPage = new AccountPage(driver);
        dataListPage = new AccountPage(driver);
        approvalRequestsPage = new AccountPage(driver);
        dataListUploading = new AccountPage(driver);
        gettingToPreviousPage = new AccountPage(driver);


        //Login
        loginPage.loginSuccessful(login, password);

        //Verify User's Account elements size, check they are correct. Approval Requests visible only for Admins
        accountOptions.AccountPageElements(myApprovalsUrl);

        //Verify Orders category
        myOrdersPage.MyOrders();

        //Find Address Book, click on it, verify page title
        addressBookPage.MyAddressBook();
        Assert.assertEquals("My AddressBook", driver.getTitle());

        //Find Data List, click on it, verify page title, upload CSV file
        dataListPage.openMyDataList();
        Assert.assertEquals("My Data List", driver.getTitle());

        //Check Data List uploading
        dataListUploading.uploadDataList(dataListFilePath);

        //Find Approval Requests, click on it, verify page title is correct
        approvalRequestsPage.approvalRequests(myApprovalsUrl);
        Assert.assertEquals("AbleCommerce | Approval Requests", driver.getTitle());

        //Go to the previous page
        gettingToPreviousPage.navigateBack();

    }

    @Test //Find VDP item, customize and add to the cart
    public void PCATest6() throws InterruptedException {
        loginPage = new LoginPage(driver);
        vdpItem = new VDPPage(driver);
        vdpItemCustomization = new VDPPage(driver);
        hoverOverPrwDwld = new VDPPage(driver);
        previewAddToCart = new VDPPage(driver);
        previewBtnColor = new VDPPage(driver);
        downloadBtnColor = new VDPPage(driver);
        backgroundOption = new VDPPage(driver);
        referenceIdValue = new VDPPage(driver);
        itemQuantity = new VDPPage(driver);
        quantityValue = new VDPPage(driver);

        //Login
        loginPage.loginSuccessful(login, password);

        //Search for a VDP item, open its PDP and verify the page is correct
        vdpItem.VDPItemSearch(validSku4);
        //Verify the VDP page is opened and correct
        Assert.assertEquals(driver.getTitle(), "PCA Business Cards - Expedited");
        //Print page's title
        System.out.println("Page Title is: " + driver.getTitle());

        //Customize the item and perform the following verifications:
        //Plant drop-down, necessary fields are pre-populated, required fields filling
        vdpItemCustomization.ItemCustomization(firstNameValue, lastNameValue, titleValue);

        //Verify Preview and Download PDF functionality
        //Find and hover over preview and download pdf buttons
        //Get button color in hover state and compare
        //Get preview button color, compare with actual result
        //Find Background drop-down get size
        //Select the las background element
        //Find reference field element, verify it is not empty
        //Increase, decrease qty, set defined value
        //Get the defined qty value and print
        hoverOverPrwDwld.hoverOver();

        //Check Preview button color in on-hover over state
        previewBtnColor.getPreviewBtnColor();
        Assert.assertEquals("rgba(255, 255, 255, 1)", previewBtnColor.getPreviewBtnColor());
        System.out.println("RGB Color:" + previewBtnColor.getPreviewBtnColor());

        //Check Download PDF buttons color in on-hover state
        downloadBtnColor.getDwnlBtnColor();
        Assert.assertEquals("rgba(255, 255, 255, 1)", downloadBtnColor.getDwnlBtnColor());
        System.out.println("RGB Color:" + downloadBtnColor.getDwnlBtnColor());

        //Check item background option
        backgroundOption.getItemBackground();

        //Verify item reference id is not null
        referenceIdValue.getReferenceId();
        Assert.assertNotNull(referenceIdValue.getReferenceId());
        System.out.println("Reference ID:" + referenceIdValue.getReferenceId());

        //Verify item qty changing functionality
        itemQuantity.changeQuantity(qtyInputValue);

        //Get qty value on the item detail page
        quantityValue.getQtyValue();

        //Verification Preview / Download PDF functionality, adding item to cart
        previewAddToCart.rendering();

    }

    @Test //Verify Cart page
    public void PCATest7() throws InterruptedException, AWTException {
        loginPage = new LoginPage(driver);
        navigateToCart = new CartPage(driver);
        verifyCartPageTitle = new CartPage(driver);
        cartUpdateVerification = new CartPage(driver);
        productDetailPage = new CartPage(driver);
        checkPlantName = new CartPage(driver);
        checkItemBasePrice = new CartPage(driver);
        updateCartButton = new CartPage(driver);
        updateItemPreview = new CartPage(driver);
        previewSuccessful = new CartPage(driver);
        pdpItemQty = new CartPage(driver);
        moveToCartAfterUpdate = new CartPage(driver);
        itemPriceInCart = new CartPage(driver);
        cartItemQty = new CartPage(driver);
        finalQty = new CartPage(driver);
        totalValueInCart = new CartPage(driver);
        anotherItemToCart = new CartPage(driver);
        addAnotherItemToCart = new CartPage(driver);
        viewCartButton = new CartPage(driver);
        secondItemTotal = new CartPage(driver);
        subtotalValue = new CartPage(driver);
        deleteItemFromCart = new CartPage(driver);
        continueShopping = new CartPage(driver);
        returnToCart = new CartPage(driver);
        upperCheckoutBtn = new CartPage(driver);
        userProfilePage = new CartPage(driver);
        lowerCheckoutBtn = new CartPage(driver);
        cleanCartFunctionality = new CartPage(driver);
        cartEmpty = new CartPage(driver);
        cartContainsItem = new CartPage(driver);

        //Login
        loginPage.loginSuccessful(login, password);

        //Open cart page, check page is correct
        navigateToCart.openCart();

        //Check the page is correct, get title and make assertion
        verifyCartPageTitle.getPageTitle();
        System.out.println("The Cart Page Title is: " + verifyCartPageTitle.getPageTitle());
        Assert.assertEquals(verifyCartPageTitle.getPageTitle(), "Basket", "Title assertion is failed!");

        //Open Item PDP from Cart
        productDetailPage.getToPdpFromCart();

        //Verify previously set up fields are pre-populated
        checkPlantName.getPlantName();

        System.out.println("Plant Name is:" + checkPlantName.getPlantName());
        Assert.assertNotNull(checkPlantName.getPlantName());

        //Get Item base price from PDP to compare with the one on Cart page
        checkItemBasePrice.getItemBasePrice();
        System.out.println("PCA Business Cards - Expedited price is: " + checkItemBasePrice.getItemBasePrice());
        String basePrice = checkItemBasePrice.getItemBasePrice();

        //Verify Add To Cart button is now changed to Update Cart
        updateCartButton.updateCart();
        System.out.println(updateCartButton.updateCart());
        Assert.assertEquals("UpdateCart", updateCartButton.updateCart());

        //Verify Preview functionality after item update
        updateItemPreview.getUpdatedItemPreview();

        //Confirm the preview was successful, check success message
        previewSuccessful.getSuccessMessage();
        Assert.assertEquals("Your preview is ready. Hover over image to zoom.", previewSuccessful.getSuccessMessage());
        Thread.sleep(2000);

        //Check item qty on PDP to compare with the one on Cart page
        pdpItemQty.getItemQtyOnPdp();
        String pdpItemQuantity = pdpItemQty.getItemQtyOnPdp();

        //Check user can move back to cart after successful cart update
        moveToCartAfterUpdate.clickUpdateCart();

        //Check Item base price in cart
        itemPriceInCart.getItemPriceInCart();
        System.out.println("Item price in cart without $: " + itemPriceInCart.getItemPriceInCart());

        //Verify the Base prices on PDP and in the Cart are the same
        Assert.assertEquals(basePrice, itemPriceInCart.getItemPriceInCart());

        //Check item qty on the cart page to compare with the one on PDP
        cartItemQty.getItemQtyOnCartPage();

        //Compare PDP and Cart qty - expected result: Qty is the same
        Assert.assertEquals(pdpItemQuantity, cartItemQty.getItemQtyOnCartPage());

        //Get cart final qty
        finalQty.getCartQty();
        System.out.println("Quantity in cart value: " + finalQty.getCartQty());

        //Verify total value in cart
        totalValueInCart.getTotalValueInCart();
        //Convert String totalIndexOff to double value
        double totIndOff = Double.parseDouble(totalValueInCart.getTotalValueInCart());
        //Convert String quantity to double value
        double qtyInCrt = Double.parseDouble(finalQty.getCartQty());
        //Convert String priceInCartIndexOff to double value
        double prcIndOff = Double.parseDouble(itemPriceInCart.getItemPriceInCart());

        //Multiply Price by Quantity
        double total = prcIndOff * qtyInCrt;
        System.out.println("Total is: " + total);
        //Compare Price * Quantity result and total
        Assert.assertEquals(totIndOff, total);

        //Search for another item
        anotherItemToCart.searchAnotherItem(validSku3);

        //Add another item to the cart
        addAnotherItemToCart.addToCart();

        //Verify View cart functionality
        viewCartButton.clickViewCartButton();

        //Get second item total
        secondItemTotal.getSecondItemTotal();
        System.out.println("Second item price without $: " + secondItemTotal.getSecondItemTotal());

        //Get subtotal value
        subtotalValue.getSubtotalValue();

        //Add 1st and 2nd items totals and compare with subtotal > the result should be the same
        double overAllTotal = totIndOff + secondItemTotal.getSecondItemTotal();
        System.out.println("1st and 2nd items total sum is: " + overAllTotal);
        Assert.assertEquals(overAllTotal, subtotalValue.getSubtotalValue());
        Thread.sleep(3000);

        //Verify user can delete item from cart - delete the second item
        deleteItemFromCart.deleteSecondItem();

        //Find Continue Shopping Button verify it works correctly (redirects to the Home Page)
        continueShopping.verifyContinueShopping();

        //Return to shopping cart page
        returnToCart.navigateBack();

        //Verify upper checkout button is clickable
        upperCheckoutBtn.clickUpperCheckoutBtn();

        //Verify once upper checkout is clicked, user is redirected to correct page "Checkout - Edit Billing Address"
        userProfilePage.getUserProfileTitle();
        //Assert page is the one as expected
        Assert.assertEquals(userProfilePage.getUserProfileTitle(), "Checkout - Edit Billing Address");
        Thread.sleep(2000);

        //Return to shopping cart page
        returnToCart.navigateBack();
        Thread.sleep(3000);

        //Verify lower checkout button is clickable
        lowerCheckoutBtn.clickLowerCheckoutBtn();

        //Verify once upper checkout is clicked, user is redirected to correct page "Checkout - Edit Billing Address"
        userProfilePage.getUserProfileTitle();
        //Assert page is the one as expected
        Assert.assertEquals(userProfilePage.getUserProfileTitle(), "Checkout - Edit Billing Address");
        Thread.sleep(2000);

        //Return to shopping cart page
        returnToCart.navigateBack();
        Thread.sleep(3000);

        //Verify user can clean cart using "Clean Cart" button
        cleanCartFunctionality.verifyCleanCart();

        //Verify the cart is empty
        cartEmpty.getCartEmptyMessage();
        Assert.assertEquals(cartEmpty.getCartEmptyMessage(), "Your shopping cart is empty.");
        Thread.sleep(3000);

        //Add item to the cart again to bring the cart to initial state
        cartContainsItem.getItemToCart(validSku2, firstNameValue, lastNameValue, titleValue);

    }

    @Test //Verify Checkout (user profile, shipping, payment)
    public void PCATest8() throws InterruptedException, AWTException {

        loginPage = new LoginPage(driver);
        navigateToCart = new CartPage(driver);
        cartSubtotal = new CheckoutPage(driver);
        navigateToCheckout = new CheckoutPage(driver);
        verifyCheckoutPageTitle = new CheckoutPage(driver);
        shippingAddressWidget = new CheckoutPage(driver);
        orderSummaryWidget = new CheckoutPage(driver);
        shipToDifferentAddress = new CheckoutPage(driver);
        orderSummaryList = new CheckoutPage(driver);
        paymentSubtotal = new CheckoutPage(driver);
        userProfileAddress = new CheckoutPage(driver);
        shippingPage = new CheckoutPage(driver);
        verifyPaymentPage = new CheckoutPage(driver);
        completeOrder = new CheckoutPage(driver);

        //Login
        loginPage.loginSuccessful(login, password);

        //Open cart page, check page is correct
        navigateToCart.openCart();

        //Open cart page, verify title, get subtotal, convert to boolean
        cartSubtotal.getCartSubtotal();
        //Convert String cartSubIndexOff to double
        double dCartSubIndexOff = Double.parseDouble(cartSubtotal.getCartSubtotal());

        //Verify user is able to proceed to Checkout page
        navigateToCheckout.proceedToCheckout();

        //Verify user is on the right page
        verifyCheckoutPageTitle.getPageTitle();
        Assert.assertEquals(verifyCheckoutPageTitle.getPageTitle(), "Checkout - Edit Billing Address");

        //Check User Profile page
        userProfileAddress.verifyUserProfileAddress();

        //Check Shipping Address widget functionality
        shippingAddressWidget.verifyShippingAddressWidget();

        //Check Order Summary widget functionality
        orderSummaryWidget.verifyOrderSummaryWidget();

        //Check Ship to a different address functionality
        shipToDifferentAddress.verifyShipToDifferentAddress();

        //Check Shipping Page
        shippingPage.verifyShippingPage();

        //Move to Payment page and check it is correct
        verifyPaymentPage.getPaymentPageTitle();
        Assert.assertEquals(verifyPaymentPage.getPaymentPageTitle(), "Checkout - Confirm and Pay");

        //Get order summary list
        orderSummaryList.verifyOrderSummaryList();

        //Verify Payment subtotal is correct and the same as in the cart
        paymentSubtotal.getPaymentSubtotal();
        //Convert String cartSubIndexOff to double
        double dPaymentSubIndexOff = Double.parseDouble(paymentSubtotal.getPaymentSubtotal());
        //Assert two booleans cart subtotal equals payment subtotal
        Assert.assertEquals(dCartSubIndexOff, dPaymentSubIndexOff);

        //Verify Complete Order functionality, CC information
        //Make sure order was not complete due to CC invalidity
        completeOrder.verifyCompleteOrder(cardData, masterCardNumber, masterCardCvv);

    }

    @Test //Verify Admin main pages)
    public void PCATest9() throws InterruptedException, AWTException {

        loginPage = new LoginPage(driver);
        navigateToAdminPage = new AdminPage(driver);
        adminPageTitle = new AdminPage(driver);
        ordersPage = new AdminPage(driver);
        usersPage = new AdminPage(driver);
        catalogPage = new AdminPage(driver);
        productsPage = new AdminPage(driver);
        clickStorefrontPage = new AdminPage(driver);
        switchTabsList = new AdminPage(driver);
        storefrontPageTitle = new AdminPage(driver);
        closeStorefrontTab = new AdminPage(driver);
        moveToPrevTab = new AdminPage(driver);
        logout = new AdminPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Login
        loginPage.loginSuccessful(login, password);

        //Verify user with admin rights can navigate to admin side
        navigateToAdminPage.openAdminPage();

        //Verify admin page is correct - verify page title
        adminPageTitle.getAdminPageTitle();
        Assert.assertEquals(adminPageTitle.getAdminPageTitle(), "AbleCommerce | Dashboard");

        //Verify Orders page is correct on the admin side - check page title
        ordersPage.verifyOrdersPage();
        Assert.assertEquals(ordersPage.verifyOrdersPage(), "AbleCommerce | Orders");

        //Verify Users page is correct on the admin side - check page title
        usersPage.verifyUsersPage();
        Assert.assertEquals(usersPage.verifyUsersPage(), "AbleCommerce | Users");

        //Verify Catalog page is correct on the admin side - check page title
        catalogPage.verifyCatalogPage();
        Assert.assertEquals(catalogPage.verifyCatalogPage(), "AbleCommerce | Manage Catalog");

        //Verify Products page is correct on the admin side - check page title
        productsPage.verifyProductsPage();
        Assert.assertEquals(productsPage.verifyProductsPage(), "AbleCommerce | Manage Products");

        //Verify Storefront Page - it opens in a new tab
        clickStorefrontPage.getStorefrontPage();

        //Get the list of tabs, switch tabs
        switchTabsList.getTabsList();

        //Verify user is redirected to the Home page after clicking the Storefront in admin
        storefrontPageTitle.getStorefrontPageTitle();

        //Close Storefront tab in browser - expected result the tab is closed
        closeStorefrontTab.closeTab();

        //Return to the previous tab
        moveToPrevTab.switchToPrevTab();

        //Verify Admin header elements and click logout
        logout.verifyLogout();

    }

    //-----------------------------------Test TearDown-----------------------------------
    @AfterMethod
    public void teardownTest() {
        //Close browser
        driver.close();

    }

}
