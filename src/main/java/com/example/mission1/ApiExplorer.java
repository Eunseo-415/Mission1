package com.example.mission1;

import com.google.gson.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ApiExplorer {
    public final WifiDbController wifiDbController = new WifiDbController();

    public int getTotalNum() throws IOException{
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
        urlBuilder.append("/" +  URLEncoder.encode("536c4f786372756e38336a4245557a","UTF-8") );
        urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") );
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("1","UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("2","UTF-8"));
        // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;

        // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        JsonObject convertedObject = new Gson().fromJson(sb.toString(), JsonObject.class);
        JsonObject jsonObject = (JsonObject) convertedObject.get("TbPublicWifiInfo");
        return Integer.parseInt(String.valueOf(jsonObject.get("list_total_count")));
    }

    public void load(int start, int end) throws IOException{
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
        urlBuilder.append("/" +  URLEncoder.encode("536c4f786372756e38336a4245557a","UTF-8") );
        urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") );
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(start),"UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(end),"UTF-8"));
        // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;

        // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject convertedObject = new Gson().fromJson(sb.toString(), JsonObject.class);
        JsonObject jsonObject = (JsonObject) convertedObject.get("TbPublicWifiInfo");
        int totalCount = Integer.parseInt(String.valueOf(jsonObject.get("list_total_count")));
        JsonArray rows = (JsonArray) jsonObject.get("row");
//        System.out.println(totalCount);
        for (int i = 0; i < rows.size(); i++) {
            JsonObject temp = (JsonObject) rows.get(i);
            Wifi wifi = gson.fromJson(temp, Wifi.class);
            wifiDbController.save(wifi);
        }

    }

    public void saveDB() throws IOException {
        int total = getTotalNum();
        int start = 1;
        int end = 1000;
        while (total > end){
            load(start, end);
            start = end+1;
            if(end + 1000 < total){
                end += 1000;
            } else{
                load(start, total);
                break;
            }
        }
    }
}
