package ru.test.testAlfaBank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.test.testAlfaBank.service.CurrencyService;

@Controller
@RequestMapping("/")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;


    @GetMapping("/getGif")
    public String getURl(@RequestParam String currency) {

        return "redirect:" + currencyService.getRandomGif(currency);
    }

}
