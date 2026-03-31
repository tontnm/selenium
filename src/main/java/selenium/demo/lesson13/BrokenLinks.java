package selenium.demo.lesson13;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BrokenLinks {
    public static void main(String[] args) throws IOException {

        //một chương trình Java dùng Selenium WebDriver kết hợp với TestNG để kiểm tra các liên kết (links)
        //trên một trang web có bị hỏng (broken links) hay không
        //📌 Ý tưởng chính
        //Truy cập vào trang web: https://rahulshettyacademy.com/AutomationPractice/.
        //Lấy tất cả các thẻ <a> trong phần tử li[class='gf-li'].
        //Với mỗi link, gửi một request kiểu HEAD để kiểm tra mã phản hồi HTTP.
        //Nếu mã phản hồi ≥ 400 (ví dụ 404 Not Found, 500 Internal Server Error) → link bị hỏng.
        //Dùng SoftAssert để gom tất cả lỗi lại và báo cáo sau khi kiểm tra xong.
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();

        //Tìm tất cả các thẻ <a> nằm trong <li class="gf-li">.
        //Lưu vào danh sách links.
        List<WebElement> links = driver.findElements(By.cssSelector("li[class='gf-li'] a"));
        //SoftAssert cho phép gom nhiều lỗi lại, không dừng ngay khi gặp lỗi đầu tiên.
        SoftAssert a = new SoftAssert();

        //Lấy URL từ thuộc tính href.
        //Tạo kết nối HTTP (HttpURLConnection) đến URL đó.
        //Dùng phương thức HEAD (chỉ lấy header, không tải toàn bộ nội dung → nhanh hơn).
        //Lấy mã phản hồi HTTP (respCode).
        //Nếu respCode >= 400 → link bị hỏng. SoftAssert sẽ ghi nhận lỗi.
        for (WebElement link : links) {
            String url = link.getAttribute("href");
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("HEAD");
            conn.connect();
            int respCode = conn.getResponseCode();
//            System.out.println(respCode);
            a.assertTrue(respCode < 400, "The link with text " + link.getText() + " is broken with code " + respCode);
//            if (respCode > 400) {
//                System.out.println("The link with text " + link.getText() + " is broken with code " + respCode);
//                Assert.assertTrue(false);
//            }
        }
        a.assertAll();
    }

    public static void checkLinksAndTitles(WebDriver driver, List<String> expectedTitles) throws IOException {
        // Khoanh vùng footer column
        WebElement footerDiv = driver.findElement(By.id("gf-BIG"));
        WebElement columnDriver = footerDiv.findElement(By.xpath("//table/tbody/tr/td[1]/ul"));
        List<WebElement> links = columnDriver.findElements(By.tagName("a"));

        String originalTab = driver.getWindowHandle();
        SoftAssert softAssert = new SoftAssert();

        // Bỏ qua link đầu tiên (Practice Page)
        for (int i = 1; i < links.size(); i++) {
            String url = links.get(i).getAttribute("href");

            // 1. Kiểm tra HTTP code trước
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("HEAD");
            conn.connect();
            int respCode = conn.getResponseCode();

            if (respCode >= 400) {
                softAssert.fail("Broken link: " + url + " với code " + respCode);
                continue; // bỏ qua mở tab nếu link hỏng
            }

            // 2. Nếu hợp lệ thì mở tab và kiểm tra title
            String clickOnLinkTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
            links.get(i).sendKeys(clickOnLinkTab);
        }

        // Chờ tất cả tab mở
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> driver.getWindowHandles().size() >= expectedTitles.size() + 1);

        Set<String> tabs = driver.getWindowHandles();
        for (String tab : tabs) {
            if (tab.equals(originalTab)) continue;

            driver.switchTo().window(tab);
            String actualTitle = driver.getTitle();
            System.out.println("Title: " + actualTitle);

            boolean match = expectedTitles.contains(actualTitle);
            softAssert.assertTrue(match, "Title không khớp: " + actualTitle);
        }

        softAssert.assertAll();
        driver.switchTo().window(originalTab);
    }

}

/*
📊 Test Plan: Broken Links & Navigation Validation
🎯 Mục tiêu
Đảm bảo tất cả liên kết trên website hoạt động bình thường.
Phân loại link theo mức độ quan trọng để tối ưu thời gian chạy test.
Kết hợp kiểm tra HTTP response code và UI/Title validation.

🗂 Phân loại link

Critical Links (quan trọng)
Ví dụ: Login, Register, Payment, Course Page, Contact Us.

Kiểm tra 2 bước:

HTTP HEAD → đảm bảo trả về code < 400.
UI check → mở tab, xác nhận title hoặc nội dung hiển thị đúng mong đợi.

Non‑Critical Links (phụ)
Ví dụ: Blog, FAQ, Social Media, External References.

Kiểm tra 1 bước:

Chỉ cần HTTP HEAD để đảm bảo link không hỏng.
Không cần mở tab kiểm tra UI (tiết kiệm thời gian).

🛠 Quy trình kiểm thử
Thu thập tất cả link từ footer, header, body.
Phân loại link dựa trên danh sách định nghĩa trước (critical vs non‑critical).
Kiểm tra link non‑critical:
Gửi HEAD request.
Nếu code ≥ 400 → báo lỗi.

Kiểm tra link critical:

Gửi HEAD request.
Nếu code < 400 → mở tab bằng Selenium.
Lấy driver.getTitle() hoặc xác nhận element đặc trưng (ví dụ form login).
So sánh với expected value.

Báo cáo kết quả:
Gom tất cả lỗi bằng SoftAssert.
Xuất report: broken links, title mismatch.

📌 Ưu điểm của cách kết hợp
Nhanh: không mở tab cho link phụ.
An toàn: vẫn đảm bảo link quan trọng được kiểm tra UI.
Thực tế: phù hợp với CI/CD pipeline, giảm thời gian chạy test nhưng vẫn đảm bảo chất lượng.

* 📌 Thực tế trong dự án automation testing
Kiểm tra nhanh broken links (HTTP HEAD)
→ Đây là bước sanity check hoặc chạy trong CI/CD để đảm bảo website không có link chết.
→ Ưu điểm: nhanh, ít tốn tài nguyên, dễ tích hợp pipeline.

Kiểm tra điều hướng và nội dung (mở tab, check title/UI)
→ Áp dụng cho các link quan trọng (ví dụ: login, course page, payment).
→ Ưu điểm: xác nhận đúng trải nghiệm người dùng, không chỉ “link sống”.

🎯 Cách kết hợp thường thấy
Chiến lược 2 tầng:

Dùng HEAD request để lọc ra các link hỏng.
Với các link hợp lệ, chọn một số link trọng điểm để mở tab và kiểm tra nội dung (title, form, UI).
Lý do không mở tất cả tab:
Tốn thời gian và tài nguyên.
Nhiều link phụ (ví dụ: blog, tài liệu ngoài) không cần kiểm tra UI chi tiết.

✅ Kết luận
Có dùng kết hợp nhưng thường theo kiểu lọc nhanh bằng HEAD → kiểm tra sâu bằng Selenium cho link quan trọng.
Đây là cách tối ưu: vừa đảm bảo chất lượng website, vừa tiết kiệm thời gian chạy test.
* */