package it.pliot.dim_impl.channel;

import it.pliot.dim_impl.channel.impl.HttpOutputChannel;
import it.pliot.dim_impl.conf.InitDim;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Service
public class BaseOutputMessageChannel {


    private HttpOutputChannel httpOutputChannel = null;

    private static final int BATCH_SIZE = 5; // Numero massimo di messaggi per batch
    private static final int TIMEOUT_MS = 2000; // Timeout massimo per batch

    private final BlockingQueue<MeasureMsg> queue = new LinkedBlockingQueue<>();
    private final ExecutorService executorService;

    public BaseOutputMessageChannel(ExecutorService executorService , RestTemplateBuilder restTemplate) {
        this.executorService = executorService;
        this.httpOutputChannel = new HttpOutputChannel( restTemplate , "http://localhost:8080/bulkmeasure");
        startConsumer(); // Avvia il thread di consumo all'avvio
    }
    public int size(){
        return queue.size();
    }
    public int capacity(){
        return queue.size();
    }
    // Metodo per produrre messaggi nella queue
    public void produce(MeasureMsg message) {
        queue.offer(message);
        // Inserisce il messaggio nella coda senza bloccare
    }

    // Metodo per consumare e inviare i messaggi in batch
    private void startConsumer() {
        executorService.execute(() -> {
            try {
                while (true) {
                    List<MeasureMsg> batch = new ArrayList<>();
                    long startTime = System.currentTimeMillis();

                    while (batch.size() < BATCH_SIZE &&
                            (System.currentTimeMillis() - startTime) < TIMEOUT_MS) {
                        MeasureMsg message = queue.poll(TIMEOUT_MS, TimeUnit.MILLISECONDS);
                        if (message != null) {
                            batch.add(message);
                        }
                    }

                    if (!batch.isEmpty()) {
                        sendBatch(batch);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    // Simulazione dell'invio dei messaggi
    protected void sendBatch( List<MeasureMsg> batch) {
        log.info( "send batch " + batch );
        httpOutputChannel.send( batch );
    }

    private static final Logger log = LoggerFactory.getLogger(InitDim.class);

}
