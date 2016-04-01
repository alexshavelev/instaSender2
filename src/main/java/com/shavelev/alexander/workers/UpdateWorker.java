package com.shavelev.alexander.workers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.*;
import com.shavelev.alexander.helpers.AuthMsg;
//import com.google.gson.*;
import net.sf.json.*;
import org.telegram.telegrambots.TelegramBotsApi;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;







/**
 * Created by alex_shavelev on 31.03.16.
 */
public class UpdateWorker {

    private final String MAIN_URL = "https://api.instagram.com/";
    private final String SELF_FEED = "v1/users/self/feed";
    private final String ACCESS_TOKEN = "30782247.1fb234f.85987a7aa25842a69bb4165a38e4f2ac";
    private final String TELEGRAM_TOKEN = "99434746:AAETpph94f6EIIBY-reY6CNoJp50nw-fdoQ";
    private final int alexShavelev = 64605342;
    private final int bastards = -133878532;
    private String httpResult;
    private ArrayList<String> linksList;
    private TelegramBot bot = TelegramBotAdapter.build(TELEGRAM_TOKEN);


    public UpdateWorker() {
        this.linksList = new ArrayList<>();
    }

    public void cleanState() {
        this.linksList.clear();
    }

    public void update() throws Exception {
        String url = MAIN_URL  + SELF_FEED + "?access_token=" + ACCESS_TOKEN;
        System.out.println("url=" + url);
        httpResult = HttpUrlConnection.sendGet(url);

        JSONObject obj = parseJson(httpResult);
        JSONArray data = obj.getJSONArray("data");


        for (int i = 0; i < data.size(); i++) {
            JSONObject op = data.getJSONObject(i);
            String link = op.getString("link");

            if (!linksList.contains(link)) {
                System.out.println("link="+link);

                bot.sendMessage(
                                alexShavelev,
                                link,
                                false,
                                1,
                        new ReplyKeyboardMarkup(new String[]{"ok", "cancel"}));
                bot.sendMessage(bastards, link, false, 1, new ReplyKeyboardMarkup(new String[]{"ok", "cancel"}));
                // send to telegram
                linksList.add(link);
            } else {
                System.out.println("skip");
            }
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
