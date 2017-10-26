package ctu.tmtai.com.quanlytiendien;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ctu.tmtai.com.api.ApiApp;
import ctu.tmtai.com.models.KhachHang;
import ctu.tmtai.com.models.User;
import ctu.tmtai.com.notify.Notify;
import ctu.tmtai.com.util.InternetConnectionUtil;

import static ctu.tmtai.com.util.Constant.*;

public class MainActivity extends AppCompatActivity implements ApiApp {

    private EditText txtUserName, txtPassword;
    private CheckBox chkRemember;
    private Button btnLogin;
    private List<User> userList;
    private List<KhachHang> khachHangList;
    public static SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MyJsonTask().execute();

        addControls();
        addEvents();

        logined();
    }

    public void addControls() {
        userList = new ArrayList<>();
        khachHangList = new ArrayList<>();

        preferences = getPreferences(Context.MODE_PRIVATE);
        editor = preferences.edit();
        txtUserName = (EditText) findViewById(R.id.txtUserName);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        chkRemember = (CheckBox) findViewById(R.id.chkRemember);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }


    private void logined() {
        if (preferences.getBoolean(LOGINED, false) == true) {
            if (preferences.getBoolean(IS_ADMIN, false) == true) {
                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                startActivity(intent);
            } else {
                if (preferences.getString(ROLE, NULL).equals(ROLE_CUSTOMER)) {
                    Intent intent = new Intent(getApplicationContext(), CustomerActivity.class);
                    startActivity(intent);
                } else if (preferences.getString(ROLE, NULL).equals(ROLE_EMPLOYEE)) {
                    Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                    startActivity(intent);
                }
            }
        }
    }

    public void addEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyJsonTask().execute();

                if (userList == null) {
                    Notify.showToast(getApplicationContext(), R.string.server_connect_error, Notify.LONG);
                } else {
                    if (!checkEmpty()) {
                        if (chkRemember.isChecked()) {
                            doLogin(txtUserName.getText().toString(), txtPassword.getText().toString(), true);
                        } else if (!chkRemember.isChecked()) {
                            doLogin(txtUserName.getText().toString(), txtPassword.getText().toString(), false);
                        }
                    } else {
                        Notify.showToast(getApplicationContext(), R.string.user_password_empty, Notify.SHORT);
                    }
                }

            }
        });
    }

    private void createBundle(Object ob, Intent intent) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(USER, (Serializable) ob);
        intent.putExtra(BUNDLE_USER, bundle);
    }

    private void doLogin(String username, String password, boolean remmember) {
        editor.putString(USERNAME, username);
        editor.putString(PASSWORD, password);
        Boolean checkLogin = false;
        for (User user : userList) {
            if (user.getCode().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
                checkLogin = true;
                if (remmember) {
                    editor.putBoolean(LOGINED, checkLogin);
                }
                if (user.isAdmin()){
                    Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                    createBundle(user, intent);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                    createBundle(user, intent);
                    startActivity(intent);
                    finish();
                }
            }
        }

        for (KhachHang khachHang : khachHangList) {
            if (khachHang.getMakh().equalsIgnoreCase(username) && khachHang.getPassword().equals(password)) {
                checkLogin = true;
                if (remmember) {
                    editor.putBoolean(LOGINED, checkLogin);
                }
                Intent intent = new Intent(getApplicationContext(), CustomerActivity.class);
                createBundle(khachHang, intent);
                startActivity(intent);
                finish();
            }
        }
        if (!checkLogin) {
            Notify.showToast(this, R.string.user_password_invalid, Notify.SHORT);
        }
    }

    private boolean checkEmpty() {
        if (txtUserName.getText().toString() == null || txtUserName.getText().toString().equals("")) {
            return true;
        }
        if (txtPassword.getText().toString() == null || txtPassword.getText().toString().equals("")) {
            return true;
        }
        return false;
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    // Lớp xử lý đa tiến trình:
    public class MyJsonTask extends AsyncTask<String, JSONObject, Void> {
        @Override
        protected Void doInBackground(String... params) {
            try {
                JSONArray jsonArray = InternetConnectionUtil.getInstance().getJSONArrayFromServer(HTTP_ALL_USER);
                userList = InternetConnectionUtil.getInstance().getListUserFromJSONArray(jsonArray);

                JSONArray jsonCustomer = InternetConnectionUtil.getInstance().getJSONArrayFromServer(HTTP_ALL_KHACH_HANG);
                khachHangList = InternetConnectionUtil.getInstance().getListKhachHangFromJSONArray(jsonCustomer);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
