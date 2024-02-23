package com.portal.user.service;

import com.portal.user.entity.User;
import com.portal.user.model.UserModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService
{
    User userSignUp(UserModel userModel);

    String userSignIn(String email,String password);

    boolean existsByEmail(String email);

    UserDetails loadUserByEmail(String email);
}
