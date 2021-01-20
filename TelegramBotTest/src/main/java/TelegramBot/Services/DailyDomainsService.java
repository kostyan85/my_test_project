package TelegramBot.Services;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import TelegramBot.Entity.Daily_domains;
import TelegramBot.Repository.DailyDomainsRepository;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class DailyDomainsService {

    @Autowired
    private DailyDomainsRepository domainsRepository;

    @Value("${dailyDomainsUrl}")
    private String url;

    private static final Logger LOGGER = LoggerFactory.getLogger(DailyDomainsService.class);

    /**
     * преобразуем JSON в String
     */
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /**
     * считываем JSON по URL
     *
     * @return JSONArray
     */
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

    /**
     * преобразуем JSONArray в JSONObject и заполняем массив данными JSON
     *
     * @return массив с данными из JSON
     */
    public static List<Daily_domains> jsonArrayToJsonObject(String url) throws IOException {
        List<Daily_domains> domArr = new ArrayList<>();
        JSONArray array = readJsonFromUrl(url);
        for (int n = 0; n < array.length(); n++) {
            Daily_domains daily_domains = new Daily_domains();
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
        }
        return domArr;
    }

    /**
     * сохраняем в БД domain полученные по URL
     */

    public void saveToBaseDailyDomains() {
        domainsRepository.deleteAll();
        ArrayList<Daily_domains> arr = null;
        try {
//            System.out.println("старт");;
            arr = (ArrayList<Daily_domains>) jsonArrayToJsonObject(url);
            domainsRepository.saveAll(arr);

        } catch (IOException e) {
            LOGGER.error("error saveUserToBase: {0} ", e);
        }
//        System.out.println("финиш");

    }

    /**
     * получаем количество domains
     *
     * @return количество domains(размер массива domains)
     */
    //TODO как правильно сделать проверку
    public int getCountDomainsInDB() {
        saveToBaseDailyDomains();
        int allDomainsSize = 0;
        if (domainsRepository.findAll() != null) {
            List<Daily_domains> allDomains = domainsRepository.findAll();
            allDomainsSize = allDomains.size();
        } else {
            LOGGER.error("error getCountDomainsInDB");
        }
        return allDomainsSize;
    }

    /**
     * формируем сообщение для ежедневной рассылки
     *
     * @return сообщение
     */
    //TODO что можно логировать в этом методе
    public String formMessageForUser() {

        System.out.println("формируем сообщение");
        String finalMessage = "\"" + getCurrentDate() + ". Собрано " + getCountDomainsInDB() + " доменов\"";
        return finalMessage;
    }

    /**
     * получаем текущую дату
     *
     * @return отформатированная текущая дата
     */
    //TODO что можно логировать в этом методе
    public String getCurrentDate() {

        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDate.format(dtf);
    }


}