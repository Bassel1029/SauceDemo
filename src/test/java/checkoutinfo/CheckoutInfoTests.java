package checkoutinfo;

import base.BaseTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CheckoutInfoPage;

import static org.testng.Assert.*;

public class CheckoutInfoTests extends BaseTests {

    public static final String USERNAME = "standard_user";
    public static final String PASSWORD = "secret_sauce";
    private CheckoutInfoPage checkoutInfoPage;

    @BeforeMethod
    public void setUp() {
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);
        var productsPage = loginPage.clickLogin();
        productsPage.clickAddBackpackToCart();
        var yourCartPage = productsPage.clickCartIcon();
        checkoutInfoPage = yourCartPage.clickCheckoutButton();
    }

    @Test
    public void testHamburgerIcon() {
        checkoutInfoPage.clickHamburgerButton();
        checkoutInfoPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        assertTrue(checkoutInfoPage.isAboutLinkVisible(),"Unable to find about link in hamburger menu.");
    }

    @Test
    public void testClickAllItems() {
        checkoutInfoPage.clickHamburgerButton();
        checkoutInfoPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        var backToProductsPage = checkoutInfoPage.clickAllItems();
        assertTrue(backToProductsPage.isSortingBarVisible(),"Products page should be displayed after clicking all items.");
    }

    @Test
    public void testLogoutFunctionality() {
        checkoutInfoPage.clickHamburgerButton();
        checkoutInfoPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        var loginPageAfterLogout = checkoutInfoPage.clickLogout();
        assertTrue(loginPageAfterLogout.isLoginButtonVisible(), "Login button is not visible.");
    }

    @Test
    public void testResetAppStateFunctionality() {
        assertEquals(checkoutInfoPage.getCartBadgeCounter(), 1, "Cart badge should equal '1'.");
        checkoutInfoPage.clickHamburgerButton();
        checkoutInfoPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        checkoutInfoPage.clickResetAppState();
        assertFalse(checkoutInfoPage.isCartBadgeVisible(), "Cart badge should not be visible after clicking reset app state.");
    }

    @Test
    public void testHamburgerCloseButtonFunctionality() {
        checkoutInfoPage.clickHamburgerButton();
        checkoutInfoPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        checkoutInfoPage.clickCloseHamburgerButton();
        checkoutInfoPage.waitForAboutLinkToBeInvisible();
        assertFalse(checkoutInfoPage.isAboutLinkVisible(), "About link should not be visible after closing hamburger menu.");
        assertTrue(checkoutInfoPage.isHamburgerMenuIconVisible(), "Hamburger menu icon should be visible after closing the menu.");
    }

    @Test
    public void testClickOnCartIcon() {
        var yourCartPage = checkoutInfoPage.clickCartIcon();
        assertTrue(yourCartPage.isCheckoutButtonVisible(), "Cart page did not load correctly.");
    }

    @Test
    public void testClickCancelButton() {
        var backToYourCartPage = checkoutInfoPage.clickCancelButton();
        assertTrue(backToYourCartPage.isCheckoutButtonVisible(),"Cart page did not load correctly.");
    }

    @Test
    public void testClickContinueButton() {
        checkoutInfoPage.setFirstName("Ahmed");
        checkoutInfoPage.setLastName("Mohammed");
        checkoutInfoPage.setPostalCode("15555");
        var checkoutOverviewPage = checkoutInfoPage.clickContinueButton();
        assertTrue(checkoutOverviewPage.isFinishButtonVisible(),"Checkout overview page did not load correctly.");
    }

    @Test
    public void testLeaveFirstNameFieldEmpty() {
        checkoutInfoPage.clickContinueButton();
        assertTrue(checkoutInfoPage.isErrorFieldsColorCorrect(),"Color of bottom border of the 3 fields is incorrect.");
        assertTrue(checkoutInfoPage.areErrorIndicatorsVisible(),"All 3 error indicators should be visible beside their respective field.");
        assertTrue(checkoutInfoPage.isErrorMessageVisible(),"Error message should appear after leaving field name empty.");
        assertEquals(checkoutInfoPage.getErrorMsg(),"Error: First Name is required","Error message is incorrect.");
    }

    @Test
    public void testLeaveLastNameFieldEmpty() {
        checkoutInfoPage.setFirstName("Ahmed");
        checkoutInfoPage.clickContinueButton();
        assertTrue(checkoutInfoPage.isErrorFieldsColorCorrect(),"Color of bottom border of the 3 fields is incorrect.");
        assertTrue(checkoutInfoPage.areErrorIndicatorsVisible(),"All 3 error indicators should be visible beside their respective field.");
        assertTrue(checkoutInfoPage.isErrorMessageVisible(),"Error message should appear after leaving field name empty.");
        assertEquals(checkoutInfoPage.getErrorMsg(),"Error: Last Name is required","Error message is incorrect.");
    }

    @Test
    public void testLeavePostalCodeFieldEmpty() {
        checkoutInfoPage.setFirstName("Ahmed");
        checkoutInfoPage.setLastName("Mohammed");
        checkoutInfoPage.clickContinueButton();
        assertTrue(checkoutInfoPage.isErrorFieldsColorCorrect(),"Color of bottom border of the 3 fields is incorrect.");
        assertTrue(checkoutInfoPage.areErrorIndicatorsVisible(),"All 3 error indicators should be visible beside their respective field.");
        assertTrue(checkoutInfoPage.isErrorMessageVisible(),"Error message should appear after leaving field name empty.");
        assertEquals(checkoutInfoPage.getErrorMsg(),"Error: Postal Code is required","Error message is incorrect.");
    }

    @Test
    public void testErrorXButtonFunctionality() {
        checkoutInfoPage.clickContinueButton();
        assertTrue(checkoutInfoPage.areErrorIndicatorsVisible(),"All 3 error indicators should be visible after clicking continue and leaving all fields empty.");
        checkoutInfoPage.clickXErrorButton();
        assertTrue(checkoutInfoPage.isNormalFieldsColorCorrect(),"Color of bottom border of the 3 fields should be back to original color.");
        assertFalse(checkoutInfoPage.isErrorMessageVisible(),"Error message should not be visible anymore.");
    }
}
