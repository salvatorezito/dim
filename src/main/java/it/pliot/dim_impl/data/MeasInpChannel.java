package it.pliot.dim_impl.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MeasInpChannel {
    @Id
    private String id;

    private String idAdapter;

    private String desc;


}
