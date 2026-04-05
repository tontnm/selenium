package automation.s03.l24;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CSSSelector {
    public static void main(String[] args) {
        // css selector chọn nhiều attributes
        // tại sao cần css selector để chọn id trong khi có thể dùng trực tiếp id
        // thỉnh thoảng complex web pages,  1 attribute ko locate đc
        // trong những trường hợp này thì sẽ tìm những lựa chọn khác
        // có khi trang gặp trang web phức tạp, tìm bằng id hay tag name có khi ko ra luôn

        /*
        tag id : tag#id
        tag class : tag.classname (dùng để tìm nhiều elements)
        tag attribute : tag[attribute="value"]
        tag class attribute : tag.classname[attribute="value"] (tìm element cụ thể trong group)
        * */

        WebDriver driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com");
        driver.manage().window().maximize();
//        driver.findElement(By.cssSelector("input#small-searchterms")).sendKeys("hehe");
//        driver.findElement(By.cssSelector("#small-searchterms")).sendKeys("hehe");

        driver.findElement(By.cssSelector("input.search-box-text")).sendKeys("hehe");
        driver.findElement(By.cssSelector(".search-box-text")).sendKeys("hehe");

        driver.findElement(By.cssSelector("input[placeholder='Search store']")).sendKeys("hehe");

        driver.findElement(By.cssSelector("input.search-box-text[name='q']")).sendKeys("hi");
        driver.findElement(By.cssSelector(".search-box-text[name='q']")).sendKeys("hi");

        driver.close();
    }
}
