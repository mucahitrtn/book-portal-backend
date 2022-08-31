package tr.com.obss.bookportal.bookportal1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import tr.com.obss.bookportal.bookportal1.Service.UserService;
import tr.com.obss.bookportal.bookportal1.model.FavouriteList;
import tr.com.obss.bookportal.bookportal1.model.Role;
import tr.com.obss.bookportal.bookportal1.model.User;

import java.util.ArrayList;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
/*
    @Bean
    CommandLineRunner run(UserService userService){

        return args -> {
            userService.saveRole(new Role(null,"ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));

            userService.saveUser(new User(null, "Mucahit", "mucoadmin", "123",20, new ArrayList<>(), null, null));
            userService.addAdminRoleToUser(1);

            //userService.addRoleToUser("muco", "ROLE_ADMIN");
            //userService.addRoleToUser("mucoadmin", "ROLE_USER");
            //userService.addRoleToUser("ali", "ROLE_USER");
            //userService.addRoleToUser("omer", "ROLE_USER");

        };
    }
*/
}

/*

 */
