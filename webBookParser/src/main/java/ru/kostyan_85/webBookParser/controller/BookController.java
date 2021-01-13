package ru.kostyan_85.webBookParser.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import ru.kostyan_85.webBookParser.DTO.MinimalBookDTO;

import ru.kostyan_85.webBookParser.Parser;
import ru.kostyan_85.webBookParser.service.BookSevice;
import org.springframework.web.bind.annotation.PostMapping;


import javax.servlet.http.HttpServletResponse;

import java.util.List;

@Controller
public class BookController {
    @Value("${spring.datasource.url}")
    String dataSource;

    @Autowired
    private BookSevice bookSevice;

    @Autowired
    private Parser parser;

    @GetMapping("/mainPage")
    public String getMainPage() {
        return "mainPage";
    }

    @GetMapping("/bookList")
    public String getAllBook(Model model) {
        List<MinimalBookDTO> allBooks = bookSevice.getAllBook();
        model.addAttribute("bookList", allBooks);
        model.addAttribute("dataSource", dataSource);// из файла
        return "/bookList";
    }

    @PostMapping("/addBook")
    public String addBook(@RequestParam String bookUrl) {

        Boolean isSuccess = bookSevice.saveBook(bookUrl);
        System.out.println("saveBook " + isSuccess);
        return "redirect:/bookList";
    }




    @GetMapping("/download")
    public void downLoadBook(@RequestParam String bookUrl,HttpServletResponse response) {
        bookSevice.downLoadBook(bookUrl,response);

    }
}
