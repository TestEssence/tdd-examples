
@Listeners(Listener.class)
public class RequestGeneratorValidation {
    private User doctor = null;

    @BeforeClass(alwaysRun = true)
    @Parameters({ "Username", "Password" })
    public void BeforeClass(String Username,
            String Password,
            ITestContext context) {
        doctor = new User(Username, Password);
        AppConfig.loadConfig();
    }

    @Test(groups = { "smoke", "validation", "aws" })
    public void validateRequestsGeneratorHealth_Aws() {
        // arrange
        AppConfig.setIsAWSMode(true);
        RequestGenerator generator = new RequestGenerator(AppConfig.getWebDriver(),
                AppConfig.getHospitalUrl());// AWS Device Farm Desktop
        // act & assert
        assert generator.healthCheck(doctor);
    }

    @Test(groups = { "smoke", "validation" })
    public void validateRequestsGeneratorHealth_Local() {
        // arrange
        AppConfig.setIsAWSMode(false);
        RequestGenerator generator = new RequestGenerator(AppConfig.getWebDriver(),
                AppConfig.getHospitalUrl()); // local selenium driver
        // act & assert
        assert generator.healthCheck(doctor);
    }
}