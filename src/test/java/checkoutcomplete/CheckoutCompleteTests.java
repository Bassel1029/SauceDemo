package checkoutcomplete;

import base.BaseTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CheckoutCompletePage;

import static org.testng.Assert.*;

public class CheckoutCompleteTests extends BaseTests {

    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";
    private CheckoutCompletePage checkoutCompletePage;

    @BeforeMethod
    public void setUp() {
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);
        var productsPage = loginPage.clickLogin();
        productsPage.clickAddBackpackToCart();
        var youCartPage = productsPage.clickCartIcon();
        var checkoutInfoPage = youCartPage.clickCheckoutButton();
        checkoutInfoPage.setFirstName("Ahmed");
        checkoutInfoPage.setLastName("Mohammed");
        checkoutInfoPage.setPostalCode("15555");
        var checkOutOverviewPage = checkoutInfoPage.clickContinueButton();
        checkoutCompletePage = checkOutOverviewPage.clickFinishButton();
    }

    @Test
    public void testCartIconIsEmpty() {
        assertFalse(checkoutCompletePage.isCartBadgeVisible(),"Cart badge should not be visible in checkout complete page");
    }

    @Test
    public void testHamburgerIcon() {
        checkoutCompletePage.clickHamburgerButton();
        checkoutCompletePage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        assertTrue(checkoutCompletePage.isAboutLinkVisible(),"Unable to find about link in hamburger menu.");
    }

    @Test
    public void testClickAllItems() {
        checkoutCompletePage.clickHamburgerButton();
        checkoutCompletePage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        var backToProductsPage = checkoutCompletePage.clickAllItems();
        assertTrue(backToProductsPage.isSortingBarVisible(),"Products page should be displayed after clicking all items.");
    }

    @Test
    public void testLogoutFunctionality() {
        checkoutCompletePage.clickHamburgerButton();
        checkoutCompletePage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        var loginPageAfterLogout = checkoutCompletePage.clickLogout();
        assertTrue(loginPageAfterLogout.isLoginButtonVisible(), "Login button is not visible.");
    }

    @Test
    public void testResetAppStateFunctionality() {
        checkoutCompletePage.clickHamburgerButton();
        checkoutCompletePage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        checkoutCompletePage.clickResetAppState();
        assertFalse(checkoutCompletePage.isCartBadgeVisible(), "Cart badge should not be visible after clicking reset app state.");
    }

    @Test
    public void testHamburgerCloseButtonFunctionality() {
        checkoutCompletePage.clickHamburgerButton();
        checkoutCompletePage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        checkoutCompletePage.clickCloseHamburgerButton();
        checkoutCompletePage.waitForAboutLinkToBeInvisible();
        assertFalse(checkoutCompletePage.isAboutLinkVisible(), "About link should not be visible after closing hamburger menu.");
        assertTrue(checkoutCompletePage.isHamburgerMenuIconVisible(), "Hamburger menu icon should be visible after closing the menu.");
    }

    @Test
    public void testClickOnCartIcon() {
        var yourCartPage = checkoutCompletePage.clickCartIcon();
        assertTrue(yourCartPage.isCheckoutButtonVisible(), "Cart page did not load correctly.");
    }

    @Test
    public void testThankYouIsCorrect(){
        assertTrue(checkoutCompletePage.isThankYouMsgVisible(),"Central Thank you message is not displayed.");
        assertEquals(checkoutCompletePage.getThankYouMsg(),"Thank you for your order!");
    }

    @Test
    public void testClickBackToHomeButton() {
        var backToProductsPage = checkoutCompletePage.clickBackHomeButton();
        assertTrue(backToProductsPage.isSortingBarVisible(),"Products page did not load correctly.");
    }
}
