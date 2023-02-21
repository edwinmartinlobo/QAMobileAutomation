package com.mtaf.runners;

import com.mtaf.managers.appium.AppiumDriverManager;
import com.mtaf.managers.appium.LocalAppiumManager;
import com.mtaf.utils.CommandPromptUtil;
import io.appium.java_client.AppiumDriver;
import io.cucumber.testng.*;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.*;

import java.util.logging.Formatter;



public class BaseRunner{

    private static final ThreadLocal<TestNGCucumberRunner> testNGCucumberRunner = new ThreadLocal<>();

    public static TestNGCucumberRunner getRunner() {
        return testNGCucumberRunner.get();
    }

    private static void setRunner(TestNGCucumberRunner testNGCucumberRunner1) {
        testNGCucumberRunner.set(testNGCucumberRunner1);
    }

    private LocalAppiumManager localAppiumManager;
    private AppiumDriverManager appiumDriverManager;
    private static String testCaseId;
    private Formatter dateFormat;


    public BaseRunner() {
        localAppiumManager = new LocalAppiumManager();
        appiumDriverManager = new AppiumDriverManager();
    }

    protected static AppiumDriver getDriver(){
        return AppiumDriverManager.getDriver();
    }

    public static String getTestCaseId() {
        return testCaseId;
    }

//    @BeforeSuite
//    public void beforeSuite() throws Exception {
//
//        initializeLogger();
//
//        killAllNodeServices();
//
//        //loadTestData();
//
//        //initializeServer();
//
//        initializeApp();
//
//
//    }

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {

        initializeLogger();

        killAllNodeServices();

        try {
            initializeApp();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        setRunner(new TestNGCucumberRunner(this.getClass()));
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void scenario(PickleWrapper pickle, FeatureWrapper cucumberFeature) throws Throwable {
        getRunner().runScenario(pickle.getPickle());
    }

    @DataProvider
    public Object[][] scenarios() {
        return getRunner().provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        if (testNGCucumberRunner != null) {
            getRunner().finish();
        }
    }


    /**
     * Initializing logger module
     */
    private void initializeLogger() {
        DOMConfigurator.configure("log4j.xml");
    }

    /**
     * Killing already running node services
     */
    private void killAllNodeServices(){
        CommandPromptUtil commandPromptUtil = new CommandPromptUtil();
        commandPromptUtil.killNodeServices();
    }

    /**
     * Initializing Appium Server
     */
    private void initializeServer(){
        localAppiumManager.startAppiumServer();
    }

    /**
     * Initializing and launching App
     */
    private void initializeApp() throws Exception {
        appiumDriverManager.startAppiumDriverInstance();
    }



//    @AfterClass
//    public void writeExtentReport() throws Exception {
////        Reporter.loadXMLConfig(new File(ExtentManager.getReportConfigPath()));
////        List<DeviceManger> availabledevices = localAppiumManager.getDevices(ConfigFileHandler.getData("PLATFORM"));
////        Reporter.setSystemInfo("udid",availabledevices.get(0).getUdid());
////        Reporter.setSystemInfo("OSVersion",availabledevices.get(0).getOsVersion());
////        Reporter.setSystemInfo("Brand",availabledevices.get(0).getBrand());
////        Reporter.setSystemInfo("Name",availabledevices.get(0).getName());
//    }
}
