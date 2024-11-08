package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetailsPage {

    private WebDriver driver;
    private By hamburgerButton = By.id("react-burger-menu-btn");
    private By allItemsLink = By.id("inventory_sidebar_link");
    private By aboutLink = By.id("about_sidebar_link");
    private By logoutLink = By.id("logout_sidebar_link");
    private By resetAppState = By.id("reset_sidebar_link");
    private By closeHamburger = By.id("react-burger-cross-btn");
    private By backToProductsButton = By.id("back-to-products");
    private By cartIcon = By.className("shopping_cart_link");
    private By cartBadge = By.className("shopping_cart_badge");
    private By addToCartButton = By.id("add-to-cart");
    private By removeButton = By.id("remove");
    private By twitterIcon = By.xpath("//a[@data-test='social-twitter']");
    private By facebookIcon = By.xpath("//a[@data-test='social-facebook']");
    private By linkedInIcon = By.xpath("//a[@data-test='social-linkedin']");


    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickHamburgerButton() {
        driver.findElement(hamburgerButton).click();
    }

    public boolean isHamburgerMenuIconVisible() {
        try {
            return driver.findElement(hamburgerButton).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickCloseHamburgerButton() {
        driver.findElement(closeHamburger).click();
    }

    public ProductsPage clickAllItems() {
        driver.findElement(allItemsLink).click();
        return new ProductsPage(driver);
    }

    public void clickAbout() {
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

    public LoginPage clickLogout() {
        driver.findElement(logoutLink).click();
        return new LoginPage(driver);
    }

    public void clickResetAppState() {
        driver.findElement(resetAppState).click();
    }

    public ProductsPage clickBackToProductsButton() {
        driver.findElement(backToProductsButton).click();
        return new ProductsPage(driver);
    }

    public void waitForHamburgerMenuToBeDisplayedWhenClicked() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(aboutLink));
    }

    public boolean isCartBadgeVisible() {
        try {
            return driver.findElement(cartBadge).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public YourCartPage clickCartIcon() {
        driver.findElement(cartIcon).click();
        return new YourCartPage(driver);
    }

    public int getCartBadgeCounter() {
        try {
            return Integer.parseInt(driver.findElement(cartBadge).getText());
        } catch (NoSuchElementException e) {
            return 0;
        }
    }

    public void clickAddToCartButton() {
        driver.findElement(addToCartButton).click();
    }

    public boolean isAddToCartButtonVisible() {
        try {
            return driver.findElement(addToCartButton).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isRemoveButtonVisible() {
        try {
            return driver.findElement(removeButton).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickRemoveButton() {
        driver.findElement(removeButton).click();
    }

    public boolean isBackToProductsButtonVisible() {
        try {
            return driver.findElement(backToProductsButton).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public void clickTwitterIcon() {
        driver.findElement(twitterIcon).click();
    }

    public void clickFacebookIcon() {
        driver.findElement(facebookIcon).click();
    }

    public void clickLinkedIn() {
        driver.findElement(linkedInIcon).click();
    }
}
