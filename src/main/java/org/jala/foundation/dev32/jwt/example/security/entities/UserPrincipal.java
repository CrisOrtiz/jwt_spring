package org.jala.foundation.dev32.jwt.example.security.entities;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import org.jala.foundation.dev32.jwt.example.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
public class UserPrincipal implements UserDetails {

  private String name;
  private String userName;
  private String email;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  public UserPrincipal(String name, String useName, String email, String password,
      Collection<? extends GrantedAuthority> authorities) {
    this.name = name;
    this.userName = useName;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
  }

  public static UserPrincipal build(User user) {
    List<GrantedAuthority> authorities =
        user.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
            .getRoleName().name())).collect(Collectors.toList());
    return new UserPrincipal(user.getName(), user.getUserName(), user.getEmail(),
        user.getPassword(), authorities);
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
    return userName;
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
}
