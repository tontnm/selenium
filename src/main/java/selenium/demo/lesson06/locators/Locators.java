package selenium.demo.lesson06.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Locators {
    /*
    * By.id
    *   driver.findElement(By.id("username"))
    *   Nhanh nhất, duy nhất, ổn định
    *   Phụ thuộc vào việc có ID hay không
    *   Khi element có id rõ ràng
    * By.name
    *   driver.findElement(By.name("email"))
    *   Đơn giản, dễ hiểu
    *   Có thể trùng lặp
    *   Khi form có name duy nhất
    * By.className
    *   driver.findElement(By.className("btn-primary"))
    *   Dễ dùng với CSS class
    *   Class thường trùng lặp
    *   Khi muốn bắt theo style/class
    * By.tagName
    *   driver.findElement(By.tagName("input"))
    *   Nhanh, đơn giản
    *   Thường không duy nhất
    *   Khi cần lấy nhiều element cùng loại
    * By.linkText
    *   driver.findElement(By.linkText("Đăng nhập"))
    *   Rõ ràng với link
    *   Phụ thuộc vào text chính xác
    *   Khi bắt link có text cố định
    * By.partialLinkText
    *   driver.findElement(By.partialLinkText("Đăng"))
    *   Linh hoạt hơn linkText
    *   Có thể bắt nhầm
    *   Khi text dài hoặc thay đổi một phần
    * By.cssSelector
    *   driver.findElement(By.cssSelector("div#login input[type='text']"))
    *   Nhanh, mạnh mẽ, hỗ trợ nhiều điều kiện
    *   Cú pháp phức tạp
    *   Khi cần bắt element phức tạp, nhiều điều kiện
    * By.xpath
    *   driver.findElement(By.xpath("//div[@id='login']//input[@type='text']"))
    *   Linh hoạt nhất, hỗ trợ cả DOM phức tạp
    *   Chậm hơn CSS, cú pháp dài
    *   Khi không có ID/name, hoặc DOM phức tạp
    *
    * ⚡ Cách nào nhanh nhất?
        Nhanh nhất: By.id → vì Selenium truy cập trực tiếp theo ID, không cần duyệt DOM nhiều.
        Thứ hai: By.cssSelector → thường nhanh hơn By.xpath vì engine trình duyệt tối ưu CSS tốt hơn.
      Trong thực tế, tester thường ưu tiên By.id nếu có, nếu không thì dùng CSS Selector, và cuối cùng mới dùng XPath khi cần.
      *
      * 🎨 Các cách bắt bằng CSS Selector (Selenium)
      * Theo tài liệu:
      * Tag + ID → tag#id (ví dụ: input#username)
      * Tag + Class → tag.class (ví dụ: button.btn-primary)
      * Tag + Attribute → tag[attribute='value'] (ví dụ: input[type='text'])
      * Tag + Class + Attribute → tag.class[attribute='value']
      * Multiple attributes → tag[attr1='val1'][attr2='val2']
      * Descendant/Child selectors → div > input hoặc div input
      * Substring match:
      *     ^= bắt đầu bằng → input[name^='user']
      *     $= kết thúc bằng → input[name$='name']
      *     *= chứa → input[name*='ser']
      * Pseudo-classes: nth-child, first-child, last-child
      * Inner text (ít dùng): :contains("text") (không chuẩn, nhưng một số framework hỗ trợ)
      * 👉 Phổ biến nhất trong CSS: tag#id, tag.class, và tag[attribute='value'] vì ngắn gọn, dễ đọc, tốc độ nhanh.
      *
      * 🧭 Các cách bắt bằng XPath (Selenium)
      * Theo tài liệu:
      * Absolute XPath → đường dẫn đầy đủ từ root: /html/body/div[1]/input
      * Relative XPath → bắt từ bất kỳ node: //input[@id='username']
      * Basic attribute match → //tag[@attr='value']
      * Contains() → //input[contains(@name,'user')]
      * Starts-with() → //input[starts-with(@id,'btn')]
      * Text() → //a[text()='Đăng nhập']
      * Logical operators → //input[@type='text' and @name='username']
      * Axes methods:
      *     following → //div[@id='login']//following::input[1]
      *     preceding → //div[@id='login']//preceding::label[1]
      *     ancestor → //input[@id='username']//ancestor::form
      *     descendant → //div[@id='login']//descendant::input
      *     sibling → //label[@for='user']//following-sibling::input
      * 👉 Phổ biến nhất trong XPath: Relative XPath với attribute match (//tag[@attr='value']) và contains() vì chúng linh hoạt,
      * dễ dùng cho DOM động.
    * */


    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\browser-for-testing\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        driver.findElement(By.id("inputUsername")).sendKeys("Ken");
        driver.findElement(By.name("inputPassword")).sendKeys("Ken");

        driver.findElement(By.className("submit")).click();

        /*
         * css selector có 3 cách xài
         * 1) class name -> tagname.classname -> button.sighInBtn
         * 2) id -> tagname#id -> input#inputUsername
         * 3) tagname[attribute='value']
         * */

        String errMsg = driver.findElement(By.cssSelector("p.error")).getText();
        System.out.println(errMsg);

//        driver.findElement(By.linkText("Forgot your password?")).click();
//        driver.navigate().back();
        driver.findElement(By.partialLinkText("Forgot")).click();

        /*
         * xpath
         * //tagname[attribute='value']
         * $x('//input[@placeholder="Name"]')
         * */
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@placeholder=\"Name\"]")).sendKeys("abc");
        driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("def");
//        driver.findElement(By.xpath("//input[@type='text'][3]")).sendKeys("abc");
//        driver.findElement(By.xpath("//input[@type='text'][3]")).clear();


        driver.findElement(By.xpath("//form/input[3]")).sendKeys("hehe");
        driver.findElement(By.cssSelector(".reset-pwd-btn")).click();

        System.out.println(driver.findElement(By.cssSelector("form p")).getText());

        driver.findElement(By.cssSelector("button[class*='go-to']")).click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("#inputUsername")).sendKeys("rahul");
        driver.findElement(By.cssSelector("input[type*='pass'")).sendKeys("rahulshettyacademy");
        driver.findElement(By.xpath("//button[contains(@class,'submit')]")).click();

//        driver.quit();
    }
}
