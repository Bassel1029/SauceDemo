package productdetails;

import base.BaseTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ProductDetailsPage;

import static org.testng.Assert.*;

public class ProductDetailsPageTests extends BaseTests {

    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";
    private static final int INDEX = 0;
    private ProductDetailsPage productDetailsPage;


    @BeforeMethod
    public void setUp() {
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);
        var productsPage = loginPage.clickLogin();
        productDetailsPage = productsPage.clickProductName(INDEX);
    }

    @Test
    public void testCartBadgeVisibilityAfterAddingItem() {
        productDetailsPage.clickAddToCartButton();
        assertTrue(productDetailsPage.isCartBadgeVisible(), "Cart badge is not visible after adding an item.");
    }

    @Test
    public void testCartBadgeCounter() {
        productDetailsPage.clickAddToCartButton();
        assertEquals(productDetailsPage.getCartBadgeCounter(),1,"Cart badge value is incorrect.");
    }

    @Test
    public void testHamburgerIcon() {
        productDetailsPage.clickHamburgerButton();
        productDetailsPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        assertTrue(productDetailsPage.isAboutLinkVisible(),"Unable to find about link in hamburger menu.");
    }

    @Test
    public void testAllItemsFunctionality() {
        productDetailsPage.clickHamburgerButton();
        productDetailsPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        var productsPage = productDetailsPage.clickAllItems();
        assertTrue(productsPage.isSortingBarVisible(),"Products Page did not load correctly after clicking back to products.");
    }

    @Test
    public void testResetAppStateFunctionality() {
        productDetailsPage.clickAddToCartButton();
        assertEquals(productDetailsPage.getCartBadgeCounter(), 1, "Cart Badge counter did not increase after clicking add to cart button.");
        productDetailsPage.clickHamburgerButton();
        productDetailsPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        productDetailsPage.clickResetAppState();
        assertFalse(productDetailsPage.isCartBadgeVisible(), "Cart badge should not be visible after clicking reset app state.");
    }

    @Test
    public void testLogoutFunctionality() {
        productDetailsPage.clickHamburgerButton();
        productDetailsPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        var loginPageAfterLogout = productDetailsPage.clickLogout();
        assertTrue(loginPageAfterLogout.isLoginButtonVisible(), "Login button is not visible.");
    }

    @Test
    public void testHamburgerCloseButtonFunctionality() {
        productDetailsPage.clickHamburgerButton();
        productDetailsPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        productDetailsPage.clickCloseHamburgerButton();
        productDetailsPage.waitForAboutLinkToBeInvisible();
        assertFalse(productDetailsPage.isAboutLinkVisible(), "About link should not be visible after closing hamburger menu.");
        assertTrue(productDetailsPage.isHamburgerMenuIconVisible(), "Hamburger menu icon should be visible after closing the menu.");
    }

    @Test
    public void testBackToProductsButtonFunctionality() {
        var backToProductsPage = productDetailsPage.clickBackToProductsButton();
        assertTrue(backToProductsPage.isSortingBarVisible(),"Failed to navigate back to the products page after clicking the 'Back to Products' button.");
    }

    @Test
    public void testAddToCartButtonFunctionality() {
        productDetailsPage.clickAddToCartButton();
        assertTrue(productDetailsPage.isRemoveButtonVisible(), "Remove button should be visible after adding item.");
        assertFalse(productDetailsPage.isAddToCartButtonVisible(),"Add to cart button should not be visible after clicking it.");
        assertTrue(productDetailsPage.isCartBadgeVisible(), "Cart badge should be visible after adding item.");
        assertEquals(productDetailsPage.getCartBadgeCounter(),1,"Cart badge value is incorrect.");
    }

    @Test
    public void testClickOnCartIcon() {
        var yourCartPage = productDetailsPage.clickCartIcon();
        assertTrue(yourCartPage.isCheckoutButtonVisible(), "Unable to find checkout button.");
    }

    @Test
    public void removeButtonFunctionality() {
        productDetailsPage.clickAddToCartButton();
        assertTrue(productDetailsPage.isRemoveButtonVisible(), "Remove button should be visible after adding item.");
        productDetailsPage.clickRemoveButton();
        assertTrue(productDetailsPage.isAddToCartButtonVisible(),"Add to cart button should be visible again after clicking remove button.");
        assertFalse(productDetailsPage.isRemoveButtonVisible(),"Remove button should no longer be visible after  removing item.");
        assertFalse(productDetailsPage.isCartBadgeVisible(),"Cart badge should not be visible after removing item");
    }
}
