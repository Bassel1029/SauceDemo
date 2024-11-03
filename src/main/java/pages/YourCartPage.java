package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class YourCartPage {

    private WebDriver driver;
    private By hamburgerButton = By.id("react-burger-menu-btn");
    private By allItemsLink = By.id("inventory_sidebar_link");
    private By aboutLink = By.id("about_sidebar_link");
    private By logoutLink = By.id("logout_sidebar_link");
    private By resetAppState = By.id("reset_sidebar_link");
    private By closeHamburger = By.id("react-burger-cross-btn");
    private By cartIcon = By.className("shopping_cart_link");
    private By cartBadge = By.className("shopping_cart_badge");
    private By productName = By.className("inventory_item_name");
    private By removeBackPackButton = By.id("remove-sauce-labs-backpack");
    private By removeBikeLightButton = By.id("remove-sauce-labs-bike-light");
    private By removeBoltTShirtButton = By.id("remove-sauce-labs-bolt-t-shirt");
    private By removeFleeceJacketButton = By.id("remove-sauce-labs-fleece-jacket");
    private By removeOneSieButton = By.id("remove-sauce-labs-onesie");
    private By removeRedTShirtButton = By.id("remove-test.allthethings()-t-shirt-(red)");
    private By continueShoppingButton = By.id("continue-shopping");
    private By checkoutButton = By.id("checkout");
    private By twitterIcon = By.xpath("//a[@data-test='social-twitter']");
    private By facebookIcon = By.xpath("//a[@data-test='social-facebook']");
    private By linkedInIcon = By.xpath("//a[@data-test='social-linkedin']");

    public YourCartPage(WebDriver driver){
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

    public void clickCloseHamburgerButton(){
        driver.findElement(closeHamburger).click();
    }

    public ProductsPage clickAllItems(){
        driver.findElement(allItemsLink).click();
        return new ProductsPage(driver);
    }

    public void clickAbout(){
        driver.findElement(aboutLink).click();
    }

    public boolean isAboutLinkVisible(){
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

    public ProductDetailsPage clickProductName(int index){
        driver.findElements(productName).get(index).click();
        return new ProductDetailsPage(driver);
    }

    public void clickBackpackRemoveButton(){
        driver.findElement(removeBackPackButton).click();
    }

    public boolean isBackpackRemoveButtonVisible(){
        try {
            return driver.findElement(removeBackPackButton).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public void clickBikeLightRemoveButton(){
        driver.findElement(removeBikeLightButton).click();
    }

    public boolean isBikeLightRemoveButtonVisible(){
        try {
            return driver.findElement(removeBikeLightButton).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public void clickBoltTShirtRemoveButton(){
        driver.findElement(removeBoltTShirtButton).click();
    }

    public boolean isBoltTShirtRemoveButtonVisible(){
        try {
            return driver.findElement(removeBoltTShirtButton).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public void clickFleeceJacketRemoveButton(){
        driver.findElement(removeFleeceJacketButton).click();
    }

    public boolean isFleeceJacketRemoveButtonVisible(){
        try {
            return driver.findElement(removeFleeceJacketButton).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public void clickOneSieRemoveButton(){
        driver.findElement(removeOneSieButton).click();
    }

    public boolean isOneSieRemoveButtonVisible(){
        try {
            return driver.findElement(removeOneSieButton).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public void clickRedTShirtRemoveButton(){
        driver.findElement(removeRedTShirtButton).click();
    }

    public boolean isRedTShirtRemoveButtonVisible(){
        try {
            return driver.findElement(removeRedTShirtButton).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public ProductsPage clickContinueShoppingButton(){
        driver.findElement(continueShoppingButton).click();
        return new ProductsPage(driver);
    }

    public CheckoutInfoPage clickCheckoutButton(){
        driver.findElement(checkoutButton).click();
        return new CheckoutInfoPage(driver);
    }

    public boolean isCheckoutButtonVisible(){
        try{
            return driver.findElement(checkoutButton).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
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
