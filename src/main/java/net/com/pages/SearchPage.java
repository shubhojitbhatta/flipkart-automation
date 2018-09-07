package net.com.pages;

import net.com.webdriver.WebDriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SearchPage extends BasePage {

    public SearchPage() {

        WebDriverManager.getDriver().get("http://www.flipkart.com");
    }

    @FindBy(xpath = "//button[@class='_2AkmmA _29YdH8']")
    private WebElement closePopup;

    @FindBy(name = "q")
    private  WebElement searchBox;

    @FindBy(xpath = "//button[@class='vh79eN']")
    private WebElement searchButton;

    public ProductsListPage searchProduct(String productSearched) {

        try {

            webDriverWait.until(ExpectedConditions.visibilityOf(closePopup));
            if(closePopup.isDisplayed()) {

                closePopup.click();
            }
        } catch (NoSuchElementException e) {
        }

        searchBox.sendKeys(productSearched);
        searchButton.click();
        return new ProductsListPage(productSearched);
    }


}
