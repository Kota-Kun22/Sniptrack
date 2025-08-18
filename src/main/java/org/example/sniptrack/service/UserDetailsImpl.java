package org.example.sniptrack.service;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.sniptrack.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String password;
    private String email;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Collection<? extends GrantedAuthority> authorities, Long id, String username, String password, String email) {
        this.authorities = authorities;
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * the purpose of this is to convert a user object
     * from our database to a user object into a userdetails implementation object for spring security
     * like we are defining the user in our way but spring security will not
     *  understand that so spring security needs
     *  a object of GrantedAuthority that understands what roles does that user has
     * */
    public static UserDetailsImpl build(User user){
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        return new UserDetailsImpl(
                Collections.singletonList(authority),
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
        );
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

}
