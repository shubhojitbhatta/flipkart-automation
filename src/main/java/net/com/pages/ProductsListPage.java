package net.com.pages;

import net.com.webdriver.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class ProductsListPage extends BasePage{

    private String productSearched;

    public ProductsListPage(String productSearched) {
        this.productSearched = productSearched;
    }

    @FindBy(xpath = "//select[@class='fPjUPw']")
    private WebElement priceMinimum;

    @FindBy(xpath = "//div[@class='bhgxx2 col-12-12'][4]//div[starts-with(text(),'Samsung')]")
    private WebElement searchResult;

    public ProductsListPage filterByMinimumPrice(String price) {

        Select selectMinimumPrice = new Select(priceMinimum);
        selectMinimumPrice.selectByValue(price);
        return this;
    }


    public ProductPage selectProductOnIndex(int indexPosition) {

        String productNameStartsWith = this.productSearched.substring(0, this.productSearched.indexOf(' '));
        StringBuilder xpathExpression = new StringBuilder("//div[@class='bhgxx2 col-12-12'][index]//div[starts-with(text(),'" + productNameStartsWith + "')]");
        xpathExpression.replace(xpathExpression.indexOf("index"), xpathExpression.indexOf("index") + "index".length(), Integer.toString(indexPosition + 1));

        String productName = retryingGetElementTextAndClick(By.xpath(xpathExpression.toString()));
        return new ProductPage(productName);
    }
}
