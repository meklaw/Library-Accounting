package ru.meklaw.app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.meklaw.app.dao.PersonDAO;
import ru.meklaw.app.models.Person;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person newPersonData = (Person) target;
        Optional<Person> oldPersonData = personDAO.show(newPersonData.getId());
        if (oldPersonData.isPresent() && oldPersonData.get().getFullName().equals(newPersonData.getFullName()))
            return;
        if (personDAO.show(newPersonData.getFullName()).isPresent())
            errors.rejectValue("age", "", "Это ФИО занято");
    }
}
