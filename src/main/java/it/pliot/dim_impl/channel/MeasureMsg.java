package it.pliot.dim_impl.channel;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class MeasureMsg implements Serializable  {

    public MeasureMsg(String idEquipment, String idSensor , String val , Date d){
        srcId = UUID.randomUUID().toString();
        this.sensorId = idSensor;
        this.val = val;
        this.mesure_dttm = d;
    }

    private String srcId;

    private String idEquipment;

    private String sensorId;

    private String val;

    public String getSrcId() {
        return srcId;
    }

    public void setSrcId(String srcId) {
        this.srcId = srcId;
    }

    public String getIdEquipment() {
        return idEquipment;
    }

    public void setIdEquipment(String idEquipment) {
        this.idEquipment = idEquipment;
    }


    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Date getMesure_dttm() {
        return mesure_dttm;
    }

    public void setMesure_dttm(Date mesure_dttm) {
        this.mesure_dttm = mesure_dttm;
    }

    private Date mesure_dttm;


}
