package tr.com.obss.bookportal.bookportal1.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.com.obss.bookportal.bookportal1.model.FavouriteList;

import java.util.Optional;
@Repository
public interface FavouriteListRepo extends CrudRepository<FavouriteList, Long> {

    @Override
    Optional<FavouriteList> findById(Long id);
    void deleteById(Long id);

}