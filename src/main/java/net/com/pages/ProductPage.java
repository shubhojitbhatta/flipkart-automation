package net.com.pages;

import net.com.webdriver.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;

public class ProductPage extends BasePage {

    private String productName;

    public ProductPage(String productName) {

        this.productName = productName;

        String parentWindow = WebDriverManager.getDriver().getWindowHandle();
        Set<String> handles =  WebDriverManager.getDriver().getWindowHandles();

        for(String windowHandle  : handles) {
            if(!windowHandle.equals(parentWindow)) {
                WebDriverManager.getDriver().switchTo().window(windowHandle);
            }
        }
    }

    @FindBy(xpath = "//span[@class='_35KyD6']")
    private WebElement productTitle;

    @FindBy(xpath = "//button[@class='_2AkmmA _2Npkh4 _2MWPVK']")
    private WebElement addToCart;

    @FindBy(xpath = "xxx")
    private WebElement addToWishList;

    @FindBy(xpath = "//div[contains(text(),'out of stock')]")
    private List<WebElement> outOfStock;


    public String getProductTitle() {

        webDriverWait.until(ExpectedConditions.visibilityOf(productTitle));
        return productTitle.getText();
    }

    public String getProductName() {
        return productName;
    }

    public BasePage addProduct() {

        webDriverWait.until(ExpectedConditions.visibilityOf(addToCart));
        if (outOfStock.size() == 0) {
            addToCart.click();
            System.out.println("Product added to cart");
            return new ShoppingCartPage();
        } else {
            addToWishList.click();
            System.out.println("Product added to wish-list");
            return new WishListPage();
        }
    }
}
