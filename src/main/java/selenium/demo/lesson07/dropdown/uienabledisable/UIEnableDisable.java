package selenium.demo.lesson07.dropdown.uienabledisable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class UIEnableDisable {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.manage().window().maximize();

        WebElement calendar = driver.findElement(By.id("Div1"));
        String element = calendar.getDomAttribute("style");
        if (element.contains("1")) {
            Assert.assertTrue(true);
            System.out.println("calendar enable");
        } else {
            Assert.assertFalse(false);
            System.out.println("calendar disable");
        }
        //
        driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();
        Thread.sleep(1000);
        element = calendar.getDomAttribute("style");
        if (element.contains("1")) {
            Assert.assertTrue(true);
            System.out.println("calendar enable");
        } else {
            Assert.assertFalse(false);
            System.out.println("calendar disable");
        }

    }
}
