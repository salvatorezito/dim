package it.pliot.dim_impl.conf;

import it.pliot.dim_impl.data.UserCredentials;
import it.pliot.dim_impl.repository.IotAdapterRepository;
import it.pliot.dim_impl.repository.MeasOutChannelRepository;
import it.pliot.dim_impl.repository.UserCredentialRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class InitDim {

    private static final Logger log = LoggerFactory.getLogger(InitDim.class);

    @Autowired
    IotAdapterRepository adapterRepository;

    @Autowired
    MeasOutChannelRepository aeasOutChannelRepository;

    @Autowired
    UserDetailsService userDetailService ;

    @Autowired
    UserCredentialRepository userCredentialRepository;


    @PostConstruct
    public void initDb( ) {
        log.info( " init adapters ");
        Instances.initialAdapters().forEach( x ->
            adapterRepository.save( x )
        );
        log.info( " init MeasOutChannel ");
        Instances.initMeasOutChannel().forEach( x ->
                aeasOutChannelRepository.save( x )
        );

        UserCredentials c = Instances.createAdmin();
        try {
            UserDetails details = userDetailService.loadUserByUsername(c.getUsername());
        }catch ( UsernameNotFoundException e ){
            log.info( " init admin ");
            userCredentialRepository.save( c );

        }


    }


}
