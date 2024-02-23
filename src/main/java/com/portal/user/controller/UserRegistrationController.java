package com.portal.user.controller;

import com.portal.user.entity.RefreshToken;
import com.portal.user.exception.RefreshTokenException;
import com.portal.user.model.*;
import com.portal.user.security.jwt.JwtUtils;
import com.portal.user.service.UserService;
import com.portal.user.service.impl.RefreshTokenService;
import com.portal.user.utility.ApplicationConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(ApplicationConstant.BASE_AUTH_URL)
public class UserRegistrationController
{
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping(ApplicationConstant.SIGN_UP)
    public ResponseEntity<?> userSignUp(@RequestBody UserModel userModel)
    {
        if (userService.existsByEmail(userModel.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponseModel("Error: Email is already in use!"));
        }

        userModel.setPassword(encoder.encode(userModel.getPassword()));
        userService.userSignUp(userModel);

        return ResponseEntity.ok(new MessageResponseModel("User registered successfully!"));
    }

    @PostMapping(ApplicationConstant.SIGN_IN)
    public ResponseEntity<?> userSignIn(@RequestBody UserModel userModel)
    {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userModel.getEmail(), userModel.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserModel userDetails = (UserModel) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        return ResponseEntity.ok(new JwtResponseModel(jwt, refreshToken.getToken(), userDetails.getEmail()));
    }

    @PostMapping(ApplicationConstant.REFRESH_TOKEN)
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequestModel request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromEmail(user.getEmail());
                    return ResponseEntity.ok(new RefreshTokenResponseModel(token, requestRefreshToken));
                })
                .orElseThrow(() -> new RefreshTokenException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

}
