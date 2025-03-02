package it.pliot.dim_impl.channel;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class MeasureMsg implements Serializable  {

    public MeasureMsg(String idEquipment, String idSensor , String val , Date d , String tenantId){
        srcId = UUID.randomUUID().toString();
        this.signalId = idSensor;
        this.val = val;
        this.measureDttm = d;
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

    private String signalId;

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


    public String getSignalId() {
        return signalId;
    }

    public void setSignalId(String signalId) {
        this.signalId = signalId;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Date getMeasureDttm() {
        return measureDttm;
    }

    public void setMeasureDttm(Date measureDttm) {
        this.measureDttm = measureDttm;
    }

    private Date measureDttm    ;


}
