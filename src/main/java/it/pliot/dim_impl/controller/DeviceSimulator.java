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
    public String simulatorStart( @RequestParam(name="idsignal", required=false, defaultValue="test") String name,
                                  @RequestParam(name="randomval", required=false, defaultValue="150") String rate,
                                  Model model) {
        int rateValue = 150;
        try{
            rateValue =  Integer.parseInt( rate );
        }catch ( Exception  e ){
            e.printStackTrace();
        }
        model.addAttribute("msg" , "Started");
        hookDevice.scheduleJob( name , name,  rateValue ,"*/5 * * * * *" );
        return "device_simulator";
    }

}
