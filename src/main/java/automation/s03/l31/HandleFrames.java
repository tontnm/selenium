package automation.s03.l31;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HandleFrames {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        /*
        * phân biệt frame và iframe
        * 1 web page có nhiều section trong mỗi section có nhiều element thì gọi là frame
        * 1 web page nhúng vào section của 1 web page khác gọi là iframe
        * có tag frame thì xác định là có frame
        * element ở trong frame, thì driver ko thể đi vào trong frame
        * phải switch vào frame đã, sau đó làm gì ở trong frame
        * rồi phải thoát frame thì mới qua frame mới được
        * */

		driver.get("https://ui.vision/demo/webtest/frames/");
		driver.manage().window().maximize();
		
		//Frame1
		WebElement frame1=driver.findElement(By.xpath("//frame[@src='frame_1.html']"));
		driver.switchTo().frame(frame1);
		driver.findElement(By.xpath("//input[@name='mytext1']")).sendKeys("Welcome");
		
		driver.switchTo().defaultContent();
		
		//Frame2
		WebElement frame2=driver.findElement(By.xpath("//frame[@src='frame_2.html']"));
		driver.switchTo().frame(frame2);
		driver.findElement(By.xpath("//input[@name='mytext2']")).sendKeys("Automation");
		
		driver.switchTo().defaultContent();
		
		//frame3
		WebElement frame3=driver.findElement(By.xpath("//frame[@src='frame_3.html']"));
		driver.switchTo().frame(frame3);
		driver.findElement(By.xpath("//input[@name='mytext3']")).sendKeys("programming");
		
		//swith to inner frame (part of frame3)
        //copy của iframe, gõ url là ra 1 trang mới
		driver.switchTo().frame(0);  // switched to inner frame
		
		driver.findElement(By.xpath("//div[@id='i5']//div[@class='AB7Lab Id5V1']")).click();

        // page - f1 - f2 (e) - page - f1 - f2
        // iframe khác frame, iframe là nhúng 1 web page vào, còn frame chỉ chứa element thôi
        // xài index cho iframe
        // xài id, name, web element, index cho frame
	}

}
