package it.pliot.dim_impl.controller;

import it.pliot.dim_impl.job.HookDevice;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@Transactional
public class DeviceSimulator {

    @Autowired
    HookDevice hookDevice;


    @GetMapping("/simulator")
    public String simulatorInit(   Model model) {

        return "device_simulator";
    }

    @PostMapping("/simulator")
    public String simulatorStart( @RequestParam(name="cmd", required=false, defaultValue="start") String name,
                                  Model model) {
        model.addAttribute("msg" , "Started");
        hookDevice.scheduleJob( "test" , "*/5 * * * * *" );
        return "device_simulator";
    }

}
