package checkoutoverview;

import base.BaseTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CheckoutOverviewPage;

import static org.testng.Assert.*;

public class CheckoutOverviewTests extends BaseTests {

    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";
    private CheckoutOverviewPage checkoutOverviewPage;

    @BeforeMethod
    public void setUp() {
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);
        var productsPage = loginPage.clickLogin();
        productsPage.clickAddBackpackToCart();
        var yourCartPage = productsPage.clickCartIcon();
        var checkoutInfoPage = yourCartPage.clickCheckoutButton();
        checkoutInfoPage.setFirstName("Ahmed");
        checkoutInfoPage.setLastName("Mohammed");
        checkoutInfoPage.setPostalCode("15555");
        checkoutOverviewPage = checkoutInfoPage.clickContinueButton();
    }

    @Test
    public void testHamburgerIcon() {
        checkoutOverviewPage.clickHamburgerButton();
        checkoutOverviewPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        assertTrue(checkoutOverviewPage.isAboutLinkVisible(),"Unable to find about link in hamburger menu.");
    }

    @Test
    public void testClickAllItems() {
        checkoutOverviewPage.clickHamburgerButton();
        checkoutOverviewPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        var backToProductsPage = checkoutOverviewPage.clickAllItems();
        assertTrue(backToProductsPage.isSortingBarVisible(),"Products page should be displayed after clicking all items.");
    }

    @Test
    public void testLogoutFunctionality() {
        checkoutOverviewPage.clickHamburgerButton();
        checkoutOverviewPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        var loginPageAfterLogout = checkoutOverviewPage.clickLogout();
        assertTrue(loginPageAfterLogout.isLoginButtonVisible(), "Login button is not visible.");
    }

    @Test
    public void testResetAppStateFunctionality() {
        assertEquals(checkoutOverviewPage.getCartBadgeCounter(), 1, "Cart badge should equal '1'.");
        checkoutOverviewPage.clickHamburgerButton();
        checkoutOverviewPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        checkoutOverviewPage.clickResetAppState();
        assertFalse(checkoutOverviewPage.isCartBadgeVisible(), "Cart badge should not be visible after clicking reset app state.");
    }

    @Test
    public void testHamburgerCloseButtonFunctionality() {
        checkoutOverviewPage.clickHamburgerButton();
        checkoutOverviewPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        checkoutOverviewPage.clickCloseHamburgerButton();
        checkoutOverviewPage.waitForAboutLinkToBeInvisible();
        assertFalse(checkoutOverviewPage.isAboutLinkVisible(), "About link should not be visible after closing hamburger menu.");
        assertTrue(checkoutOverviewPage.isHamburgerMenuIconVisible(), "Hamburger menu icon should be visible after closing the menu.");
    }

    @Test
    public void testClickOnCartIcon() {
        var yourCartPage = checkoutOverviewPage.clickCartIcon();
        assertTrue(yourCartPage.isCheckoutButtonVisible(), "Cart page did not load correctly.");
    }

    @Test
    public void testClickProductTitle() {
        var productDetailsPage = checkoutOverviewPage.clickProductName(0);
        assertTrue(productDetailsPage.isBackToProductsButtonVisible(),"Product details page for this item did not load correctly.");
        assertTrue(productDetailsPage.isRemoveButtonVisible(),"Remove button for current item should be visible.");
    }

    @Test
    public void testClickCancelButton() {
        var productsPage = checkoutOverviewPage.clickCancelButton();
        assertTrue(productsPage.isSortingBarVisible(),"Products page did not load correctly.");
    }

    @Test
    public void testClickFinishButton() {
        var checkoutCompletePage = checkoutOverviewPage.clickFinishButton();
        assertTrue(checkoutCompletePage.isBackToHomeButtonVisible(),"Checkout complete page did not load correctly.");
        assertTrue(checkoutCompletePage.isThankYouMsgVisible(),"Checkout complete page did not load correctly.");
    }
}
