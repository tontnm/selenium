package automation.s03.l31;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AuthenticatedPopup {

	public static void main(String[] args) {
		
		
		WebDriver driver=new ChromeDriver();
		
		//driver.get("http://the-internet.herokuapp.com/basic_auth");
		
		//syntax (ko phải dùng cho tất cả web app)
		//http://username:password@the-internet.herokuapp.com/basic_auth
			
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
			
		driver.manage().window().maximize();


	}

}
