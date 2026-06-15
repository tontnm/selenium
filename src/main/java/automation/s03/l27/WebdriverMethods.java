package automation.s03.l27;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class WebdriverMethods {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver=new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(5000);
        driver.findElement(By.linkText("OrangeHRM, Inc")).click(); // this will opens new browser window

        Thread.sleep(5000);
        //driver.close(); // close single browser window where ever the driver is focused
        driver.quit(); // close all the browser windows

//        WebDriver driver=new ChromeDriver();

        driver.get("https://demo.nopcommerce.com/register");
        driver.manage().window().maximize();

        //isDisplayed()

        //WebElement logo=driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']"));
        //System.out.println("Display status of logo:"+logo.isDisplayed()); //true

        //boolean status=driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']")).isDisplayed();
        //System.out.println("Display status:"+ status);


        //isEnabled()
        //boolean status=driver.findElement(By.xpath("//input[@id='FirstName']")).isEnabled();
        //System.out.println("Enable status:"+status);  //true

        //isSelected
        WebElement male_rd=driver.findElement(By.xpath("//input[@id='gender-male']"));
        WebElement female_rd=driver.findElement(By.xpath("//input[@id='gender-female']"));

        System.out.println("Before selection...............");
        System.out.println(male_rd.isSelected()); //false
        System.out.println(female_rd.isSelected()); //false

        System.out.println("After selecting male...");
        male_rd.click(); // select male radio button

        System.out.println(male_rd.isSelected()); // true
        System.out.println(female_rd.isSelected()); //false


        System.out.println("After selecting female...");
        female_rd.click(); // select female radio button

        System.out.println(male_rd.isSelected()); // false
        System.out.println(female_rd.isSelected()); //true


        boolean newsletterstatus=driver.findElement(By.xpath("//input[@id='Newsletter']")).isSelected();
        System.out.println("News letter checkbos status:"+newsletterstatus );  //true


        //get(url) - opens the url on the browser
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(5000);

        //getTitle() - returns title of the page
        System.out.println(driver.getTitle());  //OrangeHRM

        //getCurrentUrl() - returns URL of the page
        System.out.println(driver.getCurrentUrl()); //https://opensource-demo.orangehrmlive.com/web/index.php/auth/login

        //getPageSource()- returns source code of the page
        //System.out.println(driver.getPageSource());

        //getWindowHandle() - returns ID of the single Browser window
        //String windowid=driver.getWindowHandle();
        //System.out.println("Window ID:"+ windowid); //2C12CA3BA63C9819A03E4C8C98D55F7C
        //6C7FDFF673D02182158E1A2D0A4FA0A4

        //getWindowHandles() - retuns ID's of the multiple browser windows

        driver.findElement(By.linkText("OrangeHRM, Inc")).click(); // this will opens new browser window

        Set<String> windowids=driver.getWindowHandles();
        System.out.println(windowids); //[495D26972EA78583B6AC4278855D6EA0, EA208DEDD8103214FE92B755519C704D]

    }
}
