package com.portal.user.model;

import com.portal.user.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
public class UserModel implements UserDetails {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    public UserModel(Long id,String email, String password,Collection<? extends GrantedAuthority> authorities) {
        this.id=id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserModel build(User user) {
        List<GrantedAuthority> authorities = Stream.of("ROLE_USER")
                .map(role -> new SimpleGrantedAuthority("ROLE_USER"))
                .collect(Collectors.toList());

        return new UserModel(user.getId(),user.getEmail(),
                user.getPassword(),authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserModel user = (UserModel) o;
        return Objects.equals(email, user.email);
    }
}
