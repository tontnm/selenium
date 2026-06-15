package automation.s03.l30;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class HandleRadioBtn {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://testautomationpractice.blogspot.com");
        driver.manage().window().maximize();

        WebElement male_rd=driver.findElement(By.xpath("//input[@id='male']"));
        WebElement female_rd=driver.findElement(By.xpath("//input[@id='female']"));

        System.out.println("Before selection...");
        System.out.println("select status of the male radio btn: "+male_rd.isSelected());
        System.out.println("select status of the female radio btn: "+female_rd.isSelected());

        male_rd.click();

        System.out.println("after selection...");
        System.out.println("select status of the male radio btn: "+male_rd.isSelected());
        System.out.println("select status of the female radio btn: "+female_rd.isSelected());
    }
}
