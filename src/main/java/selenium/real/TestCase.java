package selenium.real;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCase {
    public static void main(String[] args) {
        WebDriver driver = BrowserFactory.getDriver("chrome"); // hoặc "firefox", "edge"
        driver.get("https://www.google.com");
        System.out.println("Title: " + driver.getTitle());

        // 1. Static Dropdown (HTML <select>)
        WebElement staticDropdown = driver.findElement(By.id("country"));
        Select select = new Select(staticDropdown);
        select.selectByVisibleText("Vietnam");   // chọn theo text hiển thị
        select.selectByValue("VN");              // chọn theo value
        select.selectByIndex(2);                 // chọn theo index

        // 2. Dynamic Dropdown (AJAX/JS generated)
        driver.findElement(By.id("searchBox")).sendKeys("Java");
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        WebElement option = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//li[contains(text(),'Java Developer')]")));
        option.click();

        // 3. Checkbox
        WebElement checkbox = driver.findElement(By.id("subscribe"));
        if (!checkbox.isSelected()) {
            checkbox.click(); // tick vào checkbox nếu chưa chọn
        }

        // 4. Radio Button
        WebElement radioBtn = driver.findElement(By.id("genderMale"));
        if (!radioBtn.isSelected()) {
            radioBtn.click(); // chọn radio button
        }

        // 5. Text Button (normal button)
        WebElement textButton = driver.findElement(By.id("loginBtn"));
        textButton.click(); // click vào nút

        // 6. Alert / JavaScript Popup
        driver.findElement(By.id("triggerAlert")).click(); // mở alert
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept(); // nhấn OK
        // driver.switchTo().alert().dismiss(); // nhấn Cancel
        // driver.switchTo().alert().sendKeys("Ton"); // nhập text vào prompt alert

        driver.quit();
    }

}
