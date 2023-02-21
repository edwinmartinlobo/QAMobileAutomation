/*********************************************************
 * Created :   Mayur Waghmare
 * Date    :   27-03-2019
 *********************************************************/

package com.mtaf.utils;

import com.mtaf.filehandler.ConfigFileHandler;
import com.mtaf.managers.CapabilityManager;
import com.mtaf.managers.appium.LocalAppiumManager;
import com.mtaf.managers.deviceManager.AndroidManager;
import com.mtaf.managers.deviceManager.DeviceManger;
import com.mtaf.managers.deviceManager.IManager;
import com.mtaf.managers.deviceManager.IOSManager;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;

public class DesiredCapabilityBuilder {

    public static ThreadLocal<DesiredCapabilities> desiredCapabilitiesThreadLocal
            = new ThreadLocal<>();
    private LocalAppiumManager localAppiumManager;
    private IManager appiumDeviceManager;
    private CapabilityManager caps;

    public DesiredCapabilityBuilder() {
        localAppiumManager = new LocalAppiumManager();
        caps = CapabilityManager.getInstance();
    }

    public static DesiredCapabilities getDesiredCapability() {
        return desiredCapabilitiesThreadLocal.get();
    }

    public void buildDesiredCapability() throws Exception {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        String platform = ConfigFileHandler.getData("PLATFORM");

        if(platform.equalsIgnoreCase(MobilePlatform.ANDROID)){
            String deviceName = String.valueOf(new AndroidManager().getDevices().get(0).getUdid());
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, localAppiumManager.
                    getAvailablePort());
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,
                    caps.getValueFromCaps(platform, "APP_PACKAGE"));
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
                    caps.getValueFromCaps(platform,"APP_ACTIVITY"));
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME
                    , caps.getValueFromCaps(platform,"AUTOMATION_NAME"));
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        }
        else if(platform.equalsIgnoreCase(MobilePlatform.IOS)){
            String deviceName = String.valueOf(new IOSManager().getDevices().get(0).getUdid());
            appiumDeviceManager = new IOSManager();
            desiredCapabilities.setCapability(MobileCapabilityType.UDID, appiumDeviceManager.
                    getDevices().get(0).getUdid());
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, appiumDeviceManager.
                    getDevices().get(0).getOsVersion());
            desiredCapabilities.setCapability(IOSMobileCapabilityType.XCODE_ORG_ID
                    , caps.getValueFromCaps(platform,"XCODE_ORG_ID"));
            desiredCapabilities.setCapability(IOSMobileCapabilityType.XCODE_SIGNING_ID
                    , caps.getValueFromCaps(platform,"XCODE_SIGNING_ID"));
            desiredCapabilities.setCapability(IOSMobileCapabilityType.USE_NEW_WDA,false);
            desiredCapabilities.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT
                    , 8100);
            desiredCapabilities.setCapability(IOSMobileCapabilityType.COMMAND_TIMEOUTS
                    , 120);
            desiredCapabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID
                    , caps.getValueFromCaps(platform,"BUNDLE_ID"));
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME
                    , caps.getValueFromCaps(platform,"AUTOMATION_NAME"));
            desiredCapabilities.setCapability(MobileCapabilityType.APP
                    , caps.getValueFromCaps(platform,"app"));
        }

        desiredCapabilities.setCapability("fullReset", false);
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET,true);
        desiredCapabilitiesThreadLocal.set(desiredCapabilities);
    }
}
