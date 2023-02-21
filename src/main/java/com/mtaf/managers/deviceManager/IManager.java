/*********************************************************
 * Created :   Nilesh Patole
 * Date    :   ${DATE}
 *********************************************************/

package com.mtaf.managers.deviceManager;


import java.util.List;


public interface IManager {

    List<DeviceManger> getDevices() throws Exception;
}
