import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class TestOne {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.lamoda.by");

        WebElement searchBar = waitForElementLocatedBy(driver, By.xpath("//*[@id='menu-wrapper']/div/div[1]/input"));
        WebElement searchButton = waitForElementLocatedBy(driver, By.xpath("//*[@id='menu-wrapper']/div/div[1]/div[2]"));
        searchBar.sendKeys("джинсы mango");
        searchButton.click();
        List<WebElement> searchResultElements = waitForElementsLocatedBy(driver, By.className("products-list-item"));
        System.out.println("amount of products in search result: " + searchResultElements.size());
        int amountOfElementsWithAppropriateTag = 0;
        for(WebElement searchResultElement : searchResultElements) {
            if(doSearchResultItemsHaveAppropriateTag(searchResultElement)) {
                amountOfElementsWithAppropriateTag++;
            }
        }
        System.out.println("amount of products that satisfy search conditions: " + amountOfElementsWithAppropriateTag);

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
    private static boolean doSearchResultItemsHaveAppropriateTag(WebElement searchResultElement) {
        if(searchResultElement.findElement(By.className("products-list-item__brand-name")).getText().toLowerCase().matches(".*mango.*")
                && searchResultElement.findElement(By.className("products-list-item__type")).getText().toLowerCase().matches(".*дж(егг)*инс.*")) {
            return true;
        }
        return false;
    }

}
