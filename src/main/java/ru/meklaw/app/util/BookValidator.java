package ru.meklaw.app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.meklaw.app.dao.BookDAO;
import ru.meklaw.app.models.Book;

import java.util.Optional;

@Component
public class BookValidator implements Validator {
    private final BookDAO bookDAO;

    @Autowired
    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book newBook = (Book) target;
        Optional<Book> oldBookData = bookDAO.show(newBook.getId());
        if (oldBookData.isPresent() && oldBookData.get().getName().equals(newBook.getName()))
            return;
        if (bookDAO.show(newBook.getName()).isPresent())
            errors.rejectValue("age", "", "Это название занято");
    }
}
