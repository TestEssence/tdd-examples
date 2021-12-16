package tdta;

//step 2: make it pass by implementing data provider
public class ShoppingCartTestMW {
    private WebStoreMarket market;

    public ShoppingCartTestMW() {
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
        WebStore store = new WebStore("Юшка & Петрушка")
                .register(market);
        Product soup = new Product("Юшка", 5.99, ProductType.Grocery);
        store.publishProduct(soup, 500);
        User customer = new User("Финтик Каленик Кононович", UserType.RegularCustomer).register(market);
        ArrayList<Product> soup = new ArrayList<Product>() {
            {
                add(soup);
            }
        };
        return new Object[][] { { customer, soup } };
    }

}
