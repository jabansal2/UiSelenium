package pageObjectModels;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultsPage extends BasePage{

    protected WebDriver driver;

    @Getter
    @FindBy(name = "submit.addToCart")
    private List<WebElement> all_add_to_cart_buttons;

    //	private static String home_page_url = "https://www.amazon.in/";


    @FindBy(xpath = "//a[contains(@href,'nav_cart')]")
    private WebElement cart;


    public SearchResultsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void go_to_cart(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(cart));
        cart.click();
    }

    public void add_first_item_to_cart(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfAllElements(all_add_to_cart_buttons));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(all_add_to_cart_buttons.get(0)));
        all_add_to_cart_buttons.get(0).click();
    }
}
