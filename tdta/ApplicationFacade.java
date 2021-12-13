package tdta;

// WARNING: Don't keep all the application functions
// in a single facade: use Additional Facades for different features
interface ApplicationFacade {
    void registerUser(User user);

    void unregisterUser(User user);

    void login(User user);

    void logout();

    void createSomething(Something something);

    void modifySomething(Something something);

    void deleteSomething(UUID somethingUuid);
}

public class UiApplication implements ApplicationFacade {
} // End-2-End level

public class ApiApplication implements ApplicationFacade {
} // Integration level

public class FakeApplication implements ApplicationFacade {
} // Unit/Validation level
