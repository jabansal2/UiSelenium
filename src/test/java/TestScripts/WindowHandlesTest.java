package TestScripts;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import pageObjectModels.HomePageAmazon;

import java.util.ArrayList;
import java.util.List;


public class WindowHandlesTest extends BaseTest {

    @Test
    public void test_window_tabs() throws InterruptedException {
        HomePageAmazon hp = new HomePageAmazon(driver);
        softAssert.assertEquals(hp.get_url(), "https://www.amazon.in/");
        Actions actions = new Actions(driver);
//        actions.moveToElement(hp.getFresh()).contextClick();
        actions.moveToElement(hp.getFresh()).keyDown(Keys.CONTROL).click(hp.getFresh()).keyUp(Keys.CONTROL).build().perform();

        List<String> wh = new ArrayList<>(driver.getWindowHandles());

        int count = 0;

        while(count <1){
            count++;
            driver.switchTo().window(wh.get(1));
//            driver.switchTo().window(wh.get(0));
        }


        Thread.sleep(10000);
    }

    public void new_window(){

    }
}
