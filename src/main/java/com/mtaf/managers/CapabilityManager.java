/*********************************************************
 * Created :   Mayur Waghmare
 * Date    :   27-03-2019
 *********************************************************/

package com.mtaf.managers;

import com.mtaf.utils.JsonParser;
import com.mtaf.utils.Log;
import org.json.JSONObject;

import java.util.Objects;

public class CapabilityManager {

    private static CapabilityManager capsInstance;
    private JSONObject capabilities;

    private CapabilityManager(){
        String capabilitiesFilePath = "./src/main/resources/config/capabilities.json";
        Log.INFO("Reading JSON file from "+capabilitiesFilePath);
        JsonParser jsonParser = new JsonParser(capabilitiesFilePath);
        capabilities = jsonParser.getObjectfromJSON();
    }

    public static CapabilityManager getInstance(){
        if(capsInstance == null){
            capsInstance = new CapabilityManager();
        }
        return capsInstance;
    }

    public JSONObject getCapabilities(){
        return capabilities;
    }

    public String getMachineIP(){
        return (String) capabilities.get("MTAFServiceHost");
    }

    private JSONObject getCapabilityObjectFromKey(String key) {
        boolean hasKey = capabilities.has(key);
        if (hasKey) {
            return (JSONObject) getCapabilities().get(key);
        } else {
            return null;
        }
    }

    /**
     *
     * @param platform
     * @param key
     * @return
     */
    public String getValueFromCaps(String platform, String key){
        return (String) Objects.requireNonNull(getCapabilityObjectFromKey(platform)).get(key);
    }
}
