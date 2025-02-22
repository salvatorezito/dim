package it.pliot.dim_impl.channel.output;

import it.pliot.dim_impl.channel.MeasureMsg;
import it.pliot.dim_impl.data.SignalChannel;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.concurrent.ExecutorService;

public interface OutputChannel {
    String RUNNING = "RUNNING";
    String STOPPED = "STOPPED";

    public void produce(MeasureMsg message);
    public void sendBatch(List<MeasureMsg> batch);
    public OutputChannelInfo info();
    public void init(ApplicationContext context , SignalChannel conf , ExecutorService executorService ) ;
    public void startConsumer();

}
