package io.fusionauth.config.security.user;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile implements UserDetails {

  private UUID userId;

  private String username;

  private String password;

  private List<Role> roles;

  private boolean isAccountNonExpired;

  private boolean isAccountNonLocked;

  private boolean isCredentialsNonExpired;

  private boolean isEnabled;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return isAccountNonExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return isAccountNonLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return isCredentialsNonExpired;
  }

  @Override
  public boolean isEnabled() {
    return isEnabled;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public void setUuid(UUID userId) {
    this.userId = userId;
  }
}
