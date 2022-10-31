package ru.meklaw.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.meklaw.app.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
