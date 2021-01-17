package ru.kostyan_85.TelegramBotTest.Services;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kostyan_85.TelegramBotTest.Entity.Daily_domains;
import ru.kostyan_85.TelegramBotTest.Repository.DailyDomainsRepository;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Service
public class DailyDomainsService {

    @Autowired
   private DailyDomainsRepository domainsRepository;
//TODO почему не получилось через Value
//    @Value("${dailyDomainsUrl}")
    private String url = "https://backorder.ru/json/?order=desc&expired=1&by=hotness&page=1&items=50";

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

    public static List<Daily_domains> jsonArrayToJsonObject(String url) throws IOException {
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
//          System.out.println(n);
        }
        return domArr;
    }
    public static void soutArray(String url) throws IOException {
        List arr =  jsonArrayToJsonObject(url);
        int count = 0;
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
            count++;
        }
        System.out.println(count);
    }
    public void saveDailyDomains()  {
//        int count = 0;
        ArrayList<Daily_domains>arr = null;
        try {
            System.out.println("старт");;
            arr = (ArrayList<Daily_domains>) jsonArrayToJsonObject(url);
            domainsRepository.saveAll(arr);
//            for (int i = 0; i < arr.size(); i++) {
//                domainsRepository.save(arr.get(i));
////                System.out.println("count: "+ i + arr.get(i));
//                count++;
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("финиш");
//        findAll();
    }
    public void findAll(){
        List<Daily_domains> all = domainsRepository.findAll();
        for (int i = 0; i <all.size() ; i++) {
            System.out.println("count: " +i + all.get(i));
        }
    }

    public static void main(String[] args) throws IOException {
    DailyDomainsService daily_domainsService = new DailyDomainsService();
//    daily_domainsService.saveDailyDomains();
    soutArray(daily_domainsService.url);
    }

}