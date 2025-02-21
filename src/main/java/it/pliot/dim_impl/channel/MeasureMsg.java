package it.pliot.dim_impl.channel;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class MeasureMsg implements Serializable  {

    public MeasureMsg(String idEquipment, String idSensor , String val , Date d , String tenantId){
        srcId = UUID.randomUUID().toString();
        this.sensorId = idSensor;
        this.val = val;
        this.mesure_dttm = d;
        this.tenantId = tenantId;
        this.equipmentId = idEquipment;
    }
    private String tenantId;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    private String srcId;

    private String equipmentId;

    private String sensorId;

    private String val;

    public String getSrcId() {
        return srcId;
    }

    public void setSrcId(String srcId) {
        this.srcId = srcId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
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
