package ru.kostyan_85.webBookParser.DTO;

public class MinimalBookDTO {

    private long id;

    private String bookName;

    private String autor;

    private String bookUrl;

    public MinimalBookDTO() {
    }

    public MinimalBookDTO(Long id, String bookName, String autor, String bookUrl) {
        this.id = id;
        this.bookName = bookName;
        this.autor = autor;
        this.bookUrl = bookUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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
