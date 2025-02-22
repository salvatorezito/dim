package it.pliot.dim_impl.conf;

import it.pliot.dim_impl.channel.output.HttpOutputChannel;
import it.pliot.dim_impl.data.IotAdapterType;
import it.pliot.dim_impl.data.SignalChannel;
import it.pliot.dim_impl.data.UserCredentials;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class Instances {

    public static List<IotAdapterType> initialAdapters(){
        List<IotAdapterType> elenco = new ArrayList<IotAdapterType>();
        elenco.add( createIotTypeInstance("01" , "Adapter Uno" , "classe") );
        elenco.add( createIotTypeInstance("02" , "Adapter Uno" , "classe") );
        return  elenco;
    }

    public static List<SignalChannel> initMeasOutChannel(){
        List<SignalChannel> elenco = new ArrayList<>();
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

    private static SignalChannel createMeasOutChannelInstance(String id , String name, Class clazz , String url  ){
        SignalChannel a = new SignalChannel();
        a.setId( id ) ;
        a.setClazz( clazz.getName() );
        a.setName( name );
        a.setUrl( url );
        return a;

    }

    public static List<SignalChannel> defaultSenderType(){
        List<SignalChannel> elenco = new ArrayList<SignalChannel>();
        elenco.add( createSenderType("01" , "Http" , "classe") );

        return  elenco;
    }

    private static SignalChannel createSenderType(String number, String adapterUno, String classe) {
        SignalChannel config = new SignalChannel();
        return  config;
    }

    public static UserCredentials createAdmin() {
        UserCredentials admin = new UserCredentials();
        PasswordEncoder passwordEncoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();
        admin.setUserName("admin");
        admin.setPassword( passwordEncoder.encode( "password") );
        admin.setEnabled( true );
        admin.setExpired( false );
        admin.setLocked( false );
        return  admin;
    }
}
