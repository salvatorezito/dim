package it.pliot.dim_impl.controller;



import it.pliot.dim_impl.channel.BaseOutputMessageChannel;
import it.pliot.dim_impl.channel.MeasureMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class BaseController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "saluto";
    }


    @Autowired
    BaseOutputMessageChannel messageChannel;

    @GetMapping("/sendMessage")
    public String sendMessage( @RequestParam(name="msg", required=false, defaultValue="10") String message) {
        messageChannel.produce(new MeasureMsg( "1" , "1" , "23" , new Date() ) );
        return "confirmmessage";
    }

}
