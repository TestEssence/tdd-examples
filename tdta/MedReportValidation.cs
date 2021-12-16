public class MedReportValidation : BaseTest, IClassFixture<MedReportServiceFixture>
       , IClassFixture<ConfigFixture>
{
    private IMedReportService _service;
    private MedReportPdf _pdf;
    public MedReportValidation(ITestOutputHelper testOutputHelper,
        MedReportServiceFixture serviceFixture, ConfigFixture config) : base(testOutputHelper, config)
    {
        config.ServiceType = AirwayReportServiceType.Fake;  // Unit
        //config.ServiceType = AirwayReportServiceType.API; // Integration
        //config.ServiceType = AirwayReportServiceType.Web; // e2e
        _service = serviceFixture.GetService(config);
        _pdf = new AirwayReportPdf(_service.GenerateReport(Config.GoldenXml));
    }
    [Theory]
    [Trait("Category", "Validation")]
    [InlineData("Age", "67 Y")]
    [InlineData("Sex", "Male")]
    public void PdfText_PatientDetailsParam(string name, string expectedValue)
    {
        String param = pdf.GetPatientDetails().GetPatientParam(name);
        param.Should().Be(expectedValue);
    }
}