package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;
    private By userNameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMsg = By.xpath("//h3[@data-test='error']");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void setUsername(String username){
        driver.findElement(userNameField).sendKeys(username);
    }

    public void setPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    public ProductsPage clickLogin(){
    driver.findElement(loginButton).click();
    return new ProductsPage(driver);
    }

    public boolean isLoginButtonVisible() {
        try {
            return driver.findElement(loginButton).isDisplayed();
        }catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isErrorDisplayed(){
        try {
            return driver.findElement(errorMsg).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public String getErrorMessage(){
        return driver.findElement(errorMsg).getText();
    }
}
