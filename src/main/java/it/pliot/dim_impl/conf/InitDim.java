package it.pliot.dim_impl.conf;

import it.pliot.dim_impl.data.IotAdapter;
import it.pliot.dim_impl.repository.IotAdapterRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitDim {

    private static final Logger log = LoggerFactory.getLogger(InitDim.class);

    @Autowired
    IotAdapterRepository adapterRepository;


    @PostConstruct
    public void initDb( ) {
        log.info( " init application ");
        Instances.initialAdapters().forEach( x -> {
            adapterRepository.save( x );
        });



    }


}
