package selenium.demo.lesson06.locators.alert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.time.Duration;

public class Alert {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();


        driver.findElement(By.id("alertbtn")).click();
        String acceptText = driver.switchTo().alert().getText();
        Assert.assertEquals(acceptText, "Hello , share this practice page and share your knowledge");
        driver.switchTo().alert().accept();

        driver.findElement(By.id("confirmbtn")).click();
        String declineText = driver.switchTo().alert().getText();
        Assert.assertEquals(declineText, "Hello , Are you sure you want to confirm?");
        driver.switchTo().alert().dismiss();
    }
}
