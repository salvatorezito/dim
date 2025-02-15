package it.pliot.dim_impl.conf;

import it.pliot.dim_impl.data.IotAdapterType;
import it.pliot.dim_impl.data.MeasureOutputChannel;

import java.util.ArrayList;
import java.util.List;

public class Instances {

    public static List<IotAdapterType> initialAdapters(){
        List<IotAdapterType> elenco = new ArrayList<IotAdapterType>();
        elenco.add( createIotTypeInstance("01" , "Adapter Uno" , "classe") );
        elenco.add( createIotTypeInstance("02" , "Adapter Uno" , "classe") );
        return  elenco;
    }


    private static IotAdapterType createIotTypeInstance(String id , String name, String clazz  ){
        IotAdapterType a = new IotAdapterType();
        a.setId( id ) ;
        a.setClazz( clazz );
        a.setName( name );
        return a;

    }

    public static List<MeasureOutputChannel> defaultSenderType(){
        List<MeasureOutputChannel> elenco = new ArrayList<MeasureOutputChannel>();
        elenco.add( createSenderType("01" , "Http" , "classe") );

        return  elenco;
    }

    private static MeasureOutputChannel createSenderType(String number, String adapterUno, String classe) {
        MeasureOutputChannel config = new MeasureOutputChannel();
        return  config;
    }
}
