package TestScripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjectModels.HomePageAmazon;
import pageObjectModels.SearchResultsPage;

public class SearchItemTest extends BaseTest{




//    @BeforeClass
//    public void setup(){
//        driver = WebDriverManager.chromedriver().create();
//    }
    @Test(enabled = false)
    public void test_search_item() throws InterruptedException {
        String text_to_search = "jbl bluetooth speakers wireless";
        HomePageAmazon hp = new HomePageAmazon(driver);
        softAssert.assertEquals(hp.get_url(), "https://www.amazon.in/");
        hp.searchItem(text_to_search);
        hp.waitForVisibilityOfElement(hp.getDropdowncontainer());
        hp.selectOptionFromSearchOptions(hp.getSearchOptions(), text_to_search);

    }
    @Test(enabled = false)
    public void test_add_to_cart() throws InterruptedException {
        String text_to_search = "jbl bluetooth speakers wireless";
        HomePageAmazon hp = new HomePageAmazon(driver);
        hp.searchItem(text_to_search);
        hp.waitForVisibilityOfElement(hp.getDropdowncontainer());
        hp.selectOptionFromSearchOptions(hp.getSearchOptions(), text_to_search);

        SearchResultsPage searchResults = new SearchResultsPage(driver);

        searchResults.add_first_item_to_cart();
        searchResults.go_to_cart();

        System.out.println("Cart page url = " + searchResults.get_url());
        System.out.println(searchResults.get_url().contains("nav_cart"));
        softAssert.assertTrue(searchResults.get_url().contains("nav_cart"));

    }

    @Test(enabled = true)
    public void test_with_keys_and_for_select_class() {
        String text_to_search = "apple";
        HomePageAmazon hp = new HomePageAmazon(driver);
        softAssert.assertTrue(hp.getProductsTypeDropDownButton().isEnabled());
        hp.select_products_type(hp.getProductsTypeDropDownButton(), "Electronics");
        hp.searchItem(text_to_search);
        String item_string_to_select = "apple buds";
        hp.select_search_option_with_keys(item_string_to_select);

    }

    @Test(dataProvider = "data_prov_td", enabled = false)
    public void test_search_and_add_to_cart_with_dataprovider(String searchtext) throws InterruptedException {
        String text_to_search = searchtext;
        System.out.println("text_to_search = "+ text_to_search);
        HomePageAmazon hp = new HomePageAmazon(driver);
        hp.searchItem(text_to_search);
        hp.waitForVisibilityOfElement(hp.getDropdowncontainer());
        hp.selectOptionFromSearchOptions(hp.getSearchOptions(), text_to_search);

        SearchResultsPage searchResults = new SearchResultsPage(driver);

        searchResults.add_first_item_to_cart();
        searchResults.go_to_cart();

        System.out.println("Cart page url = " + searchResults.get_url());
        System.out.println(searchResults.get_url().contains("nav_cart"));
        softAssert.assertTrue(searchResults.get_url().contains("nav_cart"));

    }

    @DataProvider(name = "data_prov_td")
    public Object[] testdata(){
        Object[] data = new Object[4];
        data[0] = "jbl bluetooth speakers wireless";
        data[1] = "amazon fire tv";
        data[2] = "samsung note";
        data[3] = "airpods";
        return data;
    }

}
