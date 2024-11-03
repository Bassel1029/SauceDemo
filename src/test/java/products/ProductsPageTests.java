package products;

import base.BaseTests;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ProductsPage;

import java.util.List;
import java.util.Set;

import static org.testng.Assert.*;

public class ProductsPageTests extends BaseTests {

    public static final String USERNAME = "standard_user";
    public static final String PASSWORD = "secret_sauce";
    private ProductsPage productsPage;


    @BeforeMethod
    public void navigateToProductsPage() {
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);
        productsPage = loginPage.clickLogin();
    }

    @DataProvider(name = "priceSortingOptions")
    public Object[][] priceSortingOptions() {
        return new Object[][]{
                {"Price (low to high)", true},
                {"Price (high to low)", false}
        };
    }

    @DataProvider(name = "alphabeticalSortingOptions")
    public Object[][] alphabeticalSortingOptions() {
        return new Object[][]{
                {"Name (A to Z)", true},
                {"Name (Z to A)", false}
        };
    }

    @Test
    public void testProductImagesAreUnique() {
        Set<String> imagesSources = productsPage.getProductsImagesSourcesSet();
        assertEquals(imagesSources.size(), 6, "Products images are not unique.");
    }

    @Test
    public void testCartBadgeVisibilityAfterAddingItem() {
        productsPage.clickAddBackpackToCart();
        assertTrue(productsPage.isCartBadgeVisible(), "Cart badge is not visible after adding an item.");
    }

    @Test
    public void testBackpackAddToCartButtonFunctionality() {
        productsPage.clickAddBackpackToCart();
        assertTrue(productsPage.isBackpackRemoveButtonVisible(), "Remove button should be visible after adding item.");
        assertFalse(productsPage.isBackpackAddToCartButtonVisible(),"Add to cart button should not be visible after clicking it.");
        assertTrue(productsPage.isCartBadgeVisible(), "Cart badge should be visible after adding item.");
        assertEquals(productsPage.getCartBadgeCounter(),1,"Cart badge value is incorrect");
    }

    @Test
    public void testBikeLightAddToCartButtonFunctionality() {
        productsPage.clickAddBikeLightToCart();
        assertTrue(productsPage.isBikeLightRemoveButtonVisible(), "Remove button should be visible after adding item.");
        assertFalse(productsPage.isBikeLightAddToCartButtonVisible(),"Add to cart button should not be visible after clicking it.");
        assertTrue(productsPage.isCartBadgeVisible(), "Cart badge should be visible after adding item.");
        assertEquals(productsPage.getCartBadgeCounter(),1,"Cart badge value is incorrect");
    }

    @Test
    public void testBoltTShirtAddToCartButtonFunctionality() {
        productsPage.clickAddBoltTShirtToCart();
        assertTrue(productsPage.isBoltTShirtRemoveButtonVisible(), "Remove button should be visible after adding item.");
        assertFalse(productsPage.isBoltTShirtAddToCartButtonVisible(),"Add to cart button should not be visible after clicking it.");
        assertTrue(productsPage.isCartBadgeVisible(), "Cart badge should be visible after adding item.");
        assertEquals(productsPage.getCartBadgeCounter(),1,"Cart badge value is incorrect");
    }

    @Test
    public void testFleeceJacketAddToCartButtonFunctionality() {
        productsPage.clickAddFleeceJacketToCart();
        assertTrue(productsPage.isFleeceJacketRemoveButtonVisible(), "Remove button should be visible after adding item.");
        assertFalse(productsPage.isFleeceJacketAddToCartButtonVisible(),"Add to cart button should not be visible after clicking it.");
        assertTrue(productsPage.isCartBadgeVisible(), "Cart badge should be visible after adding item.");
        assertEquals(productsPage.getCartBadgeCounter(),1,"Cart badge value is incorrect");
    }

    @Test
    public void testOneSieAddToCartButtonFunctionality() {
        productsPage.clickAddOneSieToCart();
        assertTrue(productsPage.isOneSieRemoveButtonVisible(), "Remove button should be visible after adding item.");
        assertFalse(productsPage.isOneSieAddToCartButtonVisible(),"Add to cart button should not be visible after clicking it.");
        assertTrue(productsPage.isCartBadgeVisible(), "Cart badge should be visible after adding item.");
        assertEquals(productsPage.getCartBadgeCounter(),1,"Cart badge value is incorrect");
    }

    @Test
    public void testRedTShirtAddToCartButtonFunctionality() {
        productsPage.clickAddRedTShirtToCart();
        assertTrue(productsPage.isRedTShirtRemoveButtonVisible(), "Remove button should be visible after adding item.");
        assertFalse(productsPage.isRedTShirtAddToCartButtonVisible(),"Add to cart button should not be visible after clicking it.");
        assertTrue(productsPage.isCartBadgeVisible(), "Cart badge should be visible after adding item.");
        assertEquals(productsPage.getCartBadgeCounter(),1,"Cart badge value is incorrect");
    }

    @Test
    public void testCartBadgeCounter() {
        int initialNumberOfRemoveButtons = productsPage.getRemoveButtons().size();
        List<WebElement> addButtons = productsPage.getAddToCartButtons();
        for (WebElement add : addButtons) {
            add.click();
        }
        int expectedBadgeCounter = productsPage.getRemoveButtons().size() - initialNumberOfRemoveButtons;
        assertEquals(productsPage.getCartBadgeCounter(), expectedBadgeCounter, "Cart Badge value is incorrect.");
    }

    @Test
    public void testBackpackRemoveButtonFunctionality() {
        productsPage.clickAddBackpackToCart();
        assertTrue(productsPage.isBackpackRemoveButtonVisible(), "Remove button should be visible after adding item.");
        productsPage.clickBackpackRemoveButton();
        assertFalse(productsPage.isBackpackRemoveButtonVisible(), "Remove button should no longer be visible after removing item.");
        assertTrue(productsPage.isBackpackAddToCartButtonVisible(), "Add to Cart button should be visible after removing item.");
        assertFalse(productsPage.isCartBadgeVisible(), "Cart badge should not be visible after all items are removed.");
    }

    @Test
    public void testBikeLightRemoveButtonFunctionality() {
        productsPage.clickAddBikeLightToCart();
        assertTrue(productsPage.isBikeLightRemoveButtonVisible(), "Remove button should be visible after adding item.");
        productsPage.clickBikeLightRemoveButton();
        assertFalse(productsPage.isBikeLightRemoveButtonVisible(), "Remove button should no longer be visible after removing item.");
        assertTrue(productsPage.isBikeLightAddToCartButtonVisible(), "Add to Cart button should be visible after removing item.");
        assertFalse(productsPage.isCartBadgeVisible(), "Cart badge should not be visible after all items are removed.");
    }

    @Test
    public void testBoltTShirtRemoveButtonFunctionality() {
        productsPage.clickAddBoltTShirtToCart();
        assertTrue(productsPage.isBoltTShirtRemoveButtonVisible(), "Remove button should be visible after adding item.");
        productsPage.clickBoltTShirtRemoveButton();
        assertFalse(productsPage.isBoltTShirtRemoveButtonVisible(), "Remove button should no longer be visible after removing item.");
        assertTrue(productsPage.isBoltTShirtAddToCartButtonVisible(), "Add to Cart button should be visible after removing item.");
        assertFalse(productsPage.isCartBadgeVisible(), "Cart badge should not be visible after all items are removed.");
    }

    @Test
    public void testFleeceJacketRemoveButtonFunctionality() {
        productsPage.clickAddFleeceJacketToCart();
        assertTrue(productsPage.isFleeceJacketRemoveButtonVisible(), "Remove button should be visible after adding item.");
        productsPage.clickFleeceJacketRemoveButton();
        assertFalse(productsPage.isFleeceJacketRemoveButtonVisible(), "Remove button should no longer be visible after removing item.");
        assertTrue(productsPage.isFleeceJacketAddToCartButtonVisible(), "Add to Cart button should be visible after removing item.");
        assertFalse(productsPage.isCartBadgeVisible(), "Cart badge should not be visible after all items are removed.");
    }

    @Test
    public void testOneSieRemoveButtonFunctionality() {
        productsPage.clickAddOneSieToCart();
        assertTrue(productsPage.isOneSieRemoveButtonVisible(), "Remove button should be visible after adding item.");
        productsPage.clickOneSieRemoveButton();
        assertFalse(productsPage.isOneSieRemoveButtonVisible(), "Remove button should no longer be visible after removing item.");
        assertTrue(productsPage.isOneSieAddToCartButtonVisible(), "Add to Cart button should be visible after removing item.");
        assertFalse(productsPage.isCartBadgeVisible(), "Cart badge should not be visible after all items are removed.");
    }

    @Test
    public void testRedTShirtRemoveButtonFunctionality() {
        productsPage.clickAddRedTShirtToCart();
        assertTrue(productsPage.isRedTShirtRemoveButtonVisible(), "Remove button should be visible after adding item.");
        productsPage.clickRedTShirtRemoveButton();
        assertFalse(productsPage.isRedTShirtRemoveButtonVisible(), "Remove button should no longer be visible after removing item.");
        assertTrue(productsPage.isRedTShirtAddToCartButtonVisible(), "Add to Cart button should be visible after removing item.");
        assertFalse(productsPage.isCartBadgeVisible(), "Cart badge should not be visible after all items are removed.");
    }

    @Test(dataProvider = "priceSortingOptions")
    public void testPriceSortingByPriceFunctionality(String sortingOption, boolean isAscending) {
        productsPage.selectSortingBar(sortingOption);
        List<Double> prices = productsPage.getProductPrices();
        assertTrue(productsPage.isSortedByPrice(prices, isAscending), "Products are not sorted correctly by price for option: " + sortingOption + ".");
    }

    @Test(dataProvider = "alphabeticalSortingOptions")
    public void testPriceSortingByTitleFunctionality(String sortingOption, boolean isAscending) {
        productsPage.selectSortingBar(sortingOption);
        List<String> titles = productsPage.getProductNames();
        assertTrue(productsPage.isSortedByTitle(titles, isAscending), "Products are not sorted correctly by alphabetic order for option: " + sortingOption + ".");
    }

    @Test
    public void testHamburgerIcon() {
        productsPage.clickHamburgerButton();
        productsPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        assertTrue(productsPage.isAboutLinkVisible(),"Unable to find about link in hamburger menu.");
    }

    @Test
    public void testClickAllItems() {
        productsPage.clickHamburgerButton();
        productsPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        productsPage.clickAllItems();
        assertTrue(productsPage.isSortingBarVisible(),"Products page should still be displayed after clicking all items.");
    }

    @Test
    public void testResetAppStateFunctionality() {
        productsPage.clickAddBackpackToCart();
        assertEquals(productsPage.getCartBadgeCounter(), 1, "Cart Badge counter did not increase after clicking add to cart button.");
        productsPage.clickHamburgerButton();
        productsPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        productsPage.clickResetAppState();
        assertFalse(productsPage.isCartBadgeVisible(), "Cart badge should not be visible after clicking reset app state.");
    }

    @Test
    public void testLogoutFunctionality() {
        productsPage.clickHamburgerButton();
        productsPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        var loginPageAfterLogout = productsPage.clickLogout();
        assertTrue(loginPageAfterLogout.isLoginButtonVisible(), "Login button is not visible.");
    }

    @Test
    public void testHamburgerCloseButtonFunctionality() {
        productsPage.clickHamburgerButton();
        productsPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        productsPage.clickCloseHamburgerButton();
        productsPage.waitForAboutLinkToBeInvisible();
        assertFalse(productsPage.isAboutLinkVisible(), "About link should not be visible after closing hamburger menu.");
        assertTrue(productsPage.isHamburgerMenuIconVisible(), "Hamburger menu icon should be visible after closing the menu.");
    }

    @Test
    public void testClickOnCartIcon() {
        var yourCartPage = productsPage.clickCartIcon();
        assertTrue(yourCartPage.isCheckoutButtonVisible(), "Unable to find checkout button.");
    }

    @Test
    public void testClickOnProductTitle() {
        for (int i = 0; i < productsPage.getNumberOfProducts(); i++) {
            var productDetailsPage = productsPage.clickProductName(i);
            assertTrue(productDetailsPage.isBackToProductsButtonVisible(), "Unable to find back to products button.");
            productDetailsPage.clickBackToProductsButton();
        }
    }

    @Test
    public void testClickOnProductImage() {
        for (int i = 0; i < productsPage.getNumberOfProducts(); i++){
            var productsDetailsPage = productsPage.clickProductImage(i);
            assertTrue(productsDetailsPage.isBackToProductsButtonVisible(),"Unable to find back to products button.");
            productsDetailsPage.clickBackToProductsButton();
        }
    }
}

