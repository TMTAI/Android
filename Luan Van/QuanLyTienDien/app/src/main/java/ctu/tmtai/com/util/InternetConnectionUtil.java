package ctu.tmtai.com.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ctu.tmtai.com.models.KhachHang;
import ctu.tmtai.com.models.User;

import static ctu.tmtai.com.util.Constant.HTTP_DELETE;
import static ctu.tmtai.com.util.Constant.HTTP_NEW_PASSWORD;
import static ctu.tmtai.com.util.Constant.MA_KH;
import static ctu.tmtai.com.util.Constant.NEW_PASS;

/**
 * Created by tranm on 10-Aug-17.
 */

public class InternetConnectionUtil {
    private static InternetConnectionUtil instance = new InternetConnectionUtil();

    public static InternetConnectionUtil getInstance(){
        return instance;
    }

    public JSONArray getJSONArrayFromServer(String HTTP){
        JSONArray array = null;
        try {
            String str = Jsoup.connect(HTTP).get().body().text();
            array = new JSONArray(str);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array;
    }

    public List<User> getListUserFromJSONArray(JSONArray array) throws JSONException {
        List<User> list = new ArrayList<>();
        for (int i=0;i<array.length();i++){
            User user = new User(array.getJSONObject(i));
            list.add(user);
        }

        return list;
    }

    public List<KhachHang> getListKhachHangFromJSONArray(JSONArray array) throws JSONException {
        List<KhachHang> list = new ArrayList<>();
        for (int i=0;i<array.length();i++){
            KhachHang khachHang = new KhachHang(array.getJSONObject(i));
            list.add(khachHang);
        }
        return list;
    }

    public void delete(String table, String colum, String condition) throws IOException {
        String url = String.format(HTTP_DELETE, table);
        Jsoup.connect(url).data(colum, condition).get();
    }

    public void newPassword(String table, String colum, String condition, String newPass) throws IOException {
        String url = String.format(HTTP_NEW_PASSWORD, table);
        Jsoup.connect(url).data(colum, condition)
                .data(NEW_PASS,newPass).post();
    }

}
