package ru.meklaw.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.meklaw.app.dao.BookDAO;
import ru.meklaw.app.dao.PersonDAO;
import ru.meklaw.app.models.Book;
import ru.meklaw.app.models.Person;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Optional<Book> showBook = bookDAO.show(id);
        if (showBook.isEmpty())
            return "books/show_error";

        model.addAttribute("book", showBook.get());

        Optional<Person> bookOwner = personDAO.show(showBook.get().getPersonId());
        if (bookOwner.isPresent())
            model.addAttribute("bookOwnerName", bookOwner.get().getFullName());
        else
            model.addAttribute("bookOwnerName", "Отсутствует");

        return "books/show";
    }
}
