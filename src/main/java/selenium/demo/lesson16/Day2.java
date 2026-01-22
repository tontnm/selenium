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
