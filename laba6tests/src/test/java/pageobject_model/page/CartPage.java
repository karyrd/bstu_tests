package pageobject_model.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    WebDriver driver;
    String initialPrice;
    String initialName;

    public CartPage (WebDriver driver, String initPrice, String initName) {
        this.driver = driver;
        initialPrice = initPrice;
        initialName = initName;
    }

    public boolean compareInitialAndCartNameAndPrice() {
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

        return (initialPrice == newClothElementInCartPrice && initialName.contains(newClothElementInCartName));
    }

    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, 300)
                .until(ExpectedConditions
                        .presenceOfElementLocated(by));
    }

}
