package TestScripts;

//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.time.Duration;

public class BaseTest {

    WebDriver driver;
    static SoftAssert softAssert;

    private static String home_page_url = "https://www.amazon.in/";

    @BeforeTest
    public void setup_before_test(){
        softAssert = new SoftAssert();
    }

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) throws MalformedURLException, InterruptedException {
        softAssert = new SoftAssert();
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setBrowserName(browser);
        switch (browser) {
            case "firefox":
//                driver = WebDriverManager.firefoxdriver().create();
                driver = new FirefoxDriver();
                break;
            case "chrome":
//                driver = WebDriverManager.chromedriver().create();
                driver = new ChromeDriver();
                break;
            case "edge":
//                driver = WebDriverManager.edgedriver().create();
                driver = new EdgeDriver();
                break;

        }
        driver.get(home_page_url);
        driver.manage().window().setPosition(new Point(100,100)); // This is to test placing the window on the coor
                                                                        //-dinates mentioned
//        Thread.sleep(5000);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    public void teardown(){
        driver.quit();

    }

    @AfterTest
    public void get_all_assertion_results(){
        softAssert.assertAll();
    }
}
