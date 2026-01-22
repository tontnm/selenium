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

        WebElement nameEditBox = driver.findElement(By.cssSelector("input[name='name']"));
        String name = driver.findElement(with(By.tagName("label")).above(nameEditBox)).getText();
        System.out.println(name);

        WebElement dateOfBirthLabel = driver.findElement(By.cssSelector("label[for='dateofBirth']"));
        driver.findElement(with(By.tagName("input")).below(dateOfBirthLabel)).click();

        //
        WebElement cbLabel = driver.findElement(By.cssSelector("label[for='exampleCheck1']"));
        driver.findElement(with(By.tagName("input")).toLeftOf(cbLabel)).click();

        WebElement rdb = driver.findElement(By.id("inlineRadio1"));
        System.out.println(driver.findElement(with(By.tagName("label")).toRightOf(rdb)).getText());
    }
}
