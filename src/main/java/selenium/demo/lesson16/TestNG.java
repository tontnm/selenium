package selenium.demo.lesson16;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNG {
    @Parameters({"browser"})
    @Test(priority = 1)
    public void setup(String browser) {
        System.out.println("setup");
        System.out.println(browser);
    }

    @Test(priority = 2)
    public void secondTestCase() {
        System.out.println("secondTestCase");
    }

    @Test(priority = 3)
    public void teardown() {
        System.out.println("teardown");
    }
}

/*
Danh sách Annotation trong TestNG:

1. @BeforeSuite
   - Thứ tự: chạy đầu tiên trong toàn bộ suite.
   - Cách sử dụng: đặt trong class bất kỳ.
   - Khi nào dùng: setup toàn cục (ví dụ: mở DB, load config).

2. @AfterSuite
   - Thứ tự: chạy cuối cùng trong toàn bộ suite.
   - Cách sử dụng: đặt trong class bất kỳ.
   - Khi nào dùng: cleanup toàn cục (đóng DB, gửi báo cáo).

3. @BeforeTest
   - Thứ tự: chạy trước mỗi <test> trong file testng.xml.
   - Cách sử dụng: đặt trong class liên quan đến nhóm test.
   - Khi nào dùng: setup cho nhóm test (ví dụ: khởi tạo driver cho module).

4. @AfterTest
   - Thứ tự: chạy sau mỗi <test> trong file testng.xml.
   - Cách sử dụng: đặt trong class liên quan đến nhóm test.
   - Khi nào dùng: cleanup cho nhóm test.

5. @BeforeClass
   - Thứ tự: chạy trước khi class test được thực thi.
   - Cách sử dụng: đặt trong class test.
   - Khi nào dùng: khởi tạo resource cho class (ví dụ: mở browser).

6. @AfterClass
   - Thứ tự: chạy sau khi class test được thực thi.
   - Cách sử dụng: đặt trong class test.
   - Khi nào dùng: đóng resource cho class (đóng browser).

7. @BeforeMethod
   - Thứ tự: chạy trước mỗi test method.
   - Cách sử dụng: đặt trong class test.
   - Khi nào dùng: setup trạng thái trước từng test case (login, reset data).

8. @AfterMethod
   - Thứ tự: chạy sau mỗi test method.
   - Cách sử dụng: đặt trong class test.
   - Khi nào dùng: cleanup sau test case (logout, clear cache).

9. @Test
   - Thứ tự: chính giữa, là test case.
   - Cách sử dụng: đánh dấu method là test case.
   - Khi nào dùng: viết logic kiểm thử chính.

10. @DataProvider
    - Thứ tự: gọi khi test method cần dữ liệu.
    - Cách sử dụng: tạo method trả về Object[][], gắn với @Test bằng dataProvider="name".
    - Khi nào dùng: chạy test với nhiều bộ dữ liệu khác nhau.

11. @Parameters
    - Thứ tự: gọi khi test method nhận tham số từ file testng.xml.
    - Cách sử dụng: khai báo trong XML, dùng @Parameters trong method.
    - Khi nào dùng: truyền giá trị động (ví dụ: browser, URL).

12. @Factory
    - Thứ tự: gọi khi cần tạo nhiều instance của class test.
    - Cách sử dụng: method trả về Object[], mỗi phần tử là instance của class test.
    - Khi nào dùng: chạy test với nhiều cấu hình khác nhau.

13. @Listeners
    - Thứ tự: gắn ở class hoặc trong XML, chạy xuyên suốt.
    - Cách sử dụng: implement ITestListener, gắn bằng @Listeners hoặc trong XML.
    - Khi nào dùng: theo dõi sự kiện test (log, screenshot, báo cáo).

14. @BeforeGroups
    - Thứ tự: chạy trước khi một nhóm test được thực thi.
    - Cách sử dụng: gắn trong class test, chỉ định group.
    - Khi nào dùng: setup cho nhóm test case (ví dụ: nhóm API test).

15. @AfterGroups
    - Thứ tự: chạy sau khi một nhóm test được thực thi.
    - Cách sử dụng: gắn trong class test, chỉ định group.
    - Khi nào dùng: cleanup cho nhóm test case.

* */

