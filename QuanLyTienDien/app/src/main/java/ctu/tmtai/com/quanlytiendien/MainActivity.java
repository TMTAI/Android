package ctu.tmtai.com.quanlytiendien;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import ctu.tmtai.com.api.ApiApp;
import ctu.tmtai.com.models.User;
import ctu.tmtai.com.notify.Notify;
import ctu.tmtai.com.util.JSONReader;

import static ctu.tmtai.com.util.Constant.*;

public class MainActivity extends AppCompatActivity implements ApiApp {

    EditText txtUserName, txtPassword;
    CheckBox chkRemember;
    Button btnLogin;

    private JSONArray jsonArray;
    public static SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();

        logined();
    }

    private void logined() {
        if (preferences.getBoolean(LOGINED,false) == true){
            if (preferences.getBoolean(IS_ADMIN, false) == true){
                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                startActivity(intent);
            }else{
                if (preferences.getString(ROLE, NULL).equals(ROLE_CUSTOMER)){
                    Intent intent = new Intent(getApplicationContext(), User.class);
                    startActivity(intent);
                }else if (preferences.getString(ROLE, NULL).equals(ROLE_EMPLOYEE)){
                    Intent intent = new Intent(getApplicationContext(), User.class);
                    startActivity(intent);
                }
            }
        }
    }

    public void addEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
    }

    private void doLogin(String username, String password, boolean remmember) {
        editor.putString(USERNAME, username);
        editor.putString(PASSWORD, password);
        Boolean checkLogin = false;
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                if (jsonArray.getJSONObject(i).getString(CODE).equals(username) &&
                        jsonArray.getJSONObject(i).getString(PASSWORD).equals(password)) {
                    if (remmember){
                        editor.putBoolean(LOGINED, true);
                    }
                    checkLogin = true;
                    if (jsonArray.getJSONObject(i).getBoolean(IS_ADMIN)) {
                        editor.putString(ROLE, ROLE_ADMIN);
                        editor.putBoolean(IS_ADMIN, true);
                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                        startActivity(intent);
                    } else {
                        editor.putBoolean(IS_ADMIN, false);
                        if (jsonArray.getJSONObject(i).getString(ROLE).equals(ROLE_EMPLOYEE)) {
                            editor.putString(ROLE, ROLE_EMPLOYEE);
                            Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                            startActivity(intent);
                        } else {
                            editor.putString(ROLE, ROLE_CUSTOMER);
                            Intent intent = new Intent(getApplicationContext(), CustomerActivity.class);
                            startActivity(intent);
                        }
                    }
                    break;
                }
            }
            editor.commit();
            if (!checkLogin) {
                Notify.showToast(this, R.string.user_password_invalid, Notify.SHORT);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addControls() {
        preferences = getPreferences(Context.MODE_PRIVATE);
        editor = preferences.edit();
        txtUserName = (EditText) findViewById(R.id.txtUserName);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        chkRemember = (CheckBox) findViewById(R.id.chkRemember);
        btnLogin = (Button) findViewById(R.id.btnLogin);


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
        new MyJsonTask().execute();
    }

    // Lớp xử lý đa tiến trình:
    public class MyJsonTask extends AsyncTask<String, JSONObject, Void> {
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                Document document = Jsoup.connect(HTTP_ALL_USER).get();
                String str  = document.body().text();
                // đọc và chuyển về JSONObject
                jsonArray = new JSONArray(str);
                Log.d("JSONARRAY", str);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(JSONObject... values) {
            super.onProgressUpdate(values);
            // ta cập nhật giao diện ở đây:
            JSONObject jsonObj = values[0];
            try {
                // kiểm tra xem có tồn tại thuộc tính id hay không
                    txtUserName.setText(jsonObj.getString("code"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
        }
    }
}
