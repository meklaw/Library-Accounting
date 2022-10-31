package ru.meklaw.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.meklaw.app.models.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
}
