/*********************************************************
 * Created :   Mayur Waghmare
 * Date    :   27-03-2019
 *********************************************************/

package com.mtaf.managers.appium;

import com.mtaf.filehandler.ConfigFileHandler;
import com.mtaf.managers.CapabilityManager;
import com.mtaf.utils.DesiredCapabilityBuilder;
import com.mtaf.utils.Log;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class AppiumDriverManager {

    private static ThreadLocal<AppiumDriver> appiumDriver
            = new ThreadLocal<>();
    private DesiredCapabilityBuilder desiredCapabilityBuilder;
    private ConfigFileHandler prop;
    private CapabilityManager capabilityManager;
    private LocalAppiumManager localAppiumManager;

    public AppiumDriverManager() {
        desiredCapabilityBuilder = new DesiredCapabilityBuilder();
        capabilityManager = CapabilityManager.getInstance();
        localAppiumManager = new LocalAppiumManager();
    }

    public static AppiumDriver getDriver() {
        return appiumDriver.get();
    }

    private static void setDriver(AppiumDriver driver) {
        appiumDriver.set(driver);
    }

    
    private AppiumDriver<MobileElement> initialiseDriver(
            DesiredCapabilities capabilities)
            throws Exception {
        AppiumDriver<MobileElement> currentDriverSession;
        DesiredCapabilities desiredCapabilities = capabilities;
        String remoteWDHubIP = "http://127.0.0.1:4723/wd/hub";//localAppiumManager.getRemoteWDHubIP(capabilityManager.getMachineIP());
        if (prop.getData("PLATFORM").equalsIgnoreCase(MobilePlatform.IOS)) {
            currentDriverSession = new IOSDriver(new URL(remoteWDHubIP),
                    desiredCapabilities);
            Log.INFO("Session Created for iOS ---- "
                    + currentDriverSession.getSessionId() + "---"
                    + currentDriverSession.getSessionDetail("udid"));
        } else {
            currentDriverSession = new AndroidDriver<>(new URL(remoteWDHubIP),
                    desiredCapabilities);
            Log.INFO("Session Created for Android ---- "
                    + currentDriverSession.getSessionId() + "---"
                    + currentDriverSession.getSessionDetail("udid"));
        }
        return currentDriverSession;
    }

    private void startAppiumDriverInstance(DesiredCapabilities desiredCapabilities)
            throws Exception {
        AppiumDriver<MobileElement> currentDriverSession;
        currentDriverSession = initialiseDriver(desiredCapabilities);
        AppiumDriverManager.setDriver(currentDriverSession);
    }

    public void startAppiumDriverInstance() throws Exception {
        String userSpecifiedCaps;
        DesiredCapabilities desiredCapabilities;
        desiredCapabilities = buildDesiredCapabilites("./src/main/resources/config/capabilities.json"); ;
        startAppiumDriverInstance(desiredCapabilities);
    }

    private DesiredCapabilities buildDesiredCapabilites(String capabilityPath)
            throws Exception {
        String capabilities = capabilityPath;
        if (new File(capabilityPath).exists()) {
            Path path = FileSystems.getDefault().getPath(capabilityPath);
            if (!path.getParent().isAbsolute()) {
                capabilities = path.normalize()
                        .toAbsolutePath().toString();
            }
            if (ConfigFileHandler.getData("PLATFORM").equals(MobilePlatform.ANDROID)) {
                desiredCapabilityBuilder
                        .buildDesiredCapability();
            } else {
                desiredCapabilityBuilder
                        .buildDesiredCapability();
            }
            return DesiredCapabilityBuilder.getDesiredCapability();
        } else {
            System.out.println("Capability file not found");
            return null;
        }
    }

    protected void stopAppiumDriver(){
        if(getDriver() != null){
            Log.INFO("Session Deleting ---- "
                    + getDriver().getSessionId() + "---"
                    + getDriver().getSessionDetail("udid"));
            getDriver().quit();
        }
    }
}
