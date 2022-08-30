package tr.com.obss.bookportal.bookportal1.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.com.obss.bookportal.bookportal1.model.Author;
import java.util.Optional;

@Repository
public interface AuthorRepo extends CrudRepository<Author, Long> {
    Optional<Author> findById(Long id);
    Optional<Author> findByName(String name);

    @Override
    void deleteById(Long id);
}