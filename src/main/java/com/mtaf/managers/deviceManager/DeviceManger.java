/*********************************************************
 * Created :   Nilesh Patole
 * Date    :   ${DATE}
 *********************************************************/

package com.mtaf.managers.deviceManager;

import org.json.simple.JSONObject;

public class DeviceManger {

    private String udid;
    private String name;
    private String state = "Not Supported";
    private boolean isAvailable;
    private String osVersion;
    private String os = "Not Supported";
    private String deviceType = "Not Supported";
    private String brand = "Not Supported";
    private String apiLevel = "Not Supported";
    private boolean isDevice;
    private String deviceModel = "Not Supported";
    private String screenSize;
    private String deviceManufacturer;

    public DeviceManger(JSONObject deviceJson, String deviceType) {
        this.udid = deviceJson.get("udid").toString();
        this.name = deviceJson.get("name").toString();
        this.state = deviceJson.get("state").toString();
        this.isAvailable = deviceJson.get("availability").equals("(available)");
        this.deviceType = deviceType;
        String[] osAndVersion = deviceType.split(" ");
        if (osAndVersion.length == 2) {
            this.os = osAndVersion[0];
            this.osVersion = osAndVersion[1];
        }
    }


    public DeviceManger(JSONObject deviceJson) {
        this.udid = deviceJson.get("udid").toString();
        this.name = deviceJson.get("name").toString();
        this.osVersion = deviceJson.get("osVersion").toString();
        this.brand = deviceJson.get("brand").toString();
        this.apiLevel = deviceJson.get("apiLevel").toString();
        //this.isDevice = (boolean) deviceJson.get("isDevice");
        this.deviceModel = deviceJson.get("deviceModel").toString();
        this.screenSize = deviceJson.get("screenSize").toString();
        this.os = deviceJson.get("os").toString();
    }

    public DeviceManger() {
    }

    public String getUdid() {
        return udid;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public String getOs() {
        return os;
    }

    public String getBrand() {
        return brand;
    }

    public String getApiLevel() {
        return apiLevel;
    }

    public boolean getIsDevice() {
        return isDevice;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public String getDeviceManufacturer() {
        return deviceManufacturer;
    }

    public void setDeviceManufacturer(String deviceManufacturer) {
        this.deviceManufacturer = deviceManufacturer;
    }

}
