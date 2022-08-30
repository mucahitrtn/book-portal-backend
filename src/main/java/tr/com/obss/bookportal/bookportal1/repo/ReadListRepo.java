package tr.com.obss.bookportal.bookportal1.repo;

import org.springframework.data.repository.CrudRepository;
import tr.com.obss.bookportal.bookportal1.model.FavouriteList;
import tr.com.obss.bookportal.bookportal1.model.ReadList;

import java.util.Optional;

public interface ReadListRepo extends CrudRepository<ReadList, Long> {

    Optional<ReadList> findById(Long id);
    void deleteById(Long id);


}
