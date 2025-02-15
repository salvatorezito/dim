package it.pliot.dim_impl.channel;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface OutputChannel {

    public void produce(MeasureMsg message);

    public void sendBatch(List<MeasureMsg> batch);
    public int size();
    public int capacity();
}
