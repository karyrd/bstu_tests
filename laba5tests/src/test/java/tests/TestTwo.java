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

public class TestTwo {

    @Test
    public void CartFunctionalityCheck() throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.lamoda.by");

        WebElement newCloth = waitForElementsLocatedBy(driver, By.className("menu__categories")).get(0).
                findElements(By.tagName("div")).get(4).findElements(By.tagName("a")).get(0);
        newCloth.click();
        List<WebElement> newClothElements = waitForElementsLocatedBy(driver, By.className("products-catalog__list"));
        WebElement newClothElement = newClothElements.get(0).findElement(By.className("products-list-item__link"));

        String newClothElementPrice = newClothElement.findElements(By.className("price__actual")).get(0).getText();
        String newClothElementName = newClothElement.findElements(By.className("products-list-item__brand")).get(0).getText(); // "{brand} {type}"

        newClothElement.click();
        WebElement sizeDropdown = waitForElementLocatedBy(driver, By.className("product__sizes-select-container"));
        Thread.sleep(100);
        sizeDropdown.click();
        WebElement sizeOptionsList = waitForElementsLocatedBy(driver, By.className("ii-select__columns")).get(1).
                findElements(By.className("ii-select__column_native")).get(0);

        for(WebElement sizeOption : sizeOptionsList.findElements(By.className("ii-select__option"))) {
            if(!sizeOption.getAttribute("class").contains("ii-select__option_disabled")) {
                sizeOption.click();
                break;
            }
        }

        WebElement addToCartButton = waitForElementLocatedBy(driver, By.className("product__cart-add-button"));
        addToCartButton.click();
        WebElement goToCartButton = waitForElementLocatedBy(driver, By.className("post-cart-add__basket"));
        goToCartButton.click();

        WebElement newClothElementInCart = waitForElementLocatedBy(driver, By.className("cp__body")).
                findElements(By.className("cpi")).get(0);
        String newClothElementInCartPrice = newClothElementInCart.findElements(By.className("price_current")).get(0).getText().
                split(" ", 2)[0];
        String newClothElementInCartName = newClothElementInCart.findElements(By.className("cpi__name")).get(0).
                findElement(By.tagName("b")).getText(); // {brand}

        Assert.assertTrue((newClothElementInCartPrice == newClothElementPrice) &&
                (newClothElementName.contains(newClothElementInCartName)),
                "Cart is not working correctly");
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

}
