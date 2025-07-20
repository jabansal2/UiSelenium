package pageObjectModels;

import java.time.Duration;
import java.util.List;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageAmazon extends BasePage {

//	protected WebDriver driver;
//	private static String home_page_url = "https://www.amazon.in/";
	@FindBy(id = "twotabsearchtextbox")
	private WebElement searchbox;

	@Getter
	@FindBy(id = "searchDropdownBox")
	private WebElement productsTypeDropDownButton;

	@Getter
	@FindBy(css = "div.two-pane-results-container")
	private WebElement dropdowncontainer;



	@Getter
    @FindBy(css = "div.left-pane-results-container div div div:nth-child(1)")
	private List<WebElement> searchOptions;

	@Getter
	@FindBy(xpath = "//a[contains(text(),'Fresh')]")
	private WebElement fresh;
		
	public HomePageAmazon(WebDriver driver) {
		super(driver);
//		this.driver = driver;
//		driver.get(home_page_url);
		PageFactory.initElements(driver, this);
	}

	public void searchItem(String text) {
		searchbox.sendKeys(text);
	}

    public String getSearchOptionText(@org.jetbrains.annotations.NotNull WebElement el) {
		return el.getDomAttribute("aria-label");
	}
	
	public void selectOptionFromSearchOptions(List<WebElement> els, String expectedtext) {
		els = getSearchOptions();
        for (WebElement el : els) {
            String actualText = getSearchOptionText(el);
            if (actualText.equalsIgnoreCase(expectedtext)) {
                el.click();
                return;
            }
        }
	}

//	public void waitForVisibilityOfElement(WebElement e) throws Error {
//		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(e));
//	}
//
//	public void waitForVisibilityOfAllElements(List<WebElement> els) throws Error {
//		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfAllElements(els));
//	}



	public void select_products_type(WebElement selectType, String productType){
		Select s = new Select(selectType);
		List<WebElement> optionsList = s.getOptions();
		s.selectByVisibleText(productType);
	}
	public void select_search_option_with_keys(String required_string){
		List<WebElement> all_results = getSearchOptions();
		for (WebElement we: all_results){
			searchbox.sendKeys(Keys.DOWN);
			String text_first_part = we.getText();
			String test_second_part = we.findElement(By.tagName("span")).getText();
			String actual_string = text_first_part + " " + test_second_part;
			if(actual_string.equals(required_string)){
				we.sendKeys(Keys.ENTER);
				break;
			}


		}
	}
}
