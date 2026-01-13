package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrValidation extends BaseTest {

    @Test(groups = {"ErrHandling"},retryAnalyzer = Retry.class)
    public void LoginErr() throws IOException, InterruptedException {
        lp.loginApp("tontnm@gmail.com", "123");
        Assert.assertEquals("Incorrect email or password.", lp.getErrMsg());
    }

    @Test
    public void submitOrderErr() throws IOException, InterruptedException {
        lp.loginApp("tontnm@gmail.com", "123");
        Assert.assertEquals("Incorrect email or password.", lp.getErrMsg());
    }
}
