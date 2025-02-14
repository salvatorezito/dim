package it.pliot.dim_impl.conf;

import it.pliot.dim_impl.data.IotAdapter;

import java.util.ArrayList;
import java.util.List;

public class Instances {

    public static List<IotAdapter> initialAdapters(){
        List<IotAdapter> elenco = new ArrayList<IotAdapter>();
        elenco.add( createInstance("01" , "Adapter Uno" , "classe") );
        elenco.add( createInstance("02" , "Adapter Uno" , "classe") );
        return  elenco;

    }


    private static IotAdapter createInstance(  String id , String name, String clazz  ){
        IotAdapter a = new IotAdapter();
        a.setId( id ) ;
        a.setClazz( clazz );
        a.setName( name );
        return a;

    }
}
