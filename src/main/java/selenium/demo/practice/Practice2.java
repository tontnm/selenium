package selenium.demo.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Practice2 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.manage().window().maximize();

        WebElement searchBox = driver.findElement(By.id("autosuggest"));
        searchBox.sendKeys("ind");
        Thread.sleep(1000);
        List<WebElement> dropdownList = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));
        for (WebElement item : dropdownList) {
            if (item.getText().equalsIgnoreCase("India")) {
                item.click();
                break;
            }
        }

        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("a[value='BLR']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//a[@value='MAA'])[2]")).click();

        WebElement calendar = driver.findElement(By.id("Div1"));
        driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();
        Thread.sleep(1000);
        String element = calendar.getDomAttribute("style");
        if (element.contains("1")) {
            Assert.assertTrue(true);
            System.out.println("calendar enable");
        } else {
            Assert.assertFalse(false);
            System.out.println("calendar disable");
        }

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

        WebElement staticDropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
        Select dropdown = new Select(staticDropdown);
        dropdown.selectByIndex(3);
        System.out.println(dropdown.getFirstSelectedOption().getText());
        dropdown.selectByVisibleText("AED");
        System.out.println(dropdown.getFirstSelectedOption().getText());
        dropdown.selectByValue("INR");
        System.out.println(dropdown.getFirstSelectedOption().getText());
        Assert.assertEquals(dropdown.getFirstSelectedOption().getText(), "INR");

        List<WebElement> checkboxes = driver.findElements(By.id("discount-checkbox"));
        for (WebElement checkbox : checkboxes) {
            Boolean selectedCheckbox = checkbox.isSelected();
            Assert.assertFalse(selectedCheckbox);
            if (!selectedCheckbox) {
                checkbox.click();
                Assert.assertTrue(true);
            }
        }

        driver.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();
    }
}
