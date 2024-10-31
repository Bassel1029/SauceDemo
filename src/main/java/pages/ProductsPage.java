package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class ProductsPage {

    private WebDriver driver;
    private By hamburgerButton = By.id("react-burger-menu-btn");
    private By allItemsLink = By.id("inventory_sidebar_link");
    private By aboutLink = By.id("about_sidebar_link");
    private By logoutLink = By.id("logout_sidebar_link");
    private By resetAppState = By.id("reset_sidebar_link");
    private By closeHamburger = By.id("react-burger-cross-btn");
    private By cartIcon = By.className("shopping_cart_link");
    private By cartBadge = By.className("shopping_cart_badge");
    private By sortingBar = By.className("product_sort_container");
    private By addToCartButton = By.cssSelector(".btn.btn_primary.btn_small.btn_inventory");
    private By removeButton = By.cssSelector(".btn.btn_secondary.btn_small.btn_inventory");
    private By productNames = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");
    private By productImages = By.xpath("//img[@class='inventory_item_img']");
   private By imageLinks = By.xpath("//div[@class='inventory_item_img']//a");
    private By twitterIcon = By.xpath("//a[@data-test='social-twitter']");
    private By facebookIcon = By.xpath("//a[@data-test='social-facebook']");
    private By linkedInIcon = By.xpath("//a[@data-test='social-linkedin']");

    public ProductsPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickHamburgerButton(){
        driver.findElement(hamburgerButton).click();
    }

    public void waitForHamburgerMenuToBeDisplayedWhenClicked(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(aboutLink));
    }

    public void clickCloseHamburgerButton(){
        driver.findElement(closeHamburger).click();
    }

    public boolean isHamburgerMenuIconVisible(){
        try{
            return driver.findElement(hamburgerButton).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public ProductsPage clickAllItems(){
        driver.findElement(allItemsLink).click();
        return new ProductsPage(driver);
    }

    public void clickAbout(){
        driver.findElement(aboutLink).click();
    }

    public LoginPage clickLogout(){
        driver.findElement(logoutLink).click();
        return new LoginPage(driver);
    }

    public void clickResetAppState(){
        driver.findElement(resetAppState).click();
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

    public YourCartPage clickCartIcon(){
        driver.findElement(cartIcon).click();
        return new YourCartPage(driver);
    }

    public void selectSortingBar(String option){
        Select sortSelect = new Select(driver.findElement(sortingBar));
        sortSelect.selectByVisibleText(option);
    }

    public List<Double> getProductPrices(){
        List<WebElement> productsPrices = driver.findElements(productPrices);
        List<Double> prices = new ArrayList<>();
        for (int i = 0; i < productsPrices.size(); i++){
            prices.add(Double.parseDouble(productsPrices.get(i).getText().replace("$", "")));
        }
        return prices;
    }

    public List<String> getProductNames(){
        List<WebElement> productsTitles = driver.findElements(productNames);
        List<String> names = new ArrayList<>();
        for (int i = 0; i < productsTitles.size(); i++){
            names.add((productsTitles.get(i).getText()));
        }
        return names;
    }

    public boolean isSortedByPrice(List<Double> prices, boolean isAscending) {
        for (int i = 1; i < prices.size(); i++) {
            if(isAscending) {
                if (prices.get(i) < prices.get(i - 1)) {
                    return false;
                }
            }else{
                if (prices.get(i) > prices.get(i - 1)){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isSortedByTitle(List<String> names, boolean isAscending) {
        for (int i = 1; i < names.size(); i++) {
            if (isAscending) {
                if (names.get(i).compareTo(names.get(i - 1)) < 0) {
                    return false;
                }
            } else {
                if (names.get(i).compareTo(names.get(i - 1)) > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void clickAddToCartButton(int index){
        List<WebElement> addButtons = driver.findElements(addToCartButton);
        if (index >= 0 && index < addButtons.size()) {
            addButtons.get(index).click();
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds for add to cart button: " + index);
        }
    }

    public List<WebElement> getAddToCartButtons(){
        return driver.findElements(addToCartButton);
    }

    public List<WebElement> getRemoveButtons(){
        return driver.findElements(removeButton);
    }

    public boolean isAddToCartButtonVisible(int index){
        List<WebElement> addButtons = driver.findElements(addToCartButton);
        if (index >=0 && index < addButtons.size())
            return addButtons.get(index).isDisplayed();
        return false;
    }

    public boolean isRemoveButtonVisible(int index){
       List<WebElement> removeButtons = driver.findElements(removeButton);
       if (index >=0 && index < removeButtons.size())
           return removeButtons.get(index).isDisplayed();
       return false;
    }

    public int getCartBadgeCounter(){
        try{
            return Integer.parseInt(driver.findElement(cartBadge).getText());
        }catch (NoSuchElementException e){
            return 0;
        }
    }

    public boolean isCartBadgeVisible(){
        try {
            return driver.findElement(cartBadge).isDisplayed();
        }catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickRemoveButton(int index){
        List<WebElement> removeButtons = driver.findElements(removeButton);
        if (index >= 0 && index < removeButtons.size()) {
            removeButtons.get(index).click();
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds for add to cart button: " + index);
        }
    }

    public ProductDetailsPage clickProductName(int index){
        driver.findElements(productNames).get(index).click();
        return new ProductDetailsPage(driver);
    }

    public ProductDetailsPage clickProductImage(int index){
        driver.findElements(imageLinks).get(index).click();
        return new ProductDetailsPage(driver);
    }

    public int getNumberOfImageLinks(){
        List<WebElement> images = driver.findElements(imageLinks);
        return images.size();
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

    public boolean isLoaded(){
        try{
            return driver.findElement(sortingBar).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public int getNumberOfProducts(){
        return driver.findElements(productNames).size();
    }

    /**
     * Set because order is not important
     *
     */
    public Set<String> getProductsImagesSourcesSet(){
        List<WebElement> images = driver.findElements(productImages);
        Set<String> imageSources = new HashSet<>();
        for(WebElement img : images){
            imageSources.add(img.getAttribute("src"));
        }
        return imageSources;
    }

    public void waitForPageToLoad(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(sortingBar));
    }

    public List<String> getProductNameLastWord(){
        List<WebElement> titles = driver.findElements(productNames);
        List<String> productTitles = new ArrayList<>();
       /* for(WebElement name: titles){
            productTitles.add(getLastWord(name.getText().toLowerCase()));
        }
        *///return productTitles;
        for (int i = 0; i<3; i++){
            productTitles.add(getLastWord(titles.get(i).getText().toLowerCase()));
        }
        return productTitles;
    }

    /**
     * List because order is important
     *
     */
    public List<String> getProductsImageSrcList(){
        List<WebElement> images = driver.findElements(this.productImages);
        List<String> productImages = new ArrayList<>();
        /*for(WebElement pic: images){
            productImages.add(pic.getAttribute("src"));
        }
        *///return productImages;
        for (int i = 0; i<3;i++){
            productImages.add(images.get(i).getAttribute("src"));
        }
        return productImages;
    }

    private static String getLastWord(String title) {
        if (title == null || title.isEmpty()) {
            return "";
        }
        String trimmedText = title.trim();
        String[] words = trimmedText.split("\\s+");
        if (words.length > 0) {
            return words[words.length - 1];
        } else {
            return "";
        }
    }
}
