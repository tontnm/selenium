package automation.s03.l23;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class LocatorsDemo {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://demo.opencart.com/");

        // name
        WebElement searchBox = driver.findElement(By.name("search"));
        searchBox.sendKeys("MacBook");
        driver.findElement(By.name("search")).sendKeys("MacBook");

        // id
//        WebElement logo = driver.findElement(By.id("logo"));
//        boolean status = logo.isDisplayed();

        boolean status = driver.findElement(By.id("logo")).isDisplayed();
        System.out.println(status);

        // link text - chỉ dành cho link
        driver.findElement(By.linkText("Macbook")).click();

        // partial link text - chỉ dành cho link
        driver.findElement(By.partialLinkText("Mac")).click();

        // ví dụ có 2 cái là tablets và booklets, bấm default cái đầu tiên thì xài partial
        // trường hợp có 3 tablets, thì xài xpath

        // tagname hoặc classname là khi muốn tìm nhiều elements 1 lúc thì xài

        // class element thì có class tên này, class tên kia
        List<WebElement> headerLinks = driver.findElements(By.className("list-inline-item"));
        System.out.println(headerLinks.size());

        // dùng tag name để xác định tất cả link trên webpage
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println(links.size());
        List<WebElement> imgs = driver.findElements(By.tagName("img"));
        System.out.println(imgs.size());

        /*
        * khác nhau giữa findElement và findElements
        *
        * kịch bản 1: tìm thấy 1 element
        * findElement(loc) -> single element -> WebElement
        * findElements(loc) -> single element -> List<WebElement>

        * kịch bản 2: tìm thấy nhiều element
         * findElement(loc) -> single element -> WebElement
         * findElements(loc) -> multiple element -> List<WebElement>

         * * kịch bản 3: ko tìm thấy element
         * findElement(loc) -> NoSuchElementException
         * findElements(loc) -> ko văng exception -> return 0
        * */
    }
}
