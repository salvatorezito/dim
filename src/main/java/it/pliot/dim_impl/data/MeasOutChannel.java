package it.pliot.dim_impl.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MeasOutChannel {


    @Id
    private String id;

    private String name;

    private String clazz;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String apiKey;

    private String oauth2client;

    private String oauth2secret;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
