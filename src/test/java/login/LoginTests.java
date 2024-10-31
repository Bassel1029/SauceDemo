package login;

import base.BaseTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTests extends BaseTests {

    @DataProvider(name = "invalidPasswordUsers")
    public Object[][] invalidPasswordUsers() {
        return new Object[][]{
                {"standard_user"},
                {"locked_out_user"},
                {"problem_user"},
                {"performance_glitch_user"},
                {"error_user"},
                {"visual_user"}
        };
    }

    @Test
    public void testStandardUserLogin() {
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        var productsPage = loginPage.clickLogin();
        assertTrue(productsPage.isLoaded(),"Products page did not load for standard_user.");
    }

    @Test
    public void testLockedOutUserLogin() {
        loginPage.setUsername("locked_out_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();
        assertTrue(loginPage.isErrorDisplayed(),"Error message is not displayed.");
        assertEquals(loginPage.getErrorMessage(),"Epic sadface: Sorry, this user has been locked out.","Error message is incorrect.");
    }

    @Test
    public void testProblemUserLogin() {
        loginPage.setUsername("problem_user");
        loginPage.setPassword("secret_sauce");
        var productsPage = loginPage.clickLogin();
        assertTrue(productsPage.isLoaded(),"Products page did not load for problem_user.");
    }

    @Test
    public void testPerformanceGlitchUserLogin(){
        loginPage.setUsername("performance_glitch_user");
        loginPage.setPassword("secret_sauce");
        var productsPage = loginPage.clickLogin();
        productsPage.waitForPageToLoad();
        assertTrue(productsPage.isLoaded(),"Products page did not load for performance_glitch_user.");
    }

    @Test
    public void testErrorUserLogin(){
        loginPage.setUsername("error_user");
        loginPage.setPassword("secret_sauce");
        var productsPage = loginPage.clickLogin();
        assertTrue(productsPage.isLoaded(),"Products page did not load for error_user.");
    }

    @Test
    public void testVisualUserLogin(){
        loginPage.setUsername("visual_user");
        loginPage.setPassword("secret_sauce");
        var productsPage = loginPage.clickLogin();
        assertTrue(productsPage.isLoaded(),"Products page did not load for visual_user.");
    }

    @Test(dataProvider = "invalidPasswordUsers")
    public void invalidPasswordUser(String username){
        loginPage.setUsername(username);
        loginPage.setPassword("invalid_password");
        loginPage.clickLogin();
        assertTrue(loginPage.isErrorDisplayed(),"Error message is not displayed.");
        assertEquals(loginPage.getErrorMessage(),"Epic sadface: Username and password do not match any user in this service","Error message is incorrect.");
    }
}
