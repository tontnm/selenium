package demo.lesson07.dropdown;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DynamicDropdown {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.manage().window().maximize();

        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.cssSelector("a[value='BLR']")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.xpath("(//a[@value='MAA'])[2]")).click();

        //
        driver.findElement(By.xpath("//div[@id='ctl00_mainContent_ddl_originStation1_CTNR'] //a[@value='BLR']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA']")).click();

//        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();

//        Thread.sleep(2000);

//        WebElement dynamicDropdown = driver.findElement(By.id("ctl00_mainContent_ddl_originStation1"));
//        Select dropdown = new Select(dynamicDropdown);

//        String fromObservedResult = driver.findElement(By.id("ctl00_mainContent_ddl_originStation1")).getText();
//        String fromObservedResult = dropdown.getFirstSelectedOption().getText();
//        System.out.println(dropdown.getFirstSelectedOption().getText());
//        String fromExpectedResult = "Bengaluru (BLR)";
//
//        String toObservedResult = driver.findElement(By.id("ctl00_mainContent_ddl_destinationStation1")).getText();
//        System.out.println(toObservedResult);
//        String toExpectedResult = "Chennai (MAA)";
//
//        Assert.assertEquals(fromObservedResult, fromExpectedResult);
//        Assert.assertEquals(toObservedResult, toExpectedResult);
    }
}
