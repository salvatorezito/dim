package it.pliot.dim_impl.controller;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@Transactional
public class DeviceSimulator {

    @GetMapping("/simulator")
    public String simulatorInit(   Model model) {

        return "device_simulator";
    }

    @PostMapping("/simulator")
    public String simulatorStart(   Model model) {
        model.addAttribute("msgconfirmation" , "Started");
        return "device_simulator";
    }

}
