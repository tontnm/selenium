package selenium.demo.lesson11;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class Calendar {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        driver.manage().window().maximize();

        String monthNumber = "6";
        String day = "15";
        String year = "2027";
        String[] expectedList = {monthNumber, day, year};

//        selectDatePicker1(driver, monthNumber, day, year);
        selectDatePicker2(driver, monthNumber, day, year, expectedList);
    }

    public static void selectDatePicker1(WebDriver driver, String month, String day, String year) {
        // Tìm các ô nhập
        WebElement monthInput = driver.findElement(By.name("month"));
        WebElement dayInput = driver.findElement(By.name("day"));
        WebElement yearInput = driver.findElement(By.name("year"));

        // Click vào ô tháng
        Actions actions = new Actions(driver);
        actions.moveToElement(monthInput).click().perform();

        // Nhập giá trị monthInput.clear();
        monthInput.sendKeys(month);
        dayInput.clear();
        dayInput.sendKeys(day);
        yearInput.clear();
        yearInput.sendKeys(year);
    }

    public static void selectDatePicker2(WebDriver driver, String month, String day, String year, String[] expectedList) {
        driver.findElement(By.cssSelector(".react-date-picker__calendar-button__icon")).click();
        driver.findElement(By.cssSelector(".react-calendar__navigation__label")).click();
        driver.findElement(By.cssSelector(".react-calendar__navigation__label")).click();
        driver.findElement(By.xpath("//button[text()='" + year + "']")).click();
        driver.findElements(By.cssSelector(".react-calendar__year-view__months__month")).get(Integer.parseInt(month) - 1).click();
        selectDay(driver, day);

        //
        List<WebElement> actualList = driver.findElements(By.cssSelector(".react-date-picker__inputGroup__input"));

        for (int i = 0; i < actualList.size(); i++) {
            Assert.assertEquals(actualList.get(i).getAttribute("value"), expectedList[i]);
        }

    }

    public static void selectDay(WebDriver driver, String day) {
        // Tạo XPath động dựa trên aria-label
        String xpath = String.format("//abbr[text()='%s']", day);
        WebElement dayButton = driver.findElement(By.xpath(xpath));
        dayButton.click();
    }

    public void selectDOBByDatePicker() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://example.com/register");
        driver.manage().window().maximize();

        // Mở Date Picker
        driver.findElement(By.cssSelector(".date-picker-button")).click();

        // Chọn năm
        driver.findElement(By.cssSelector(".react-calendar__navigation__label")).click();
        driver.findElement(By.cssSelector(".react-calendar__navigation__label")).click();
        driver.findElement(By.xpath("//button[text()='1995']")).click();

        // Chọn tháng (tháng 6 = index 5)
        driver.findElements(By.cssSelector(".react-calendar__year-view__months__month")).get(5).click();

        // Chọn ngày
        driver.findElement(By.xpath("//abbr[text()='15']")).click();

        // Xác minh
        List<WebElement> actualList = driver.findElements(By.cssSelector(".react-date-picker__inputGroup__input"));
        String[] expectedList = {"6", "15", "1995"};

        for (int i = 0; i < actualList.size(); i++) {
            Assert.assertEquals(actualList.get(i).getAttribute("value"), expectedList[i]);
        }

        driver.quit();
    }

    public void selectDOBByTyping() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://example.com/register");
        driver.manage().window().maximize();

        // Tìm các ô nhập ngày/tháng/năm
        WebElement dayInput = driver.findElement(By.name("day"));
        WebElement monthInput = driver.findElement(By.name("month"));
        WebElement yearInput = driver.findElement(By.name("year"));

        // Nhập dữ liệu
        dayInput.clear();
        dayInput.sendKeys("15");
        monthInput.clear();
        monthInput.sendKeys("06");
        yearInput.clear();
        yearInput.sendKeys("1995");

        // Xác minh
        Assert.assertEquals(dayInput.getAttribute("value"), "15");
        Assert.assertEquals(monthInput.getAttribute("value"), "06");
        Assert.assertEquals(yearInput.getAttribute("value"), "1995");

        driver.quit();
    }


}

/*
* 📋 Checklist Selenium – Date Picker
🔹 Trước khi thao tác
[ ] Kiểm tra xem Date Picker có phải iframe không → nếu có, dùng driver.switchTo().frame().
[ ] Xác định loại Date Picker:
Input text (nhập trực tiếp).
UI calendar (click chọn ngày/tháng/năm).
[ ] Chuẩn bị dữ liệu test (ngày, tháng, năm) tách riêng để dễ bảo trì.

🔹 Khi thao tác
[ ] Với input text → dùng .clear() trước khi sendKeys().
[ ] Với UI calendar → click mở calendar, chọn năm → tháng → ngày theo thứ tự.
[ ] Viết locator động cho ngày/tháng/năm (ví dụ: //abbr[text()='15']).
[ ] Nếu phần tử khó click → dùng Actions.moveToElement().click().perform().
[ ] Kết hợp explicit wait để đảm bảo calendar đã load trước khi chọn.

🔹 Sau khi thao tác
[ ] Dùng Assert để xác minh giá trị hiển thị đúng với dữ liệu mong đợi.
[ ] Quay lại defaultContent() nếu đã switch vào iframe.
[ ] Gom nhóm test case theo module (ví dụ: Date Picker trong form đăng ký, Date Picker trong báo cáo).
* */