package it.pliot.dim_impl.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class ConfChannelService {
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
