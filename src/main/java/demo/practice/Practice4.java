package demo.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Iterator;
import java.util.Set;

public class Practice4 {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();

        // a[href='/windows']
        driver.findElement(By.cssSelector("a[href='/windows']")).click();
        Set<String> w = driver.getWindowHandles();
        Iterator<String> it = w.iterator();
        String parentWindow = it.next();
        driver.findElement(By.cssSelector("a[href='/windows/new']")).click();
        driver.switchTo().window(it.next());

        System.out.println(driver.findElement(By.cssSelector("div[class='example'] h3")).getText());
        driver.switchTo().window(parentWindow);
        System.out.println(driver.findElement(By.cssSelector("div[class='example'] h3")).getText());
    }
}
