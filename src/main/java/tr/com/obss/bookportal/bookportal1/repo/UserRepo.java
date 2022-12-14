package tr.com.obss.bookportal.bookportal1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.obss.bookportal.bookportal1.model.User;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);

    Optional<User> findById(Long id);

    User findByName(String name);

}
