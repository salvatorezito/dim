package it.pliot.dim_impl.job.task;

import it.pliot.dim_impl.channel.MeasureMsg;
import it.pliot.dim_impl.channel.output.OutputChannel;

import java.util.Date;

public class MockDavice implements Runnable {

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

        MeasureMsg msg = new MeasureMsg( idEquipment , idSensor , "4" , new Date() );

        output.produce( msg );
    }
}
