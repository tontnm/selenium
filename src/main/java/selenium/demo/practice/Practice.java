package selenium.demo.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.Duration;

public class Practice {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        driver.manage().window().maximize();

        driver.findElement(By.cssSelector("div[class='form-group'] input[name='name']")).sendKeys("Ken");
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys("Ken");
        driver.findElement(By.id("exampleInputPassword1")).sendKeys("Ken");
        driver.findElement(By.id("exampleCheck1")).click();

        WebElement staticDropdown = driver.findElement(By.id("exampleFormControlSelect1"));
        Select dropdown = new Select(staticDropdown);
        dropdown.selectByIndex(1);
        driver.findElement(By.cssSelector("input[value='Submit']")).click();
        Thread.sleep(1000);

        String observed = driver.findElement(By.cssSelector(".alert.alert-success.alert-dismissible")).getText();
        Assert.assertEquals(observed, "×\n" + "Success! The Form has been submitted successfully!.");
    }
}
