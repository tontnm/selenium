package selenium.demo.lesson10;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class AjaxMouseInteractions {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.amazon.com");
        driver.manage().window().maximize();

        Actions a = new Actions(driver);
        List<WebElement> continueBtn = driver.findElements(By.className("a-button-text"));
        if (!continueBtn.isEmpty()) {
            driver.findElement(By.className("a-button-text")).click();
        }
        a.moveToElement(driver.findElement(By.id("twotabsearchtextbox"))).click().keyDown(Keys.SHIFT).sendKeys("hello").doubleClick().build().perform();

        a.moveToElement(driver.findElement(By.id("nav-link-accountList"))).contextClick().build().perform();
    }
}

/*
*  1. Di chuột (Mouse Hover)
java
WebElement menu = driver.findElement(By.id("menu"));
Actions a = new Actions(driver);
a.moveToElement(menu).perform();
👉 Dùng để mở dropdown hoặc hiển thị tooltip khi di chuột vào phần tử.

2. Click và Double Click
java
WebElement button = driver.findElement(By.id("btn"));
a.click(button).perform();        // Click
a.doubleClick(button).perform();  // Double click
3. Right Click (Context Click)
java
WebElement element = driver.findElement(By.id("context"));
a.contextClick(element).perform();
👉 Mở menu chuột phải.

4. Kéo thả (Drag and Drop)
java
WebElement source = driver.findElement(By.id("drag"));
WebElement target = driver.findElement(By.id("drop"));
a.dragAndDrop(source, target).perform();
5. Nhấn giữ và thả (Click and Hold → Release)
java
WebElement element = driver.findElement(By.id("item"));
a.clickAndHold(element).moveByOffset(100, 0).release().perform();
👉 Dùng để kéo đối tượng theo tọa độ.

6. Gõ phím (Keyboard Actions)
java
a.sendKeys(Keys.ENTER).perform();   // Nhấn Enter
a.sendKeys(Keys.chord(Keys.CONTROL, "a")).perform(); // Ctrl + A
📌 Tóm gọn
Actions giúp bạn mô phỏng chuột và bàn phím nâng cao trong Selenium.

Các thao tác phổ biến: hover, click, double click, right click, drag‑and‑drop, keyboard shortcuts.
*
* 📌 1. Dùng trực tiếp .perform()
Ví dụ:

java
Actions a = new Actions(driver);
a.moveToElement(element).click().perform();
Ở đây, bạn xây dựng và thực thi ngay lập tức chuỗi hành động.

.perform() sẽ gửi hành động xuống trình duyệt ngay.

Thường dùng cho các thao tác ngắn gọn, đơn lẻ.

📌 2. Dùng .build().perform()
Ví dụ:

java
Actions a = new Actions(driver);
Action action = a.moveToElement(element).click().build();
action.perform();
.build() tạo ra một Action object từ chuỗi hành động.

Sau đó .perform() mới thực thi.

Hữu ích khi bạn muốn lưu trữ, tái sử dụng, hoặc chạy nhiều lần cùng một chuỗi hành động.

✅ Kết luận
Nếu bạn chỉ cần chạy hành động một lần duy nhất, thì không cần .build(), chỉ .perform() là đủ.

Nếu bạn muốn tái sử dụng hoặc quản lý chuỗi hành động phức tạp, thì dùng .build().perform().
* */