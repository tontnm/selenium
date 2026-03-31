package selenium.demo.lesson14;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class WebSort {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        driver.manage().window().maximize();

        //sort list data
        //1.click nút sort trên web
        //2.lấy hết web element của column mình muốn kiểm tra về
        //3.lấy hết text của web element -> tạo list
        //4.sort cái list đó rồi tạo cái list đã sort
        //5.compare list gốc và list đã sort, nếu giống nhau, thì là trên web đã sort đúng
        driver.findElement(By.cssSelector("th:nth-child(1)")).click();
        List<WebElement> elementList = driver.findElements(By.xpath("//td[1]"));
        List<String> originalList = elementList.stream().map(s -> s.getText()).collect(Collectors.toList());
        List<String> sortedList = originalList.stream().sorted().collect(Collectors.toList());

        Assert.assertTrue(originalList.equals(sortedList));

        //tìm trong table, thấy  Rice thì qua cột kế bên lấy price của nó
        //1.tạo priceList rỗng
        //2.lấy hết web element của cột đầu tiên chứa name
        //3.loop qua từng dòng, dùng stream api, dùng filter kiếm name Rice, dùng map để lấy giá trị price của Rice
        //4.nếu ko thấy thì nhấn nút next rồi bắt đầu lại từ đầu
        List<String> priceList;
        do {
            elementList = driver.findElements(By.xpath("//td[1]"));
            priceList = elementList.stream().filter(s -> s.getText().contains("Rice")).
                    map(e -> getPriceVeg(e)).collect(Collectors.toList());

            priceList.forEach(s -> System.out.println(s));

            if (priceList.size() < 1) {
                driver.findElement(By.cssSelector("a[aria-label='Next']")).click();
            }
        } while (priceList.size() < 1);
    }

    private static String getPriceVeg(WebElement e) {
        String price = e.findElement(By.xpath("following-sibling::td[1]")).getText();
        return price;
    }
}
