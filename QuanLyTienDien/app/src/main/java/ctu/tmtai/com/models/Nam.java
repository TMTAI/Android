package ctu.tmtai.com.models;

import org.json.JSONException;
import org.json.JSONObject;

import static ctu.tmtai.com.util.Constant.MA_NAM;

/**
 * Created by tranm on 08-Aug-17.
 */

public class Nam {
    private String manam;

    public Nam() {
    }

    public Nam(String manam) {
        this.manam = manam;
    }

    public Nam(JSONObject object) {
        try {
            this.manam = object.getString(MA_NAM);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getManam() {
        return manam;
    }

    public void setManam(String manam) {
        this.manam = manam;
    }
}
