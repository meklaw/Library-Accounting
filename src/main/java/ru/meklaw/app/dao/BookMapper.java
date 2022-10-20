package ru.meklaw.app.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.meklaw.app.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        book.setYear(rs.getInt("year"));
        book.setPersonId(rs.getInt("person_id"));
        return book;
    }
}
