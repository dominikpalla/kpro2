package cz.uhk.kpro2.service;

import cz.uhk.kpro2.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    boolean createUser(User user);
    boolean updateUser(User User);
    User getUserDetails(Long id);
    User deleteUser(Long id);
    List<User> getAllUsers();

    User findByUsername(String username);
}
