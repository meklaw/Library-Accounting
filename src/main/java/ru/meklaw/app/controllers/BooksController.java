package ru.meklaw.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.meklaw.app.models.Book;
import ru.meklaw.app.models.Person;
import ru.meklaw.app.service.BooksService;
import ru.meklaw.app.service.PeopleService;
import ru.meklaw.app.util.BookValidator;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final PeopleService peopleService;
    private final BookValidator bookValidator;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService, BookValidator bookValidator) {
        this.booksService = booksService;
        this.peopleService = peopleService;
        this.bookValidator = bookValidator;
    }


    @GetMapping()
    public String index(Model model) {
        List<Book> allBooks = booksService.findAll();
        model.addAttribute("freeBooks", allBooks.stream()
                .filter(book -> book.getOwner() == null).collect(Collectors.toList()));
        model.addAttribute("occupiedBooks", allBooks.stream()
                .filter(book -> book.getOwner() != null).collect(Collectors.toList()));
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,
                       Model model,
                       @ModelAttribute("person") Person person) {
        Optional<Book> showBook = booksService.findOne(id);
        if (showBook.isEmpty())
            return "books/show_error";

        model.addAttribute("book", showBook.get());

        Person bookOwner = showBook.get().getOwner();

        if (bookOwner != null)
            model.addAttribute("owner", bookOwner);
        else
            model.addAttribute("people", peopleService.findAll());

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

        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findOne(id).orElse(new Book()));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/edit";

        booksService.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@ModelAttribute("person") Person person,
                         @PathVariable("id") int bookId) {
        booksService.assign(bookId, person);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int bookId) {
        booksService.release(bookId);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }
}
