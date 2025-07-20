package TestScripts;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import pageObjectModels.HomePageAmazon;

import java.util.ArrayList;
import java.util.List;

public class ActionsTest extends BaseTest{

    @Test
    public void test_right_click_and_open_new_tab() throws InterruptedException {
        HomePageAmazon hp = new HomePageAmazon(driver);
        softAssert.assertEquals(hp.get_url(), "https://www.amazon.in/");
        Actions actions = new Actions(driver);

//        actions.moveToElement(hp.getFresh()).contextClick(hp.getFresh()).perform();
//        actions.keyDown(Keys.RIGHT).keyUp(Keys.DOWN)
//                .keyDown(Keys.ENTER).keyUp(Keys.ENTER).build().perform();

        actions.moveToElement(hp.getFresh()).keyDown(Keys.CONTROL).click(hp.getFresh()).keyUp(Keys.CONTROL).build().perform();
//        Thread.sleep(5000);
//        actions.moveToElement(hp.getFresh()).contextClick(hp.getFresh()).keyDown(Keys.DOWN).keyUp(Keys.DOWN)
//                .keyDown(Keys.ENTER).keyUp(Keys.ENTER).build().perform();
//
        Thread.sleep(2000);//To view the actions class impact


        List<String> wh = new ArrayList<>(driver.getWindowHandles());

        int count = 0;

        assert wh.size() == 2;
    }
}
