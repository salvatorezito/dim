package it.pliot.dim_impl.job.task;

import it.pliot.dim_impl.channel.MeasureMsg;
import it.pliot.dim_impl.channel.output.OutputChannel;
import it.pliot.dim_impl.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Random;

public class MockDavice implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(MockDavice.class);

    private OutputChannel output;
    private String idEquipment;
    private String idSensor;
    private String idTenant;

    private int coeffVar;

    public MockDavice( OutputChannel output  , String idEquipment , String idSensor , String idTenant ){
       this( output,idEquipment,idSensor,idTenant , 150 );
    }
    public MockDavice( OutputChannel output  , String idEquipment , String idSensor , String idTenant , int coeffVar ){
        this.output = output;
        this.idEquipment = idEquipment;
        this.idSensor = idSensor;
        this.idTenant = idTenant;
        this.coeffVar = coeffVar;
    }

    @Override
    public void run() {
        log.info(" new measure ");
        Date d = new Date();
        long time = d.getTime();
        long value = ( time / 60 ) % coeffVar;
        MeasureMsg msg = new MeasureMsg( idEquipment , idSensor , Long.toString( value ) , new Date() , idTenant , idSensor);
        output.produce( msg );
    }
}
