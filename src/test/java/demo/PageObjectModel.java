package demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class PageObjectModel extends BaseTest {
    //    String prodName = "ADIDAS ORIGINAL";
    String browser1;
    String browser2;

    //    @BeforeMethod
    @Parameters({"browser1", "browser2"})
    @BeforeTest
    public void setup(@Optional("chrome") String browser1, @Optional("edge") String browser2) {
        this.browser1 = browser1;
        this.browser2 = browser2;
    }

    @Test(groups = {"Purchase"}, dataProvider = "getData")
    public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
        System.out.println(browser1);
        System.out.println(browser2);

//        LandingPage lp = launchApp();
        ;
//        ProductCatalogue pc = lp.loginApp(prop.getProperty("username"), prop.getProperty("password"));
        ProductCatalogue pc = lp.loginApp(input.get("email"), input.get("password"));

//        ProductCatalogue pc = new ProductCatalogue(driver);
//        pc.waitForElementToAppear(By.cssSelector(".mb-3"));
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0,500)");
//        List<WebElement> products = pc.getProductList();

//        pc.moveToElement(driver.findElement(By.cssSelector(".mb-3")));
        pc.addProductToCart1(input.get("product"));

        CartPage cp = pc.goToCartPage();

//        CartPage cp = new CartPage(driver);
        Boolean matched = cp.VerifyProductDisplay(input.get("product"));
        Assert.assertTrue(matched);

        CheckoutPage cop = cp.goToCheckoutPage();
        cop.selectCountry("ind", "india");

        PlaceOrderPage pop = cop.goToPlaceOrderPage();
        String confirmMsg = pop.getConfirmMsg();
        Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods = {"submitOrder"}, groups = {"Purchase"}, dataProvider = "getData")
    public void OrderHistoryTest(HashMap<String, String> input) {
        ProductCatalogue pc = lp.loginApp(input.get("email"), input.get("password"));
        OrdersPage op = pc.goToOrdersPage();
        Boolean matched = op.VerifyProductDisplay(input.get("product"));
        Assert.assertTrue(matched);
    }

    //20 test case - 20 account - làm sao?
    @DataProvider
    public Object[][] getData() throws IOException {
//        HashMap<String, String> input1 = new HashMap<>();
//        input1.put("email", "tontnm@gmail.com");
//        input1.put("password", "Aa!1234567890");
//        input1.put("product", "ADIDAS ORIGINAL");
//
//        HashMap<String, String> input2 = new HashMap<>();
//        input2.put("email", "tontnm@gmail.com");
//        input2.put("password", "Aa!1234567890");
//        input2.put("product", "ZARA COAT 3");

        List<HashMap<String, String>> data = getJSONDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\demo\\PurchaseOrder.json");


        return new Object[][]{
                {data.get(0)},
                {data.get(1)},
//                {input1},
//                {input2},
//                {"tontnm@gmail.com", "Aa!1234567890", "ZARA COAT 3"}
        };
    }


}

/*
* testng xml file importance
* control test case executions from testng xml file
* include exclude mechanism
* testng annotations
* groups
* data provider annotation
* annotation helper attribute
* parameter in testng xml file
* run test in parallel with testng
* testng listener
*
* */

/*
 * Selenium FW Interview Questions
 * 0. làm sao truyền data vào test case? testng.xml parameter, data provider
 * 0. chạy parallel 100 test case như thế nào?
 * 0. làm sao để thằng parent nhận được data từ thằng child truyền lên: super
 * 1. what is the design pattern you used in writing the tests in the framework?
 *   - POM (Page Object Model), 1 Page = 1 Class, trả về object của page tiếp theo
 *   - PageFactory dùng để tạo những objects nhờ FindBy annotations
 *   - single, multiple WebElement
 * 2. How are reusable utilities handled within the fw?
 *   - Tạo AbstractComponent class để chứa toàn bộ những methods được tái sử dụng (wait,goToCartPage,OrderPage)
 *   - cho các class khác inherit từ AbstractComponent, để sử dụng đc các methods
 *   - dùng BaseTest class để chứa những methods initialize dùng chung (initializeDriver,launchApp,Teardown)
 * 3. Where did you use Inheritance OOPs Concept in your fw?
 * 4. How did you drive the data from external files in the fw?
 *   - dùng json file để truyền data
 *   - làm cách nào? dùng testng data provider notation, dùng json file, convert data vào hashmap
 *   - convert như thế nào? dùng library Jackson Databind, json file -> List<HashMap>
 * 5. did you use interface in the fw? if so, what is the scope of it?
 *   - ITestListener interface
 *   - tại sao dùng interface đó? giúp viết code mà ko cần lặp lại ở trong từng tc
 *   - Tách biệt trách nhiệm: Test chỉ tập trung vào kiểm thử nghiệp vụ; Listener xử lý logging, báo cáo, screenshot.
 *       Điều này làm code sạch, dễ bảo trì.
 *   - Tự động hóa theo vòng đời: Không cần lặp lại try/catch hay if (fail) chụp ảnh trong từng test—Listener chạy tự động
 *       khi sự kiện xảy ra.
 *   - Tính nhất quán: Mọi test đều được log theo cùng một chuẩn (PASS/FAIL/SKIP), cùng format, cùng cách chụp ảnh—
 *       giảm sai lệch giữa các test.
 *   - Dễ mở rộng: Khi muốn thêm tính năng (ví dụ: gửi Slack khi fail, attach HAR, log network), chỉ cần sửa Listener,
 *       không phải chạm vào từng test case.
 * 6. how are you achieving Encapsulation in the fw?
 *   - cho các element là private
 * 7. does your fw support Parallel run? How are you writing Thread safe code?
 *   - testng.xml -> parallel=tests
 * 8. do you have static keywords in the fw? if so, its usage?
 * 9. how are you sending Global Properties to your test at run time?
 * 10. what is the machenism you used to run only selected set of test inside the fw?
 * 11. how are you handling Flaky tests in the fw?
 * 12. does your fw take screenshot on test failures? how did you implement it?
 * */