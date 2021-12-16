package tdta;

// WARNING: Don't keep all the application functions
// in a single facade: use Additional Facades for different features
interface User {
    public User register(Application app);

    public void unregister();

    public User login();

    public void logout();

    public Something createSomething(Something something);

    public Something modifySomething(Something something);

    public void deleteSomething(UUID somethingUuid);
}

public class UiUser implements User {
}

public class ApiUser implements User {
}

public class MockUser implements User {
}

public class SomethingTest {
    public SomethingTest() {
        app = GlobalConfig.getApp();
    }

    @Test(groups = { "validation", "fake" })
    public void mockCreateSomething() {
        // arrange
        User user = new MockUser("Odike, Prince of Nigeria", "Odike.II@nigeria.gov.ng");
        user.register(app)
                .login();
        // act
        Something thing = user.createSomething(new Something("Special"));
        // assert
        Assert.assertEquals(thing.getName(), "Special");
    }
}