import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class TestFive {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.lamoda.by");

        WebElement onSaleLink = waitForElementLocatedBy(driver, By.xpath("//*[@id='menu-wrapper']/div/div[2]/a[10]"));
        onSaleLink.click();
        List<WebElement> onSaleElements = waitForElementsLocatedBy(driver, By.className("products-list-item"));
        System.out.println("amount of products in sale category: " + onSaleElements.size());
        int actualAmountOfElementsOnSale = 0;
        for(WebElement searchResultElement : onSaleElements) {
            if(isElementOnSale(searchResultElement)) {
                actualAmountOfElementsOnSale++;
            }
        }
        System.out.println("amount of products that are actually on sale: " + actualAmountOfElementsOnSale);

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
