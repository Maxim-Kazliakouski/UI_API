package steps;

//import adapters.ProjectAPI;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
//import dto.Project;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Listeners;
//import tests.api.moduls.APIResponse;
//import tests.api.moduls.Project.Entity;
import tests.base.TestListener;
import utils.PropertyReader;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.lang.String.format;

@Log4j2
@Listeners(TestListener.class)
public class Hooks {
    String username;
    String password;
//    public static String caseID;
//    public static String scenarioName;

    @Before
    public void init() {
        System.out.println("Starting tests...");
//        if (Boolean.parseBoolean(PropertyReader.getProperty("api"))) {
//        } else {
////      getting test case ID
//            Collection<String> caseIDs = scenario.getSourceTagNames();
//            for (String eachCaseID : caseIDs) {
//                caseID = eachCaseID.substring(12);
//            }
//            scenarioName = scenario.getName();
//            username = System.getProperty("USERNAME", PropertyReader.getProperty("qase.username"));
//            password = System.getProperty("PASSWORD", PropertyReader.getProperty("qase.password"));
//            DesiredCapabilities capabilities = new DesiredCapabilities();
//            capabilities.setCapability("browserName", PropertyReader.getProperty("browser"));
//            capabilities.setCapability("browserVersion", PropertyReader.getProperty("browserVersion"));
//            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
//                    "enableVNC", true,
//                    "enableVideo", Boolean.parseBoolean(PropertyReader.getProperty("videoTestRecord")),
//                    "enableLog", true
//            ));
//            capabilities.setCapability("logName", "my-cool-log.log");
//            capabilities.setCapability("videoScreenSize", "1920x1080");
//            capabilities.setCapability("videoName", format("%s.mp4", scenarioName));
//            Configuration.baseUrl = System.getProperty("QASE_URL", PropertyReader.getProperty("qase.url"));
//            Configuration.browser = PropertyReader.getProperty("browser");
//            Configuration.headless = Boolean.parseBoolean(PropertyReader.getProperty("headless"));
//            Configuration.timeout = 10000;
//            Configuration.reportsFolder = "target/screenshots";
//            SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
//            Configuration.remote = "http://localhost:4444/wd/hub";
//            Configuration.browserCapabilities = capabilities;
//            open();
//            getWebDriver().manage().window().maximize();
//        }
    }

    @After
    public void close() {
        System.out.println("Finishing tests...");
//        if (Boolean.parseBoolean(PropertyReader.getProperty("api"))) {
//        } else if (getWebDriver() != null) {
//            getWebDriver().quit();
//        }
    }

//    @AfterAll
//    public static void clearingProjects() {
//        Gson gson = new Gson();
//        ProjectAPI projectAPI = new ProjectAPI();
//        Response response = projectAPI.getAllProjects();
//        APIResponse<Project> result = gson.fromJson(response.asString(),
//                new TypeToken<APIResponse<Project>>() {
//                }.getType());
//        List<Entity> entities = result.getResult().entities;
//        List<String> code = entities.stream().map(Entity::getCode).collect(Collectors.toList());
//        for (String eachCode : code) {
//            if (!eachCode.equals("QASE") && !eachCode.equals("SHARELANE") && !eachCode.equals("PFT")) {
//                projectAPI.deleteProjectByCode(eachCode);
//                System.out.printf("Project '%s' has been deleted\n", eachCode);
//                log.info(format("Project '%s' has been deleted\n", eachCode));
//            }
//            else log.info("There is nothing to delete");
//        }
//    }
}