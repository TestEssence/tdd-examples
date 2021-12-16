package tdta;

//step 1: write a failing test
public class ShoppingCartTestFT {

    private WebStoreMarket market;

    public ShoppingCartTestFT() {
        market = GlobalConfig.getMarket();
    }

    @Test(groups = { "presentation",
            "story-007" }, dataProvider = "shopping-card-cases", description = "verify that items can be added to cart")
    public void addItemsToCart(User customer, List<Product> items) {
        // ARRANGE
        customer.login();
        // ACT
        for (Product item : items) {
            customer.addToCart(customer.findProduct(item), 1);
        }
        // ASSERT
        Cart expectedCart = new Cart(items);
        Assert.assertEquals(customer.getCart(), expectedCart);
    }

    @DataProvider(name = "shopping-card-cases")
    public Object[][] shoppingCartCases() {
        return new Object[][] { { null, null }, { null, null } };
    }

}
