package net.com.pages;

import net.com.webdriver.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    WebDriverWait webDriverWait = new WebDriverWait(WebDriverManager.getDriver(), 30);

    WebDriverWait ajaxWait = new WebDriverWait(WebDriverManager.getDriver(), 30, 100);


    BasePage() {

        PageFactory.initElements(WebDriverManager.getDriver(), this);
    }

    public String retryingGetElementTextAndClick(By by) {
        String text = "";
        int attempts = 0;
        while(attempts < 30) {
            try {
                text = WebDriverManager.getDriver().findElement(by).getText();
                WebDriverManager.getDriver().findElement(by).click();
                break;
            } catch(StaleElementReferenceException e) {
                System.out.println("Found in Attempt = " + attempts);
            } catch (WebDriverException e) {
                System.out.println("Attempt in WebDriverException catch = " + attempts);
            }
            attempts++;
        }
        return text;
    }

}
