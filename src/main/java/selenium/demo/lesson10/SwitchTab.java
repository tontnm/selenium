package selenium.demo.lesson10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class SwitchTab {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
        driver.manage().window().maximize();

        driver.findElement(By.cssSelector(".blinkingText")).click();
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        String parentID = it.next();
        String childID = it.next();

        driver.switchTo().window(childID);
        String email = driver.findElement(By.cssSelector(".im-para.red")).getText();
        email = email.split(" ")[4];

        driver.switchTo().window(parentID);
        driver.findElement(By.id("username")).sendKeys("rahulshettyacademy");
        driver.findElement(By.id("password")).sendKeys("learning");
        driver.findElement(By.xpath("(//input[@id='usertype'])[2]")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal-content")));

        WebElement modalText = driver.findElement(By.cssSelector(".modal-body"));
        String popupText = modalText.getText();

        Assert.assertEquals(popupText, "You will be limited to only fewer functionalities of the app. Proceed?");

        // Click nút OK trong modal
        driver.findElement(By.id("okayBtn")).click();

        driver.findElement(By.cssSelector("select[class='form-control']")).click();
        WebElement dd = driver.findElement(By.cssSelector("select[class='form-control']"));
        Select dropdown = new Select(dd);
        dropdown.selectByValue("consult");
//        driver.findElement(By.id("okayBtn")).click();

        WebElement checkbox = driver.findElement(By.id("terms"));
        Boolean selectedCheckbox = checkbox.isSelected();
        Assert.assertFalse(selectedCheckbox);
        if (!selectedCheckbox) {
            checkbox.click();
            Assert.assertTrue(true);
        }

        driver.findElement(By.id("signInBtn")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".nav-link.btn.btn-primary")));
    }
}

/*
 📌 Công dụng của driver.getWindowHandles()
Trong Selenium WebDriver, khi bạn mở nhiều tab hoặc cửa sổ trong trình duyệt, mỗi cửa sổ/tab sẽ có một Window Handle (ID duy nhất).

Lệnh:

java
Set<String> windows = driver.getWindowHandles();
sẽ trả về một tập hợp (Set) chứa tất cả các Window Handle hiện có.

Bạn dùng nó để chuyển đổi giữa các tab/cửa sổ khi test automation.

✨ Ví dụ phổ biến thực tế
1. Chuyển sang tab mới mở
java
// Lấy tất cả window handles
Set<String> windows = driver.getWindowHandles();

// Duyệt qua từng window
for (String win : windows) {
    driver.switchTo().window(win);
    // Kiểm tra nếu title đúng thì dừng lại
    if (driver.getTitle().equals("New Tab Title")) {
        break;
    }
}
👉 Thường dùng khi click vào link mở tab mới (ví dụ: “Privacy Policy” mở trong tab khác).

2. Đóng popup và quay lại main window
java
String mainWindow = driver.getWindowHandle();
Set<String> windows = driver.getWindowHandles();

for (String win : windows) {
    if (!win.equals(mainWindow)) {
        driver.switchTo().window(win);
        driver.close(); // đóng popup
    }
}
driver.switchTo().window(mainWindow); // quay lại main window
👉 Dùng khi trang web mở popup quảng cáo hoặc form đăng ký.

3. Làm việc với nhiều tab (ví dụ: login qua OAuth)
java
String parent = driver.getWindowHandle();
Set<String> windows = driver.getWindowHandles();

for (String win : windows) {
    if (!win.equals(parent)) {
        driver.switchTo().window(win);
        // Thực hiện login trên tab OAuth
        driver.findElement(By.id("username")).sendKeys("test");
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.id("login")).click();
    }
}
driver.switchTo().window(parent); // quay lại tab chính
👉 Thường gặp khi web redirect sang Google/Facebook login ở tab khác.

✅ Tóm gọn
getWindowHandles() → lấy tất cả ID của các tab/cửa sổ.

Dùng để switch giữa các tab, đóng popup, hoặc xử lý luồng OAuth/Payment.

📌 Sự khác nhau giữa getWindowHandle() và getWindowHandles()
driver.getWindowHandle()

Trả về ID duy nhất (String) của cửa sổ/tab hiện tại mà WebDriver đang focus.

Dùng khi bạn muốn lưu lại main window để sau này quay lại.

Ví dụ:

java
String mainWindow = driver.getWindowHandle();
driver.getWindowHandles()

Trả về tập hợp (Set<String>) chứa ID của tất cả cửa sổ/tab đang mở trong phiên làm việc.

Dùng khi bạn muốn duyệt qua tất cả các tab, hoặc chuyển sang tab khác.

Ví dụ:

java
Set<String> windows = driver.getWindowHandles();
for (String win : windows) {
    driver.switchTo().window(win);
}
✨ Ví dụ thực tế
Giả sử bạn click vào một link và nó mở ra tab mới:

java
String mainWindow = driver.getWindowHandle(); // lấy ID tab chính
Set<String> windows = driver.getWindowHandles(); // lấy tất cả ID tab

for (String win : windows) {
    if (!win.equals(mainWindow)) {
        driver.switchTo().window(win); // chuyển sang tab mới
        System.out.println("Title of new tab: " + driver.getTitle());
    }
}

// quay lại tab chính
driver.switchTo().window(mainWindow);
👉 Ở đây:

getWindowHandle() → chỉ lấy 1 ID (tab hiện tại).
getWindowHandles() → lấy nhiều ID (tất cả tab).

✅ Tóm gọn:

getWindowHandle() = ID của tab hiện tại.
getWindowHandles() = tập hợp ID của tất cả tab.

📌 Hai cách duyệt
1. Dùng Iterator
java
Iterator<String> it = windows.iterator();
while (it.hasNext()) {
    String win = it.next();
    driver.switchTo().window(win);
}
Đặc điểm: duyệt tuần tự từng phần tử, có thể remove() phần tử trong lúc duyệt.
Ưu điểm: kiểm soát tốt hơn, đặc biệt khi cần thao tác phức tạp (xóa, nhảy qua, dừng giữa chừng).
Nhược điểm: cú pháp dài hơn, hơi “cồng kềnh” nếu chỉ cần duyệt đơn giản.

2. Dùng for-each
java
for (String win : windows) {
    driver.switchTo().window(win);
}
Đặc điểm: cú pháp ngắn gọn, dễ đọc, dễ viết.
Ưu điểm: phù hợp khi chỉ cần duyệt toàn bộ tập hợp, không cần xóa phần tử.
Nhược điểm: không thể gọi remove() trực tiếp trong vòng lặp.

📌 Khi nào dùng cái nào?
Dùng for-each:
Khi bạn chỉ cần duyệt qua tất cả window handles để switch hoặc kiểm tra.
Ví dụ: chuyển sang tab mới, in ra title của từng tab.
→ Đây là cách phổ biến nhất trong Selenium.

Dùng Iterator:
Khi bạn cần xóa phần tử trong lúc duyệt (it.remove()), hoặc cần logic phức tạp (ví dụ: duyệt đến phần tử thứ N rồi dừng).
Ví dụ: đóng tất cả popup ngoại trừ main window.
→ Dùng khi cần thao tác nâng cao.

✅ Tóm gọn
for-each → ngắn gọn, dễ đọc, dùng cho hầu hết trường hợp.
Iterator → linh hoạt hơn, dùng khi cần remove hoặc kiểm soát chặt chẽ quá trình duyệt.
 */