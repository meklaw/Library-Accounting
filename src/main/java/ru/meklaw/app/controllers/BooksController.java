package ru.meklaw.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.meklaw.app.dao.BookDAO;
import ru.meklaw.app.dao.PersonDAO;
import ru.meklaw.app.models.Book;
import ru.meklaw.app.models.Person;
import ru.meklaw.app.util.BookValidator;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    private final BookValidator bookValidator;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO, BookValidator bookValidator) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
        this.bookValidator = bookValidator;
    }


    @GetMapping()
    public String index(Model model) {
        List<Book> allBooks = bookDAO.index();
        model.addAttribute("freeBooks", allBooks.stream()
                .filter(book -> book.getPersonId() == 0).collect(Collectors.toList()));
        model.addAttribute("occupiedBooks", allBooks.stream()
                .filter(book -> book.getPersonId() != 0).collect(Collectors.toList()));
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,
                       Model model,
                       @ModelAttribute("person") Person person) {
        Optional<Book> showBook = bookDAO.show(id);
        if (showBook.isEmpty())
            return "books/show_error";

        model.addAttribute("book", showBook.get());
        model.addAttribute("people", personDAO.index());

        Optional<Person> bookOwner = personDAO.show(showBook.get().getPersonId());
        if (bookOwner.isPresent())
            model.addAttribute("bookOwnerName", bookOwner.get().getFullName());
        else
            model.addAttribute("bookOwnerName", "Отсутствует");

        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/new";

        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.show(id).orElse(new Book()));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/edit";

        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@ModelAttribute("person") Person person,
                         @PathVariable("id") int bookId) {
        bookDAO.assign(bookId, person.getId());
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int bookId) {
        bookDAO.release(bookId);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
