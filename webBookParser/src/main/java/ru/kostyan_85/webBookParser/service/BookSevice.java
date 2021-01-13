package ru.kostyan_85.webBookParser.service;

import ru.kostyan_85.webBookParser.DTO.MinimalBookDTO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface BookSevice {
    List<MinimalBookDTO> getAllBook();

    Boolean saveBook(String bookUrl);

   void downLoadBook( String bookUrl,HttpServletResponse response);

}
