package it.pliot.dim_impl.channel;

import java.io.Serializable;
import java.util.Date;

public class SendResult implements Serializable {

    private boolean success;

    private Date confirmDttm;

    public SendResult( boolean esit , Date confirmDttm){
        this.success = esit;
        this.confirmDttm = confirmDttm;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Date getConfirmDttm() {
        return confirmDttm;
    }

    public void setConfirmDttm(Date confirmDttm) {
        this.confirmDttm = confirmDttm;
    }
}
