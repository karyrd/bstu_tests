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

public class TestSix {

    @Test
    public void FiltersCheck() throws InterruptedException {

        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.lamoda.by");

        boolean resultOne = new HomePage(driver).
                openNewItemsPage().
                filterPricesCheck(50, 250);
        boolean resultTwo = new HomePage(driver).
                openNewItemsPage().
                filterPricesCheck(-700, -100);
        boolean resultThree = new HomePage(driver).
                openNewItemsPage().
                filterPricesCheck(2500, 100);
        boolean resultFour = new HomePage(driver).
                openNewItemsPage().
                filterPricesCheck(0, 0);

        Assert.assertTrue(resultOne && resultTwo && resultThree && resultFour, "Cart is not working correctly");
        driver.quit();
    }

}
