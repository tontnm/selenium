package demo.lesson12;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.util.List;

public class Scroll {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();

//        compareTotal(driver);
        countRow(driver);
    }

    public static void compareTotal(WebDriver driver) throws InterruptedException {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("window.scrollBy(0,500)");
        Thread.sleep(3000);
        je.executeScript("document.querySelector('.tableFixHead').scrollTop=5000");

        //
        List<WebElement> values = driver.findElements(By.cssSelector(".tableFixHead td:nth-child(4)"));
        String[] parts = driver.findElement(By.cssSelector(".totalAmount")).getText().split(":");
        int amount = Integer.parseInt(parts[1].trim());

        int total = 0;
        for (int i = 0; i < values.size(); i++) {
            total += Integer.parseInt(values.get(i).getText());
        }

        Assert.assertEquals(amount, total);
    }

    public static void countRow(WebDriver driver) throws InterruptedException {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("window.scrollBy(0,500)");
        Thread.sleep(2000);
        List<WebElement> rows = driver.findElements(By.cssSelector(".left-align #product tr"));
        List<WebElement> columns = driver.findElements(By.cssSelector(".left-align #product th"));
        List<WebElement> rowSecond = rows.get(2).findElements(By.tagName("td"));

        System.out.println(rows.size());
        System.out.println(columns.size());
        for (int i = 0; i < rowSecond.size(); i++) {
            System.out.println(rowSecond.get(i).getText());
        }

    }
}
