package it.pliot.dim_impl.controller;



import it.pliot.dim_impl.channel.OutputChannelFactory;
import it.pliot.dim_impl.channel.output.BaseOutputMessageChannel;
import it.pliot.dim_impl.channel.MeasureMsg;
import it.pliot.dim_impl.channel.output.OutputChannel;
import it.pliot.dim_impl.channel.output.OutputChannelInfo;
import it.pliot.dim_impl.conf.GlobalConfiguration;
import it.pliot.dim_impl.job.MemoryMonitor;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Transactional
public class BaseController {

    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    GlobalConfiguration globalConfiguration;


    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "home_new";
    }


    @Autowired
    OutputChannelFactory channelFactory;

    @GetMapping("/sendMessage")
    public String sendMessage( @RequestParam(name="msg", required=false, defaultValue="10") String message) {
        OutputChannel edgeChannel = channelFactory.getChannel( "HTTP_EDGE_CHANNEL");
        if ( edgeChannel == null )
            log.info( " channel not found " + edgeChannel );
        edgeChannel.produce(
                new MeasureMsg( "1" , "1" , "23" , new Date() , globalConfiguration.getTenantId() )
        );
        return "confirmmessage";
    }

    @GetMapping("/status")
    public String status( Model model) {
        List<OutputChannelInfo> list = new ArrayList<OutputChannelInfo>();
        channelFactory.outChannels().forEach( c -> list.add( c.info() ) );
        model.addAttribute("channels", list );
        return "status";
    }
}
