package org.example.sniptrack.service;

import jakarta.transaction.Transactional;
import org.example.sniptrack.models.User;
import org.example.sniptrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional()
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //this is actually going to load the user from our database
        User user= userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User Not Found with username: " + username));
        //and going to convert it to object type that spring security understands
        return UserDetailsImpl.build(user) ;
    }
}
//the whole is that spring sec works well with our application along with JWT authentication and our custom user model
