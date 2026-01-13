package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AbstractComponent {
    WebDriver driver;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[routerlink='/dashboard/cart']")
    WebElement cartHeader;

    @FindBy(css = "[routerlink='/dashboard/myorders']")
    WebElement ordersHeader;

    public void waitForElementToAppear(By findBy) {
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
        w.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForWebElementToAppear(WebElement findBy) {
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
        w.until(ExpectedConditions.visibilityOf(findBy));
    }

    public void waitForElementToDisappear(WebElement element) throws InterruptedException {
        Thread.sleep(1000);
//        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
//        w.until(ExpectedConditions.invisibilityOf(element));
    }

    public CartPage goToCartPage() throws InterruptedException {
//        moveToElement(cartHeader);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartHeader);
//        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");
//        cartHeader.click();

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");
        // Chờ header sẵn sàng
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cartHeader)).click();

        CartPage cp = new CartPage(driver);

        return cp;
    }

    public OrdersPage goToOrdersPage() {
        moveToElement(ordersHeader);
        ordersHeader.click();

        OrdersPage op = new OrdersPage(driver);

        return op;
    }

//    public void moveToElement(WebElement e) {
//        Actions actions = new Actions(driver);
//        actions.moveToElement(e).perform();
//    }


    public void moveToElement(WebElement e) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
    }
}
