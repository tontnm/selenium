package demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingPage lp;
    public Properties prop;

    public WebDriver initializeDriver() throws IOException {

        // mvn test -PRegression -Dbrowser=chrome
        // java -jar jenkins.war -httpPort=9090

        prop = new Properties();
        String path = System.getProperty("user.dir");
        System.out.println(path);
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\demo\\GlobalData.properties");
        prop.load(fis);
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");

        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "chromeheadless":
                ChromeOptions options = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                options.addArguments("headless");
                driver = new ChromeDriver(options);
                driver.manage().window().setSize(new Dimension(1440, 900));
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApp() throws IOException {
        driver = initializeDriver();
        lp = new LandingPage(driver);
        lp.openWebsite();
        return lp;
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public List<HashMap<String, String>> getJSONDataToMap(String filePath) throws IOException {
        //read json to string
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

        // String to HashMap
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        }); // {map,map}

        return data;
    }

    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String screenShotPath = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
        File file = new File(screenShotPath);
        FileUtils.copyFile(source, file);

        return screenShotPath;
    }
}
