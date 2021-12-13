package tdta;

//step 2: make it pass by implementing data provider
public class ShoppingCartTestMP {
    private WebStoreMarket market;

    public ShoppingCartTestMP() {
        market = GlobalConfig.getMarket();
    }

    @BeforeMethod()
    public void CleanUp() {
        market.cleanUp();
    }

    @DataProvider(name = "shopping-card-data")
    public Object[][] dpMethod() {
        WebStore store = new WebStore("Юшка & Петрушка");
        market.addWebStore(store);
        Product soup = new Product("Юшка", 5.99, ProductType.Grocery);
        Product species = new Product("Петрушка", 0.99, ProductType.Grocery);
        Product spirit = new Product("Наливка", 19.99, ProductType.Liquor,
                new LiquorLicense("LL-23000065123", "2022-02-02"));
        market.publishProduct(store, soup, 500);
        market.publishProduct(store, species, 1500);
        market.publishProduct(store, spirit, 100);
        User admin = market.registerUser("Іван Семенович Нечуй-Левицький", UserType.SystemAdministrator);
        User premiumCustomer = market.registerUser("Омелько Кайдаш", UserType.PremiumCustomer);
        User regularCustomer = market.registerUser("Карпо Кайдашенко", UserType.RegularCustomer);
        User minorCustomer = market.registerUser("Петрик П'яточкін", UserType.MinorCustomer);
        ArrayList<Product> soup = new ArrayList<Product>() {
            {add(soup);}
        };
        ArrayList<Product> menu = new ArrayList<Product>() {
            {
                add(soup);
                add(species);
            }
        };
        ArrayList<Product> adultMenu = new ArrayList<Product>() {
            {
                add(soup);
                add(species);
                add(spirit);
            };
        ArrayList<Product> adultMenu = new ArrayList<Product>() {
            {
                add(soup);
                add(species);
                add(spirit);
                add(soup);
                add(species);
                add(spirit);
        };
        return new Object[][] { { regularCustomer, menu },
                { regularCustomer, adultMenu},
                { premiumCustomer, adultMenu },
                { minorCustomer, soup },
                { admin, doubleMenu },

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
