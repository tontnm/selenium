package demo.lesson16;

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
