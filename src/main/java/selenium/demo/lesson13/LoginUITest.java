package selenium.demo.lesson13;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert; // Hard Assert
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert; // Soft Assert

public class LoginUITest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://example.com/login"); // thay bằng URL thực tế
    }

    @Test
    public void testLoginAndUI() { // --- Hard Assert cho bước login ---
        driver.findElement(By.id("username")).sendKeys("testuser");
        driver.findElement(By.id("password")).sendKeys("password123");
        driver.findElement(By.id("loginBtn")).click(); // Kiểm tra URL sau login phải đúng (nếu sai thì dừng test ngay)
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/dashboard"), "Login thất bại, không vào được dashboard"); // --- Soft Assert cho kiểm tra UI sau login ---

        SoftAssert softAssert = new SoftAssert();
        WebElement profilePic = driver.findElement(By.id("profilePic"));
        WebElement welcomeMsg = driver.findElement(By.id("welcomeMsg"));
        WebElement logoutBtn = driver.findElement(By.id("logoutBtn"));
        WebElement footer = driver.findElement(By.id("footer"));

        softAssert.assertTrue(profilePic.isDisplayed(), "Profile picture không hiển thị");
        softAssert.assertTrue(welcomeMsg.getText().contains("Welcome"), "Welcome message sai");
        softAssert.assertTrue(logoutBtn.isDisplayed(), "Logout button không hiển thị");
        softAssert.assertTrue(footer.isDisplayed(), "Footer không hiển thị"); // Gom tất cả lỗi UI lại và báo cáo
        softAssert.assertAll();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

/*
* 🎯 Ý nghĩa
Hard Assert: đảm bảo login thành công → nếu sai thì dừng ngay, vì các bước sau không còn ý nghĩa.
SoftAssert: kiểm tra nhiều yếu tố UI trên dashboard (profile, welcome message, logout button, footer).
* Nếu có lỗi, vẫn chạy tiếp để báo cáo toàn bộ.
* 🎯 Kết luận
Hard Assert: dùng cho điều kiện bắt buộc, logic quan trọng, sanity check.
SoftAssert: dùng cho UI testing, kiểm tra nhiều yếu tố song song, regression testing.
Trong dự án thực tế, thường kết hợp cả hai: Hard Assert cho bước quan trọng, SoftAssert cho bước kiểm tra toàn diện.
*
* ⚖️ Hard Assert
📌 Đặc điểm
Dùng Assert.assertTrue, Assert.assertEquals, v.v.
Nếu điều kiện sai → test dừng ngay lập tức.
Các bước sau không được thực hiện.

✅ Khi nên dùng
Điều kiện bắt buộc để tiếp tục test:
Ví dụ: sau khi login, URL phải đúng trang dashboard. Nếu sai thì không cần chạy tiếp.
Kiểm tra logic quan trọng:
Ví dụ: số dư tài khoản sau giao dịch phải chính xác.
Sanity check: đảm bảo môi trường test đúng trước khi chạy các bước khác.

❌ Không nên dùng
Khi muốn kiểm tra nhiều yếu tố song song (UI, nhiều link, nhiều field).
Khi cần báo cáo toàn bộ lỗi trong một lần chạy.

🌱 SoftAssert
📌 Đặc điểm
Dùng SoftAssert.assertTrue, SoftAssert.assertEquals.
Nếu điều kiện sai → test vẫn chạy tiếp.
Gom tất cả lỗi lại, báo cáo cuối cùng bằng assertAll().

✅ Khi nên dùng
Kiểm tra nhiều yếu tố UI cùng lúc:
Ví dụ: trên trang login, kiểm tra title, logo, button, footer.
Kiểm tra danh sách link:
Ví dụ: kiểm tra tất cả link trong footer có hoạt động không.
Regression testing: muốn thấy toàn bộ lỗi trong một lần chạy để developer sửa cùng lúc.

❌ Không nên dùng
Khi điều kiện sai sẽ khiến các bước sau không còn ý nghĩa (ví dụ login fail thì không thể test chức năng bên trong).
Khi cần dừng ngay để tránh chạy sai logic.
* */