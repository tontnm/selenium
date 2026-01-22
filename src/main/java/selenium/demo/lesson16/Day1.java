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
