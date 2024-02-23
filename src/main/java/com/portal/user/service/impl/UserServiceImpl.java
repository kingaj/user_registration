package com.portal.user.service.impl;

import com.portal.user.entity.User;
import com.portal.user.model.UserModel;
import com.portal.user.repository.UserRepository;
import com.portal.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User userSignUp(UserModel userModel) {

        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setPassword(userModel.getPassword());

        userRepository.save(user);

        return user;
    }

    @Override
    public String userSignIn(String email,String password) {

        Optional<User> user = userRepository.findByEmailAndPassword(email,password);

        if(user.isPresent())
        {
            return "User Login Successfully";
        }

        return "User is Unauthorised";
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByEmail(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserModel.build(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));

        return UserModel.build(user);
    }
}
