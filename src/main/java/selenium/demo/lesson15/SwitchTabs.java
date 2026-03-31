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
        //Selenium 4 hỗ trợ mở tab mới hoặc window mới.
        //Ở đây mở một window mới (cửa sổ riêng biệt).
        driver.switchTo().newWindow(WindowType.WINDOW);

        //getWindowHandles() trả về tất cả ID của các window đang mở.
        //Dùng Iterator để lấy lần lượt ID của parent window và child window.
        //switchTo().window(childWindowId) → chuyển sang window mới.
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> it = handles.iterator();
        String parentWindowId = it.next();
        String childWindowId = it.next();
        driver.switchTo().window(childWindowId);

        //Mở trang chính của Rahul Shetty Academy.
        //Tìm phần tử h3 chứa tên khóa học “Selenium WebDriver with Java - Basics to Advanced”.
        //Lấy text và in ra console.
        driver.get("https://rahulshettyacademy.com/");
        WebElement courseTitle = driver.findElement(By.xpath("//img[@alt='Selenium WebDriver with Java - Basics to Advanced']/ancestor::div[contains(@class,'rounded-lg')]//h3"));
        String text = courseTitle.getText();
        System.out.println(text);

        //Chuyển lại về parent window.
        //Tìm ô nhập tên và nhập text vừa lấy từ child window.
        driver.switchTo().window(parentWindowId);
        WebElement name = driver.findElement(By.xpath("(//input[@name='name'])[1]"));
        name.sendKeys(text);

        // Partial screenshot
        //Chụp ảnh chỉ riêng phần tử name (partial screenshot).
        //Lưu file ảnh thành logo.png.
        File file = name.getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(file, new File("logo.png"));

        // get height & width
        //Lấy chiều cao và chiều rộng của phần tử name.
        //Dùng để kiểm tra UI (ví dụ: kích thước input box có đúng yêu cầu không).
        System.out.println(name.getRect().getDimension().getHeight());
        System.out.println(name.getRect().getDimension().getWidth());
    }
}
