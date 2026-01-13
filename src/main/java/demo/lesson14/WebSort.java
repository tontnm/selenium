package demo.lesson14;

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

        driver.findElement(By.cssSelector("th:nth-child(1)")).click();
        List<WebElement> elementList = driver.findElements(By.xpath("//td[1]"));
        List<String> originalList = elementList.stream().map(s -> s.getText()).collect(Collectors.toList());
        List<String> sortedList = originalList.stream().sorted().collect(Collectors.toList());

        Assert.assertTrue(originalList.equals(sortedList));

        List<String> price;
        do {
            elementList = driver.findElements(By.xpath("//td[1]"));
            price = elementList.stream().filter(s -> s.getText().contains("Rice")).
                    map(s -> getPriceVeg(s)).collect(Collectors.toList());

            price.forEach(s -> System.out.println(s));

            if (price.size() < 1) {
                driver.findElement(By.cssSelector("a[aria-label='Next']")).click();
            }
        } while (price.size() < 1);
    }

    private static String getPriceVeg(WebElement s) {
        String price = s.findElement(By.xpath("following-sibling::td[1]")).getText();
        return price;
    }
}
