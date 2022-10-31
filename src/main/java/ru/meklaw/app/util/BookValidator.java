package ru.meklaw.app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.meklaw.app.models.Book;
import ru.meklaw.app.service.BooksService;

import java.util.Optional;

@Component
public class BookValidator implements Validator {
    private final BooksService booksService;

    @Autowired
    public BookValidator(BooksService booksService) {
        this.booksService = booksService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book newBook = (Book) target;
        Optional<Book> oldBookData = booksService.findOne(newBook.getId());
        if (oldBookData.isPresent() && oldBookData.get().getName().equals(newBook.getName()))
            return;
        if (booksService.findOne(newBook.getName()).isPresent())
            errors.rejectValue("name", "", "Это название занято");
    }
}
