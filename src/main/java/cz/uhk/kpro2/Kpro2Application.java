package cz.uhk.kpro2;

import cz.uhk.kpro2.model.User;
import cz.uhk.kpro2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Kpro2Application {

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public Kpro2Application(UserService userService, PasswordEncoder passwordEncoder){
        this.userService =userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            addUser("admin", "test", "ADMIN");
            addUser("user", "test", "USER");
        };
    }

    private void addUser(String username, String password, String role) {
        if (userService.findByUsername(username) == null) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(role);
            userService.createUser(user);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Kpro2Application.class, args);
    }

}
