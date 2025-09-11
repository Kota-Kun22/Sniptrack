package org.example.sniptrack.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.sniptrack.dtos.LoginRequest;
import org.example.sniptrack.models.User;
import org.example.sniptrack.repository.UserRepository;


import org.example.sniptrack.security.jwt.JwtAuthenticationResponse;
import org.example.sniptrack.security.jwt.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private  JwtUtils jwtUtils;
    private  UserRepository userRepository;

    private PasswordEncoder passwordEncoder;


    private  AuthenticationManager authenticationManager;

    public User registerUser(User user){
        System.out.println("Encoding password for: " + user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public JwtAuthenticationResponse authenticateUser(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetails);
        return new JwtAuthenticationResponse(jwt);
    }

    public User findByUserName(String name) {
         User user = userRepository.findByUsername(name).orElseThrow(
                 () -> new UsernameNotFoundException("User not Found with the name"+name)
         );
         return user;
    }
}
