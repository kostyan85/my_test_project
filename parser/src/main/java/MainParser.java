import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MainParser {
    public static final String BASE_URL_VIEW = "https://ka4ka.ru/lib/index.php?mod=view_book&bid=";
    public static final String BASE_URL_READ = "https://ka4ka.ru/lib/index.php?mod=read_book&bid=";

    public void parse(long id) throws IOException {
        int countPage = countPage(id);
        String fileName = getBookNameAndAutor(id);
        FileWriter writer = new FileWriter("library/"+fileName + ".txt", false);

        for (int pageIndex = 1; pageIndex < countPage + 1; pageIndex++) {
            String url = BASE_URL_READ + id + "&sym=8000&page=" + pageIndex;
            String htmlSTR = htmlToString(url);
            String htmlSTRWithOutBR = htmlSTR.replaceAll("<br>", "///");
            Element element = Jsoup.parse(htmlSTRWithOutBR);
            String textWithOutHTMLTags = element.getElementById("l-popular").text();
            String strWithLineBreak = textWithOutHTMLTags.replaceAll("///", "\n");
            String result = strWithLineBreak.substring(0, strWithLineBreak.indexOf("... 1|"));
            writer.write(result);
            System.out.println(result);


        }

        writer.close();
    }

    /**
     * JSOUP DOM To String
     *
     * @param url - url for connect
     */
    public String htmlToString(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Element baseHTML = doc.getElementById("l-popular");
            return baseHTML.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Что то пошло не туда";

        }

    }

    /**
     * метод получения названия книги и автора
     *
     * @param id - id книги
     * @return возвращает строку c содержанием названия автора и книги
     */
    private String getBookNameAndAutor(long id) {
        String titleUrl = BASE_URL_VIEW + id;
//        String titleUrl = BASE_URL_READ + id;

        String htmlToStr = htmlToString(titleUrl);
        String strWithOutBR = htmlToStr.substring(0, htmlToStr.indexOf("<br>"));
        return Jsoup.parse(strWithOutBR).text();

    }

    /** метод получения ID с консоли
     *
     * @return введенное значение
     */
    public long scanId() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ID");
//        return Integer.parseInt(scanner.nextLine());
        long id = scanner.nextInt();
        return id;
    }


    /**
     * метод получения количества страниц в книге
     *
     * @param id - id книги
     * @return количество траниц
     */
    public int countPage(long id) {
        String htmlToString = htmlToString(BASE_URL_READ + id + "&sym=8000&page=1");

        Element element = Jsoup.parse(htmlToString);
        String strFromTagA = element.getElementsByTag("a").text();
        String strNoCharInFront = strFromTagA.replaceAll("... 1 2 3 ", "");
        String resultStr = strNoCharInFront.substring(0, strNoCharInFront.indexOf(" "));
        int countPage = Integer.parseInt(resultStr);
        System.out.println("Количество страниц " + countPage);
        return countPage;
    }

    public static void main(String[] args) throws IOException {
        MainParser mainParser = new MainParser();
        // mainParser.parse(245635, mainParser.countPage());
        mainParser.parse(mainParser.scanId());
//        mainParser.countPage(245655);
        //Todo написать тесты
        //todo обработка исключений
        //todo переделать URL т.к меняется значение sum в запросе
        //todo не работает с id 151515 хотя книга с таким id есть

    }

}
