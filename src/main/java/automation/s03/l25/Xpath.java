package automation.s03.l25;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Xpath {
    public static void main(String[] args) {
        /*
        1. Absolute xpath (full xpath)
        2. Relative xpath (partial xpath)

        tại sao ưu tiên dùng relative xpath?
        - vì dev thay đổi element thường xuyên theo requirement mới, fix cứng thì sẽ bị tạch

        khác nhau giữa full và partial xpath
        - full xpath dùng /, ko dùng attribute, đi qua từng node để tìm element
        - partial dùng //, dùng attribute, nhảy trực tiếp đến element = attribute

        relative xpath
        - auto -> selectorhub, devtool
        - manually(own path)

        syntax:
        - //tagname[@attribute='value']
        - //*[@attribute='value']

        xpath+single attribute
        xpath+multiple attribute
        xpath+ 'and' 'or'

        xpath+inner text -> dùng cho tag a
        <a href="https://xyz.com">Click me</a>
        //a[text()=' Electronics ']
        linktext=yes,inner text=yes

        <div>welcome</div>
        linktext=no,inner text=yes

        text() chỉ dùng cho inner text và link text thôi

        xpath+contains
        //tagname[contains(@placeholder,'come')]

         */


        WebDriver driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
//        driver.findElement(By.xpath("//input[@name='q']")).sendKeys("abc");
//        driver.findElement(By.xpath("//input[@name='q'][@placeholder='Search store']")).sendKeys("abc");
        driver.findElement(By.xpath("//input[@name='q' and @placeholder='Search store']")).sendKeys("abc");
        driver.findElement(By.xpath("//input[@name='q' or @placeholder='Search store']")).sendKeys("abc");

        WebElement msg = driver.findElement(By.xpath("//h2[text()='Welcome to our store']"));
        boolean isWelcome = msg.isDisplayed();
        if (isWelcome) {
            String title = msg.getText();
            System.out.println("có " + title);
        } else {
            System.out.println("không");
        }

        //input[contains(@placeholder,'Search')]
        //a[contains(@href,'cart')]
        //input[contains(@name,'q') and contains(@placeholder,'store')]
        //h2[contains(text(),'Welcome') and contains(@class,'title')]
        //input[contains(@placeholder,'store') or contains(@placeholder,'products')]

        WebElement msg2 = driver.findElement(By.xpath("//h2[contains(text(),'come')]"));
        boolean isWelcome2 = msg2.isDisplayed();
        if (isWelcome2) {
            String title = msg2.getText();
            System.out.println("có contain " + title);
        } else {
            System.out.println("không");
        }

        // câu hỏi phỏng vấn, làm sao để handle dynamic element, dùng starts-with và contains
        // 1 nút lúc start lúc stop
        // //*[@id='start' or @id='stop']
        // //*[contains(@id,'st')]
        // //*[starts-with(@id,'st')]

        // name=xyz001 xyz002...
        // //*[contains(@name,'xyz')]
        // //*[contains(@name,'00')]
        // //*[starts-with(@name,'xyz')]

        // name=001xyz 002xyz...
        // //*[contains(@name,'xyz')]

        // chain xpath
        //div[@id='logo']/a/img

        //<div></div>
        //div[contains(text(),'')]
        //[contains(.,'')]

        // css selector đi theo hướng trên xuống dưới để tìm element
        // xpath đi theo mọi hướng để tìm element
    }
}
