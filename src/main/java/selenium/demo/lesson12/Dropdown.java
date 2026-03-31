package selenium.demo.lesson12;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Dropdown {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();

        driver.findElement(By.id("autocomplete")).sendKeys("ind");
        Thread.sleep(2000);
        driver.findElement(By.id("autocomplete")).sendKeys(Keys.DOWN);
        driver.findElement(By.id("autocomplete")).sendKeys(Keys.DOWN);
        System.out.println(driver.findElement(By.id("autocomplete")).getAttribute("value"));
    }

    public static void autosuggestUnknownOrderDropdown(WebDriver driver) {
        WebElement countryField = driver.findElement(By.id("autocomplete"));
        countryField.sendKeys("ind"); // Sử dụng WebDriverWait thay vì Thread.sleep
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ui-menu-item div")));
        List<WebElement> options = driver.findElements(By.cssSelector(".ui-menu-item div"));
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase("India")) {
                option.click();
                break;
            }
        }
    }

    public static void autosuggestOptimized(WebDriver driver) {
        WebElement countryField = driver.findElement(By.id("autocomplete"));
        countryField.sendKeys("ind");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ui-menu-item div")));
        List<WebElement> options = driver.findElements(By.cssSelector(".ui-menu-item div")); // Ưu tiên chọn theo index (ví dụ index = 1
        int preferredIndex = 1; // giả sử bạn biết chắc India thường ở vị trí thứ 2
        if (options.size() > preferredIndex && options.get(preferredIndex).getText().equalsIgnoreCase("India")) {
            options.get(preferredIndex).click();
        } else { // fallback: duyệt toàn bộ để tìm đúng "India"
            for (WebElement option : options) {
                if (option.getText().equalsIgnoreCase("India")) {
                    option.click();
                    break;
                }
            }
        }
    }
}
