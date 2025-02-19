package it.pliot.dim_impl.job;

import it.pliot.dim_impl.data.GatewayConf;
import it.pliot.dim_impl.job.task.DeviceReaderFactory;
import it.pliot.dim_impl.repository.GatewayConfRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@EnableScheduling
public class HookDevice {

    private static final Logger logger = LoggerFactory.getLogger(HookDevice.class);


    @Autowired
    GatewayConfRepository gatewayConfRepository;

    @Autowired
    DeviceReaderFactory deviceReader;

    private String idEquipment;

    private String getIdEquipment() {
        if (idEquipment == null) {
            List<GatewayConf> l = gatewayConfRepository.findAll();
            if (l.size() == 1) {
                idEquipment = l.get(0).getIdEquipment();
            } else {
                throw new RuntimeException(" Wrong configuration multiple equipment ");
            }
        }
        return idEquipment;
    }

    private final ThreadPoolTaskScheduler taskScheduler;
    private final Map<String, String> jobSchedules = new HashMap<>();

    public HookDevice() {


        this.taskScheduler = new ThreadPoolTaskScheduler();
        this.taskScheduler.initialize();
    }

    public void scheduleJob( String jobName , String cronExpression) {
        Runnable task = deviceReader.reader( jobName );
        taskScheduler.schedule( task , new CronTrigger(cronExpression));
        jobSchedules.put( jobName, cronExpression);
        System.out.println("Job " + jobName + " schedulato con cron: " + cronExpression);
    }
}
