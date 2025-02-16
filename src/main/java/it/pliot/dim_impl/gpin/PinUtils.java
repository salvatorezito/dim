package it.pliot.dim_impl.gpin;

import com.pi4j.context.Context;
import com.pi4j.Pi4J;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalInputConfig;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.io.gpio.digital.PullResistance;

public class PinUtils {

    public static Object readPinState( int  adress  ){
        Context pi4j = Pi4J.newAutoContext();

        // Configura il GPIO 18 come input con pull-down
        DigitalInputConfig pin = DigitalInput.newConfigBuilder( pi4j)
                .id("PIN_READER")
                .address( adress )  // GPIO 18
                .pull(PullResistance.PULL_DOWN) // Evita stati fluttuanti
                .build();

        // Legge lo stato del pin
        DigitalState state = pin.getOnState();
        System.out.println("Lo stato del pin è: " + state);

        // Chiude il contesto Pi4J
        pi4j.shutdown();
        return state.getValue();
    }
}
