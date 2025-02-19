package it.pliot.dim_impl.data;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class GatewayConf {

    @Id
    private String idEquipment;

    public String getIdEquipment() {
        return idEquipment;
    }

    public void setIdEquipment(String idEquipment) {
        this.idEquipment = idEquipment;
    }
}
