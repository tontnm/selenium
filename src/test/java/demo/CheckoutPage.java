package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[placeholder='Select Country']")
    WebElement countryField;

    @FindBy(css = ".ta-item")
    List<WebElement> searchedResults;

    @FindBy(css = ".action__submit")
    WebElement placeOrderBtn;

    By result = By.cssSelector(".ta-results");

    public void selectFromAutoSuggest(String valueToSelect) {
        for (WebElement option : searchedResults) {
            if (option.getText().equalsIgnoreCase(valueToSelect)) {
                option.click();
                break;
            }
        }
    }

    public void selectCountry(String key, String search) {
        countryField.sendKeys(key);
        waitForElementToAppear(result);
        selectFromAutoSuggest(search);
    }

    public PlaceOrderPage goToPlaceOrderPage() {
        placeOrderBtn.click();

        PlaceOrderPage pop = new PlaceOrderPage(driver);

        return pop;
    }
}
