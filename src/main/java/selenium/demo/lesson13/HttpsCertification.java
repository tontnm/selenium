package selenium.demo.lesson13;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HttpsCertification {
    public static void main(String[] args) {

        /*
        * một chương trình Java sử dụng Selenium WebDriver để mở trình duyệt Chrome, cấu hình proxy, chấp nhận chứng chỉ HTTPS
        * không hợp lệ, và truy cập vào một trang web có chứng chỉ hết hạn. Mình sẽ phân tích chi tiết từng phần
        * */
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        //Tạo đối tượng ChromeOptions để cấu hình các tùy chọn cho Chrome.
        ChromeOptions options = new ChromeOptions();

        //Cấu hình Proxy
        //Tạo đối tượng Proxy.
        //Gán địa chỉ proxy ipaddress:4444 (ví dụ: 127.0.0.1:4444).
        //Thêm proxy vào cấu hình của Chrome.
        //👉 Điều này giúp tất cả request từ trình duyệt đi qua proxy.
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("ipaddress:4444");
        options.setCapability("proxy", proxy);

        //Chấp nhận chứng chỉ HTTPS không hợp lệ
        //setBinary: chỉ định đường dẫn đến file thực thi của Chrome (trường hợp bạn dùng bản Chrome riêng để test).
        //setAcceptInsecureCerts(true): cho phép WebDriver bỏ qua lỗi chứng chỉ HTTPS (ví dụ chứng chỉ hết hạn, không hợp lệ).
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        options.setAcceptInsecureCerts(true);

        //Tạo đối tượng ChromeDriver với các tùy chọn đã cấu hình.
        WebDriver driver = new ChromeDriver(options);

        //maximize(): phóng to cửa sổ trình duyệt.
        //deleteAllCookies(): xóa toàn bộ cookie.
        //deleteCookieNamed("abc"): xóa cookie có tên "abc".
        //addCookie(""): (comment) – có thể thêm cookie mới nếu cần.
        //👉 Các thao tác này thường dùng để đảm bảo môi trường test sạch sẽ, không bị ảnh hưởng bởi session cũ.
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
