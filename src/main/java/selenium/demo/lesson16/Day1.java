package selenium.demo.lesson16;

import org.testng.annotations.Test;

public class Day1 {
    @Test(priority = 1)
    public void setup() {
        System.out.println("day 1 set up");
    }

    @Test(priority = 2)
    public void testcase() {
        System.out.println("day 1 test case");
    }

    @Test(priority = 3)
    public void teardown() {
        System.out.println("day 1 tear down");
    }
}

/*
Danh sách Annotation trong TestNG với Properties:

1. @BeforeSuite
   - Properties: không có.
   - Hoạt động: chạy trước tất cả test trong suite.
   - Khi nào dùng: setup toàn cục (ví dụ: mở DB, load config).

2. @AfterSuite
   - Properties: không có.
   - Hoạt động: chạy sau tất cả test trong suite.
   - Khi nào dùng: cleanup toàn cục (đóng DB, gửi báo cáo).

3. @BeforeTest
   - Properties: không có.
   - Hoạt động: chạy trước mỗi <test> trong file testng.xml.
   - Khi nào dùng: setup cho nhóm test (ví dụ: khởi tạo driver cho module).

4. @AfterTest
   - Properties: không có.
   - Hoạt động: chạy sau mỗi <test> trong file testng.xml.
   - Khi nào dùng: cleanup cho nhóm test.

5. @BeforeClass
   - Properties: không có.
   - Hoạt động: chạy trước khi class test được thực thi.
   - Khi nào dùng: khởi tạo resource cho class (ví dụ: mở browser).

6. @AfterClass
   - Properties: không có.
   - Hoạt động: chạy sau khi class test được thực thi.
   - Khi nào dùng: đóng resource cho class (đóng browser).

7. @BeforeMethod
   - Properties: không có.
   - Hoạt động: chạy trước mỗi test method.
   - Khi nào dùng: setup trạng thái trước từng test case (login, reset data).

8. @AfterMethod
   - Properties: không có.
   - Hoạt động: chạy sau mỗi test method.
   - Khi nào dùng: cleanup sau test case (logout, clear cache).

9. @Test
   - Properties:
     + priority: xác định thứ tự chạy test.
     + groups: gom test vào nhóm.
     + dependsOnMethods: chỉ chạy nếu method khác chạy thành công.
     + dependsOnGroups: chỉ chạy nếu group khác chạy thành công.
     + enabled: true/false để bật/tắt test.
     + invocationCount: số lần lặp lại test.
     + threadPoolSize: số thread chạy song song khi invocationCount > 1.
     + timeOut: giới hạn thời gian chạy test.
     + dataProvider: liên kết với @DataProvider.
     + description: mô tả test case.
   - Hoạt động: đánh dấu method là test case.
   - Khi nào dùng: viết logic kiểm thử chính, điều khiển cách chạy.

10. @DataProvider
    - Properties:
      + name: tên của data provider.
      + parallel: true/false để chạy dữ liệu song song.
    - Hoạt động: cung cấp dữ liệu cho test method.
    - Khi nào dùng: chạy test với nhiều bộ dữ liệu khác nhau.

11. @Parameters
    - Properties: tên tham số lấy từ file testng.xml.
    - Hoạt động: nhận tham số từ XML.
    - Khi nào dùng: truyền giá trị động (ví dụ: browser, URL).

12. @Factory
    - Properties: không có.
    - Hoạt động: tạo nhiều instance của class test.
    - Khi nào dùng: chạy test với nhiều cấu hình khác nhau.

13. @Listeners
    - Properties: class-name của listener.
    - Hoạt động: gắn listener để theo dõi sự kiện test.
    - Khi nào dùng: log, screenshot, báo cáo.

14. @BeforeGroups
    - Properties: groups: tên nhóm cần setup.
    - Hoạt động: chạy trước khi nhóm test được thực thi.
    - Khi nào dùng: setup cho nhóm test case (ví dụ: nhóm API test).

15. @AfterGroups
    - Properties: groups: tên nhóm cần cleanup.
    - Hoạt động: chạy sau khi nhóm test được thực thi.
    - Khi nào dùng: cleanup cho nhóm test case.

* */