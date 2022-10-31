package ru.meklaw.app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.meklaw.app.models.Person;
import ru.meklaw.app.service.PeopleService;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person newPersonData = (Person) target;
        Optional<Person> oldPersonData = peopleService.findOne(newPersonData.getId());
        if (oldPersonData.isPresent() && oldPersonData.get().getFullName().equals(newPersonData.getFullName()))
            return;
        if (peopleService.findOne(newPersonData.getFullName()).isPresent())
            errors.rejectValue("fullName", "", "Это ФИО занято");
    }
}
