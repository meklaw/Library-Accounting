package ru.meklaw.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.meklaw.app.repositories.BooksRepository;

public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }
}
