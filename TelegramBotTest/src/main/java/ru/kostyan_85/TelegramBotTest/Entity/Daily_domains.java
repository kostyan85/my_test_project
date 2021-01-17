package ru.kostyan_85.TelegramBotTest.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONPropertyIgnore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.persistence.Entity;
import java.io.*;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//@Entity

public class Daily_domains {

//   static List<Daily_domains> domArr = new ArrayList<>();

    private String domainname;

    private int hotness;
    private int price;
    private int x_value;
    private String yandex_tic;
    private int links;
    private int visitors;
    private String registrar;
    private int old;
    private String delete_date;
    private boolean rkn;
    private boolean judicial;
    private boolean block;
    public Daily_domains() throws MalformedURLException {
    }


    public String getDomainname() {
        return domainname;
    }

    public void setDomainname(String domainname) {
        this.domainname = domainname;
    }


    public int getHotness() {
        return hotness;
    }

    public void setHotness(int hotness) {
        this.hotness = hotness;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public int getX_value() {
        return x_value;
    }

    public void setX_value(int x_value) {
        this.x_value = x_value;
    }


    public String getYandex_tic() {
        return yandex_tic;
    }

    public void setYandex_tic(String yandex_tic) {
        this.yandex_tic = yandex_tic;
    }


    public int getLinks() {
        return links;
    }

    public void setLinks(int links) {
        this.links = links;
    }


    public int getVisitors() {
        return visitors;
    }

    public void setVisitors(int visitors) {
        this.visitors = visitors;
    }


    public String getRegistrar() {
        return registrar;
    }

    public void setRegistrar(String registrar) {
        this.registrar = registrar;
    }


    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }


    public String getDelete_date() {
        return delete_date;
    }

    public void setDelete_date(String delete_date) {
        this.delete_date = delete_date;
    }


    public boolean isRkn() {
        return rkn;
    }

    public void setRkn(boolean rkn) {
        this.rkn = rkn;
    }


    public boolean isJudicial() {
        return judicial;
    }

    public void setJudicial(boolean judicial) {
        this.judicial = judicial;
    }


    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    static String url = "https://backorder.ru/json/?order=desc&expired=1&by=hotness&page=1&items=50";

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONArray readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONArray json = new JSONArray(jsonText);
            return json;
        } finally {
            is.close();
        }

    }

    public static List<Daily_domains> jsonArrayToJsonObject() throws IOException {
        List<Daily_domains> domArr = new ArrayList<>();


        JSONArray array = readJsonFromUrl(url);

        for(int n = 0; n < array.length(); n++)
        {
            Daily_domains daily_domains  = new Daily_domains();
            JSONObject object = array.getJSONObject(n);

                daily_domains.setDomainname(object.getString("domainname"));
                daily_domains.setHotness(object.getInt("hotness"));
                daily_domains.setPrice(object.getInt("price"));

            daily_domains.setYandex_tic(object.optString("yandex_tic"));
                daily_domains.setLinks(object.getInt("links"));
                daily_domains.setRegistrar(object.getString("registrar"));
                daily_domains.setOld(object.getInt("old"));
                daily_domains.setDelete_date(object.getString("delete_date"));
                daily_domains.setRkn(object.getBoolean("rkn"));
                daily_domains.setJudicial(object.getBoolean("judicial"));
                daily_domains.setBlock(object.getBoolean("block"));

                domArr.add(daily_domains);
            System.out.println(n);
        }

return domArr;
//
    }
    public static void main(String[] args) throws IOException, JSONException {
//        getValuesForGivenKey("domainname");
//       jsonArrayToJsonObject();

   soutArray();
    }
    public static void soutArray() throws IOException {
        List arr = jsonArrayToJsonObject();
        int count = 0;
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
            count++;
        }
        System.out.println(count);
    }
    @Override
    public String toString() {
        return "Daily_domains{" +
                "domainname='" + domainname + '\'' +
                ", hotness=" + hotness +
                ", price=" + price +
                ", x_value=" + x_value +
                ", yandex_tic=" + yandex_tic +
                ", links=" + links +
                ", visitors=" + visitors +
                ", registrar='" + registrar + '\'' +
                ", old=" + old +
                ", delete_date='" + delete_date + '\'' +
                ", rkn=" + rkn +
                ", judicial=" + judicial +
                ", block=" + block +
                '}';
    }
}
