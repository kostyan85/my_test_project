package ru.kostyan_85.TelegramBotTest.Entity;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.Entity;

//@Entity
public class Daily_domains {
    String url = "https://backorder.ru/json/?order=desc&expired=1&by=hotness&page=1&items=50";
    JSONObject obj = new JSONObject(url);
    JSONArray arr = obj.getJSONArray("");


    public static void main(String[] args) {
        Daily_domains daily_domains = new Daily_domains();
//        System.out.println(daily_domains.arr);
    }
}
