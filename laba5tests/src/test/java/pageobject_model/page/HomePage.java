package pageobject_model.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage {

    WebDriver driver;

    public HomePage (WebDriver driver) {
        this.driver = driver;
    }

    public NewItemsPage openNewItemsPage() {
        WebElement newCloth = waitForElementsLocatedBy(driver, By.className("menu__categories")).get(0).
                findElements(By.tagName("div")).get(4).findElements(By.tagName("a")).get(0);
        newCloth.click();

        return new NewItemsPage(driver);
    }

    private static List<WebElement> waitForElementsLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, 300)
                .until(ExpectedConditions.
                        presenceOfAllElementsLocatedBy(by));
    }

}
