package tdta;

//step 3: refactor
public class ShoppingCartTestRF {
    private WebStoreMarket market;

    public TestShoppingCartMP() {
        market = GlobalConfig.getMarket();
    }

    @BeforeMethod()
    public void CleanUp() {
        market.cleanUp();
    }

    @DataProvider(name = "shopping-card-data")
    public Object[][] dpMethod() {
        ProductFactory productFactory = new ProductFactory(new WebStore("Юшка & Петрушка"), market);
        UserFactory userFactory = new UserFactory(market);
        return new Object[][] { { userFactory.getRegularCustomer(), groceries[0] },
                { userFactory.getUser(UserType.RegularCustomer), productFactory.getMenu() },
                { userFactory.getUser(UserType.PremiumCustomer), productFactory.getAdultMenu() },
                { userFactory.getUser(UserType.MinorCustomer), productFactory.getGroceryItem("soup") },
                { userFactory.getUser(UserType.SystemAdministrator), productFactory.getDoubleMenu() },
        };
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
