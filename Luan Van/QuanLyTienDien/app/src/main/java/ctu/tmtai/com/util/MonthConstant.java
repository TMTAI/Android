package ctu.tmtai.com.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tranm on 17-Nov-17.
 */

public class MonthConstant {
    private static Map<String, String> mapThang = new HashMap<String, String>();

    static {
        mapThang.put("1", "JAN");
        mapThang.put("2", "FEB");
        mapThang.put("3", "MAR");
        mapThang.put("4", "APR");
        mapThang.put("5", "MAY");
        mapThang.put("6", "JUN");
        mapThang.put("7", "JUL");
        mapThang.put("8", "AUG");
        mapThang.put("9", "SEP");
        mapThang.put("10", "OCT");
        mapThang.put("11", "NOV");
        mapThang.put("12", "DEC");
    }

    public static Map<String, String> getMapThang(){
        return mapThang;
    }
}
