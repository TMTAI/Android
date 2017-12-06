package ctu.tmtai.com.util;

import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import ctu.tmtai.com.quanlytiendien.R;

/**
 * Created by tranm on 17-Nov-17.
 */

public class MonthConstant {
    private static Map<String, Integer> mapThang = new HashMap<String, Integer>();

    static {
        mapThang.put("month_1", R.string.month_1);
        mapThang.put("month_2", R.string.month_2);
        mapThang.put("month_3", R.string.month_3);
        mapThang.put("month_4", R.string.month_4);
        mapThang.put("month_5", R.string.month_5);
        mapThang.put("month_6", R.string.month_6);
        mapThang.put("month_7", R.string.month_7);
        mapThang.put("month_8", R.string.month_8);
        mapThang.put("month_9", R.string.month_9);
        mapThang.put("month_10", R.string.month_10);
        mapThang.put("month_11", R.string.month_11);
        mapThang.put("month_12", R.string.month_12);
    }

    public static Map<String, Integer> getMapThang(){
        return mapThang;
    }
}
