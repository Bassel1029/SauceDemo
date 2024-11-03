package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutOverviewPage {

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
    private By cancelButton = By.id("cancel");
    private By finishButton = By.id("finish");
    private By twitterIcon = By.xpath("//a[@data-test='social-twitter']");
    private By facebookIcon = By.xpath("//a[@data-test='social-facebook']");
    private By linkedInIcon = By.xpath("//a[@data-test='social-linkedin']");

    public CheckoutOverviewPage(WebDriver driver){
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

    public void waitForAboutLinkToBeInvisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(aboutLink));
    }

    public boolean isAboutLinkVisible() {
        try {
            return driver.findElement(aboutLink).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
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

    public int getCartBadgeCounter(){
        try{
            return Integer.parseInt(driver.findElement(cartBadge).getText());
        }catch (NoSuchElementException e){
            return 0;
        }
    }

    public ProductDetailsPage clickProductName(int index){
        driver.findElements(productName).get(index).click();
        return new ProductDetailsPage(driver);
    }

    public ProductsPage clickCancelButton(){
        driver.findElement(cancelButton).click();
        return new ProductsPage(driver);
    }

    public CheckoutCompletePage clickFinishButton(){
        driver.findElement(finishButton).click();
        return new CheckoutCompletePage(driver);
    }

    public boolean isFinishButtonVisible() {
        try {
            return driver.findElement(finishButton).isDisplayed();
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
