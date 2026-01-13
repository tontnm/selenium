package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //    WebElement userEmail = driver.findElement(By.id("userEmail"));
    @FindBy(id = "userEmail")
    WebElement userEmail;

    //    WebElement userPassword = driver.findElement(By.id("userPassword"));
    @FindBy(id = "userPassword")
    WebElement userPassword;

    //    WebElement loginBtn = driver.findElement(By.id("login"));
    @FindBy(id = "login")
    WebElement loginBtn;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errMsg;

    public void openWebsite() {
        driver.get("https://rahulshettyacademy.com/client/#/auth/login");
        driver.manage().window().maximize();
    }

    public ProductCatalogue loginApp(String email, String password) {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginBtn.click();

        ProductCatalogue pc = new ProductCatalogue(driver);

        return pc;
    }

    public String getErrMsg() {
        waitForWebElementToAppear(errMsg);
        return errMsg.getText();
    }
}
