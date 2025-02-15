package it.pliot.dim_impl.channel.impl;

import it.pliot.dim_impl.channel.MeasureMsg;
import it.pliot.dim_impl.channel.OutputChannel;
import it.pliot.dim_impl.channel.SendResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;


public class HttpOutputChannel {

    private static final Logger logger = LoggerFactory.getLogger(HttpOutputChannel.class);


    private final RestTemplate restTemplate;
    private final String url;

    public HttpOutputChannel(RestTemplateBuilder restTemplateBuilder, String url ) {
        this.restTemplate = restTemplateBuilder.connectTimeout( Duration.ofSeconds( 10 ) )
                .build();
        this.url = url;
    }


    public void send(List<MeasureMsg> msgs) {
        logger.info( " send ");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", "application/json;charset=UTF-8");


        Object o = restTemplate.postForObject(
                url,
                msgs,
                ResponseEntity.class);
        logger.info( " sent result " + o );

    }


}
