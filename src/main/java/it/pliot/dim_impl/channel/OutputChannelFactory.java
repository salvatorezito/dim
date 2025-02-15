package it.pliot.dim_impl.channel;

import it.pliot.dim_impl.channel.output.HttpOutputChannel;
import it.pliot.dim_impl.channel.output.OutputChannel;
import it.pliot.dim_impl.data.MeasOutChannel;
import it.pliot.dim_impl.repository.MeasOutChannelRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ExecutorService;

@Component
public class OutputChannelFactory {

    private static final Logger logger = LoggerFactory.getLogger(OutputChannelFactory.class);


    @Autowired
    MeasOutChannelRepository repo;

    @Autowired
    ApplicationContext context;

    @Autowired
    ExecutorService executorService;


    private Map<String,OutputChannel> IN_MEMORY_CHANNELS = new HashMap<String,OutputChannel>();

    private void initChannel(MeasOutChannel x) {
        Class jobTypeClass = null;

        if ( IN_MEMORY_CHANNELS.containsKey( x.getId() ) ) {
            logger.error("Key " + x.getId() + " for clazz " + x.getClazz() + " already present ");
            return;
        }
         try {
            jobTypeClass = Class.forName( x.getClazz() );
            OutputChannel jobType = ( OutputChannel ) jobTypeClass.getDeclaredConstructor().newInstance();
            jobType.init( context , x , executorService );
            jobType.startConsumer();
            IN_MEMORY_CHANNELS.put( x.getId() , jobType );
            return;
        } catch (Exception e) {
            logger.error( e.getMessage() );
        }

    }

    @Transactional
    @PostConstruct
    public void loadChannels(){
       List<MeasOutChannel> outChannel =  repo.findAll();
       Iterator<MeasOutChannel> itera = outChannel.stream().iterator();
       while( itera.hasNext() ) {
           initChannel(itera.next());
       }

    }

    public Collection<OutputChannel> outChannels(){
        return IN_MEMORY_CHANNELS.values();
    }

    public OutputChannel getChannel( String id ){
        return IN_MEMORY_CHANNELS.get( id );
    }
}
