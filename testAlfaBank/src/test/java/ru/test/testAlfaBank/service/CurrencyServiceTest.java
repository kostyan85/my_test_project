package ru.test.testAlfaBank.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.Test;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;


class CurrencyServiceTest {
    CurrencyService currencyService = new CurrencyService();
    WireMockServer wireMockServer = new WireMockServer(8080);


    @Test
    void compareCurrency() {

        CurrencyService currencyService = new CurrencyService();
        String resultBroke = currencyService.compareCurrency(1d, 2d);
        assertEquals(currencyService.BROKE, resultBroke);
        String resultRich = currencyService.compareCurrency(2d, 1d);
        assertEquals(currencyService.RICH, resultRich);

    }

    @Test
    void getCurrentCourse() {
        wireMockServer.start();
        configureFor("localhost", 8080);
        stubFor(get(urlEqualTo("/currentCourse")).willReturn(aResponse().withBody("{\n" +
                "  \"rates\": {\n" +
                "    \"USD\": 1.2,\n" +
                "    \"AFN\": 78.217564\n" +
                "  }\n" +
                "}")));
        Double course = currencyService.getCurrentCourse("USD", "http://localhost:8080/currentCourse");
        assertEquals(1.2D, course);
        assertNotEquals(5, course);
        wireMockServer.stop();
    }

    @Test
    void getYesterdayCourse() {
        wireMockServer.start();
        configureFor("localhost", 8080);
        stubFor(get(urlEqualTo("/yesterdayCourse")).willReturn(aResponse().withBody("{\n" +
                "  \"rates\": {\n" +
                "    \"USD\": 1.5,\n" +
                "    \"AFN\": 78.217564\n" +
                "  }\n" +
                "}")));
        Double course = currencyService.getCurrentCourse("USD", "http://localhost:8080/yesterdayCourse");
        assertEquals(1.5D, course);
        assertNotEquals(3, course);
        wireMockServer.stop();

    }


    @Test
    void getUrlGif() {
        wireMockServer.start();
        configureFor("localhost", 8080);
        stubFor(get(urlEqualTo("/getGif")).willReturn(aResponse().withBody("{\n" +
                "   \"data\":{\n" +
                "      \"images\":{\n" +
                "         \"original\":{\n" +
                "            \"url\":\"https://media4.giphy.com/media/KfIGknHk52IKx0Ki4W/giphy.gif?\"\n" +
                "         }\n" +
                "      }\n" +
                "   }\n" +
                "}")));

        String urlGif = currencyService.getUrlGif("http://localhost:8080/getGif");
        assertEquals("https://media4.giphy.com/media/KfIGknHk52IKx0Ki4W/giphy.gif?", urlGif);
        assertNotEquals("https://media4.giphy.com/media/KfIGknHk52IKx0Ki4W/giphy.gif", urlGif);
        wireMockServer.stop();
    }

}