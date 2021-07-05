import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class MainClass {
    static TreeSet<String> finalSet = new TreeSet<>();
    static int count = 0;


    public static TreeSet<String> parser() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = f.newDocumentBuilder();
        Document doc = builder.parse(new File("cars.xml"));
        NodeList nodes = doc.getElementsByTagName("modification");
        TreeSet<String> set = new TreeSet<String>();

        for (int i = 0; i < nodes.getLength(); i++) {
            String s = String.valueOf(nodes.item(i).getAttributes().getNamedItem("name"));
            s = s.replace("name=", "");
            s = s.replace("\"", "");
            set.add(s);
        }
        return set;
    }
    public static void separatingParts() throws IOException, SAXException, ParserConfigurationException {
        TreeSet<String> firstCutSet = new TreeSet<>();
        TreeSet<String> secondCutSet = new TreeSet<>();
        TreeSet<String> fourthCutSet = new TreeSet<>();
        TreeSet<String> fifthCutSet = new TreeSet<>();

        String mainStr;
        String firstCut;
        String secondCut;
        String fourthCut;
        String fifthCut;
        String remainderOfSecondCut;
        String remainderOfThirdCut;
        String remainderOfFourthCut;

        TreeSet<String> setString = parser();

        for (String str : setString) {
            boolean firstCutFlag = false;
            boolean secondCutFlag = false;
            boolean fourthCutFlag = false;
            boolean fifthCutFlag = false;


            mainStr = str.substring(0, str.indexOf(")") + 1);
            fifthCut = str.substring(str.indexOf(")") + 1).trim();
            fourthCut = mainStr.substring(str.indexOf(" (")).trim();

            remainderOfFourthCut = mainStr.substring(0, str.indexOf(" ("));
            remainderOfThirdCut = remainderOfFourthCut.substring(0, remainderOfFourthCut.lastIndexOf(" "));
            if (remainderOfThirdCut.lastIndexOf(" ") == -1) {
                secondCut = remainderOfThirdCut;
                firstCut = "";
            } else {

                secondCut = remainderOfThirdCut.substring(remainderOfThirdCut.lastIndexOf(" ")).trim();
                remainderOfSecondCut = remainderOfThirdCut.substring(0, remainderOfThirdCut.lastIndexOf(" "));
                firstCut = remainderOfSecondCut;
            }


            if (!firstCut.isEmpty() & !firstCutSet.contains(firstCut)) {
                firstCutFlag = true;
            }

            if (secondCut.isEmpty()) {
                secondCutFlag = true;
            }
            if (!secondCut.isEmpty() & !secondCutSet.contains(secondCut)) {
                secondCutFlag = true;
            }


            if (fourthCut.isEmpty()) {
                fourthCutFlag = true;
            }
            if (!fourthCut.isEmpty() & !fourthCutSet.contains(fourthCut)) {
                fourthCutFlag = true;
            }

            if (fifthCut.isEmpty()) {
                fifthCutFlag = true;
            }
            if (!fifthCut.isEmpty() & !fifthCutSet.contains(fifthCut)) {
                fifthCutFlag = true;
            }
            if (firstCutFlag & secondCutFlag & fourthCutFlag & fifthCutFlag) {
                firstCutSet.add(firstCut);
                secondCutSet.add(secondCut);
                fourthCutSet.add(fourthCut);
                fifthCutSet.add(fifthCut);
                count++;
                finalSet.add(mainStr);
            }
        }
        for (String s : finalSet) {
            System.out.println(s);
        }
        System.out.println(finalSet.size());

        System.out.println(count + " count");
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        separatingParts();

    }
}
