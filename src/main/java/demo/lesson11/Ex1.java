package demo.lesson11;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Ex1 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();

        WebElement footerDiv = driver.findElement(By.id("gf-BIG"));
        WebElement columnDriver = footerDiv.findElement(By.xpath("//table/tbody/tr/td[1]/ul"));

        // Titles mong đợi (không có Practice Page)
        String[] titles = {
                "Learn REST API Design - REST API Tutorial",
                "Appium Mobile Automation Testing from Scratch + Frameworks Tutorial | Udemy",
                "Apache JMeter - Apache JMeter™",
                "The World’s Most Popular API Testing Tool | SoapUI"
        };

        List<WebElement> links = columnDriver.findElements(By.tagName("a"));

        // Mở các link từ vị trí 1 (bỏ Practice Page)
        for (int i = 1; i < links.size(); i++) {
            String clickOnLinkTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
            links.get(i).sendKeys(clickOnLinkTab);
        }

        // Chờ tất cả tab mở
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> driver.getWindowHandles().size() >= titles.length + 1); // +1 vì có tab gốc

        Set<String> tabs = driver.getWindowHandles();
        String originalTab = driver.getWindowHandle();

        for (String tab : tabs) {
            if (tab.equals(originalTab)) {
                continue; // bỏ qua tab gốc (Practice Page)
            }

            driver.switchTo().window(tab);
            String actualTitle = driver.getTitle();
            System.out.println(actualTitle);

            // Kiểm tra title có nằm trong danh sách mong đợi
            boolean match = false;
            for (String expected : titles) {
                if (actualTitle.equals(expected)) {
                    match = true;
                    break;
                }
            }
            Assert.assertTrue(match, "Title không khớp với bất kỳ giá trị mong đợi nào");
        }

        driver.quit();
    }
}
