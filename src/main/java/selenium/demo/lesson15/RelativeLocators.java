package selenium.demo.lesson15;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.openqa.selenium.support.locators.RelativeLocator.*;

public class RelativeLocators {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://rahulshettyacademy.com/angularpractice/");
        driver.manage().window().maximize();

        //Tìm ô nhập tên (input[name='name']).
        //Dùng Relative Locator: tìm label ở trên ô nhập tên.
        //In ra text của label đó.
        //👉 Ví dụ: sẽ in "Name".
        WebElement nameEditBox = driver.findElement(By.cssSelector("input[name='name']"));
        String name = driver.findElement(with(By.tagName("label")).above(nameEditBox)).getText();
        System.out.println(name);

        //Tìm label "Date of Birth".
        //Dùng Relative Locator: tìm input ở dưới label này.
        //Click vào ô nhập ngày sinh.
        WebElement dateOfBirthLabel = driver.findElement(By.cssSelector("label[for='dateofBirth']"));
        driver.findElement(with(By.tagName("input")).below(dateOfBirthLabel)).click();

        //Tìm label của checkbox.
        //Dùng Relative Locator: tìm input ở bên trái label.
        //Click vào checkbox.
        WebElement cbLabel = driver.findElement(By.cssSelector("label[for='exampleCheck1']"));
        driver.findElement(with(By.tagName("input")).toLeftOf(cbLabel)).click();

        //Tìm radio button có id "inlineRadio1".
        //Dùng Relative Locator: tìm label ở bên phải radio button.
        //In ra text của label đó.
        //👉 Ví dụ: "Male" hoặc "Female" tùy vị trí.
        WebElement rdb = driver.findElement(By.id("inlineRadio1"));
        System.out.println(driver.findElement(with(By.tagName("label")).toRightOf(rdb)).getText());

        //🔹 Khi nào cần dùng Relative Locators?
        //Khi HTML không có locator duy nhất (id, name, css rõ ràng).
        //Khi phần tử thay đổi động nhưng vị trí tương đối với phần tử khác thì ổn định.
        //Khi muốn code dễ đọc, dễ hiểu hơn thay vì viết XPath phức tạp.
        //
        //Ví dụ:
        //Form có nhiều input nhưng không có id → dùng relative locator dựa vào label.
        //Checkbox/radio button nằm cạnh label → dễ dàng tìm bằng toLeftOf hoặc toRightOf.
    }
}
