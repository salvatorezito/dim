package it.pliot.dim_impl.job.task;

import it.pliot.dim_impl.channel.OutputChannelFactory;
import it.pliot.dim_impl.channel.output.OutputChannel;
import it.pliot.dim_impl.conf.GlobalConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceReaderFactory {

    @Autowired
    OutputChannelFactory channelFactory;

    @Autowired
    GlobalConfiguration globalConfiguration;





    public  Runnable reader( String sensorId   ){

        OutputChannel httpChannel = channelFactory.getChannel( "HTTP_EDGE_CHANNEL");
        return new MockDavice( httpChannel  ,
                            globalConfiguration.getIdEquipment() ,
                            sensorId ,
                            globalConfiguration.getTenantId() );

    }
}
