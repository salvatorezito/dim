package it.pliot.dim_impl.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
public class UserCredentials implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String userName;

    private String password;

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    private boolean enabled = true;

    private boolean locked  = false;

    private boolean expired = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private static GrantedAuthority ADMIN = new GrantedAuthority() {
        @Override
        public String getAuthority() {
            return "ADMIN";
        }
    };

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(ADMIN);
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return ! expired;
    }


    @Override
    public boolean isAccountNonLocked() {
        return ! locked;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return ! expired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
