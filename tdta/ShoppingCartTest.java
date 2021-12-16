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
            "story-007" }, description = "verify that two items can be added to cart")
    public void add2ItemsToCart() {
        // ARRANGE
        WebStore store = new WebStore("Юшка & Петрушка")
                .register(market);
        Product soup = new Product("Юшка", 5.99, ProductType.Grocery);
        store.publishProduct(soup, 500);
        User customer = new User("Финтик Каленик Кононович", UserType.RegularCustomer).register(market);
        customer.login();
        // ACT
        customer.addToCart(customer.findProduct(soup), 2);
        // ASSERT
        List<CartItem> expectedItems = new List<CartItem>() {
            {
                add(new CartItem(soup), 2);
            }
        };
        Assert.assertEquals(customer.getCart().getItems(), expectedItems);
    }

    @Test(groups = { "presentation",
            "story-007" }, description = "verify that items can be added to cart")
    public void addLiquerToCart() {
        // ARRANGE
        WebStore store = new WebStore("Юшка & Петрушка")
                .register(market);
        Product spirit = new Product("Наливка", 19.99, ProductType.Liquor,
                new LiquorLicense("LL-23000065123", "2022-02-02"));
        store.publishProduct(spirit, 500);
        User customer = new User("Финтик Каленик Кононович", UserType.RegularCustomer).register(market).login();
        // ACT
        customer.addToCart(customer.findProduct(spirit), 2);
        // ASSERT
        List<CartItem> expectedItems = new List<CartItem>() {
            {
                add(new CartItem(spirit), 1);
            }
        };

        Assert.assertEquals(customer.getCart().getItems(), expectedItems);
    }

}