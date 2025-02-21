package it.pliot.dim_impl.conf;

import it.pliot.dim_impl.data.GatewayConf;
import it.pliot.dim_impl.job.task.DeviceReaderFactory;
import it.pliot.dim_impl.repository.GatewayConfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GlobalConfiguration {


    @Autowired
    GatewayConfRepository gatewayConfRepository;

    private String idEquipment;

    public String getIdEquipment() {
        if (idEquipment == null) {
            List<GatewayConf> l = gatewayConfRepository.findAll();
            if (l.size() == 1) {
                idEquipment = l.get(0).getIdEquipment();
            }else {
                if ( l.isEmpty() )
                    idEquipment = initEquipent();
                else{
                    throw new RuntimeException(" Wrong configuration multiple equipment ");
                }
            }
        }
        return idEquipment;
    }

    public String getTenantId() {
        return tenantId;
    }

    private String initEquipent() {
        String id = UUID.randomUUID().toString();
        GatewayConf conf = new GatewayConf();
        conf.setIdEquipment( id );
        conf = gatewayConfRepository.save( conf );
        return conf.getIdEquipment();
    }

    private String tenantId = UUID.randomUUID().toString();

}
