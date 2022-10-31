package ru.meklaw.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.meklaw.app.models.Book;
import ru.meklaw.app.models.Person;
import ru.meklaw.app.repositories.BooksRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public List<Book> findAllByOwner(Person person) {
        return booksRepository.findAllByOwner(person);
    }

    public Optional<Book> findOne(int id) {
        return booksRepository.findById(id);
    }

    public Optional<Book> findOne(String name) {
        return booksRepository.findByName(name);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book book) {
        book.setId(id);
        booksRepository.save(book);
    }

    @Transactional
    public void assign(int id, Person person) throws NoSuchElementException {
        Book book = booksRepository.findById(id).orElseThrow();
        book.setOwner(person);
        update(id, book);
    }

    @Transactional
    public void release(int id) throws NoSuchElementException {
        Book book = booksRepository.findById(id).orElseThrow();
        book.setOwner(null);
        update(id, book);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }
}
