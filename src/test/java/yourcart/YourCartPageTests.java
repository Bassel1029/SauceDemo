package yourcart;

import base.BaseTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ProductsPage;
import pages.YourCartPage;

import static org.testng.Assert.*;

public class YourCartPageTests extends BaseTests {

    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";
    private YourCartPage yourCartPage;
    private ProductsPage productsPage;

    @BeforeMethod
    public void setUp() {
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);
        productsPage = loginPage.clickLogin();
    }

    @Test
    public void testHamburgerIcon() {
        yourCartPage = productsPage.clickCartIcon();
        yourCartPage.clickHamburgerButton();
        yourCartPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        assertTrue(yourCartPage.isAboutLinkVisible(), "Unable to find about link in hamburger menu.");
    }

    @Test
    public void testAllItemsFunctionality() {
        yourCartPage = productsPage.clickCartIcon();
        yourCartPage.clickHamburgerButton();
        yourCartPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        var productsPage = yourCartPage.clickAllItems();
        assertTrue(productsPage.isSortingBarVisible(), "Products Page did not load correctly after clicking back to products.");
    }

    @Test
    public void testLogoutFunctionality() {
        yourCartPage = productsPage.clickCartIcon();
        yourCartPage.clickHamburgerButton();
        yourCartPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        var loginPageAfterLogout = yourCartPage.clickLogout();
        assertTrue(loginPageAfterLogout.isLoginButtonVisible(), "Login button is not visible.");
    }

    @Test
    public void testResetAppStateFunctionality() {
        yourCartPage = productsPage.clickCartIcon();
        yourCartPage.clickHamburgerButton();
        yourCartPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        yourCartPage.clickResetAppState();
        assertFalse(yourCartPage.isCartBadgeVisible(), "Cart badge should not be visible after clicking reset app state.");
    }

    @Test
    public void testHamburgerCloseButtonFunctionality() {
        yourCartPage = productsPage.clickCartIcon();
        yourCartPage.clickHamburgerButton();
        yourCartPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        yourCartPage.clickCloseHamburgerButton();
        yourCartPage.waitForAboutLinkToBeInvisible();
        assertFalse(yourCartPage.isAboutLinkVisible(), "About link should not be visible after closing hamburger menu.");
        assertTrue(yourCartPage.isHamburgerMenuIconVisible(), "Hamburger menu icon should be visible after closing the menu.");
    }

    @Test
    public void testClickOnCartIcon() {
        yourCartPage = productsPage.clickCartIcon();
        var yourCartPageStillDisplayed = yourCartPage.clickCartIcon();
        assertTrue(yourCartPageStillDisplayed.isCheckoutButtonVisible(), "Cart page should still be displayed after clicking cart icon.");
    }

    @Test
    public void testBackpackRemoveButtonFunctionality() {
        productsPage.clickAddBackpackToCart();
        yourCartPage = productsPage.clickCartIcon();
        yourCartPage.clickBackpackRemoveButton();
        assertFalse(yourCartPage.isBackpackRemoveButtonVisible(), "Remove button should no longer be visible after  removing item.");
        assertFalse(yourCartPage.isCartBadgeVisible(), "Cart badge should not be visible after removing item");
    }

    @Test
    public void testBikeLightRemoveButtonFunctionality() {
        productsPage.clickAddBikeLightToCart();
        yourCartPage = productsPage.clickCartIcon();
        yourCartPage.clickBikeLightRemoveButton();
        assertFalse(yourCartPage.isBikeLightRemoveButtonVisible(), "Remove button should no longer be visible after  removing item.");
        assertFalse(yourCartPage.isCartBadgeVisible(), "Cart badge should not be visible after removing item");
    }

    @Test
    public void testBoltTShirtRemoveButtonFunctionality() {
        productsPage.clickAddBoltTShirtToCart();
        yourCartPage = productsPage.clickCartIcon();
        yourCartPage.clickBoltTShirtRemoveButton();
        assertFalse(yourCartPage.isBoltTShirtRemoveButtonVisible(), "Remove button should no longer be visible after  removing item.");
        assertFalse(yourCartPage.isCartBadgeVisible(), "Cart badge should not be visible after removing item");
    }

    @Test
    public void testFleeceJacketRemoveButtonFunctionality() {
        productsPage.clickAddFleeceJacketToCart();
        yourCartPage = productsPage.clickCartIcon();
        yourCartPage.clickFleeceJacketRemoveButton();
        assertFalse(yourCartPage.isFleeceJacketRemoveButtonVisible(), "Remove button should no longer be visible after  removing item.");
        assertFalse(yourCartPage.isCartBadgeVisible(), "Cart badge should not be visible after removing item");
    }

    @Test
    public void testOneSieRemoveButtonFunctionality() {
        productsPage.clickAddOneSieToCart();
        yourCartPage = productsPage.clickCartIcon();
        yourCartPage.clickOneSieRemoveButton();
        assertFalse(yourCartPage.isOneSieRemoveButtonVisible(), "Remove button should no longer be visible after  removing item.");
        assertFalse(yourCartPage.isCartBadgeVisible(), "Cart badge should not be visible after removing item");
    }

    @Test
    public void testRedTShirtRemoveButtonFunctionality() {
        productsPage.clickAddRedTShirtToCart();
        yourCartPage = productsPage.clickCartIcon();
        yourCartPage.clickRedTShirtRemoveButton();
        assertFalse(yourCartPage.isRedTShirtRemoveButtonVisible(), "Remove button should no longer be visible after  removing item.");
        assertFalse(yourCartPage.isCartBadgeVisible(), "Cart badge should not be visible after removing item");
    }

    @Test
    public void testContinueShoppingButton() {
        yourCartPage = productsPage.clickCartIcon();
        var backToProductsPage = yourCartPage.clickContinueShoppingButton();
        assertTrue(backToProductsPage.isSortingBarVisible(), "Products page did not load correctly.");
    }

    @Test
    public void testClickProductName() {
        productsPage.clickAddBackpackToCart();
        yourCartPage = productsPage.clickCartIcon();
        var productDetailsPage = yourCartPage.clickProductName(0);
        assertTrue(productDetailsPage.isBackToProductsButtonVisible(),"Product Details page did not load correctly.");
    }

    @Test
    public void testClickCheckoutButton() {
        yourCartPage = productsPage.clickCartIcon();
        var checkoutInfoPage = yourCartPage.clickCheckoutButton();
        assertTrue(checkoutInfoPage.isFirstNameFieldVisible(),"Checkout information page did not load correctly.");
    }
}
