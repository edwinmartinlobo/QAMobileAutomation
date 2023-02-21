/*********************************************************
 * Created :   Mayur Waghmare
 * Date    :   27-03-2019
 *********************************************************/

package com.mtaf.managers.appium;

import com.mtaf.managers.deviceManager.AndroidManager;
import com.mtaf.managers.deviceManager.IOSManager;
import com.mtaf.managers.deviceManager.DeviceManger;
import com.mtaf.utils.Log;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LocalAppiumManager {

    private static AppiumDriverLocalService appiumDriverLocalService;

    private static AppiumDriverLocalService getAppiumDriverLocalService() {
        return appiumDriverLocalService;
    }

    private static void setAppiumDriverLocalService(AppiumDriverLocalService appiumDriverLocalService) {
        LocalAppiumManager.appiumDriverLocalService = appiumDriverLocalService;
    }

    private URL getAppiumUrl(){ return getAppiumDriverLocalService().getUrl(); }

    public String getRemoteWDHubIP(String host) throws IOException {
        return getAppiumUrl().toString();
    }

    public void destroyAppiumNode(){
        getAppiumDriverLocalService().stop();
        Log.INFO("Appium server stopped successfully");
        if(getAppiumDriverLocalService().isRunning()){
            Log.ERROR("Appium server didn't shut down properly, Trying again");
            getAppiumDriverLocalService().stop();
        }
    }

    /**
     * Method to start Appium server
     */
    public void startAppiumServer(){
        Log.INFO("-----------------------------------------------------------");
        Log.INFO("          Starting Appium Server on Localhost              ");
        Log.INFO("-----------------------------------------------------------");

        appiumDriverLocalService = AppiumDriverLocalService
                .buildService(new AppiumServiceBuilder()
                        .usingAnyFreePort()
                        .withArgument(() -> "-pa", "/wd/hub"));
        appiumDriverLocalService.start();

//        AppiumServiceBuilder builder =
//                getAppiumServerBuilder()
//                        .withLogFile(new File("./Logs/AppiumLogs/appium_logs.log"))
//                        .withIPAddress("127.0.0.1")
//                        .usingAnyFreePort()
//                        .withArgument(() -> "--base-path", "/wd/hub");
//        appiumDriverLocalService = AppiumDriverLocalService.buildService(builder);
//        appiumDriverLocalService.start();
        Log.INFO("-----------------------------------------------------------");
        Log.INFO("          Appium Server started at "+ appiumDriverLocalService.getUrl());
        Log.INFO("-----------------------------------------------------------");
        setAppiumDriverLocalService(appiumDriverLocalService);
    }

    private AppiumServiceBuilder getAppiumServerBuilder(){
        return new AppiumServiceBuilder();
    }

    public List<DeviceManger> getDevices(String platform) throws Exception {
        List<DeviceManger> devices = new ArrayList<>();
        if (platform.equalsIgnoreCase("Android")
                || platform.equalsIgnoreCase("Both")) {
            devices.addAll(new AndroidManager().getDevices());
        }
        if (platform.equalsIgnoreCase("iOS")
                || platform.equalsIgnoreCase("Both")) {
                devices.addAll(new IOSManager().getDevices());
            }
        return devices;
    }

    public int getAvailablePort() throws IOException {
        ServerSocket serverSocket = new ServerSocket(0);
        serverSocket.setReuseAddress(true);
        int port = serverSocket.getLocalPort();
        serverSocket.close();
        return port;
    }
}
