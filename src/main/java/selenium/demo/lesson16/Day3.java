package selenium.demo.lesson16;

import org.testng.annotations.Test;

public class Day3 {
    @Test(priority = 1)
    public void MobileSetup() {
        System.out.println("day 3 set up");
    }

    @Test(priority = 2)
    public void WebTestcase() {
        System.out.println("day 3 test case");
    }

    @Test(priority = 3)
    public void APITeardown1() {
        System.out.println("day 3 tear down 1");
    }

    @Test(priority = 4)
    public void APITeardown2() {
        System.out.println("day 3 tear down 2");
    }
}

/*
Nguyên lý khi annotation trùng nhau (parent-child):

1. TestNG sẽ chạy tất cả các method có cùng annotation.
   - Ví dụ: nhiều @BeforeMethod trong cùng class → tất cả đều chạy trước mỗi @Test.
   - Nhiều @AfterMethod → tất cả đều chạy sau mỗi @Test.

2. Thứ tự thực thi:
   - Với annotation cùng cấp (ví dụ nhiều @BeforeMethod), TestNG chạy theo thứ tự định nghĩa trong file .java.
   - Với annotation khác cấp (parent-child), TestNG vẫn giữ thứ tự phân cấp:
     @BeforeSuite → @BeforeTest → @BeforeClass → @BeforeMethod → @Test → @AfterMethod → @AfterClass → @AfterTest → @AfterSuite.

3. Parent-child trùng nhau:
   - Nếu bạn có nhiều @BeforeClass trong cùng class → tất cả chạy trước khi class bắt đầu.
   - Nếu bạn có nhiều @BeforeMethod → tất cả chạy trước mỗi test method.
   - Nếu bạn có nhiều @Test → mỗi test case chạy theo priority hoặc thứ tự định nghĩa.

4. Khi nào nên dùng nhiều annotation cùng cấp:
   - Khi muốn tách biệt logic setup/cleanup cho rõ ràng (ví dụ: một @BeforeMethod để mở browser, một @BeforeMethod để login).
   - Khi muốn chia nhỏ trách nhiệm (separation of concerns).

5. Khi nào không nên:
   - Nếu logic liên quan chặt chẽ, gộp vào một method sẽ dễ quản lý hơn.
   - Quá nhiều method cùng annotation dễ gây khó hiểu về thứ tự chạy.

* */