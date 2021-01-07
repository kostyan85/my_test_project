package ru.test.testAlfaBank;

import org.junit.jupiter.api.Test;

import ru.test.testAlfaBank.service.CurrencyService;


class TestAlfaBankApplicationTests {

    @Test
    void contextLoads() {
//
        CurrencyService currencyService = new CurrencyService();
        currencyService.getYesterdayDate();
        System.out.println();
//        CurrencyBuilder target = Feign.builder()
//
//                .client(new OkHttpClient())
//                .encoder(new GsonEncoder())
//                .decoder(new GsonDecoder())
//                .target(CurrencyBuilder.class, "https://api.giphy.com/v1/gifs/random?api_key=cUYFpUjEG2GR7jDNA8DR2L4kMmu7QxCM&tags=broke");
////
//        gifBuilder.setUrl("https://api.giphy.com/v1/gifs/random?api_key=cUYFpUjEG2GR7jDNA8DR2L4kMmu7QxCM&tags=broke");
//        Object gifJson = target.findGif();

        System.out.println();

    }

}

