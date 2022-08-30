package tr.com.obss.bookportal.bookportal1.Service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.obss.bookportal.bookportal1.Service.UserService;
import tr.com.obss.bookportal.bookportal1.dto.UserDto;
import tr.com.obss.bookportal.bookportal1.filter.exception.UserNotFound;
import tr.com.obss.bookportal.bookportal1.model.FavouriteList;
import tr.com.obss.bookportal.bookportal1.model.ReadList;
import tr.com.obss.bookportal.bookportal1.model.Role;
import tr.com.obss.bookportal.bookportal1.model.User;
import tr.com.obss.bookportal.bookportal1.repo.RoleRepo;
import tr.com.obss.bookportal.bookportal1.repo.UserRepo;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        if(user == null || user.getUsername().length()<2 ){
            log.info("User cant be null or username is too small.");
            return null;
        }
        log.info("Save User");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        FavouriteList favouriteList = new FavouriteList();
        ReadList readList = new ReadList();
        readList.setName("MyReadList_"+user.getName());
        user.setReadList(readList);
        favouriteList.setName("MyFavouriteList_"+user.getName());
        Role role = roleRepo.findByName("ROLE_USER");
        user.getRoles().add(role);
        user.setFavouriteList(favouriteList);
        return userRepo.save(user);
    }


    @Override
    public Role saveRole(Role role) {
        log.info("Save Role");
        return roleRepo.save(role);
    }

    @Override
    public void addAdminRoleToUser(Long id) {
        log.info("Add Role To User");
        User user = userRepo.findById(id).get();
        Role role = roleRepo.findByName("ROLE_ADMIN");
        user.getRoles().add(role);
        userRepo.save(user);
    }

    @Override
    public User getUser(String username) {
        if(username==null){
            log.info("Username can not be null.");
            throw new UserNotFound("User not found");
        }
        User user = userRepo.findByUsername(username);
        if(user == null){
            throw new UserNotFound();
        }
        return user;
    }

    @Override
    public List<User> getUsers() {
        log.info("Get Users");
        return userRepo.findAll();
    }
    //return userRepository.findUserByUsername(username).orElseThrow(()-> new UserNotFound());
    @Override
    public User findUserById(Long id) {
        Optional <User> user = userRepo.findById(id);
        log.info("user not found");
        if(user.isEmpty()) {
            log.info("user not found");
            throw new UserNotFound("User Not Found");
        }

        return user.get();
    }

    @Override
    public UserDto findByName(String name) {
        final User user = userRepo.findByName(name);
        if(user==null){
            log.info("User not found");
            return null;
        }
        return UserDto.builder().id(user.getId()).name(user.getName()).age(user.getAge()).build();
    }

    @Override
    public User updateUserByUsername(Long id, User user) {
        User userToBeUpdated  = userRepo.findById(id).get();
        userToBeUpdated.setName(user.getName());
        userToBeUpdated.setAge(user.getAge());
        userToBeUpdated.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(userToBeUpdated);
        return userToBeUpdated;
    }

    @Override
    public Boolean deleteUserById(Long id) {
        User user  = userRepo.findById(id).get();
        Long userId = user.getId();
        if(userId==null){
            log.info("User not found");
            return Boolean.FALSE;
        }
        userRepo.deleteById(userId);
        return Boolean.TRUE;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null){
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        else{
            log.info("User found", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {authorities.add(new SimpleGrantedAuthority(role.getName()));});
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
