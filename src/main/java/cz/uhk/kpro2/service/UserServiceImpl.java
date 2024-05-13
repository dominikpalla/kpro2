package cz.uhk.kpro2.service;

import cz.uhk.kpro2.model.User;
import cz.uhk.kpro2.repository.UserRepository;
import cz.uhk.kpro2.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        Optional<User> userDB = userRepository.findById(user.getId());
        if(userDB.isPresent()){
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public User getUserDetails(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User deleteUser(Long id) {
        Optional<User> userDB = userRepository.findById(id);
        if(userDB.isPresent()){
            User user = userDB.get();
            userRepository.delete(user);
            return user;
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserDetails(user);
    }

}
