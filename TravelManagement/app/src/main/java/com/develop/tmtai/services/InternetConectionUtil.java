package com.develop.tmtai.services;

import com.develop.tmtai.models.Schedule;

import org.json.JSONArray;
import org.json.JSONException;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.develop.tmtai.util.Constant.HTTP_GET_ALL_SCHEDULE;

/**
 * Created by tmtai on 8/11/2017.
 */

public class InternetConectionUtil {
    private static InternetConectionUtil instance = new InternetConectionUtil();

    public static InternetConectionUtil getInstance(){
        return instance;
    }

    public List<Schedule> getListSchedule() throws IOException, JSONException {
        List<Schedule> list = new ArrayList<>();
        String str = Jsoup.connect(HTTP_GET_ALL_SCHEDULE).ignoreContentType(true).get().body().text();
        JSONArray array = new JSONArray(str);
        for (int i=0;i<array.length();i++){
            Schedule sche = new Schedule(array.getJSONObject(i));
            list.add(sche);
        }
        return list;
    }

    public JSONArray getJSONArrayFromServer(String url) throws IOException, JSONException {
        String str = Jsoup.connect(url).ignoreContentType(true).get().body().text();
        JSONArray array = new JSONArray(str);
        return array;
    }
}
