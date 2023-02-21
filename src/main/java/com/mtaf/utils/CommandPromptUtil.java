/*********************************************************
 * Created :   Nilesh Patole
 * Date    :   ${DATE}
 *********************************************************/

package com.mtaf.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class CommandPromptUtil {
    public String OSType =null;
    public CommandPromptUtil()
    {
        OSType = System.getProperty("os.name");

    }

    public String runCommandThruProcess(String command)
            throws InterruptedException, IOException {
        BufferedReader br = getBufferedReader(command);
        String line;
        String allLine = "";
        while ((line = br.readLine()) != null) {
            allLine = allLine + "" + line + "\n";
        }
        return allLine;
    }

    public List<String> runCommand(String command)
            throws InterruptedException, IOException {
        BufferedReader br = getBufferedReader(command);
        String line;
        List<String> allLine = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            allLine.add(line.replaceAll("[\\[\\](){}]","_").split("_")[1]);
        }
        return allLine;
    }

    private BufferedReader getBufferedReader(String command) throws IOException {
        List<String> commands = new ArrayList<>();
        if(OSType.contains("Windows"))
        {
            commands.add("cmd");
            commands.add("/c");
        }
        else
        {
            commands.add("/bin/sh");
            commands.add("-c");
        }
        commands.add(command);
        ProcessBuilder builder = new ProcessBuilder(commands);
        final Process process = builder.start();
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        return new BufferedReader(isr);
    }

    public Process execForProcessToExecute(String cmd) throws IOException {
        Process pr = null;
        List<String> commands = new ArrayList<>();

        if(OSType.contains("Windows"))
        {
            commands.add("cmd");
            commands.add("/c");
        }
        else {
            commands.add("/bin/sh");
            commands.add("-c");
        }
        commands.add(cmd);
        ProcessBuilder builder = new ProcessBuilder(commands);
        pr = builder.start();
        return pr;
    }

    public String runProcessCommandToGetDeviceID(String command)
            throws InterruptedException, IOException {
        BufferedReader br = getBufferedReader(command);
        String line;
        String allLine = "";
        while ((line = br.readLine()) != null) {
            allLine = allLine.trim() + "" + line.trim() + "\n";
        }
        return allLine.trim();
    }


    public void killNodeServices(){
        try {
            String line;
            Process process = execForProcessToExecute("taskkill /im node.exe /F");
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            try
            {
                while ((line = br.readLine()) != null) {
                    Log.INFO(line);
                }
            } catch (IOException e) {
                Log.ERROR(e.toString());
            }
        } catch (IOException e) {
            Log.ERROR(e.toString());
        }

    }
}
