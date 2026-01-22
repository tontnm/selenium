package selenium.demo.lesson10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Frames {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://jqueryui.com/droppable/");
        driver.manage().window().maximize();

        driver.switchTo().frame(driver.findElement(By.cssSelector(".demo-frame")));
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));
        Actions a = new Actions(driver);
        a.dragAndDrop(source, target).build().perform();
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[contains(text(),'Accept')]")).click();
    }
}
/*
* 🔹 Làm việc với iframe
[ ] Kiểm tra phần tử có nằm trong iframe không (dùng DevTools → Elements → iframe tag).
[ ] Dùng driver.switchTo().frame(...) trước khi thao tác phần tử bên trong.
[ ] Chọn frame bằng index, name/id, hoặc WebElement.
[ ] Sau khi xong, quay lại trang chính bằng driver.switchTo().defaultContent().
[ ] Nếu có nhiều iframe lồng nhau → switch tuần tự từng cấp.
[ ] Luôn xác nhận frame đã được load trước khi thao tác (có thể dùng wait).

🔹 Làm việc với Actions
[ ] Khởi tạo Actions a = new Actions(driver);.
[ ] Dùng .perform() cho hành động đơn lẻ, .build().perform() khi muốn tái sử dụng chuỗi hành động.
[ ] Các thao tác phổ biến:

moveToElement(element) → hover chuột.
click(element) → click nâng cao.
dragAndDrop(source, target) → kéo thả.
doubleClick(element) → double click.
contextClick(element) → chuột phải.

[ ] Luôn kiểm tra phần tử có visible & enabled trước khi thực hiện Actions.
[ ] Kết hợp với explicit wait để đảm bảo phần tử sẵn sàng.
* */