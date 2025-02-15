package it.pliot.dim_impl.conf;

import it.pliot.dim_impl.repository.IotAdapterRepository;
import it.pliot.dim_impl.repository.MeasOutChannelRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitDim {

    private static final Logger log = LoggerFactory.getLogger(InitDim.class);

    @Autowired
    IotAdapterRepository adapterRepository;

    @Autowired
    MeasOutChannelRepository aeasOutChannelRepository;


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



    }


}
