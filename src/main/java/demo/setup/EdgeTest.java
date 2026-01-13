package demo.setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.edge.driver", "C:\\browser drivers\\msedgedriver.exe");
        EdgeOptions options = new EdgeOptions();
        options.setBinary("C:\\browser-for-testing\\edgedriver_win64\\msedgedriver.exe");

        WebDriver driver = new EdgeDriver();
        driver.get("google.com");
        String title = driver.getTitle();
        String currentURL = driver.getCurrentUrl();
        driver.manage().window().maximize();
        System.out.println(title);
        System.out.println(currentURL);
        driver.quit();
    }
}