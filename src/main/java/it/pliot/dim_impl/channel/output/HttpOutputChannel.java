package it.pliot.dim_impl.channel.output;

import it.pliot.dim_impl.channel.MeasureMsg;
import it.pliot.dim_impl.data.MeasOutChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ExecutorService;


public class HttpOutputChannel extends BaseOutputMessageChannel {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpOutputChannel.class);

    private RestTemplate restTemplate;
    private String url;

    protected void init0(ApplicationContext context , MeasOutChannel conf , ExecutorService executorService ){
        RestTemplateBuilder restTemplateBuilder = ( RestTemplateBuilder ) context.getBean(RestTemplateBuilder.class);
        this.restTemplate = restTemplateBuilder.connectTimeout( Duration.ofSeconds( 10 ) )
                .build();
        this.url = conf.getUrl();
        LOGGER.info( " created a Channel to " + this.url );

    }


    @Override
    public void sendBatch(List<MeasureMsg> batch) {
        LOGGER.info( " send ");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", "application/json;charset=UTF-8");

        Object o = restTemplate.postForObject(
                url,
                batch,
                ResponseEntity.class);
        LOGGER.info( "  result sent " + o );

    }


}
