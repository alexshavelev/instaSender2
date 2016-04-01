package com.shavelev.alexander.workers;

import com.shavelev.alexander.helpers.AuthMsg;
//import com.google.gson.*;
import net.sf.json.*;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by alex_shavelev on 31.03.16.
 */
public class UpdateWorker {

    private final String MAIN_URL = "https://api.instagram.com/";
    private final String SELF_FEED = "v1/users/self/feed";
    private final String ACCESS_TOKEN = "30782247.1fb234f.85987a7aa25842a69bb4165a38e4f2ac";
    private String httpResult;

    public void update() throws Exception {
        String url = MAIN_URL  + SELF_FEED + "?access_token=" + ACCESS_TOKEN;
        System.out.println("url=" + url);
        httpResult = HttpUrlConnection.sendGet(url);
        System.out.println("res=" + httpResult);
//        UpdateWorker x = new Gson().fromJson(httpResult, UpdateWorker.class);
//        System.out.println("x=" + x);

        JSONObject obj = parseJson(httpResult);
        JSONArray data = obj.getJSONArray("data");

//        Map<String, String> result = new HashMap<String, String>();
        for (int i = 0; i < data.size(); i++) {
            JSONObject op = data.getJSONObject(i);
            String link = op.getString("link");
            System.out.println("link="+link);
        }
    }


    private static JSONObject parseJson(Object content) throws Exception {
        try {
            return (JSONObject) JSONSerializer.toJSON(content);
        } catch (Exception ex) {
            throw new Exception(String.format("json parsing error. %s", ex.getMessage()));
        }
    }
}
