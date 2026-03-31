package selenium.demo.lesson12;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.util.List;

public class Scroll {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();

//        compareTotal(driver);
        countRow(driver);
    }

    //👉 Ý nghĩa: Hàm này kiểm tra xem tổng số tiền hiển thị trên trang có đúng bằng tổng các giá trị trong bảng hay không.
    public static void compareTotal(WebDriver driver) throws InterruptedException {
        //Dùng JavascriptExecutor để cuộn trang xuống 500px.
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("window.scrollBy(0,500)");
        Thread.sleep(3000);
        //Sau đó cuộn bên trong bảng có class .tableFixHead xuống tận cùng.
        je.executeScript("document.querySelector('.tableFixHead').scrollTop=5000");

        //Lấy tổng số tiền hiển thị ở phần tử .totalAmount trên trang, tách chuỗi theo dấu : để lấy số.
        String[] parts = driver.findElement(By.cssSelector(".totalAmount")).getText().split(":");
        int amount = Integer.parseInt(parts[1].trim());

        //Lấy tất cả các giá trị ở cột thứ 4 trong bảng .tableFixHead.
        List<WebElement> values = driver.findElements(By.cssSelector(".tableFixHead td:nth-child(4)"));
        //Cộng tất cả các giá trị trong cột 4.
        int total = 0;
        for (int i = 0; i < values.size(); i++) {
            total += Integer.parseInt(values.get(i).getText());
        }

        //So sánh tổng tính được với số hiển thị trên trang bằng JUnit Assert → kiểm tra tính đúng đắn.
        Assert.assertEquals(amount, total);
    }

    //👉 Ý nghĩa: Hàm này đếm số hàng, số cột trong bảng và in nội dung của một hàng cụ thể.
    public static void countRow(WebDriver driver) throws InterruptedException {
        //Cuộn trang xuống 500px để thấy bảng.
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("window.scrollBy(0,500)");
        Thread.sleep(2000);

        //Lấy tất cả các hàng (tr) trong bảng #product.
        //Lấy tất cả các cột (th) trong bảng.
        //Lấy các ô dữ liệu (td) của hàng thứ 3 (index = 2 vì bắt đầu từ 0).
        List<WebElement> rows = driver.findElements(By.cssSelector(".left-align #product tr"));
        List<WebElement> columns = driver.findElements(By.cssSelector(".left-align #product th"));
        List<WebElement> rowSecond = rows.get(2).findElements(By.tagName("td"));

        //In ra số lượng hàng trong bảng.
        //In ra số lượng cột trong bảng.
        //In ra nội dung của từng ô trong hàng thứ 3.
        System.out.println(rows.size());
        System.out.println(columns.size());
        for (int i = 0; i < rowSecond.size(); i++) {
            System.out.println(rowSecond.get(i).getText());
        }

    }
}
