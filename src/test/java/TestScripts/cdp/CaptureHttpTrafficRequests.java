package TestScripts.cdp;

import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v133.fetch.Fetch;
import org.openqa.selenium.devtools.v133.network.Network;
import org.openqa.selenium.devtools.v133.network.model.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class CaptureHttpTrafficRequests {

//    private final

    WebDriver driver;

    @BeforeTest
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test(enabled = false)
    public void basicAuthenticationTest(){
        Predicate<URI> uriPred = uri -> uri.getHost().contains("httpbin");
        ((HasAuthentication)driver).register(uriPred, UsernameAndPassword.of("user", "passwd"));
        driver.get("http://httpbin.org/basic-auth/user/passwd");
        Assert.assertTrue(driver.getPageSource().contains("\"authenticated\": true"));
    }


    @Test
    public void networkInterceptionTest(){
        HasDevTools devToolsDriver = (HasDevTools) driver;
        DevTools devTools = devToolsDriver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));
        List<Fetch> requests = Collections.synchronizedList(new ArrayList<>());
        List<Response> capturedResponses = Collections.synchronizedList(new ArrayList<>());


//        devTools.addListener(Fetch.getResponseBody());
        devTools.addListener(Network.responseReceived(), responseReceived -> {
            capturedResponses.add(responseReceived.getResponse());
        });

        driver.get("https://www.lambdatest.com/");

        capturedResponses.forEach(System.out::println);
//        Route
    }

    @AfterTest
    public void teardown(){
        driver.quit();
    }
}
