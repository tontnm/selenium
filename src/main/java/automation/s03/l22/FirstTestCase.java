package automation.s03.l22;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class FirstTestCase {
    public static void main(String[] args) {
        //1.mở chrome
//    ChromeDriver driver = new ChromeDriver();
        WebDriver driver = new ChromeDriver(); // driver2 có thể thay = ff,edge
        driver.get("https://demo.opencart.com/");
        String actualTitle = driver.getTitle();

        if (actualTitle.equalsIgnoreCase("Your Store")) {
            System.out.println("Test Passed");
        } else {
            System.out.println("Test Failed");
        }

        driver.close();
        driver.quit();
    }
}
