package tr.com.obss.bookportal.bookportal1.Service;

import tr.com.obss.bookportal.bookportal1.dto.UserDto;
import tr.com.obss.bookportal.bookportal1.model.Role;
import tr.com.obss.bookportal.bookportal1.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    Role saveRole(Role role);

    void addAdminRoleToUser(Long id);

    User getUser(String username);

    List<User> getUsers();

    User findUserById(Long id);

    UserDto findByName(String name);

    User updateUserByUsername(Long id, User user);

    Boolean deleteUserById(Long id);


}
