package selenium.demo.lesson17;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Parent {


    @BeforeMethod
    public void beforeRun() {
        System.out.println("Run me first.");
    }

    @Test
    public void parentMethod() {
        System.out.println("Parent Method");
    }


    @AfterMethod
    public void afterRun() {
        System.out.println("Run me last.");
    }
}
