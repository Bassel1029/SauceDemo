package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckoutInfoPage {

    private WebDriver driver;
    private By hamburgerButton = By.id("react-burger-menu-btn");
    private By allItemsLink = By.id("inventory_sidebar_link");
    private By aboutLink = By.id("about_sidebar_link");
    private By logoutLink = By.id("logout_sidebar_link");
    private By resetAppState = By.id("reset_sidebar_link");
    private By closeHamburger = By.id("react-burger-cross-btn");
    private By cartIcon = By.className("shopping_cart_link");
    private By cartBadge = By.className("shopping_cart_badge");
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By normalFields = By.cssSelector(".input_error.form_input");
    private By errorFields = By.cssSelector(".input_error.form_input.error");
    private By inputErrorIndicators = By.cssSelector(".svg-inline--fa.fa-times-circle.fa-w-16.error_icon");
    private By xErrorButton = By.className("error-button");
    private By errorMessage = By.xpath("//h3[@data-test='error']");
    private By cancelButton = By.id("cancel");
    private By continueButton = By.id("continue");
    private By twitterIcon = By.xpath("//a[@data-test='social-twitter']");
    private By facebookIcon = By.xpath("//a[@data-test='social-facebook']");
    private By linkedInIcon = By.xpath("//a[@data-test='social-linkedin']");

    public CheckoutInfoPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickHamburgerButton(){
        driver.findElement(hamburgerButton).click();
    }

    public boolean isHamburgerMenuIconVisible(){
        try{
            return driver.findElement(hamburgerButton).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public void waitForHamburgerMenuToBeDisplayedWhenClicked(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(aboutLink));
    }

    public ProductsPage clickAllItems(){
        driver.findElement(allItemsLink).click();
        return new ProductsPage(driver);
    }

    public void clickAbout(){
        driver.findElement(aboutLink).click();
    }

    public boolean isAboutLinkVisible() {
        try {
            return driver.findElement(aboutLink).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void waitForAboutLinkToBeInvisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(aboutLink));
    }

    public LoginPage clickLogout(){
        driver.findElement(logoutLink).click();
        return new LoginPage(driver);
    }

    public void clickCloseHamburgerButton(){
        driver.findElement(closeHamburger).click();
    }

    public void clickResetAppState(){
        driver.findElement(resetAppState).click();
    }

    public YourCartPage clickCartIcon(){
        driver.findElement(cartIcon).click();
        return new YourCartPage(driver);
    }

    public boolean isCartBadgeVisible(){
        try {
            return driver.findElement(cartBadge).isDisplayed();
        }catch (NoSuchElementException e) {
            return false;
        }
    }

    public int getCartBadgeCounter(){
        try{
            return Integer.parseInt(driver.findElement(cartBadge).getText());
        }catch (NoSuchElementException e){
            return 0;
        }
    }

    public void setFirstName(String firstName){
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void setLastName(String lastName){
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void setPostalCode(String postalCode){
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }

    public boolean isNormalFieldsColorCorrect() {
        List<WebElement> fields = driver.findElements(normalFields);
        String BottomBorderColor = "rgba(237, 237, 237, 1)";
        for (WebElement field : fields){
            if (!field.getCssValue("border-bottom-color").equals(BottomBorderColor))
                return false;
        }
        return true;
    }


    public boolean isFirstNameFieldVisible() {
        try{
            return driver.findElement(firstNameField).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean areErrorIndicatorsVisible() {
        List<WebElement> errorIndicators = driver.findElements(inputErrorIndicators);
        for (WebElement errorIcon : errorIndicators){
            if (!errorIcon.isDisplayed())
                return false;
        }
        return true;
    }

    public boolean isErrorMessageVisible() {
        try {
            return driver.findElement(errorMessage).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public String getErrorMsg() {
        return driver.findElement(errorMessage).getText();
    }

    public boolean isErrorFieldsColorCorrect() {
        List<WebElement> fieldsAfterError = driver.findElements(errorFields);
        String BottomBorderColor = "rgba(226, 35, 26, 1)";
        for (WebElement field : fieldsAfterError){
            if (!field.getCssValue("border-bottom-color").equals(BottomBorderColor))
                return false;
        }
        return true;
    }

    public void clickXErrorButton() {
        driver.findElement(xErrorButton).click();
    }

    public YourCartPage clickCancelButton(){
        driver.findElement(cancelButton).click();
        return new YourCartPage(driver);
    }

    public CheckoutOverviewPage clickContinueButton(){
        driver.findElement(continueButton).click();
        return new CheckoutOverviewPage(driver);
    }

    public void clickTwitterIcon(){
        driver.findElement(twitterIcon).click();
    }

    public void clickFacebookIcon(){
        driver.findElement(facebookIcon).click();
    }

    public void clickLinkedIn(){
        driver.findElement(linkedInIcon).click();
    }
}
