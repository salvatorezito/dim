package it.pliot.dim_impl.job.task;

import it.pliot.dim_impl.channel.MeasureMsg;
import it.pliot.dim_impl.channel.output.OutputChannel;
import it.pliot.dim_impl.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class MockDavice implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(MockDavice.class);

    private OutputChannel output;
    private String idEquipment;
    private String idSensor;

    public MockDavice( OutputChannel output  , String idEquipment , String idSensor ){
        this.output = output;
        this.idEquipment = idEquipment;
        this.idSensor = idSensor;
    }


    @Override
    public void run() {
        log.info(" new measure ");
        MeasureMsg msg = new MeasureMsg( idEquipment , idSensor , "4" , new Date() );
        output.produce( msg );
    }
}
