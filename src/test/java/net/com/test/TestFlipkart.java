package net.com.test;

import com.relevantcodes.extentreports.LogStatus;
import net.com.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestFlipkart extends BaseTest {

    @Test
    public void testProductSearch() throws IOException {

        String productToSearch = "Samsung Mobile";
        try {
            searchPage = new SearchPage();
            logger.log(LogStatus.INFO, "Launched Flipkart Web Application");

            productsListPage = searchPage.searchProduct(productToSearch);
            passStep("Search for " + productToSearch);

            productPage = productsListPage.filterByMinimumPrice("10000").selectProductOnIndex(3);
            passStep("Selected product with price greater than Rs.10,000/-");
            passStep("Launched Flipkart Web Application");

            if (productPage.getProductTitle().contains(productPage.getProductName())) {

                passStep(productPage.getProductName() + " is same as the product " + productPage.getProductTitle());
                Assert.assertTrue(true);
            } else {
                failStep("Selected and displayed products are diferent");
                Assert.fail();
            }
            productPage.addProduct();
            passStep("Product " + productPage.getProductName() + " is added to the cart");

        } catch (Exception e) {

            failStep(e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void testArbitrary() throws IOException {

        String productToSearch = "Apple iPhone Mobile";
        try {
            searchPage = new SearchPage();
            logger.log(LogStatus.INFO, "Launched Flipkart Web Application");

            productsListPage = searchPage.searchProduct(productToSearch);
            passStep("Search for " + productToSearch);

            productPage = productsListPage.filterByMinimumPrice("10000").selectProductOnIndex(3);
            passStep("Selected product with price greater than Rs.10,000/-");
            passStep("Launched Flipkart Web Application");

            if (productPage.getProductTitle().contains(productPage.getProductName())) {

                passStep(productPage.getProductName() + " is same as the product " + productPage.getProductTitle());
                Assert.assertTrue(true);
            } else {
                failStep("Selected and displayed products are diferent");
                Assert.fail();
            }
            productPage.addProduct();
            passStep("Product " + productPage.getProductName() + " is added to the cart");

        } catch (Exception e) {

            failStep(e.getMessage());
            Assert.fail();
        }
    }

}
