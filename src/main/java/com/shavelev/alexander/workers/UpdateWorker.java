package com.shavelev.alexander.workers;

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
    }
}
