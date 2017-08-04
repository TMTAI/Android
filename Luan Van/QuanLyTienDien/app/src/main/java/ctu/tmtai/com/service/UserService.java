package ctu.tmtai.com.service;

import android.content.Context;
import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ctu.tmtai.com.models.User;
import ctu.tmtai.com.notify.Notify;
import ctu.tmtai.com.quanlytiendien.AdminActivity;
import ctu.tmtai.com.quanlytiendien.R;

import static ctu.tmtai.com.util.Constant.HTTP_ADD_USER;
import static ctu.tmtai.com.util.Constant.HTTP_CODE_USER;
import static ctu.tmtai.com.util.Constant.HTTP_UPDATE_USER;

/**
 * Created by tranm on 23-Jul-17.
 */

public class UserService {

    private static UserService instance = new UserService();

    public static void addUser(User user, final Context context) throws JSONException {
        final boolean[] add = {false};
        Gson gson = new GsonBuilder().create();
        String strJson = gson.toJson(user);
        JSONObject jsonObject = new JSONObject(strJson);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                HTTP_ADD_USER,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Notify.showToast(context.getApplicationContext(), R.string.Add_susscess, Notify.SHORT);
                        Intent intent = new Intent(context, AdminActivity.class);
                        context.startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Notify.showToast(context.getApplicationContext(), error.toString(), Notify.SHORT);
                        add[0] = false;
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    public static void updateUser(User user, final Context context){
        Gson gson = new GsonBuilder().create();
        String strJson = gson.toJson(user);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(strJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.PUT,
                HTTP_UPDATE_USER,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Notify.showToast(context.getApplicationContext(), R.string.change_susscess, Notify.SHORT);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Notify.showToast(context.getApplicationContext(), error.toString(), Notify.SHORT);
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    public static User findUserByUsername(String username, final Context context){
        final List<User> user = new ArrayList<>();
        final Gson gson = new GsonBuilder().create();
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest jsonObjectRequest = new StringRequest(
                Request.Method.GET,
                HTTP_CODE_USER+username,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        User us = gson.fromJson(response, User.class);
                        user.add(us);
                        Notify.showToast(context.getApplicationContext(), user.size(), Notify.SHORT);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Notify.showToast(context.getApplicationContext(), error.toString(), Notify.SHORT);
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
        Notify.showToast(context.getApplicationContext(), user.size(), Notify.SHORT);
        return user.get(0);
    }
}
