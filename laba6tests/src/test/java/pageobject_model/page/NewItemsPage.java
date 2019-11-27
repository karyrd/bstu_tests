package pageobject_model.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class NewItemsPage {

    WebDriver driver;

    public NewItemsPage (WebDriver driver) {
        this.driver = driver;
    }

    public MakingOrderPage chooseNewItem() {
        List<WebElement> newClothElements = waitForElementsLocatedBy(driver, By.className("products-catalog__list"));
        WebElement newClothElement = newClothElements.get(0).findElement(By.className("products-list-item__link"));

        String newClothElementPrice = newClothElement.findElements(By.className("price__actual")).get(0).getText();
        String newClothElementName = newClothElement.findElements(By.className("products-list-item__brand")).get(0).getText(); // "{brand} {type}"

        newClothElement.click();

        return new MakingOrderPage(driver, newClothElementPrice, newClothElementName);
    }

    public boolean filterPricesCheck(int left_value, int right_value) {
        boolean result = true;
        WebElement dropdownPrice = waitForElementLocatedBy(driver, By.className("multifilter_price"));
        dropdownPrice.click();

        dropdownPrice.findElement(By.className("range__value_left")).sendKeys(valueOf(left_value));
        dropdownPrice.findElement(By.className("range__value_right")).sendKeys(valueOf(right_value));
        dropdownPrice.findElement(By.className("multifilter-actions__apply")).click();

        try {
            List<WebElement> filterResultItems = waitForElementsLocatedBy(driver, By.className("price__actual"));

            for (WebElement filterResultItem : filterResultItems) {
                String price = "";
                for (int i = 0; i < filterResultItem.getText().length() - 3; i++) {
                    price += filterResultItem.getText().charAt(i);
                }
                if(parseInt(price) < left_value || parseInt(price) > right_value) {
                    result = false;
                    break;
                }
            }
        } catch(Exception ex) {
            try {
                if (waitForElementLocatedBy(driver, By.className("products-catalog__not-found")).
                        findElement(By.className("title")).getText()
                        == "В выбранной категории ничего не найдено") {
                    return true;
                }
            } catch(Exception ex_) {
                return false;
            }
        }

        return result;
    }

    private static List<WebElement> waitForElementsLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.
                        presenceOfAllElementsLocatedBy(by));
    }

    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .presenceOfElementLocated(by));
    }

}
