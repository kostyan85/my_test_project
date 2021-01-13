package ru.kostyan_85.webBookParser.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookName;

    private String autor;

//    private String bookText;

    private String bookUrl;

    public Book() {
    }

    public Book( String bookName, String autor,/*String bookText,*/ String bookUrl) {

        this.bookName = bookName;
        this.autor = autor;
//        this.bookText = bookText;
        this.bookUrl = bookUrl;
    }

//    public String getBookText() {
//        return bookText;
//    }
//
//    public void setBookText(String bookText) {
//        this.bookText = bookText;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String name) {
        this.bookName = name;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }
}
