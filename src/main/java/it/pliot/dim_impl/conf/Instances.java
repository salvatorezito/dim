package it.pliot.dim_impl.conf;

import it.pliot.dim_impl.channel.output.HttpOutputChannel;
import it.pliot.dim_impl.data.IotAdapterType;
import it.pliot.dim_impl.data.MeasOutChannel;

import java.util.ArrayList;
import java.util.List;

public class Instances {

    public static List<IotAdapterType> initialAdapters(){
        List<IotAdapterType> elenco = new ArrayList<IotAdapterType>();
        elenco.add( createIotTypeInstance("01" , "Adapter Uno" , "classe") );
        elenco.add( createIotTypeInstance("02" , "Adapter Uno" , "classe") );
        return  elenco;
    }

    public static List<MeasOutChannel> initMeasOutChannel(){
        List<MeasOutChannel> elenco = new ArrayList<>();
        elenco.add( createMeasOutChannelInstance( "HTTP_EDGE_CHANNEL" ,
                                                "Http Vs Edge" ,
                                                HttpOutputChannel.class,
                                                "http://localhost:8080/bulkmeasure"));
        return elenco;
    }

    private static IotAdapterType createIotTypeInstance(String id , String name, String clazz  ){
        IotAdapterType a = new IotAdapterType();
        a.setId( id ) ;
        a.setClazz( clazz );
        a.setName( name );
        return a;

    }

    private static MeasOutChannel createMeasOutChannelInstance(String id , String name, Class clazz , String url  ){
        MeasOutChannel a = new MeasOutChannel();
        a.setId( id ) ;
        a.setClazz( clazz.getName() );
        a.setName( name );
        a.setUrl( url );
        return a;

    }

    public static List<MeasOutChannel> defaultSenderType(){
        List<MeasOutChannel> elenco = new ArrayList<MeasOutChannel>();
        elenco.add( createSenderType("01" , "Http" , "classe") );

        return  elenco;
    }

    private static MeasOutChannel createSenderType(String number, String adapterUno, String classe) {
        MeasOutChannel config = new MeasOutChannel();
        return  config;
    }
}
