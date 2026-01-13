package demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) throws InterruptedException {
        String prodName = "ADIDAS ORIGINAL";

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client/#/auth/login");
        driver.manage().window().maximize();

        driver.findElement(By.id("userEmail")).sendKeys("tontnm@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Aa!1234567890");
        driver.findElement(By.id("login")).click();

        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        WebElement prod = products.stream().
                filter(p -> p.findElement(By.cssSelector("b")).
                        getText().equalsIgnoreCase(prodName)).
                findFirst().orElse(null);
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
//        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ng-animating")));
        w.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        Thread.sleep(2000);
//        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();

        //.cartSection h3
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

        Boolean matched = cartProducts.stream().anyMatch(cp -> cp.getText().equalsIgnoreCase(prodName));
        Assert.assertTrue(matched);

        //
        driver.findElement(By.cssSelector(".totalRow button")).click();

        //
        driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");

        /*
        Dùng Actions thường để mô phỏng tổ hợp phím, drag & drop, hover, keyboard events phức tạp.
        Với input đơn giản, cách này dài dòng, không cần thiết.
        Chỉ nên dùng khi bạn cần mô phỏng hành vi người dùng nâng cao (ví dụ: nhấn phím đặc biệt, kết hợp nhiều thao tác).
        * */
//        Actions a = new Actions(driver);
//        a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "ind").build().perform();
        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        //ta-item
        selectFromAutoSuggest(driver, ".ta-item", "india");

        WebElement placeOrderBtn = w.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));
        placeOrderBtn.click();

        //hero-primary
        String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText().trim();
        Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

        driver.close();
    }

    public static void selectFromAutoSuggest(WebDriver driver, String locator, String valueToSelect) {
        List<WebElement> options = driver.findElements(By.cssSelector(locator));
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(valueToSelect)) {
                option.click();
                break;
            }
        }
    }
}
