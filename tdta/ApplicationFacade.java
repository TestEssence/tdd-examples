package tdta;

// WARNING: Don't keep all the application functions
// in a single facade 
Interface ApplicationFacade {
    void registerUser(User user);
    void unregisterUser(User user);
    void login(User user);
    void logout();
    void createSomething(Something something);
    void modifySomething(Something something);
    void deleteSomething(UUID somethingUuid);
}

public class FakeApplication implements ApplicationFacade{}
public class ApiApplication implements ApplicationFacade{}
public class UiApplication implements ApplicationFacade{}
