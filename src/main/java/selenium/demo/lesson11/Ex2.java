package selenium.demo.lesson11;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Ex2 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();

        WebElement checkboxOption2 = driver.findElement(By.id("checkBoxOption2"));
        checkboxOption2.click();

        // Lấy text "Option2" từ label chứa checkbox
        WebElement labelOption2 = driver.findElement(By.xpath("//label[input[@id='checkBoxOption2']]"));
        String option2Text = labelOption2.getText();

        String selectedValue = "";
        if (checkboxOption2.isSelected()) {
            selectedValue = option2Text;
        }

        Select dropdown = new Select(driver.findElement(By.id("dropdown-class-example")));
        dropdown.selectByVisibleText(selectedValue);

        WebElement selectedOption = dropdown.getFirstSelectedOption();
        System.out.println("Dropdown đã chọn: " + selectedOption.getText());

        // Nhập "Option2" vào input field
        WebElement nameField = driver.findElement(By.id("name"));
        nameField.sendKeys("Option2");

        // Click nút Alert
        WebElement alertButton = driver.findElement(By.id("alertbtn"));
        alertButton.click();

        // Chuyển sang alert và handle
        Alert alert = driver.switchTo().alert();
        System.out.println("Alert text: " + alert.getText());

        if (alert.getText().contains(selectedValue)) {
            // Accept alert (click OK)
            alert.accept();
            Assert.assertTrue(true);
        } else {
            Assert.assertFalse(false);
        }
    }
}
