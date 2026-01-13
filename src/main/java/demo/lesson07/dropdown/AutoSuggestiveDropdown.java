package demo.lesson07.dropdown;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class AutoSuggestiveDropdown {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.manage().window().maximize();

        String searchBoxText = "";
        WebElement searchBox = driver.findElement(By.id("autosuggest"));
        searchBox.sendKeys("ind");
        Thread.sleep(1000);
        List<WebElement> dropdownList = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));
        for (WebElement item : dropdownList) {
            if (item.getText().equalsIgnoreCase("India")) {
                item.click();
                searchBoxText = item.getText();
                break;
            }
        }
        Thread.sleep(1000);
//        Assert.assertEquals(driver.findElement(By.id("autosuggest")).getText(), "India");
    }
}
