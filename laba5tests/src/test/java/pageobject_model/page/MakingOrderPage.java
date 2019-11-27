package pageobject_model.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MakingOrderPage  {

    WebDriver driver;
    String initialPrice;
    String initialName;

    public MakingOrderPage (WebDriver driver, String initPrice, String initName) {
        this.driver = driver;
        initialPrice = initPrice;
        initialName = initName;
    }

    public CartPage addToCart() throws InterruptedException {
        WebElement sizeDropdown = waitForElementsLocatedBy(driver, By.className("product__sizes-select-container")).get(0);
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

        return new CartPage(driver, initialPrice, initialName);
    }

    private static List<WebElement> waitForElementsLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, 300)
                .until(ExpectedConditions.
                        presenceOfAllElementsLocatedBy(by));
    }

}
