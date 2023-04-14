package kz.beka.start.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotNull(message = "Name should not be empty")
    @Size(min=2, max=30, message = "Name should be between from 2 to 30")
    @Column(name="name")
    private String name;

    @Min(value = 1  , message = "Greater or equal 1")
    @Column(name = "date_birth")
    private int date_birth;

    @OneToMany(mappedBy = "owner")
    private List<Book>books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(int date_birth) {
        this.date_birth = date_birth;
    }

    public Person(){

    }

    public Person(int id, String name,  int date_birth) {
        this.id = id;
        this.name = name;

        this.date_birth=date_birth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
