package tdta;

//step 1: write a failing test
public class ShoppingCartTestFT {

    private WebStoreMarket market;

    public ShoppingCartTestFT() {
        market = GlobalConfig.getMarket();
    }

    @BeforeMethod()
    public void CleanUp() {
        market.cleanUp();
    }

    @DataProvider(name = "shopping-card-data")
    public Object[][] dpMethod() {
        return new Object[][] { { null, null }, { null, null } };
    }

    @Test(groups = { "presentation",
            "story-007" }, dataProvider = "shopping-card-data", description = "verify that items can be added to cart")

    public void addItemsToCart(User customer, List<Product> products) {
        // ARRANGE
        market.login(customer);
        // ACT
        for (Product product : products) {
            market.findProduct(product).addToCart(1);
        }
        // ASSERT
        Cart expectedCart = new Cart(products);
        Assert.assertEquals(market.getCart(customer), expectedCart);
    }

}
