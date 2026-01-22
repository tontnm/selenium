package selenium.demo.lesson09.wait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class Wait {
    public static void main(String[] args) throws InterruptedException {
        // Implicit wait: đợi 3 giây rồi hãy báo lỗi nha mạy, tui 2 giây đã xuất hiện rồi nha, dùng cho search
        /*
         * 📌 Khi nào dùng cái nào?
         * Dùng Implicit Wait:
         * Khi toàn bộ trang có độ trễ tải chung, bạn muốn Selenium tự động chờ cho mọi element.
         * Ví dụ: trang thương mại điện tử có nhiều hình ảnh tải chậm.
         * Dùng Explicit Wait:
         * Khi bạn chỉ cần chờ một số điều kiện đặc biệt cho một element.
         * Ví dụ: chờ popup xuất hiện, chờ nút "Submit" có thể click, chờ text thay đổi.
         * 👉 Thực tế, nhiều dự án kết hợp cả hai: đặt Implicit Wait ngắn (3–5 giây) để xử lý độ trễ chung,
         * và dùng Explicit Wait cho các tình huống đặc biệt.
         *
         * ✅ Điểm hay
         * Implicit Wait
         * - Đơn giản, dễ thiết lập.
         * - Giúp xử lý độ trễ chung khi trang tải chậm.
         * Explicit Wait
         * - Kiểm soát chính xác điều kiện cần chờ.
         * - Giúp test ổn định hơn trong các tình huống động (AJAX, popup, animation).
         *
         * ❌ Điểm dở
         * Implicit Wait
         * - Không kiểm soát trạng thái element (chỉ cần có trong DOM là coi như tìm thấy).
         * - Nếu locator sai, sẽ chờ đủ thời gian rồi mới báo lỗi → làm chậm test.
         * - Dễ gây xung đột khi kết hợp với Explicit Wait.
         * Explicit Wait
         * - Code dài hơn, phức tạp hơn.
         * - Phải viết riêng cho từng element cần chờ.
         *
         * 📌 Khi nào dùng cả hai, khi nào dùng một?
         * Dùng cả hai:
         * - Đặt Implicit Wait ngắn (2–3 giây) để xử lý độ trễ chung.
         * - Dùng Explicit Wait cho các tình huống đặc biệt (ví dụ: chờ nút "Login" có thể click, chờ popup xuất hiện).
         * - Đây là cách nhiều dự án thực tế áp dụng.
         * Chỉ dùng Implicit Wait:
         * - Khi trang web khá ổn định, không có nhiều element động.
         * - Test đơn giản, không cần kiểm soát trạng thái phức tạp.
         * Chỉ dùng Explicit Wait:
         * - Khi ứng dụng có nhiều element động, AJAX, popup, animation.
         * - Muốn kiểm soát chính xác điều kiện chờ và tránh performance issue.
         * - Đây là cách được khuyến nghị trong các framework hiện đại.
         * */

        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        driver.manage().window().maximize();

        List<String> selectedItems = Arrays.asList("Cucumber", "Apple", "Beetroot");

        AddItemsToCart(driver, selectedItems);
        Checkout(driver);
    }

    public static void AddItemsToCart(WebDriver driver, List<String> selectedItems) {
        // Lấy danh sách sản phẩm và nút "Add to Cart" một lần
        List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));
        //button[text()='ADD TO CART'] không đc dùng text như vầy, vì sau khi click vô text sẽ đổi thành ADDED và
        // từ 30 element nó sẽ rớt xuống còn 29, và sẽ click sai những item sau, giải quyết vấn đề này bằng cách
        // bắt cái vòng ngoài của nút thay vì bắt nút bằng text
        List<WebElement> addToCartButtons = driver.findElements(By.cssSelector("div[class='product-action']"));

        int clickedCount = 0;

        for (int i = 0; i < products.size(); i++) {
            String productName = products.get(i).getText();

            // Kiểm tra xem sản phẩm có nằm trong danh sách cần chọn không
            for (String item : selectedItems) {
                if (productName.contains(item)) {
                    addToCartButtons.get(i).click();
                    System.out.println("Added: " + productName);
                    clickedCount++;
                    break; // thoát vòng lặp item vì đã tìm thấy
                }
            }

            // Nếu đã click đủ số lượng item cần chọn thì thoát luôn vòng lặp products
            if (clickedCount == selectedItems.size()) {
                break;
            }
        }
    }

    public static void Checkout(WebDriver driver) throws InterruptedException {
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.findElement(By.cssSelector("img[alt='Cart']")).click();
//        Thread.sleep(1000);
        driver.findElement(By.cssSelector("div[class='cart-preview active'] button[type='button']")).click();
        w.until(ExpectedConditions.visibilityOfElementLocated(By.className("promoCode")));

        driver.findElement(By.className("promoCode")).sendKeys("rahulshettyacademy");
        driver.findElement(By.cssSelector(".promoBtn")).click();


        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".promoInfo")));

        String observedResult = driver.findElement(By.cssSelector(".promoInfo")).getText();
        String expectedResult = "Code applied ..!";
        Assert.assertEquals(observedResult, expectedResult);
    }
}
