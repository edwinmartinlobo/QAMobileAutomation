/*********************************************************
 * Created :   Nilesh Patole
 * Date    :   ${DATE}
 *********************************************************/

package com.mtaf.managers.deviceManager;

import com.mtaf.utils.CommandPromptUtil;
import org.json.simple.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IOSManager implements IManager {

    private CommandPromptUtil cmd;
    public ArrayList<String> deviceUDIDiOS = new ArrayList<String>();
    private final static int IOS_UDID_LENGTH = 40;
    private final static int SIM_UDID_LENGTH = 36;
    JSONObject iOSDevices;
    String profile = "system_profiler SPUSBDataType | sed -n -E -e '/(iPhone|iPad|iPod)/"
            + ",/Serial/s/ *Serial Number: *(.+)/\\1/p'";

    public IOSManager() {
        cmd = new CommandPromptUtil();
        iOSDevices = new JSONObject();
    }


    public DeviceManger getDevice(String udid) {
        Optional<DeviceManger> device = getDevices().stream().filter(d ->
                    udid.equals(d.getUdid())).findFirst();
        return device.orElseThrow(() ->
                new RuntimeException("Provided DeviceUDID " + udid
                        + " is not found on the machine")
        );
    }

    public List<DeviceManger> getDevices() {
        List<DeviceManger> deviceManger = new ArrayList<>();
        getIOSUDID().forEach(udid -> {
            try {
                deviceManger.add(new DeviceManger(getDeviceInfo(udid)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return deviceManger;
    }

    private JSONObject getDeviceInfo(String udid) throws InterruptedException, IOException {

        String model = cmd.runProcessCommandToGetDeviceID("ideviceinfo -u deviceinfo -u "
                + udid + " | grep ProductType").replace("\n", "");

        String name = cmd.runProcessCommandToGetDeviceID("idevicename --udid " + udid);
        String osVersion = cmd.runProcessCommandToGetDeviceID("ideviceinfo --udid "
                + udid
                + " | grep ProductVersion").replace("ProductVersion:", "")
                .replace("\n","").trim();

        iOSDevices.put("deviceModel",model);
        iOSDevices.put("udid",udid);
        iOSDevices.put("name",name);
        iOSDevices.put("brand","Apple");
        iOSDevices.put("isDevice","true");
        iOSDevices.put("screenSize","Not Supported");
        iOSDevices.put("apiLevel","");
        iOSDevices.put("osVersion",osVersion);
        iOSDevices.put("os", "ios");
        return iOSDevices;
    }

    private ArrayList<String> getIOSUDID() {
        try {
            int startPos = 0;
            int endPos = IOS_UDID_LENGTH - 1;
            Optional<String> getIOSDeviceID = Optional.of(cmd.runProcessCommandToGetDeviceID(profile));
            while (endPos < getIOSDeviceID.get().length()) {
                deviceUDIDiOS.add(getIOSDeviceID.get().substring(startPos, endPos + 1)
                        .replace("\n", ""));
                startPos += IOS_UDID_LENGTH;
                endPos += IOS_UDID_LENGTH;
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to fetch iOS device connected");
        }
        return deviceUDIDiOS;
    }
}
