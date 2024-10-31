package products;

import base.BaseTests;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static org.testng.Assert.*;

public class ProductsPageTests extends BaseTests {

    public static final String USERNAME = "standard_user";
    public static final String PASSWORD = "secret_sauce";

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
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);
        var productsPage = loginPage.clickLogin();
        Set<String> imagesSources = productsPage.getProductsImagesSourcesSet();
        assertEquals(imagesSources.size(), 6, "Products images are not unique.");
    }

    //problem here//////////////////////////////////
    ////////////////////
    /////////////////
    @Test
    public void testProductImagesMatchTitles() {
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);
        var productsPage = loginPage.clickLogin();
        var productsTitles = productsPage.getProductNameLastWord();
        var productsSrc = productsPage.getProductsImageSrcList();
        //SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < productsSrc.size(); i++) {
            System.out.println(productsTitles.get(i));
            System.out.println(productsSrc.get(i));
            //softAssert.assertTrue(productsSrc.get(i).contains(productsTitles.get(i)));
            //softAssert.assertAll();
            assertTrue(productsSrc.get(i).contains(productsTitles.get(i)), "Product image is not paired with correct title.");
        }
        //softAssert.assertAll();
    }

    @Test
    public void testCartBadgeVisibilityAfterAddingItem() {
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);
        var productsPage = loginPage.clickLogin();
        productsPage.clickAddToCartButton(0);
        assertTrue(productsPage.isCartBadgeVisible(), "Cart badge is not visible after adding an item.");
    }

    @Test
    public void testFirstAddToCartButtonFunctionality() {
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);
        var productsPage = loginPage.clickLogin();
        productsPage.clickAddToCartButton(0);
        List<WebElement> addButtons = productsPage.getAddToCartButtons();
        assertTrue(productsPage.isRemoveButtonVisible(0), "Remove button should be visible after adding item.");
        assertEquals(addButtons.size(),5,"Add to cart button should not be visible after clicking it.");
        assertTrue(productsPage.isCartBadgeVisible(), "Cart badge should be visible after adding item.");
        assertEquals(productsPage.getCartBadgeCounter(),1,"Cart badge value is incorrect");
    }

    @Test
    public void testFinalAddToCartButtonFunctionality() {
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);
        var productsPage = loginPage.clickLogin();
        productsPage.clickAddToCartButton(5);
        List<WebElement> addButtons = productsPage.getAddToCartButtons();
        assertTrue(productsPage.isRemoveButtonVisible(0), "Remove button should be visible after adding item.");
        assertEquals(addButtons.size(),5,"Add to cart button should not be visible after clicking it.");
        assertTrue(productsPage.isCartBadgeVisible(), "Cart badge should be visible after adding item.");
        assertEquals(productsPage.getCartBadgeCounter(),1,"Cart badge value is incorrect");
    }

    @Test
    public void testCartBadgeCounter() {
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);
        var productsPage = loginPage.clickLogin();
        int initialNumberOfRemoveButtons = productsPage.getRemoveButtons().size();
        List<WebElement> addButtons = productsPage.getAddToCartButtons();
        for (WebElement add : addButtons) {
            add.click();
        }
        int expectedBadgeCounter = productsPage.getRemoveButtons().size() - initialNumberOfRemoveButtons;
        assertEquals(productsPage.getCartBadgeCounter(), expectedBadgeCounter, "Cart Badge value is incorrect.");
    }

    @Test
    public void testFirstRemoveButtonFunctionality() {
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);
        var productsPage = loginPage.clickLogin();
        productsPage.clickAddToCartButton(0);
        assertTrue(productsPage.isRemoveButtonVisible(0), "Remove button should be visible after adding item.");
        productsPage.clickRemoveButton(0);
        assertFalse(productsPage.isRemoveButtonVisible(0), "Remove button should no longer be visible after removing item.");
        assertTrue(productsPage.isAddToCartButtonVisible(0), "Add to Cart button should be visible after removing item.");
        assertFalse(productsPage.isCartBadgeVisible(), "Cart badge should not be visible after all items are removed.");
    }

    @Test
    public void testFinalRemoveButtonFunctionality() {
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);
        var productsPage = loginPage.clickLogin();
        productsPage.clickAddToCartButton(5);
        assertTrue(productsPage.isRemoveButtonVisible(0), "Remove button should be visible after adding item.");
        productsPage.clickRemoveButton(0);
        assertFalse(productsPage.isRemoveButtonVisible(0), "Remove button should no longer be visible after removing item.");
        assertTrue(productsPage.isAddToCartButtonVisible(5), "Add to Cart button should be visible after removing item.");
        assertFalse(productsPage.isCartBadgeVisible(), "Cart badge should not be visible after all items are removed.");
    }

    @Test(dataProvider = "priceSortingOptions")
    public void testPriceSortingByPriceFunctionality(String sortingOption, boolean isAscending) {
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);
        var productsPage = loginPage.clickLogin();
        productsPage.selectSortingBar(sortingOption);
        List<Double> prices = productsPage.getProductPrices();
        assertTrue(productsPage.isSortedByPrice(prices, isAscending), "Products are not sorted correctly by price for option: " + sortingOption + ".");
    }

    @Test(dataProvider = "alphabeticalSortingOptions")
    public void testPriceSortingByTitleFunctionality(String sortingOption, boolean isAscending) {
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);
        var productsPage = loginPage.clickLogin();
        productsPage.selectSortingBar(sortingOption);
        List<String> titles = productsPage.getProductNames();
        assertTrue(productsPage.isSortedByTitle(titles, isAscending), "Products are not sorted correctly by alphabetic order for option: " + sortingOption + ".");
    }

    @Test
    public void testHamburgerIcon(){
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);
        var productsPage = loginPage.clickLogin();
        productsPage.clickHamburgerButton();
        productsPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        assertTrue(productsPage.isAboutLinkVisible(),"Unable to find about link.");
    }

    @Test
    public void testResetAppStateFunctionality() {
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);
        var productsPage = loginPage.clickLogin();
        productsPage.clickAddToCartButton(0);
        assertEquals(productsPage.getCartBadgeCounter(), 1, "Cart Badge counter did not increase after clicking add to cart button.");
        productsPage.clickHamburgerButton();
        productsPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        productsPage.clickResetAppState();
        assertFalse(productsPage.isCartBadgeVisible(), "Cart badge should not be visible after clicking reset app state.");
    }

    @Test
    public void testLogoutFunctionality() {
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);
        var productsPage = loginPage.clickLogin();
        productsPage.clickHamburgerButton();
        productsPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        var loginPageAfterLogout = productsPage.clickLogout();
        assertTrue(loginPageAfterLogout.isLoginButtonVisible(), "Login button is not visible.");
    }

    @Test
    public void testHamburgerCloseButtonFunctionality() {
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);
        var productsPage = loginPage.clickLogin();
        productsPage.clickHamburgerButton();
        productsPage.waitForHamburgerMenuToBeDisplayedWhenClicked();
        productsPage.clickCloseHamburgerButton();
        productsPage.waitForAboutLinkToBeInvisible();
        assertFalse(productsPage.isAboutLinkVisible(), "About link should not be visible");
        assertTrue(productsPage.isHamburgerMenuIconVisible(), "Hamburger menu icon should be visible after closing the menu.");
    }

    @Test
    public void testClickOnCartIcon() {
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);
        var productsPage = loginPage.clickLogin();
        var yourCartPage = productsPage.clickCartIcon();
        assertTrue(yourCartPage.isCheckoutButtonVisible(), "Unable to find checkout button.");
    }

    @Test
    public void testClickOnProductTitle() {
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);
        var productsPage = loginPage.clickLogin();
        for (int i = 0; i < productsPage.getNumberOfProducts(); i++) {
            var productDetailsPage = productsPage.clickProductName(i);
            assertTrue(productDetailsPage.isBackToProductsVisible(), "Unable to find back to products button.");
            productDetailsPage.clickBackToProductsButton();
        }
    }

    @Test
    public void testClickOnProductImage() {
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);
        var productsPage = loginPage.clickLogin();
        for (int i = 0; i < productsPage.getNumberOfProducts(); i++){
            var productsDetailsPage = productsPage.clickProductImage(i);
            assertTrue(productsDetailsPage.isBackToProductsVisible(),"Unable to find back to product button.");
            productsDetailsPage.clickBackToProductsButton();
        }
    }
}

