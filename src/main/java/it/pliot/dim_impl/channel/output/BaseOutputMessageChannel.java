package it.pliot.dim_impl.channel.output;

import it.pliot.dim_impl.channel.MeasureMsg;
import it.pliot.dim_impl.conf.InitDim;
import it.pliot.dim_impl.data.SignalChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


public abstract class BaseOutputMessageChannel implements OutputChannel{


    private static final Logger LOGGER = LoggerFactory.getLogger(BaseOutputMessageChannel.class);

    private static final int BATCH_SIZE     = 30; // Numero massimo di messaggi per batch
    private static final int TIMEOUT_MS     = 20000; // Timeout massimo per batch
    private static final int MAX_QUEUE_CAPACITY   = 20000; // Timeout massimo per batch

    private BlockingQueue<MeasureMsg> queue = null;
    private ExecutorService executorService;
    private String status = "";
    private Date lastInsert = null;
    private Date lastPush = null;
    private String id;
    private String name;


    public final void init(ApplicationContext context , SignalChannel conf , ExecutorService executorService ){
        this.executorService = executorService;
        id = conf.getId();
        this.name = conf.getName();
        queue = new LinkedBlockingQueue<>( MAX_QUEUE_CAPACITY );
        init0( context , conf , executorService );
    }

    protected abstract void init0(ApplicationContext context, SignalChannel conf, ExecutorService executorService);

    // Metodo per produrre messaggi nella queue
    public void produce(MeasureMsg message) {
        queue.offer(message);
        lastInsert = new Date();
        // Inserisce il messaggio nella coda senza bloccare
    }

    // Metodo per consumare e inviare i messaggi in batch
    public void startConsumer() {

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
                        lastPush = new Date();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        status = RUNNING;
    }


    @Override
    public OutputChannelInfo info() {
        OutputChannelInfo info = new OutputChannelInfo();
        info.setCapacity( this.queue.remainingCapacity() );
        info.setSize( this.queue.size() );
        info.setLastInsert( lastInsert );
        info.setLastPush( lastPush );
        info.setId( id );
        info.setName( name );
        info.setStatus( status );
        return info;
    }
    // Simulazione dell'invio dei messaggi
    public abstract void sendBatch( List<MeasureMsg> batch) ;

    private static final Logger log = LoggerFactory.getLogger(InitDim.class);

}
