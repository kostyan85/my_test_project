package ru.kostyan_85.webBookParser.service;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kostyan_85.webBookParser.DTO.MinimalBookDTO;
import ru.kostyan_85.webBookParser.Entity.Book;
import ru.kostyan_85.webBookParser.Parser;
import ru.kostyan_85.webBookParser.repository.BookRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookSevice {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private Parser parser;

    /**
     * превращаем сущность в модель
     */

    public MinimalBookDTO entity2model(Book entity) {
        MinimalBookDTO model = new MinimalBookDTO();
        model.setId(entity.getId());
        model.setBookName(entity.getBookName());
        model.setAutor(entity.getAutor());
        model.setBookUrl(entity.getBookUrl());
        return model;
    }

    /**
     * Получить все книги из таблицы
     *
     * @return коллеция книг
     **/
    @Override
    public List<MinimalBookDTO> getAllBook() {
        List<Book> bookList = bookRepository.findAll();
        List<MinimalBookDTO> dtoBookList = new ArrayList<>();
        for (Book book : bookList) {
            MinimalBookDTO minimalBookDTO = entity2model(book);
            dtoBookList.add(minimalBookDTO);
        }
        return dtoBookList;
    }

    /**
     * Сохраняем книгу в базу
     *
     * @return статус сохранения в базу
     */
//    @Override
//    public Boolean saveBook(String bookName, String autor, String bookText, String bookUrl) {
//        Book book = new Book(bookName, autor, bookText, bookUrl);
//        Book savedBook = bookRepository.save(book);
//        if (savedBook.getId() != null) {
//            return true;
//        }
//        return null;
//    }
    @Override
    public Boolean saveBook(String bookUrl) {
        Book book = new Book(parser.getBookName(bookUrl), parser.getAutorName(bookUrl),/*parser.parse(bookUrl),*/ bookUrl);
        Book savedBook = bookRepository.save(book);
        if (savedBook.getId() != null) {
            return true;
        }
        return null;
    }

    @Override
    public void downLoadBook( String bookUrl,HttpServletResponse response) {
        String fileName = parser.getBookNameAndAutor(bookUrl);
        File fileToDownload = parser.parse(bookUrl);

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(fileToDownload);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        response.setContentType("text/txt");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName+".txt");
        try {
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
