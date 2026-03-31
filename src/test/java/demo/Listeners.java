package demo;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed Ken");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test Failed Ken");
//        test.fail(result.getThrowable());
        extentTest.get().fail(result.getThrowable());
        String filePath = null;

//        try {
//            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (SecurityException e) {
//            e.printStackTrace();
//        }

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            filePath = getScreenShot(result.getMethod().getMethodName(), driver);
            extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
        }

//        try {
//            filePath = getScreenShot(result.getMethod().getMethodName(), driver);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}

/*
🎯 Listener này dùng để làm gì?
Listener trong TestNG là một cơ chế “nghe” các sự kiện xảy ra trong quá trình chạy test (bắt đầu, thành công, thất bại, bỏ qua…).
Ở đây, Listeners implement ITestListener, nghĩa là nó override các method callback của TestNG.

Mục đích:
Tạo log trong ExtentReports cho từng test case.
Ghi nhận trạng thái test (PASS/FAIL/SKIP).
Nếu test fail, chụp screenshot và attach vào báo cáo.
Cuối cùng flush báo cáo để ghi ra file HTML.

🧩 Giải thích chi tiết từng method
1. onTestStart(ITestResult result)
Được gọi ngay khi test bắt đầu chạy.
Tạo một ExtentTest mới với tên là method test (result.getMethod().getMethodName()).
Lưu vào ThreadLocal<ExtentTest> để đảm bảo thread-safe khi chạy song song.
Khi dùng: luôn chạy tự động khi test bắt đầu, bạn không cần gọi thủ công.

2. onTestSuccess(ITestResult result)
Được gọi khi test chạy thành công (không có exception, assertion pass).
Ghi log vào ExtentReports: Status.PASS.
Khi dùng: tự động chạy khi test pass, giúp báo cáo hiển thị rõ ràng.

3. onTestFailure(ITestResult result)
Được gọi khi test bị fail (có exception hoặc assertion fail).
Ghi log vào ExtentReports: Status.FAIL.
Ghi chi tiết lỗi (result.getThrowable()).
Lấy đối tượng WebDriver từ test instance để chụp screenshot.
Gọi getScreenShot(...) để lưu ảnh, sau đó attach ảnh vào báo cáo (addScreenCaptureFromPath).
Khi dùng: cực kỳ hữu ích để debug, vì báo cáo sẽ có cả stacktrace và ảnh chụp màn hình tại thời điểm fail.

4. onTestSkipped(ITestResult result)
Được gọi khi test bị bỏ qua (ví dụ do dependency fail hoặc bị disable).
Hiện tại chưa implement gì, nhưng bạn có thể log Status.SKIP.
Khi dùng: để biết test nào không chạy.

5. onTestFailedButWithinSuccessPercentage(ITestResult result)
Được gọi khi test fail nhưng nằm trong ngưỡng “success percentage” (ít dùng).
Ví dụ: bạn cho phép 90% test pass thì 10% fail vẫn coi là success.
Khi dùng: hiếm khi cần, thường để log thêm.

6. onStart(ITestContext context)
Được gọi trước khi bất kỳ test nào trong <test> bắt đầu.
Có thể dùng để setup ExtentReports, log thông tin test suite.
Ở đây chưa implement gì.
Khi dùng: để chuẩn bị trước khi chạy test.

7. onFinish(ITestContext context)
Được gọi sau khi tất cả test trong <test> kết thúc.
Gọi extent.flush() để ghi toàn bộ log ra file báo cáo.
Khi dùng: luôn cần để đảm bảo báo cáo được lưu.

📌 Tóm tắt
onTestStart → tạo test trong báo cáo.
onTestSuccess → log PASS.
onTestFailure → log FAIL + stacktrace + screenshot.
onTestSkipped → log SKIP.
onStart → chuẩn bị trước khi chạy test.
onFinish → flush báo cáo sau khi chạy xong.

🔹 ITestResult
Đại diện cho kết quả của một test method (mỗi lần chạy một test case).

Những thông tin chính bên trong:
getMethod() → trả về thông tin method test (tên, class chứa nó).
getName() → tên của test method.
getStatus() → trạng thái test (PASS, FAIL, SKIP).
getThrowable() → exception hoặc lỗi gây fail.
getStartMillis() / getEndMillis() → thời gian bắt đầu/kết thúc test.
getTestClass() → class chứa test method.
getInstance() → instance của class test (dùng để lấy field như driver).
isSuccess() → test có pass hay không.

Khi nào sử dụng?
Trong Listener (onTestStart, onTestSuccess, onTestFailure, onTestSkipped).
Để log trạng thái test, lấy tên test, lấy exception khi fail.
Để chụp screenshot khi fail (lấy driver từ getInstance()).
Để đo thời gian chạy test.

🔹 ITestContext
Đại diện cho ngữ cảnh của một <test> trong file XML (không phải từng method, mà là cả block <test>).

Những thông tin chính bên trong:
getName() → tên của <test> trong XML.
getSuite() → thông tin về suite chứa test này.
getStartDate() / getEndDate() → thời gian bắt đầu/kết thúc toàn bộ test.
getAllTestMethods() → danh sách tất cả test method trong test này.
getPassedTests() / getFailedTests() / getSkippedTests() → kết quả tổng hợp.
getCurrentXmlTest() → lấy parameter từ XML (ví dụ browser, env).
getAttribute() / setAttribute() → lưu và chia sẻ dữ liệu giữa các test method trong cùng một test.

Khi nào sử dụng?
Trong Listener (onStart, onFinish).
Để log thông tin tổng quan khi test suite bắt đầu/kết thúc.
Để lấy parameter từ XML mà bạn khai báo ở <suite> hoặc <test>.
Để chia sẻ dữ liệu giữa các test method (ví dụ: lưu token login để dùng lại).

📌 Tóm tắt
ITestResult → chi tiết về một test method (trạng thái, exception, thời gian, class, instance).
ITestContext → chi tiết về một <test> trong XML (tên test, danh sách method, kết quả tổng hợp, parameter).
👉 Bạn dùng ITestResult khi muốn xử lý từng test case (log, screenshot, exception).
👉 Bạn dùng ITestContext khi muốn xử lý toàn bộ test block (lấy parameter, tổng hợp kết quả, chia sẻ dữ liệu).
* */