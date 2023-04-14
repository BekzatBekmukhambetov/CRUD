package kz.beka.start.controllers;

import kz.beka.start.models.Book;
import kz.beka.start.models.Person;
import kz.beka.start.services.BookService;
import kz.beka.start.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final PeopleService peopleService;

    @Autowired

    public BookController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }


    @GetMapping()
    public String index(Model model , @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "books_per_page", required = false) Integer booksPerPage,
                        @RequestParam(value = "year", required = false) boolean year ) {
        if(page == null || booksPerPage == null)
        model.addAttribute("books",bookService.findAll(year));
        else
            model.addAttribute("books", bookService.findWithPagination(page, booksPerPage, year));
        return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person){
        model.addAttribute("book",bookService.findOne(id));
        Person owner= bookService.bookOwner(id);

        if(owner != null)
            model.addAttribute("owner",owner);
        else
            model.addAttribute("people", peopleService.findAll());
        return "book/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book")Book book){
        return "book/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "book/new";
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id ){
        model.addAttribute("book", bookService.findOne(id));
        return "book/edit";
    }


    @PatchMapping ("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult , @PathVariable("id") int id) {
        if(bindingResult.hasErrors())
            return "book/edit";
        bookService.update(id,book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public  String delete(@PathVariable("id") int id){
        bookService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/add")
    public String add(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson){
        bookService.addOwner(id,selectedPerson);
        return "redirect:/books/"+id;
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        bookService.deleteOwner(id);
        return "redirect:/books/"+id;
    }

    @GetMapping("/search")
    public String searchBook(){

        return "book/search";
    }

    @PostMapping("/search")
    public String nameBook(Model model, @RequestParam("book_name") String book_name){
    model.addAttribute("books", bookService.searchByTitle(book_name));
        return "book/search";
    }


}
