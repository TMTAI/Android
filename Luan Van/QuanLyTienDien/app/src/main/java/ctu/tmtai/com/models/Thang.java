package ctu.tmtai.com.models;

import org.json.JSONException;
import org.json.JSONObject;

import static ctu.tmtai.com.util.Constant.MA_THANG;

/**
 * Created by tranm on 08-Nov-17.
 */

public class Thang {
    private String mathang;

    public Thang() {
    }

    public Thang(String manam) {
        this.mathang = manam;
    }

    public Thang(JSONObject object) {
        try {
            this.mathang = object.getString(MA_THANG);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String setMathang() {
        return mathang;
    }

    public void setMathang(String manam) {
        this.mathang = manam;
    }
}
