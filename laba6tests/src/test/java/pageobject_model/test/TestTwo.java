package pageobject_model.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject_model.page.HomePage;

import java.util.List;

public class TestTwo {

    @Test
    public void CartFunctionalityCheck() throws InterruptedException {

        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.lamoda.by");

        boolean doesPriceAndNameMatch = new HomePage(driver).
                openNewItemsPage().
                chooseNewItem().
                addToCart().
                compareInitialAndCartNameAndPrice();

        Assert.assertTrue(doesPriceAndNameMatch, "Cart is not working correctly");
        driver.quit();
    }

}
