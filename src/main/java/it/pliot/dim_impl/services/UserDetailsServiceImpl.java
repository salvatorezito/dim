package it.pliot.dim_impl.services;

import it.pliot.dim_impl.data.UserCredentials;
import it.pliot.dim_impl.repository.UserCredentialRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Transactional
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserCredentialRepository userCredentialRepository;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserCredentials uc = new UserCredentials();
        uc.setUserName( username );
        Example<UserCredentials> example = Example.of( uc );
        Optional<UserCredentials> element = userCredentialRepository.findOne(  example );
        if ( element.isEmpty()  )
            throw new UsernameNotFoundException( "user " + username + " not found ");
        return element.get();

    }
}
