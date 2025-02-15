package it.pliot.dim_impl.channel.output;

import java.io.Serializable;
import java.util.Date;

public class OutputChannelInfo implements Serializable  {

    private int size;
    private int capacity;
    private Date lastInsert;
    private String status;


    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OutputChannelInfo{" +
                "size=" + size +
                ", capacity=" + capacity +
                ", lastInsert=" + lastInsert +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastPush=" + lastPush +
                '}';
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

    private String id;
    private String name;

    public Date getLastInsert() {
        return lastInsert;
    }

    public void setLastInsert(Date lastInsert) {
        this.lastInsert = lastInsert;
    }

    public Date getLastPush() {
        return lastPush;
    }

    public void setLastPush(Date lastPush) {
        this.lastPush = lastPush;
    }

    private Date lastPush;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
