package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;

public class BaseTests {

    private WebDriver driver;
    protected LoginPage loginPage;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        goToLogin();
        loginPage = new LoginPage(driver);
    }

    @BeforeMethod
    public void goToLogin(){
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void reset(){
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
