package it.pliot.dim_impl.gpin;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.spi.Spi;
import com.pi4j.io.spi.SpiBus;
import com.pi4j.io.spi.SpiMode;

import java.io.IOException;

public class VoltageSensorReaderUtils {

    private static final int CHANNEL = 0; // Canale MCP3008
    private static final double VREF = 3.3; // Tensione di riferimento ADC

    public static void read( int address ) {
        Context pi4j = Pi4J.newAutoContext();

        try {


            Spi spi = pi4j.create(Spi.newConfigBuilder(pi4j)
                    .id("SPI0")
                    .address( address )
                    .bus( SpiBus.BUS_0)
                    .mode( SpiMode.MODE_0)
                    .baud(1000000)
                    .build());

            while (true) {
                double voltage = readVoltage(spi, CHANNEL );
                System.out.println("Tensione rilevata: " + voltage + "V");
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static double readVoltage(Spi spi, int channel) throws IOException {
        byte[] request = new byte[]{(byte) (0x01), (byte) ((0x08 | channel ) << 4), 0x00};
        byte[] response = new byte[3]; // Buffer per la risposta

        spi.transfer(request, response); // Scambio dati corretto

        int result = ((response[1] & 0x03) << 8) | (response[2] & 0xFF);
        return (result * VREF) / 1023.0; // Conversione in tensione
    }
}
