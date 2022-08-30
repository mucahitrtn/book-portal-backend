package tr.com.obss.bookportal.bookportal1.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.obss.bookportal.bookportal1.Service.UserService;
import tr.com.obss.bookportal.bookportal1.dto.UserDto;
import tr.com.obss.bookportal.bookportal1.model.Role;
import tr.com.obss.bookportal.bookportal1.model.User;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>>getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return ResponseEntity.ok().body(userService.saveUser(user));
    }
    @PostMapping("/role/save")
    public ResponseEntity<Role> saveUser(@RequestBody Role role){
        return ResponseEntity.ok().body(userService.saveRole(role));
    }

    @PostMapping("/role/addToUser/{id}")
    public ResponseEntity<?>addAdminRoleToUser(@PathVariable("id") Long id){
        userService.addAdminRoleToUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{name}")
    public UserDto findByName(@PathVariable("name") String name, @RequestParam(required=false, name = "val") String val){
        return userService.findByName(name);
    }

    @PutMapping("/user/update/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user){
        return userService.updateUserByUsername(id  , user);
    }
    @DeleteMapping("/user/{id}")
    public Boolean deleteUser(@PathVariable("id") Long id){
        Boolean b = userService.deleteUserById(id);
        return b;
    }

    @GetMapping("/user/getUser/{id}")
    public User findUserById(@PathVariable("id") Long id){
        return userService.findUserById(id);
    }

}


@Data
class RoleToUserForm{
    private String username;
    private String roleName;
}