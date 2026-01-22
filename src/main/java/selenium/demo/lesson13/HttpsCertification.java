package selenium.demo.lesson13;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HttpsCertification {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("ipaddress:4444");
        options.setCapability("proxy", proxy);

        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        options.setAcceptInsecureCerts(true);
        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().deleteCookieNamed("abc");
//        driver.manage().addCookie("");
        // click on any link
        // login page - verify login url

        driver.get("https://expired.badssl.com/");

        System.out.println(driver.getTitle());
    }
}
