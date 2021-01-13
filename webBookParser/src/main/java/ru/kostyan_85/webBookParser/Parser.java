package ru.kostyan_85.webBookParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class Parser {
    public static final String BASE_URL_VIEW = "https://ka4ka.ru/lib/index.php?mod=view_book&bid=";
    public static final String BASE_URL_READ = "https://ka4ka.ru/lib/index.php?mod=read_book&bid=";


//    public String parse(String url) throws IOException {
//        int countPage = countPage(url);
//        String fileName = getBookNameAndAutor(url);
//        String result = "";
//
//        FileWriter writer = new FileWriter(fileName + ".txt", false);
//        for (int pageIndex = 1; pageIndex < countPage + 1; pageIndex++) {
//            String resultUrl = url.substring(0, url.length() - 1);
////            String url = BASE_URL_READ + id + "&sym=8000&page=" + pageIndex;
//            String totalUrl = resultUrl + pageIndex;
//            String htmlSTR = htmlToString(totalUrl);
//            String htmlSTRWithOutBR = htmlSTR.replaceAll("<br>", "///");
//            Element element = Jsoup.parse(htmlSTRWithOutBR);
//            String textWithOutHTMLTags = element.getElementById("l-popular").text();
//            String strWithLineBreak = textWithOutHTMLTags.replaceAll("///", "\n");
//            result = strWithLineBreak.substring(0, strWithLineBreak.indexOf("... 1|"));
//            writer.write(result);
//            System.out.println(result);
//        }
//        writer.close();
//        return result;
//    }
    public File parse(String url)  {
        int countPage = countPage(url);
        String fileName = getBookNameAndAutor(url);
        String result = "";
        File file = new File(fileName + ".txt");
        FileWriter writer = null;
        try {
            writer = new FileWriter(file, false);
            for (int pageIndex = 1; pageIndex < countPage + 1; pageIndex++) {
                String resultUrl = url.substring(0, url.length() - 1);
//            String url = BASE_URL_READ + id + "&sym=8000&page=" + pageIndex;
                String totalUrl = resultUrl + pageIndex;
                String htmlSTR = htmlToString(totalUrl);
                String htmlSTRWithOutBR = htmlSTR.replaceAll("<br>", "///");
                Element element = Jsoup.parse(htmlSTRWithOutBR);
                String textWithOutHTMLTags = element.getElementById("l-popular").text();
                String strWithLineBreak = textWithOutHTMLTags.replaceAll("///", "\n");
                result = strWithLineBreak.substring(0, strWithLineBreak.indexOf("... 1|"));
                writer.write(result);
                System.out.println(result);
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

//    public void bufferReader(String text) throws IOException {
//        String line = null;
//        StringReader stringReader = new StringReader(text);
//
//        BufferedReader reader = new BufferedReader(stringReader);
//        BufferedWriter writer = new BufferedWriter(new FileWriter("String.valueOf(reader)"));
//        while (reader.readLine() != null) {
////            reader.readLine();
//            writer.write(reader.readLine());
//
//            System.out.println(reader);
//            System.out.println(writer);
//        }
//        reader.close();
//        writer.close();
//    }

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
     * меняем в URL read на view
     *
     * @param url - url книги
     * @return возвращает строку c содержанием названия автора и книги
     */
    public String getBookNameAndAutor(String url) {
        String titleUrl = url.replaceAll("read", "view");
//        String titleUrl = BASE_URL_VIEW + id;

        String htmlToStr = htmlToString(titleUrl);
        String strWithOutBR = htmlToStr.substring(0, htmlToStr.indexOf("<br>"));
        return Jsoup.parse(strWithOutBR).text();

    }

    /**
     * метод получения из строки отдельно названия книги
     */
    public String getBookName(String url) {
        String bookName = getBookNameAndAutor(url);
        String resultBookName = bookName.replaceAll("автор", "*");
        String result = resultBookName.substring(0, resultBookName.indexOf("(*"));
        System.out.println(result);
        return result;
    }

    /**
     * метод получения из строки отдельно имя автора
     */
    public String getAutorName(String url) {
        String bookName = getBookNameAndAutor(url);
        int index = bookName.lastIndexOf("(");
        String resultAutorName = bookName.substring(index);
        String result = resultAutorName.replaceAll("автор", "*");
        String res = result.replace("(", "*");
        String res1 = res.replace(")", "*");
        String autorName = res1.replace("*", "");
        System.out.println(autorName);
        return autorName;
    }

//    /** метод получения ID с консоли
//     *
//     * @return введенное значение
//     */
//    public long scanId() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Введите ID");
////        return Integer.parseInt(scanner.nextLine());
//        long id = scanner.nextInt();
//        return id;
//    }

    /**
     * метод получения количества страниц в книге
     *
     * @param url - url книги
     * @return количество траниц
     */
    public int countPage(String url) {
        String htmlToString = htmlToString(url);
//        String htmlToString = htmlToString("https://ka4ka.ru/lib/index.php?mod=read_book&bid=245655&sym=2000&page=1");
//        System.out.println(htmlToString);
        Element element = Jsoup.parse(htmlToString);
        String strFromTagA = element.getElementsByTag("a").text();
        String strNoCharInFront = strFromTagA.replaceAll("... 1 2 3 ", "");
        String resultStr = strNoCharInFront.substring(0, strNoCharInFront.indexOf(" "));
        int countPage = Integer.parseInt(resultStr);
        System.out.println("Количество страниц " + countPage);
        return countPage;
    }

    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        parser.parse("https://ka4ka.ru/lib/index.php?mod=read_book&bid=182030&sym=2000&page=1");
//        parser.getAutorName("https://ka4ka.ru/lib/index.php?mod=read_book&bid=161718&sym=2000&page=1");
//        parser.getBookName("https://ka4ka.ru/lib/index.php?mod=read_book&bid=161718&sym=2000&page=1");
//        parser.bufferReader(parser.parse("https://ka4ka.ru/lib/index.php?mod=read_book&bid=182030&sym=2000&page=1"));
    }
}
