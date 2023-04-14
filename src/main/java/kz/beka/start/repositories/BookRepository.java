package kz.beka.start.repositories;

import kz.beka.start.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
List<Book> findByTitleStartingWith(String book_name);
}
