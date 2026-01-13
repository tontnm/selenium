package demo.lesson10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class SwitchTab {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
        driver.manage().window().maximize();

        driver.findElement(By.cssSelector(".blinkingText")).click();
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        String parentID = it.next();
        String childID = it.next();

        driver.switchTo().window(childID);
        String email = driver.findElement(By.cssSelector(".im-para.red")).getText();
        email = email.split(" ")[4];
        driver.switchTo().window(parentID);
        driver.findElement(By.id("username")).sendKeys("rahulshettyacademy");
        driver.findElement(By.id("password")).sendKeys("learning");
        driver.findElement(By.xpath("(//input[@id='usertype'])[2]")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal-content")));

        WebElement modalText = driver.findElement(By.cssSelector(".modal-body"));
        String popupText = modalText.getText();

        Assert.assertEquals(popupText, "You will be limited to only fewer functionalities of the app. Proceed?");

        // Click nút OK trong modal
        driver.findElement(By.id("okayBtn")).click();

        driver.findElement(By.cssSelector("select[class='form-control']")).click();
        WebElement dd = driver.findElement(By.cssSelector("select[class='form-control']"));
        Select dropdown = new Select(dd);
        dropdown.selectByValue("consult");
//        driver.findElement(By.id("okayBtn")).click();

        WebElement checkbox = driver.findElement(By.id("terms"));
        Boolean selectedCheckbox = checkbox.isSelected();
        Assert.assertFalse(selectedCheckbox);
        if (!selectedCheckbox) {
            checkbox.click();
            Assert.assertTrue(true);
        }

        driver.findElement(By.id("signInBtn")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".nav-link.btn.btn-primary")));
    }
}
