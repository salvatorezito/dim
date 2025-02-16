package it.pliot.dim_impl.controller;

import it.pliot.dim_impl.gpin.PinUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestPinController {

    private static  String READ_STATE ="STATE";

    @PostMapping("/test_pin")
    public String postTestPin( @RequestParam(name="pin" , required=false) String pin ,
                            @RequestParam(name="type", required=false ,defaultValue = "STATE" ) String object2read  ,
                            Model model){
        if ( pin != null ) {
            model.addAttribute("pin", pin);
            int adress = Integer.parseInt(pin);
            StringBuffer b = new StringBuffer();
            b.append(" Request to read ").append(object2read);
            b.append(" for adress ").append(adress);

            if (READ_STATE.equals(object2read)) {
                b.append(" Value : ").append(PinUtils.readPinState(adress));
            }

            model.addAttribute("result", b.toString());
        }

        return "test_pin";
    }
    @GetMapping("/test_pin")
    public String getTestPin( @RequestParam(name="pin" , required=false) String pin ,
                            @RequestParam(name="type", required=false ,defaultValue = "STATE" ) String object2read  ,
                            Model model){

        return "test_pin";
    }
}
