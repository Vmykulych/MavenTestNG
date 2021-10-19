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

import java.util.List;

public class VDPPage {
    private final WebDriver driver;

    public VDPPage(WebDriver driver) {
        this.driver = driver;
    }

    //Web Elements
    By headerSearch = By.xpath("//a[@id='search-collapser']//i[1]");
    By headerSrchInput = By.xpath("//div[@id='header-store-search']//input[@id='Keywords']");
    By itemSku4 = By.xpath("//div[@class='product-overlay']//a[1]");
    By selectPlant = By.xpath("//select[@id='dynamicField_plantname']");
    By seletcPlantOptions = By.xpath("//select[@id='dynamicField_plantname']//option");
    By firstName = By.xpath("//input[@id='dynamicField_firstname']");
    By lastName = By.xpath("//input[@id='dynamicField_lastname']");
    By titleName = By.xpath("//input[@id='dynamicField_jobtitle']");
    By previewBtn = By.id("preview-btn");
    By downloadPdfBtn = By.xpath("//button[@id='download-pdf-btn']");
    By backgroundOptions = By.xpath("//select[@id='dynamicField_background']//option");
    By backgroundElementsSelect = By.xpath("//select[@id='dynamicField_background']");
    By referenceId = By.xpath("//input[@id='INPUT_FIELD_9']");
    By qtyMinus = By.xpath("//a[@id='modal-qty-minus']");
    By qtyPlus = By.xpath("//a[@id='modal-qty-plus']");
    By qtyInput = By.xpath("//input[@id='modal-qty']");
    By imageView = By.xpath("//div[@class='item active']");
    By previewSuccessMsg = By.xpath("//div[text()='Your preview is ready. Hover over image to zoom.']");
    By addToCartBtn = By.xpath("//button[@class='btn btn-primary btn-block']");

    public void VDPItemSearch(String validSku4) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Wait until header is loaded
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(headerSearch));

        //Click header search and find the VDP item 1207X
        driver.findElement(headerSearch).click();
        driver.findElement(headerSrchInput).sendKeys(validSku4 + Keys.ENTER);

        //Wait until the VDP item is present, click to open the PDP
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(itemSku4));
        driver.findElement(itemSku4).click();
        Thread.sleep(2000);

    }

    public void ItemCustomization(String firstNameValue, String lastNameValue, String titleValue) throws InterruptedException {

        //Find the number of Select Plant options
        List<WebElement> selectPlantElements = driver.findElements(seletcPlantOptions);
        int s = selectPlantElements.size();
        System.out.println("Number of Select Plant elements:" + s);

        //Find plant drop-down and select "Chicago", index = 27
        driver.findElement(selectPlant);
        Select plantName = new Select(driver.findElement(selectPlant));
        plantName.selectByIndex(27);
        Thread.sleep(2000);

        //Check autofilled placeholders are not empty
        WebElement address = driver.findElement(By.xpath("//input[@id='dynamicField_address']"));
        String addressText = address.getText();

        if (addressText.isEmpty()) {
            System.out.println(addressText);
        } else {
            System.out.println("The address field is empty");
        }

        //Fill required fields: First Name, Last Name, Title
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(firstNameValue);
        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(lastNameValue);
        driver.findElement(titleName).sendKeys(titleValue);
        Thread.sleep(2000);
    }

    public void hoverOver() throws InterruptedException {
        Actions action = new Actions(driver);

        //Find and hover over preview and download pdf buttons
        WebElement preview = driver.findElement(previewBtn);
        action.moveToElement(preview).build().perform();
        Thread.sleep(2000);
    }

    public String getPreviewBtnColor() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        Actions action = new Actions(driver);

        //Get button color in hover state and compare
        String colorOnHover = driver.findElement(previewBtn).getCssValue("colorOnHover").trim();
        System.out.println("RGB Color on Hover:" + colorOnHover);

        WebElement downloadPdf = driver.findElement(downloadPdfBtn);
        action.moveToElement(downloadPdf).build().perform();
        Thread.sleep(2000);

        //Get preview button color, compare with actual result
        String previewBtnColor = driver.findElement(previewBtn).getCssValue("color").trim();
        return previewBtnColor;


    }

    public String getDwnlBtnColor() throws InterruptedException {
        Actions action = new Actions(driver);

        //Get button color in hover state and compare
        String colorOnHover = driver.findElement(previewBtn).getCssValue("colorOnHover").trim();
        System.out.println("RGB Color on Hover:" + colorOnHover);

        WebElement downloadPdf = driver.findElement(downloadPdfBtn);
        action.moveToElement(downloadPdf).build().perform();
        Thread.sleep(2000);

        //Get preview button color, compare with actual result
        String dwldBtnColor = driver.findElement(previewBtn).getCssValue("color").trim();
        return dwldBtnColor;

    }

    public void getItemBackground() throws InterruptedException {

        //Find Item Background drop-down get size
        List<WebElement> backgroundElements = driver.findElements(backgroundOptions);
        int s = backgroundElements.size();
        System.out.println("Number of background elements:" + s);

        //Select the last background element
        driver.findElement(selectPlant);
        Select backgroundName = new Select(driver.findElement(backgroundElementsSelect));
        backgroundName.selectByIndex(s - 1);
        Thread.sleep(2000);

    }

    public String getReferenceId() {

        //Find reference field element, verify it is not empty
        String referenceText = driver.findElement(referenceId).getAttribute("value");
        return referenceText;

    }

    public void changeQuantity(String qtyInputValue) throws InterruptedException {
        Actions action = new Actions(driver);

        //Increase, decrease qty, set defined value
        WebElement qtyIncrease = driver.findElement(qtyPlus);
        action.moveToElement(qtyIncrease).clickAndHold().perform();
        Thread.sleep(2000);
        action.moveToElement(qtyIncrease).release();

        WebElement qtyDecrease = driver.findElement(qtyMinus);
        action.moveToElement(qtyDecrease).clickAndHold().perform();
        Thread.sleep(1000);
        action.moveToElement(qtyDecrease).release();
        Thread.sleep(2000);

        driver.findElement(qtyInput).clear();
        driver.findElement(qtyInput).sendKeys(qtyInputValue);
        Thread.sleep(2000);

    }

    public String getQtyValue() throws InterruptedException {

        //Get the defined qty value and print
        String inputValue = driver.findElement(qtyInput).getAttribute("value");
        System.out.println("Defined Input Value:" + inputValue);
        return inputValue;

    }

    public void rendering() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        WebElement previewButton = driver.findElement(previewBtn);
        previewButton.sendKeys(Keys.TAB);
        previewButton.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(previewSuccessMsg));
        String successMsgTxt = driver.findElement(previewSuccessMsg).getText();
        Assert.assertEquals("Your preview is ready. Hover over image to zoom.", successMsgTxt);
        Thread.sleep(2000);

        //Find Add To Cart button, click and move to the cart
        WebElement addToCartButton = driver.findElement(addToCartBtn);
        addToCartButton.sendKeys(Keys.TAB);
        addToCartButton.sendKeys(Keys.ENTER);
        Thread.sleep(5000);



        /*Point previewLocation = previewButton.getLocation();
        int x = previewLocation.getX();
        int y = previewLocation.getY();
        System.out.println("Preview button coordinates:" + x + "and" + y);

        robot.mouseMove(219, 636);
        robot.delay(2000);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);*/


        /*WebElement previewButton = driver.findElement(previewBtn);
        if (previewButton.isDisplayed() && previewButton.isEnabled()) {
            previewButton.click();
        }*/
        /*driver.findElement(previewBtn);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", previewBtn);*/

        //wait.until(ExpectedConditions.elementToBeClickable(previewBtn)).click();
        /*WebElement previewButton = driver.findElement(previewBtn);
        action.moveToElement(previewButton).click().perform();*/
        //Thread.sleep(10000);
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(imageView));
    }








}
