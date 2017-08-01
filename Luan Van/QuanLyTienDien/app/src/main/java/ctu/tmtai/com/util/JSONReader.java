package ctu.tmtai.com.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by tranm on 22-Jul-17.
 */

public class JSONReader {
    public static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /**
     * Hàm trả về JSONObject
     * @param url - Truyền link URL có định dạng JSON
     * @return - Trả về JSONOBject
     * @throws IOException
     * @throws JSONException
     */
    public static JSONObject readJsonObjectFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
        //đọc nội dung với Unicode:
            BufferedReader rd = new BufferedReader
                    (new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static JSONArray readJsonArrayFromUrl(String url) throws IOException, JSONException{
        InputStream is = new URL(url).openStream();
        try {
            //đọc nội dung với Unicode:
            BufferedReader rd = new BufferedReader
                    (new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONArray json = new JSONArray(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}
