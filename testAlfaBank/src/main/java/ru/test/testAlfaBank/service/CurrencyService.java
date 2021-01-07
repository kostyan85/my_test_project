package ru.test.testAlfaBank.service;


import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.test.testAlfaBank.model.FeignApi;

import java.time.LocalDate;
import java.util.Map;

@Service
public class CurrencyService {

    @Value("${pathToRich}")
    public String RICH;

    @Value("${pathToBroke}")
    public String BROKE;

    @Value("${urlForCurrentCourse}")
    private String urlForCurrentCourse;

    @Value("${urlForYesterdayCourse}")
    private String urlForYesterdayCourse;

    @Value("${baseCurrency}")
    private String baseCurrency;

    /**
     * сравнение курсов валют
     *
     * @param currentCourse   сегодняшний курс
     * @param yesterdayCourse вчрашний курс
     * @return String url с которого будет браться gif
     */
    public String compareCurrency(double currentCourse, double yesterdayCourse) {
        if (currentCourse > yesterdayCourse) {
            return RICH;
        } else return BROKE;
    }

    /**
     * вычисление текущий курса
     *
     * @param currencyCode код валюты
     * @param url          откуда берутся котировки
     * @return Double курс
     */
    public Double getCurrentCourse(String currencyCode, String url) {
        Object currentCurrency = getBuilder(url).find();
        Map<String, Double> rates = (Map) ((Map) currentCurrency).get("rates");
        Double currencyCourse = rates.get(currencyCode);
        return currencyCourse;
    }

    /**
     * вычисление вчерашнего курса
     *
     * @param currencyCode код валюты
     * @param url          откуда берутся котировки
     * @return Double курс
     */
    public Double getYesterdayCourse(String currencyCode, String url) {
        Object yesterdayCurrency = getBuilder(url).find();
        Map<String, Double> rates = (Map) ((Map) yesterdayCurrency).get("rates");
        Double yesterdayCourse = rates.get(currencyCode);
        return yesterdayCourse;
    }

    /**
     * создание FeignBuilder
     *
     * @param url c которого получаем Json
     * @return FeignBuilder
     */
    public FeignApi getBuilder(String url) {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(FeignApi.class, url);
    }

    /**
     * получение url итоговой gif
     *
     * @param url откуда берется gif
     * @return String url
     */
    public String getUrlGif(String url) {
        Object data = ((Map) getBuilder(url).find()).get("data");
        Object images = ((Map) data).get("images");
        Object original = ((Map) images).get("original");
        String gifUrl = ((Map) original).get("url").toString();
        return gifUrl;
    }

    /**
     * вычисление url рандомной gif на основании сравнения курсов валют
     *
     * @param currencyCode код валюты
     * @return String url gif
     */
    public String getRandomGif(String currencyCode) {
        Double currentCourse = getCurrentCourse(currencyCode, urlForCurrentCourse + baseCurrency);
        Double yesterdayCourse = getYesterdayCourse(currencyCode,
                urlForYesterdayCourse.replace("yesterdayDate", getYesterdayDate()) + baseCurrency);
        String url = compareCurrency(currentCourse, yesterdayCourse);
        String urlGif = getUrlGif(url);
        return urlGif;
    }

    /**
     * вычисление вчерашней даты
     *
     * @return String вчерашняя дата в формате YYYY-MM-DD
     */
    public String getYesterdayDate() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        return String.valueOf(yesterday);
    }


}
