package ctu.tmtai.com.models;

import java.io.Serializable;

/**
 * Created by tranm on 04-Aug-17.
 */

public class KhuVuc implements Serializable{
    private String makv;
    private String tenkv;
    private String matp;

    public KhuVuc() {
    }

    public KhuVuc(String makv, String tenkv, String matp) {
        this.makv = makv;
        this.tenkv = tenkv;
        this.matp = matp;
    }

    public String getMakv() {
        return makv;
    }

    public void setMakv(String makv) {
        this.makv = makv;
    }

    public String getTenkv() {
        return tenkv;
    }

    public void setTenkv(String tenkv) {
        this.tenkv = tenkv;
    }

    public String getMatp() {
        return matp;
    }

    public void setMatp(String matp) {
        this.matp = matp;
    }
}
