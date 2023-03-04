package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Model.User;
import com.example.demo.Repo.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    
    public void registerNewUserAccount(User user) throws UserAlreadyExistException {
        if (UserExists(user.getUsername())) {
            throw new UserAlreadyExistException("There is an account with that user name");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        // the rest of the registration operation
    }
    private boolean UserExists(String username) {
        return userRepository.FindByUserName(username) != null;
    }
}
