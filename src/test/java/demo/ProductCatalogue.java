package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductCatalogue extends AbstractComponent {
    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //    List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
    @FindBy(css = ".mb-3")
    List<WebElement> products;

    @FindBy(css = ".ng-animating")
    WebElement spinner;

    By productBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");

    public List<WebElement> getProductList() {
        waitForElementToAppear(productBy);
        return products;
    }

    public WebElement getProductByName(String productName) {
        WebElement prod = getProductList().stream().
                filter(p -> p.findElement(By.cssSelector("b")).
                        getText().equalsIgnoreCase(productName)).
                findFirst().orElse(null);

        return prod;
    }

    public void addProductToCart(String productName) throws InterruptedException {
        WebElement prod = getProductByName(productName);

        moveToElement(prod);
        waitForElementToAppear(addToCart);
        prod.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }

    public void addProductToCart1(String productName) throws InterruptedException {
        WebElement prod = getProductByName(productName);

        // Scroll tới sản phẩm
        moveToElement(prod);

        // Lấy nút Add To Cart trong sản phẩm
        WebElement addBtn = prod.findElement(addToCart);

        // Chờ nút này clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();

        // Chờ toast và spinner
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }

}
