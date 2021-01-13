package ru.kostyan_85.webBookParser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kostyan_85.webBookParser.Entity.Book;

public interface BookRepository extends JpaRepository<Book,Long> {
}
