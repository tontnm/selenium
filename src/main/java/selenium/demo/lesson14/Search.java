package selenium.demo.lesson14;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import javax.lang.model.element.Element;
import java.util.List;
import java.util.stream.Collectors;

public class Search {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        driver.manage().window().maximize();


        driver.findElement(By.id("search-field")).sendKeys("rice");
        List<WebElement> veg = driver.findElements(By.xpath("//td[1]"));
        List<WebElement> filteredList = veg.stream().filter(v -> v.getText().contains("Rice")).collect(Collectors.toList());

        //Giả sử search bị sai → UI trả về 5 kết quả, nhưng chỉ có 1 kết quả thực sự chứa "Rice".
        //Bạn lấy toàn bộ kết quả hiển thị (veg) rồi dùng Stream filter để lọc ra những phần tử có chứa "Rice".
        //Nếu số lượng sau khi lọc (filteredList.size()) khác với số lượng ban đầu (veg.size()), thì chứng tỏ chức năng
        // search không đúng.
        Assert.assertEquals(veg.size(), filteredList.size());
    }
}
