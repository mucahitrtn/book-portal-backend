package tr.com.obss.bookportal.bookportal1.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.com.obss.bookportal.bookportal1.model.Book;
import java.util.List;
import java.util.Optional;
@Repository
public interface BookRepo extends CrudRepository<Book, Long> {

    List<Book> findByName(String name);

    Optional<Book> findById(Long id);

    List<Book> findByNameLike(String input);

    @Override
    void deleteById(Long aLong);
}