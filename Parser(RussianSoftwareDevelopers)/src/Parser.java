import java.io.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    private final List<String> urls = Arrays.asList(
            "https://gpsfront.aliexpress.com/getRecommendingResults.do?callback=jQuery18307595981755480468_1615279364955&widget_id=5547572&platform=pc&limit=40&offset=0&phase=1&productIds2Top=&postback=&_=1615279365317",
            "https://gpsfront.aliexpress.com/getRecommendingResults.do?callback=jQuery18307595981755480468_1615279364955&widget_id=5547572&platform=pc&limit=40&offset=48&phase=1&productIds2Top=&postback=73ed54b6-48f2-4ae3-a4e3-a3a0c6a9f76e&_=1615279444806",
            "https://gpsfront.aliexpress.com/getRecommendingResults.do?callback=jQuery18307595981755480468_1615279364955&widget_id=5547572&platform=pc&limit=40&offset=96&phase=1&productIds2Top=&postback=73ed54b6-48f2-4ae3-a4e3-a3a0c6a9f76e&_=1615279556851");
/**
 * создаем URL из String
 * */
    public static URL createUrl(String link) {
        try {
            return new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
/**
 * конвертируем в строку полученный Json
 * */
    public static String getStringFromUrl (URL url) {
        if (url == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.substring(stringBuilder.indexOf("(") + 1);
    }
/**
 * парсим все ссылки
 * @return список полученный со всех страниц
 * */
    private List<Products> parse() {
        List<Products> resultsList = new ArrayList<>();
        for (int i = 0; i < urls.size(); i++) {
            String data = getStringFromUrl(createUrl(urls.get(i)));
            resultsList.addAll(parseOnePage(data));
        }
        return resultsList;
    }
/**
 * парсим одну ссылку
 * @return список полученный с одной страницы
 * */
    private List<Products> parseOnePage(String url) {
        List<Products> list = new ArrayList<>();
        JSONObject obj = new JSONObject(url);
        JSONArray array = obj.getJSONArray("results");
        for (int i = 0; i < array.length(); i++) {
            Products products = new Products(array.getJSONObject(i).getLong("productId"),
                    array.getJSONObject(i).getLong("sellerId"),
                    array.getJSONObject(i).getString("oriMinPrice"),
                    array.getJSONObject(i).getString("oriMaxPrice"),
                    array.getJSONObject(i).getLong("promotionId"),
                    array.getJSONObject(i).getLong("startTime"),
                    array.getJSONObject(i).getLong("endTime"),
                    array.getJSONObject(i).getInt("phase"),
                    array.getJSONObject(i).getString("productTitle"),
                    array.getJSONObject(i).getString("minPrice"),
                    array.getJSONObject(i).getString("maxPrice"),
                    array.getJSONObject(i).getString("discount"),
                    array.getJSONObject(i).getString("totalStock"),
                    array.getJSONObject(i).getString("stock"),
                    array.getJSONObject(i).getString("orders"),
                    array.getJSONObject(i).getBoolean("soldout"),
                    array.getJSONObject(i).getString("productImage"),
                    array.getJSONObject(i).getString("productDetailUrl"),
                    array.getJSONObject(i).getString("totalTranpro3"),
                    array.getJSONObject(i).getString("productPositiveRate"),
                    array.getJSONObject(i).getString("productAverageStar"),
                    array.getJSONObject(i).getInt("itemEvalTotalNum"),
                    array.getJSONObject(i).getString("icon"));
            list.add(products);
        }
        return list;
    }
    /**
     * преобразовываем объекты Results в .csv формат и запысываем в файл
     * */

    void createCsvFile() throws IOException {
        char CSV_SEPARATOR = ';';
        List<Products> listToWrite = parse();
        String fileName = "data.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            listToWrite.forEach(results -> {
                try {
                    writer.append(results.getProductId()).append(CSV_SEPARATOR)
                            .append(results.getSellerId()).append(CSV_SEPARATOR)
                            .append(results.getOriMinPrice()).append(CSV_SEPARATOR)
                            .append(results.getOriMaxPrice()).append(CSV_SEPARATOR)
                            .append(results.getPromotionId()).append(CSV_SEPARATOR)
                            .append(results.getStartTime()).append(CSV_SEPARATOR)
                            .append(results.getEndTime()).append(CSV_SEPARATOR)
                            .append(results.getPhase()).append(CSV_SEPARATOR)
                            .append(results.getProductTitle()).append(CSV_SEPARATOR)
                            .append(results.getMinPrice()).append(CSV_SEPARATOR)
                            .append(results.getMaxPrice()).append(CSV_SEPARATOR)
                            .append(results.getDiscount()).append(CSV_SEPARATOR)
                            .append(results.getTotalStock()).append(CSV_SEPARATOR)
                            .append(results.getStock()).append(CSV_SEPARATOR)
                            .append(results.getOrders()).append(CSV_SEPARATOR)
                            .append(results.isSoldout()).append(CSV_SEPARATOR)
                            .append(results.getProductImage()).append(CSV_SEPARATOR)
                            .append(results.getProductDetailUrl()).append(CSV_SEPARATOR)
                            .append(results.getTotalTranpro3()).append(CSV_SEPARATOR)
                            .append(results.getProductPositiveRate()).append(CSV_SEPARATOR)
                            .append(results.getProductAverageStar()).append(CSV_SEPARATOR)
                            .append(results.getItemEvalTotalNum()).append(CSV_SEPARATOR)
                            .append(results.getIcon()).append(CSV_SEPARATOR).append(System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }
    }
}


