package tr.com.obss.bookportal.bookportal1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.obss.bookportal.bookportal1.model.Role;
@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findByName(String name);

}

