package demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cartSection h3")
    List<WebElement> cartProducts;

    @FindBy(css = ".totalRow button")
    WebElement checkoutBtn;

    public Boolean VerifyProductDisplay(String productName) {
        Boolean matched = cartProducts.stream().anyMatch(cp -> cp.getText().equalsIgnoreCase(productName));
        return matched;
    }

    public CheckoutPage goToCheckoutPage() {
        checkoutBtn.click();

        CheckoutPage cp = new CheckoutPage(driver);

        return cp;
    }
}
