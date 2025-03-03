package it.pliot.dim_impl.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class IotAdapterType {
    @Id
    private String id;

    private String name;

    private String clazz;

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
