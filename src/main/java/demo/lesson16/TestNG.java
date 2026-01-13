package demo.lesson16;

import org.testng.annotations.Test;

public class TestNG {
    public static void main(String[] args) {

    }

    @Test(priority = 1)
    public void setup() {
        System.out.println("setup");
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
