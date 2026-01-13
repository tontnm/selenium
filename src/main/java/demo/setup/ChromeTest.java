package demo.setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("http://google.com");
        String title = driver.getTitle();
        String currentURL = driver.getCurrentUrl();
        driver.manage().window().maximize();
        System.out.println(title);
        System.out.println(currentURL);
//        driver.close();
    }
}
