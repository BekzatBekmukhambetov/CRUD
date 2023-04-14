package kz.beka.start.repositories;

import kz.beka.start.models.Book;
import kz.beka.start.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PeopleRepository extends JpaRepository<Person,Integer> {

    List<Book> findAllById(int id);
    Optional<Person> findByName(String name);
}
