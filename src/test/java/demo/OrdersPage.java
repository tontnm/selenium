package demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends AbstractComponent {
    WebDriver driver;

    public OrdersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "td:nth-child(3)")
    List<WebElement> orderProducts;

    public Boolean VerifyProductDisplay(String productName) {
        Boolean matched = orderProducts.stream().anyMatch(op -> op.getText().equalsIgnoreCase(productName));
        return matched;
    }
}
