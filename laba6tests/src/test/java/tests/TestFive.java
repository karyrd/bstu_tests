package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestFive {

    @Test
    public void SalePageCheck() {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.lamoda.by");

        WebElement onSaleLink = waitForElementLocatedBy(driver, By.xpath("//*[@id='menu-wrapper']/div/div[2]/a[10]"));
        onSaleLink.click();
        List<WebElement> onSaleElements = waitForElementsLocatedBy(driver, By.className("products-catalog__list"));
        int actualAmountOfElementsOnSale = 0;
        for(WebElement searchResultElement : onSaleElements) {
            if(isElementOnSale(searchResultElement)) {
                actualAmountOfElementsOnSale++;
            }
        }

        Assert.assertTrue(onSaleElements.size() == actualAmountOfElementsOnSale, "Not all elements on page are on sale");
        driver.quit();
    }

    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, 300)
                .until(ExpectedConditions
                        .presenceOfElementLocated(by));
    }
    private static List<WebElement> waitForElementsLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, 300)
                .until(ExpectedConditions.
                        presenceOfAllElementsLocatedBy(by));
    }
    private static boolean isElementOnSale(WebElement searchResultElement) {
        if((searchResultElement.findElements(By.className("price__old")).size() > 0)) {
            return true;
        }
        return false;
    }

}
