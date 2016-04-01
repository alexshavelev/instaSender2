package com.shavelev.alexander.workers;

/**
 * Created by alex_shavelev on 31.03.16.
 */


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class HttpUrlConnection{

    private static final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {

        HttpUrlConnection http = new HttpUrlConnection();

        System.out.println("Testing 1 - Send Http GET request");
        http.sendGet();

        System.out.println("\nTesting 2 - Send Http POST request");
        http.sendPost();

    }

    public void sendGet() throws Exception {
        sendGet("http://www.google.com/search?q=mkyong");
    }

    // HTTP GET request
    public static String sendGet(String url) throws Exception {

//        String url = "http://www.google.com/search?q=mkyong";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);



        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));




        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line+"\n");
        }




//        String inputLine;
        StringBuffer response = new StringBuffer();

//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }

        in.close();

        return sb.toString();

        //print result
//        System.out.println(response.toString());

    }

    public void sendPost() throws Exception {
        sendPost("https://selfsolve.apple.com/wcResults.do");
    }

    // HTTP POST request
    public void sendPost(String url) throws Exception {

//        String url = "https://selfsolve.apple.com/wcResults.do";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

}
