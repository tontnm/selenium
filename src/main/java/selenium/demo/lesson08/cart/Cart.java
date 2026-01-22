package selenium.demo.lesson08.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cart {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        driver.manage().window().maximize();

        List<String> selectedItems = Arrays.asList("Cucumber", "Apple", "Beetroot");

        // làm sao để viết code 1 cách chung chung để sử dụng cho nhiều data khác nhau
        // Lấy danh sách sản phẩm và nút "Add to Cart" một lần
        List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));
        //button[text()='ADD TO CART'] không đc dùng text như vầy, vì sau khi click vô text sẽ đổi thành ADDED và
        // từ 30 element nó sẽ rớt xuống còn 29, và sẽ click sai những item sau, giải quyết vấn đề này bằng cách
        // bắt cái vòng ngoài của nút thay vì bắt nút bằng text
        List<WebElement> addToCartButtons = driver.findElements(By.cssSelector("div[class='product-action']"));

        int clickedCount = 0;

        for (int i = 0; i < products.size(); i++) {
            String productName = products.get(i).getText();

            // Kiểm tra xem sản phẩm có nằm trong danh sách cần chọn không
            for (String item : selectedItems) {
                if (productName.contains(item)) {
                    addToCartButtons.get(i).click();
                    System.out.println("Added: " + productName);
                    clickedCount++;
                    break; // thoát vòng lặp item vì đã tìm thấy
                }
            }

            // Nếu đã click đủ số lượng item cần chọn thì thoát luôn vòng lặp products
            if (clickedCount == selectedItems.size()) {
                break;
            }
        }
    }
}

/*
* 1.tạo ra ds tên mình muốn click
* 2.lấy hết tên trên web về
* 3.lấy hết nút trên web cần click về
* 4.đi qua từng product, compare tên với ds mình có, khớp là click nút
* 5.thêm biến count để thoát sớm
* */