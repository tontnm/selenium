package selenium.demo.lesson15;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class SwitchTabs {
    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://rahulshettyacademy.com/angularpractice/");
        driver.manage().window().maximize();

        // switch window
        driver.switchTo().newWindow(WindowType.WINDOW);

        Set<String> handles = driver.getWindowHandles();
        Iterator<String> it = handles.iterator();
        String parentWindowId = it.next();
        String childWindowId = it.next();
        driver.switchTo().window(childWindowId);
        driver.get("https://rahulshettyacademy.com/");
        WebElement courseTitle = driver.findElement(By.xpath("//img[@alt='Selenium WebDriver with Java - Basics to Advanced']/ancestor::div[contains(@class,'rounded-lg')]//h3"));
        String text = courseTitle.getText();
        System.out.println(text);
        driver.switchTo().window(parentWindowId);
        WebElement name = driver.findElement(By.xpath("(//input[@name='name'])[1]"));
        name.sendKeys(text);

        // Partial screenshot
        File file = name.getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(file, new File("logo.png"));

        // get height & width
        System.out.println(name.getRect().getDimension().getHeight());
        System.out.println(name.getRect().getDimension().getWidth());
    }
}
