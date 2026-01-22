package selenium.demo.lesson07.dropdown;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.time.Duration;

public class UpdatedDropdown {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.manage().window().maximize();

        driver.findElement(By.id("divpaxinfo")).click();
        Thread.sleep(2000);

        for (int i = 0; i < 6; i++) {
            driver.findElement(By.id("hrefIncAdt")).click();
        }
        for (int i = 0; i < 2; i++) {
            driver.findElement(By.id("hrefIncChd")).click();
        }
        for (int i = 0; i < 4; i++) {
            driver.findElement(By.id("hrefIncInf")).click();
        }
        driver.findElement(By.id("btnclosepaxoption")).click();

        String observedResult = driver.findElement(By.id("divpaxinfo")).getText();
        String expectedResult = "7 Adult, 2 Child, 4 Infant";
        Assert.assertEquals(observedResult, expectedResult);
    }
}
