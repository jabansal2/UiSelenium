package TestScripts;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class runWithDocker {

    @Test(invocationCount = 1)
    public void test_docker() throws MalformedURLException {

        ChromeOptions options = new ChromeOptions();
        URL url = new URL("http://localhost:4446/wd/hub");
        RemoteWebDriver driver = new RemoteWebDriver(url, options);

        driver.get("https://www.scaler.com/topics/");

        System.out.println("Title = " + driver.getTitle());
    }
}
