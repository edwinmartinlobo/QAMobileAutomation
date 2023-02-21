package com.mtaf.utils;

import org.json.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Random;
import java.util.stream.IntStream;

public class JsonParser {
    private String filePath;

    public JsonParser(String filePath) { this.filePath= filePath; }

    /**
     * This method will read the JSON file and return the JSON object
     * @return
     */
    public JSONObject getObjectfromJSON() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filePath)));
            String jsonContent = IOUtils.toString(bufferedReader);
            return new JSONObject(jsonContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

