package ru.kostyan_85.TelegramBotTest.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONPropertyIgnore;
import org.springframework.web.client.RestTemplate;

import javax.persistence.Entity;
import java.lang.reflect.Type;
import java.util.ArrayList;

//@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Daily_domains {

    private String domainname;
    private int hotness;
    private int price;
    private int x_value;
    private int yandex_tic;
    private int links;
    private int visitors;
    private String registrar;
    private int old;
    private String delete_date;
    private boolean rkn;
    private boolean judicial;
    private boolean block;

    @JsonProperty("domainname")
    public String getDomainname() {
        return domainname;
    }

    public void setDomainname(String domainname) {
        this.domainname = domainname;
    }

    @JsonProperty("hotness")
    public int getHotness() {
        return hotness;
    }

    public void setHotness(int hotness) {
        this.hotness = hotness;
    }

    @JsonProperty("price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @JsonProperty("x_value")
    public int getX_value() {
        return x_value;
    }

    public void setX_value(int x_value) {
        this.x_value = x_value;
    }

    @JsonProperty("yandex_tic")
    public int getYandex_tic() {
        return yandex_tic;
    }

    public void setYandex_tic(int yandex_tic) {
        this.yandex_tic = yandex_tic;
    }

    @JsonProperty("links")
    public int getLinks() {
        return links;
    }

    public void setLinks(int links) {
        this.links = links;
    }

    @JsonProperty("visitors")
    public int getVisitors() {
        return visitors;
    }

    public void setVisitors(int visitors) {
        this.visitors = visitors;
    }

    @JsonProperty("registrar")
    public String getRegistrar() {
        return registrar;
    }

    public void setRegistrar(String registrar) {
        this.registrar = registrar;
    }

    @JsonProperty("old")
    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }

    @JsonProperty("delete_date")
    public String getDelete_date() {
        return delete_date;
    }

    public void setDelete_date(String delete_date) {
        this.delete_date = delete_date;
    }

    @JsonProperty("rkn")
    public boolean isRkn() {
        return rkn;
    }

    public void setRkn(boolean rkn) {
        this.rkn = rkn;
    }

    @JsonProperty("judicial")
    public boolean isJudicial() {
        return judicial;
    }

    public void setJudicial(boolean judicial) {
        this.judicial = judicial;
    }

    @JsonProperty("block")
    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    static String url = "https://backorder.ru/json/?order=desc&expired=1&by=hotness&page=1&items=50";
//    JSONObject obj = new JSONObject(url);
//    JSONArray arr = obj.getJSONArray("");
//    String json =

    public static void main(String[] args) throws JsonProcessingException {

//        ObjectMapper mapper = new ObjectMapper();
//        ArrayList<Daily_domains> res = mapper.readValue(url, new TypeReference<ArrayList<Daily_domains>>() {
//            @Override
//            public Type getType() {
//
//                return super.getType();
//            }
//        });
//        for (Daily_domains d : res) {
//            System.out.println(d);
//        }
        RestTemplate restTemplate = new RestTemplate();
        Daily_domains daily_domains = restTemplate.getForObject("https://backorder.ru/json/?order=desc&expired=1&by=hotness&page=1&items=50",Daily_domains.class);
        System.out.println(daily_domains.getDomainname());
//        System.out.println(daily_domains.arr);

    }


//    private RestTemplate restTemplate = new RestTemplate();
//
//    Daily_domains daily_domain = restTemplate.getForObject(url + "/1",Daily_domains.class);
//
//    public Daily_domains getDaily_domain() {
//        return daily_domain;
//    }
}
//
//    String fooResourceUrl
//            = "https://backorder.ru/json/?order=desc&expired=1&by=hotness&page=1&items=50";
//    ResponseEntity<String> response= restTemplate.getForEntity(fooResourceUrl + "/1", String.class);
//}
