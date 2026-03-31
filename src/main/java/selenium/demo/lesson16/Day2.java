package selenium.demo.lesson16;

import org.testng.annotations.Test;

public class Day2 {
    @Test(priority = 1)
    public void setup() {
        System.out.println("day 2 set up");
    }

    @Test(priority = 2)
    public void testcase() {
        System.out.println("day 2 test case");
    }

    @Test(priority = 3)
    public void teardown() {
        System.out.println("day 2 tear down");
    }
}

/*
Annotation trùng nhau trong TestNG:

1. Có thể khai báo nhiều method cùng annotation
   - Ví dụ: bạn có thể có nhiều @BeforeMethod trong cùng một class.
   - TestNG sẽ chạy tất cả các method đó theo thứ tự mặc định (thứ tự định nghĩa trong class).

2. Thứ tự thực thi khi trùng annotation
   - Nếu có nhiều method cùng annotation, TestNG sẽ chạy lần lượt theo thứ tự xuất hiện trong file .java.
   - Bạn có thể kiểm soát thứ tự bằng cách dùng thuộc tính priority (chỉ áp dụng cho @Test).
   - Với @BeforeMethod, @AfterMethod, @BeforeClass... thì không có priority, chỉ chạy theo thứ tự code.

3. Khi nào nên dùng nhiều method cùng annotation
   - Khi bạn muốn tách biệt logic setup/cleanup cho rõ ràng.
     Ví dụ:
       @BeforeMethod public void openBrowser() {}
       @BeforeMethod public void login() {}
     → cả hai sẽ chạy trước mỗi test case.
   - Khi bạn muốn chia nhỏ trách nhiệm (separation of concerns).

4. Khi nào không nên
   - Nếu logic setup/cleanup liên quan chặt chẽ, nên gộp vào một method để dễ quản lý.
   - Nếu có nhiều @BeforeMethod phức tạp, dễ gây khó hiểu về thứ tự chạy.

5. Với @Test annotation
   - Bạn có thể có nhiều method @Test trong cùng class.
   - Mỗi method là một test case riêng biệt.
   - Có thể điều khiển thứ tự bằng priority, hoặc nhóm bằng groups.

* */