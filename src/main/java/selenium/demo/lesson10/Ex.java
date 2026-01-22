package selenium.demo.lesson10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Ex {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();

        driver.findElement(By.cssSelector("a[href='/nested_frames'")).click();

        // Bước 1: switch vào frame ngoài cùng "frame-top"
        driver.switchTo().frame("frame-top");

// Bước 2: trong frame-top có frameset-middle, ta switch tiếp vào frame "frame-middle"
        driver.switchTo().frame("frame-middle");

// Bước 3: tìm và thao tác với <div id="content">
        WebElement contentDiv = driver.findElement(By.id("content"));
        System.out.println("Text trong div: " + contentDiv.getText());

// Bước 4: quay lại nội dung chính nếu cần
        driver.switchTo().defaultContent();

    }
}
