package ctu.tmtai.com.quanlytiendien;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.Serializable;

import ctu.tmtai.com.api.ApiApp;
import ctu.tmtai.com.models.KhachHang;
import ctu.tmtai.com.models.User;
import ctu.tmtai.com.notify.Notify;

import static ctu.tmtai.com.util.Constant.BUNDLE_USER;
import static ctu.tmtai.com.util.Constant.CODE;
import static ctu.tmtai.com.util.Constant.HTTP_NEW_PASSWORD;
import static ctu.tmtai.com.util.Constant.MA_KH;
import static ctu.tmtai.com.util.Constant.NEW_PASS;
import static ctu.tmtai.com.util.Constant.ROLE;
import static ctu.tmtai.com.util.Constant.ROLE_ADMIN;
import static ctu.tmtai.com.util.Constant.ROLE_CUSTOMER;
import static ctu.tmtai.com.util.Constant.USER;

public class ResetPasswordActivity extends AppCompatActivity implements ApiApp{

    EditText txtOldPassword, txtNewPassword, txtConfirmPassword;
    FloatingActionButton btnChangePassword;
    private User user;
    private KhachHang khachHang;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        addControls();
        addEvents();
    }

    @Override
    public void addControls() {
        txtOldPassword = (EditText) findViewById(R.id.txtOldPassword);
        txtNewPassword = (EditText) findViewById(R.id.txtNewPassword);
        txtConfirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);
        btnChangePassword = (FloatingActionButton) findViewById(R.id.btnChangePassword);

        Intent intent = getIntent();

        bundle = intent.getBundleExtra(BUNDLE_USER);
        if (bundle != null) {
            String role = bundle.getString(ROLE);
            if (role.equals(ROLE_CUSTOMER)) {
                khachHang = (KhachHang) bundle.getSerializable(USER);
            } else {
                user = (User) bundle.getSerializable(USER);
            }
        }
    }

    @Override
    public void addEvents() {
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldpass = txtOldPassword.getText().toString();
                String newpass = txtNewPassword.getText().toString();
                String confirm = txtConfirmPassword.getText().toString();
                if (!oldpass.equals("") && !newpass.equals("") && !confirm.equals("")){
                    if (khachHang != null){
                        Log.d("USER : ", khachHang.getTenkh());
                        if (khachHang.getPassword().equals(oldpass)){
                            if (newpass.equals(confirm)){
                                khachHang.setPassword(newpass);
                                new KhachHangConnection().execute();
                                bundle.putString(ROLE, khachHang.getRole());
                                changeSusscess(khachHang);
                            }else{
                                Notify.showToast(getApplicationContext(), R.string.confirm_password_error, Notify.SHORT);
                            }
                        }else {
                            Notify.showToast(getApplicationContext(), R.string.change_fail, Notify.SHORT);
                        }
                    }else if (user != null){
                        Log.d("USER : ", user.getName());
                        if (user.getPassword().equals(oldpass)){
                            if (newpass.equals(confirm)){
                                user.setPassword(newpass);
                                new UserConnection().execute();
                                bundle.putString(ROLE, user.getRole());
                                changeSusscess(user);
                            }else{
                                Notify.showToast(getApplicationContext(), R.string.confirm_password_error, Notify.SHORT);
                            }
                        }else{
                            Notify.showToast(getApplicationContext(), R.string.change_fail, Notify.SHORT);
                        }
                    }
                }else{
                    Notify.showToast(getApplicationContext(), R.string.password_empty, Notify.SHORT);
                }
            }
        });
    }

    private void changeSusscess(Object object){
        Notify.showToast(getApplicationContext(), R.string.change_susscess, Notify.SHORT);
        Intent intent = new Intent(getApplicationContext(), InfomationUserActivity.class);
        bundle.putSerializable(USER, (Serializable) object);
        intent.putExtra(BUNDLE_USER, bundle);
        startActivity(intent);
        finish();
    }
    public class UserConnection extends AsyncTask<String, Object, Void> {
        @Override
        protected Void doInBackground(String... params) {
            try {
                String url = String.format(HTTP_NEW_PASSWORD,"User");
                Jsoup.connect(url).data(CODE, user.getCode())
                        .data(NEW_PASS, user.getPassword()).post();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class KhachHangConnection extends AsyncTask<String, Object, Void> {
        @Override
        protected Void doInBackground(String... params) {
            try {
                String url = String.format(HTTP_NEW_PASSWORD,"KhachHang");
                Jsoup.connect(url).data(MA_KH, khachHang.getMakh())
                        .data(NEW_PASS, khachHang.getPassword()).post();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        if (user != null){
            if (user.getRole().equals(ROLE_ADMIN)){
                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                intent.putExtra(BUNDLE_USER, bundle);
                startActivity(intent);
                finish();
            }else{
                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                intent.putExtra(BUNDLE_USER, bundle);
                startActivity(intent);
                finish();
            }
        }else if (khachHang != null){
            Intent intent = new Intent(getApplicationContext(), CustomerActivity.class);
            intent.putExtra(BUNDLE_USER, bundle);
            startActivity(intent);
            finish();
        }
        super.onBackPressed();
    }
}
