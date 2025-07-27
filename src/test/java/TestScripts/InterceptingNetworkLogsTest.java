package TestScripts;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v133.network.Network;
import org.testng.annotations.Test;

import java.util.Optional;

public class InterceptingNetworkLogsTest{

    @Test
    public void testnetworktrafficlogs() {

        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
                )
        );
        devTools.addListener(Network.requestWillBeSent(),
                request -> {
                    System.out.println("Req url :" + request.getRequest().getUrl());
                    System.out.println("Req method : " + request.getRequest().getMethod());
                });

//        devTools.addListener();
        driver.get("https://the-internet.herokuapp.com/broken_images");

//        Thread.sleep(2000);
        driver.quit();

    }
}
