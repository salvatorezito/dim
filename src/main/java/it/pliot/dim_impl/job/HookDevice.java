package it.pliot.dim_impl.job;

import it.pliot.dim_impl.channel.OutputChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashMap;
import java.util.Map;

@Component
@EnableScheduling
public class HookDevice {

    private static final Logger logger = LoggerFactory.getLogger(HookDevice.class);

    @Autowired
    DeviceReaderFactory deviceReader;

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
