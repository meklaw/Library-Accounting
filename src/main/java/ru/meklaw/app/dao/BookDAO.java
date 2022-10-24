package ru.meklaw.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.meklaw.app.models.Book;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("select * from book", new BookMapper());
    }

    public Optional<Book> show(int id) {
        return jdbcTemplate.query("select * from book where id = ?", new Object[]{id}, new BookMapper())
                .stream().findAny();
    }
    public Optional<Book> show(String name) {
        return jdbcTemplate.query("select * from book where name = ?", new Object[]{name}, new BookMapper())
                .stream().findAny();
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(name, author, year) VALUES (?, ?, ?)",
                book.getName(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("update book set name = ?, author = ?, year = ? where id = ?",
                book.getName(), book.getAuthor(), book.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from person where id = ?;", id);
    }
}
