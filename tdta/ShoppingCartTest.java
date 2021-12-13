import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.TestRunner;
import org.testng.annotations.*;

public class ShoppingCartTest {
    private WebStoreMarket market;

    public ShoppingCartTest() {
        market = GlobalConfig.getMarket();
    }

    @BeforeMethod()
    public void CleanUp() {
        market.cleanUp();
    }

    @Test(groups = { "presentation",
            "story-007" }, description = "verify that liquer items can be added to cart")
    public void add2ItemsToCart() {
        // ARRANGE
        WebStore store = new WebStore("Юшка & Петрушка");
        market.addWebStore(store);
        Product soup = new Product("Юшка", 5.99, ProductType.Grocery);
        market.publishProduct(store, soup, 500);
        User customer = market.registerUser("Финтик Каленик Кононович", UserType.RegularCustomer);
        market.login(customer);
        // ACT
        market.findProduct(soup)
                .addToCart(2);
        // ASSERT
        List<CartItem> expectedItems = new List<CartItem>();
        expectedItems.add(new CartItem(soup), 2);
        Assert.assertEquals(market.getCart(customer).getItems(), expectedItems);
    }

    @Test(groups = { "presentation",
            "story-007" }, description = "verify that liquer items can be added to cart")
    public void addLiquerToCart() {
        // ARRANGE
        WebStore store = new WebStore("Юшка & Петрушка");
        market.addWebStore(store);
        Product spirit = new Product("Наливка", 19.99, ProductType.Liquor,
                new LiquorLicense("LL-23000065123", "2022-02-02"));
        market.publishProduct(store, spirit, 500);

        User customer = market.registerUser("Финтик Каленик Кононович", UserType.RegularCustomer);
        market.login(customer);
        // ACT
        market.findProduct(spirit)
                .addToCart(1);
        // ASSERT
        List<CartItem> expectedItems = new List<CartItem>();
        expectedItems.add(new CartItem(spirit), 1);
        Assert.assertEquals(market.getCart(customer).getItems(), expectedItems);
    }

}