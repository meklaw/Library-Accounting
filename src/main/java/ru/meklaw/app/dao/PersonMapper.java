package ru.meklaw.app.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.meklaw.app.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();

        person.setId(rs.getInt("id"));
        person.setName(rs.getString("name"));
        person.setDateOfBirthday(rs.getDate("setDateOfBirthday"));

        return person;
    }
}
