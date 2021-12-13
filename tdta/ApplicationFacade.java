package tdta;

// WARNING: Don't keep all the application functions
// in a single facade: use Additional Facades for different features
interface ApplicationFacade {
    public User registerUser(User user);

    public void unregisterUser(User user);

    public Something createSomething(Something something);

    public Something modifySomething(Something something);

    public void deleteSomething(UUID somethingUuid);
}

public class UiApplication implements ApplicationFacade {
} // End-2-End level

public class ApiApplication implements ApplicationFacade {
} // Integration level

public class FakeApplication implements ApplicationFacade {
} // Unit/Validation level

public class SomethingTest {

    ApplicationFacade app;

    public SomethingTest() {
        app = GlobalConfig.getApp();
    }

    @Test(groups = { "validation", "fake" })
    public void createSomething() {
        // arrange
        User user = app.registerUser(new User("Odike, Prince of Nigeria", "Odike.II@nigeria.gov.ng"));
        user.login();
        // act
        Something thing = app.createSomething(new Something("Special"));
        // assert
        Assert.assertEquals(thing.getName(), "Special");
    }
}