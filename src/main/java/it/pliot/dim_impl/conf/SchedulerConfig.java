package it.pliot.dim_impl.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.*;

public class SchedulerConfig implements SchedulingConfigurer {

    private final int POOL_SIZE = 10;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(POOL_SIZE);
        threadPoolTaskScheduler.setThreadNamePrefix("pliot-scheduled-task-pool-");
        threadPoolTaskScheduler.initialize();
        scheduledTaskRegistrar.setTaskScheduler(threadPoolTaskScheduler);
    }

    @Bean
    public ExecutorService taskExecutor() {
        return new ThreadPoolExecutor(
                2, // Core pool size
                4, // Max pool size
                60, TimeUnit.SECONDS, // Keep-alive time
                new LinkedBlockingQueue<>(100), // Coda di attesa
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy() // Politica di rifiuto
        );
    }
}