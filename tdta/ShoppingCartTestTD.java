package tdta;

//step 3: refactor
public class ShoppingCartTestTD {
    private WebStoreMarket market;

    public TestShoppingCartTD() {
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
        ProductFactory productFactory = new ProductFactory(new WebStore("Юшка & Петрушка"), market);
        UserFactory userFactory = new UserFactory(market);
        return new Object[][] {
                { userFactory.getUser(UserType.RegularCustomer), productFactory.getMenu() },
                { userFactory.getUser(UserType.PremiumCustomer), productFactory.getAdultMenu() },
                { userFactory.getUser(UserType.MinorCustomer), productFactory.getGroceryItem("Юшка") },
                { userFactory.getUser(UserType.SystemAdministrator), productFactory.getDoubleMenu() },
        };
    }
}
