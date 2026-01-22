package selenium.real;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

    public static WebDriver getDriver(String browser) {
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":
//                System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
//                System.setProperty("webdriver.gecko.driver", "C:\\browser drivers\\geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "edge":
//                System.setProperty("webdriver.edge.driver", "C:\\browser drivers\\msedgedriver.exe");
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        driver.manage().window().maximize();
        return driver;
    }
}

